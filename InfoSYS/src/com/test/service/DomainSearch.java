package com.test.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.bean.Domain;
import com.test.bean.Pager;
import com.test.dao.DNSDAO;
import com.test.dao.DomainDAO;
import com.test.dao.VPSDao;
import com.test.util.Validate;

public class DomainSearch extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String domaintype = request.getParameter("domaintype");
		String kw = request.getParameter("kws");
		String kws = new String(kw.getBytes("iso-8859-1"),"utf-8");
		String timerange = request.getParameter("timerange");	//时间范围
		String searchtype = request.getParameter("searchtype");	//字段类型
		String onlinetype = request.getParameter("onlinetype");	//上线状态
		String order = request.getParameter("order");	//排序字段
		String orderby = request.getParameter("orderby");	//排序方式
		String chk_all = request.getParameter("chk_all");
		String[] chk_list = request.getParameterValues("chk_list");
		String[] indiaDomain = {".co.in",".in",".org.in",".gen.in",".firm.in",".ind.in"};	//印度后缀
		String[] freeDomain = {".tk",".ga",".gq",".cf",".ml"};	
		String[] commonDomain = {".com",".org",".net",".mx",".ru",".biz",".asia",".us",".info",".pw"};
		String[] otherDomain = {".space",".tv",".cn",".se",".mobi",".de",".cc",".ca","co",".me",".nu",".uk",".es",".it",".ch",".at",".be",".eu",".au",".fr",".nl"};
		String[] endsArr = null;
		if(chk_list != null && chk_all == null) {
			List<String> ends = new ArrayList<String>();
			for(String dends : chk_list) {
				if("india".equals(dends)) {
					for(String india : indiaDomain) {
						ends.add(india);
					}
				}
				if("free".equals(dends)) {
					for(String free : freeDomain) {
						ends.add(free);
					}
				}
				if(!"free".equals(dends) && !"india".equals(dends)) {
					//判断是否存在于上述数组commonDomain
//					List<String> templist = Arrays.asList(commonDomain);
//					if(templist.contains(dends)) {
//						ends.add("."+dends);
//					}
					ends.add("."+dends);
				}
				if("other".equals(dends)) {
					for (String other : otherDomain) {
						ends.add(other);
					}
				}
			}
			
			endsArr = (String[]) ends.toArray(new String[ends.size()]);
		}
		int onlinestatus = 2;
		int domainStatus = 3;
		long userid = 110120;
		String[] stime = null;
		if(timerange != null && !"".equals(timerange)) {
			stime = timerange.split("/");
		}
		if("died".equals(domaintype)) {
			domainStatus = 0;
		}else if("active".equals(domaintype)) {
			domainStatus = 1;
		}else if("pending".equals(domaintype)) {
			domainStatus = 2;
		}
		int page = 1;
		String p = request.getParameter("page");
		if(p!=null && !"".equals(p) && Validate.isInteger(p)) {
			page = Integer.parseInt(p);			//获取当前页
		}
		boolean online = true;
		DomainDAO dd = new DomainDAO();
		Pager<Domain> pager = null;
		if("all".equals(onlinetype)) {
			onlinestatus = 2;
		}else if("online".equals(onlinetype)) {
			onlinestatus = 1;
		}else if("offline".equals(onlinetype)) {
			onlinestatus = 0;
		}
		if(searchtype==null||"".equals(searchtype)) searchtype = "domain";
		String ord = null;	//排序方式
		if(orderby!=null&&order!=null) {
			ord = order +" "+ orderby;
		}
		pager = dd.search(userid, domainStatus, stime, kws, page, 20,onlinestatus,searchtype,endsArr,ord);
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
			String VpsName = null;
			if(domain.getVps()!=null) {
				VpsName = vpsdao.queryByIp(domain.getVps(), userid).getName();
			}
			domain.setVpsname(VpsName);
			domains.add(domain);
		}
		int pageNumber = 0;
		StringBuilder sb = new StringBuilder();
		if(domaintype!=null) {
			sb.append("?domaintype="+domaintype);
		}
		if(kws != null) {
			sb.append("&kws="+kws);
		}
		if(timerange != null) {
			sb.append("&timerange="+timerange);
		}
		if(searchtype != null) {
			sb.append("&searchtype="+searchtype);
		}
		if(onlinetype != null) {
			sb.append("&onlinetype="+onlinetype);
		}
		if(chk_list != null) {
			for(String cl : chk_list) {
				sb.append("&chk_list="+cl);
			}
		}
		if(chk_all != null) {
			sb.append("&chk_all="+chk_all);
		}
		String pageUrl1 = sb.toString();
		if(order!=null) {
			sb.append("&order="+order);
		}
		if(orderby!=null) {
			sb.append("&orderby="+orderby);
		}
		
		String pageUrl = sb.toString();
		pager.setPageUrl(pageUrl);
		String dieurl = getOrderUrl("dietime", orderby, pageUrl1, p);
		String addurl = getOrderUrl("addtime", orderby, pageUrl1, p);
		String sdateurl = getOrderUrl("sdate", orderby, pageUrl1, p);
		request.setAttribute("dieurl", dieurl);
		request.setAttribute("addurl", addurl);
		request.setAttribute("sdateurl", sdateurl);
		request.setAttribute("pg", pager);
		if(domains.size()==0) {
			domains = null;
		}
		request.setAttribute("domains", domains);
		request.getRequestDispatcher("../admin/searchResults.jsp").forward(request, response);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	/**
	 * 
	 * @param order	排序列
	 * @param orderby	排序方式
	 * @param pageUrl	当前页面url
	 * @param page	分页页码
	 * @return
	 */
	public String getOrderUrl(String order,String orderby,String pageUrl,String page) {
		String orderurl = "";
		if(orderby==null) {
			orderby = "desc";
			orderurl = pageUrl+"&order="+order+"&orderby=desc";
		}else if("desc".equals(orderby)) {
			orderby = "desc";
			orderurl = pageUrl+"&order="+order+"&orderby=asc";
		}else {
			orderby = "asc";
			orderurl = pageUrl+"&order="+order+"&orderby=desc";
		}
		if(page != null&&"".equals(page)) {
			orderurl = pageUrl;
		}
		return orderurl;
	}

}
