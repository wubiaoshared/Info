package com.test.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.test.bean.DNS;
import com.test.bean.Domain;
import com.test.bean.Language;
import com.test.bean.Pager;
import com.test.bean.User;
import com.test.bean.VPS;
import com.test.dao.DNSDAO;
import com.test.dao.DomainDAO;
import com.test.dao.LanguageDAO;
import com.test.dao.VPSDao;
import com.test.util.Validate;

public class StaticMain extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		long userid = user.getId();
		String method = request.getParameter("method");
		int page = 1;
		String p = request.getParameter("page");
		String url = request.getHeader("Referer");	//获取url地址
		String refurl = request.getParameter("refurl");
		String usr_confirm = request.getParameter("usr_confirm");
		String c_id = request.getParameter("c_id");
		String order = request.getParameter("order");	//排序字段
		String orderby = request.getParameter("orderby");	//排序方式
		String type = "danger";	//默认为失败
		String title = "提示";
		String code = "alert";
		String ord = null;	//排序方式
		if(orderby!=null&&order!=null) {
			ord = order +" "+ orderby;
		}
		if(p!=null && !"".equals(p) && Validate.isInteger(p)) {
			page = Integer.parseInt(p);			//获取当前页
		}
		String s=null,msg=null;
		String time = String.valueOf(new Date().getTime()); //当前时间字符串
		String confirm_id = null;	//确认id
		//设定随机时间 
		if(session.getAttribute("confirm_id")!=null) {
			confirm_id = (String) session.getAttribute("confirm_id");
		}else {
			confirm_id = time;
		}
		JSONObject jsonObject = new JSONObject();
		if("".equals(method) || method == null) {
			DomainDAO da = new DomainDAO();
			Pager<Domain> pager = da.queryAll(page, 20, userid, true,ord);
			List<Domain> Domains = pager.getObjlist();
			DNSDAO dnsDao = new DNSDAO();
			VPSDao vpsdao = new VPSDao();
			List<Domain> domains = new ArrayList<Domain>();
			for(Domain domain : Domains) {
				String dnsName = dnsDao.queryByServer(domain.getDns(), userid).getDnsname();
				domain.setDnsname(dnsName);
				String VpsName = vpsdao.queryByIp(domain.getVps(), userid).getName();
				domain.setVpsname(VpsName);
				domains.add(domain);
			}
			String pageurl = request.getRequestURL().toString();
			String dietime = getOrderUrl("dietime", orderby, pageurl, p);
			request.setAttribute("dietime", dietime);
			request.setAttribute("pager", pager);
			request.setAttribute("domains", domains);
			request.getRequestDispatcher("domain.jsp").forward(request, response);
		}else if("delete".equals(method)) {
			DomainDAO da = new DomainDAO();
			String[] ids = request.getParameterValues("chk_list");
			if(ids==null) {
				msg = "请至少选择一项";
			}else {
				if("yes".equals(usr_confirm)&&confirm_id.equals(c_id)) {
					for(String domainsid : ids) {
						int id = Integer.parseInt(domainsid);
						da.delete(id,userid);
					}
					session.removeAttribute("confirm_id");
					type = "success";
					msg = "删除成功！";
					//String url = "servlet/StaticMain?page="+page;
					jsonObject.put("url", url);
				}else if("".equals(usr_confirm)||usr_confirm==null) {
					session.setAttribute("confirm_id", time);
					code = "confirm";
					title = "确认删除";
					msg = "确认删除选中的"+ids.length+"条数据吗？";
					String data = "{chk_list : "+Arrays.toString(ids)+"}";
					jsonObject.put("data", data);
					jsonObject.put("url", request.getRequestURI()+"?method=delete&usr_confirm=yes&c_id="+time);
				}else {
					msg = "删除失败！";
				}
			}
		}else if("add".equals(method)) {
			VPSDao vd = new VPSDao();
			Pager<VPS> pagerVps = vd.queryAll(userid, page, 20);
			List<VPS> vpslist = pagerVps.getObjlist();
			DNSDAO dnsDao = new DNSDAO();
			Pager<DNS> pagerDns = dnsDao.queryAll(userid, page, 20);
			List<DNS> dnslist = pagerDns.getObjlist();
			LanguageDAO ld = new LanguageDAO();
			List<Language> lanlist = ld.queryAll(userid);
			request.setAttribute("lanlist", lanlist);
			request.setAttribute("vpslist", vpslist);
			request.setAttribute("dnslist", dnslist);
			request.setAttribute("backurl", url);
			request.getRequestDispatcher("domain_add.jsp").forward(request, response);
		}else if("d2".equals(method)) {
			DomainDAO da = new DomainDAO();
			Pager pager = da.queryAll(page, 20, userid, false,ord);
			List<Domain> Domains = pager.getObjlist();
			DNSDAO dnsDao = new DNSDAO();
			VPSDao vpsdao = new VPSDao();
			List<Domain> domains = new ArrayList<Domain>();
			for(Domain domain : Domains) {
				String dnsName = null;
				if(domain.getDns()!=null) {
					dnsName = dnsDao.queryByServer(domain.getDns(), userid).getDnsname();
				}
				domain.setDnsname(dnsName);
				domains.add(domain);
			}
//			int count = da.queryCount();
			request.setAttribute("pager", pager);
			request.setAttribute("domains", domains);
			request.getRequestDispatcher("sdomain.jsp").forward(request, response);
		}else if("add2".equals(method)) {
			DomainDAO da = new DomainDAO();
			DNSDAO dd = new DNSDAO();
			Pager<DNS> pager = dd.queryAll(userid, page, 20);
			List<DNS> dnslist = pager.getObjlist();
			request.setAttribute("refurl", url);
			request.setAttribute("dnslist", dnslist);
			request.getRequestDispatcher("domain_add2.jsp").forward(request, response);
		}else if("edit".equals(method)) {
			DomainDAO da = new DomainDAO();
			DNSDAO dd = new DNSDAO();
			int cid = Integer.parseInt(request.getParameter("id"));
			String upDomain = request.getParameter("status");
			Domain domain = da.queryById(cid,userid);
			if(da.queryById(cid, userid).getDns()!=null) {
				domain.setDnsname(dd.queryByServer(da.queryById(cid, userid).getDns(), userid).getDnsname());
			}
			DNSDAO dnsDao = new DNSDAO();
			Pager<DNS> pager = dnsDao.queryAll(userid, page, 20);
			List<DNS> dnslist = pager.getObjlist();
			if(upDomain==null&&domain.getVps()==null&&domain.getSdate()==null) {
				request.setAttribute("refurl", url);
				request.setAttribute("domain", domain);
				request.setAttribute("dnslist", dnslist);
				request.getRequestDispatcher("domain_edit2.jsp").forward(request, response);
			}else if((domain.getVps()!=null&&domain.getDns()!=null&&domain.getSdomain()!=null&&domain.getLanguage()!=null&&domain.getSdate()!=null)||upDomain.equals("sonline")) {
				VPSDao vd = new VPSDao();
				String vpsname = null;
				String dnsname = null;
				if(domain.getVps()!=null) {
					vpsname = vd.queryByIp(domain.getVps(), userid).getName();
				}
				if(domain.getDns()!=null) {
					dnsname = dnsDao.queryByServer(domain.getDns(), userid).getDnsname();
				}
				domain.setVpsname(vpsname);
				domain.setDnsname(dnsname);
				Pager<VPS> pagerVps = vd.queryAll(userid, page, 20);
				List<VPS> vpslist = pagerVps.getObjlist();
				LanguageDAO ld = new LanguageDAO();
				List<Language> lanlist = ld.queryAll(userid);
				request.setAttribute("refurl", url);
				request.setAttribute("lanlist", lanlist);
				request.setAttribute("vpslist", vpslist);
				request.setAttribute("dnslist", dnslist);
				request.setAttribute("domain", domain);
				request.getRequestDispatcher("domain_edit.jsp").forward(request, response);
			}
		}else if("died".equals(method)) {
			//标记死亡
			String[] ids = request.getParameterValues("chk_list");
			DomainDAO dd = new DomainDAO();
			if(ids!=null) {
				if("yes".equals(usr_confirm)&&confirm_id.equals(c_id)) {
					for (int i = 0; i < ids.length; i++) {
						int id = Integer.parseInt(ids[i]);
						Domain d = dd.queryById(id, userid);
						if(d.getActive() == 0) {
							msg = "<strong>标记失败域名:</strong> "+d.getDomain()+"重复标记项目！";
						}else {
							String domain = dd.queryById(id, userid).getDomain();
							//标记死亡
							dd.update(userid, domain, 0);
							session.removeAttribute("confirm_id");
							msg = "标记成功！";
							type="success";
							//String url = "servlet/StaticMain";
							jsonObject.put("url", url);
						}
					}
				}else if("".equals(usr_confirm)||usr_confirm==null) {
					session.setAttribute("confirm_id", time);
					code = "confirm";
					title = "确认标记";
					msg = "确认标记已选中的"+ids.length+"条记录为死亡状态吗？";
					String data = "{chk_list : "+Arrays.toString(ids)+"}";
					jsonObject.put("data", data);
					jsonObject.put("url", request.getRequestURI()+"?method=died&usr_confirm=yes&c_id="+time);
				}else {
					code = "alert";
					title = "失败";
					msg = "操作失败！";
				}
			}else {
				msg = "请至少选择一项";
			}
		}else if("pending".equals(method)) {
			//标记待定
			String[] ids = request.getParameterValues("chk_list");
			DomainDAO dd = new DomainDAO();
			if(ids!=null) {
				if("yes".equals(usr_confirm)&&confirm_id.equals(c_id)) {
					for (int i = 0; i < ids.length; i++) {
						int id = Integer.parseInt(ids[i]);
						Domain d = dd.queryById(id, userid);
						if(d.getActive() == 0) {
							msg = "<strong>标记失败域名:</strong> "+d.getDomain()+"已死亡的域名不能被标记！";
						}else if(d.getActive() == 2) {
							msg = "<strong>标记失败域名:</strong> "+d.getDomain()+"重复标记项目！";
						}else {
							String domain = dd.queryById(id, userid).getDomain();
							//标记死亡
							dd.update(userid, domain, 2);
							session.removeAttribute("confirm_id");
							msg = "标记成功！";
							type="success";
							jsonObject.put("url", url);
						}
					}
				}else {
					session.setAttribute("confirm_id", time);
					code = "confirm";
					title = "确认标记";
					msg = "确认标记已选中的"+ids.length+"条记录为待定状态吗？";
					String data = "{chk_list : "+Arrays.toString(ids)+"}";
					jsonObject.put("data", data);
					jsonObject.put("url", request.getRequestURI()+"?method=pending&usr_confirm=yes&c_id="+time);
				}
			}else {
				msg = "请至少选择一项";
			}
		}else if("active".equals(method)) {
			//标记未死亡
			String[] ids = request.getParameterValues("chk_list");
			DomainDAO dd = new DomainDAO();
			if(ids!=null) {
				if("yes".equals(usr_confirm)&&confirm_id.equals(c_id)) {
					for (int i = 0; i < ids.length; i++) {
						int id = Integer.parseInt(ids[i]);
						Domain d = dd.queryById(id, userid);
						if(d.getActive() == 0) {
							msg = "<strong>标记失败域名:</strong> "+d.getDomain()+"已死亡的域名不可以再设置该状态！";
						}else if(d.getActive() == 1){
							msg = "<strong>标记失败域名:</strong> "+d.getDomain()+"重复标记的项目！";
						}else {
							String domain = dd.queryById(id, userid).getDomain();
							//标记死亡
							dd.update(userid, domain, 1);
							session.removeAttribute("confirm_id");
							msg = "标记成功！";
							type = "success";
							jsonObject.put("url", url);
						}
					}
				}else {
					session.setAttribute("confirm_id", time);
					code = "confirm";
					title = "确认标记";
					msg = "确认标记已选中的"+ids.length+"条记录为未死亡吗？";
					String data = "{chk_list : "+Arrays.toString(ids)+"}";
					jsonObject.put("data", data);
					jsonObject.put("url", request.getRequestURI()+"?method=active&usr_confirm=yes&c_id="+time);
				}
			}else {
				msg = "请至少选择一项";
			}
		}else if("data".equals(method)) {
			code = "";
			DomainDAO da = new DomainDAO();
			Pager<Domain> pager = da.queryAll(page, 20, userid, true,ord);
			DomainDAO dd = new DomainDAO();
			LanguageDAO ld = new LanguageDAO();
			List<Language> lanList = ld.queryAll(userid);
			Object[][] obj = new Object[lanList.size()][2];	//创建包含语种和语种数量的二维数组
			int i = 0;
			for(Language lan : lanList) {
				String language = lan.getLanguage();
				int languageCount = dd.queryLanguageCount(language, userid);
				obj[i][0] = language;
				obj[i][1] = languageCount;
				i ++;
			}
			jsonObject.put("statistical", obj);
		}
		jsonObject.put("type", type);
		jsonObject.put("title", title);
		jsonObject.put("code", code);
		jsonObject.put("msg", msg);
		out.print(jsonObject);
		out.flush();
		out.close();
	}
	
	public String getOrderUrl(String order,String orderby,String pageUrl,String page) {
		String orderurl = "";
		if(orderby==null) {
			orderby = "desc";
			orderurl = pageUrl+"?order="+order+"&orderby=desc";
		}else if("desc".equals(orderby)) {
			orderby = "desc";
			orderurl = pageUrl+"?order="+order+"&orderby=asc";
		}else {
			orderby = "asc";
			orderurl = pageUrl+"?order="+order+"&orderby=desc";
		}
		if(page != null&&"".equals(page)) {
			orderurl = pageUrl;
		}
		return orderurl;
	}

}
