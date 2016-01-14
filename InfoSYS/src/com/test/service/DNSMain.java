package com.test.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.test.bean.DNS;
import com.test.bean.Pager;
import com.test.bean.User;
import com.test.dao.DNSDAO;
import com.test.util.Validate;

public class DNSMain extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String method = request.getParameter("method");
		String refurl = request.getHeader("Referer");
		String usr_confirm = request.getParameter("usr_confirm");
		int currentPage = 1;
		String type = "danger";	//默认为失败
		String title = "提示";
		String code = "alert";
		String p = request.getParameter("page");
		if(p!=null && !"".equals(p) && Validate.isInteger(p)) {
			currentPage = Integer.parseInt(p);			//获取当前页
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		long userid = user.getId();
		String msg = null;
		JSONObject jsonObject = new JSONObject();
		DNSDAO DnsDao = new DNSDAO();
		if(method == null || "".equals(method)) {
			Pager<DNS> pager = DnsDao.queryAll(userid, currentPage, 20);
			List<DNS> dnslist = pager.getObjlist();
			request.setAttribute("pager", pager);
			request.setAttribute("dnslist", dnslist);
			request.getRequestDispatcher("dns.jsp").forward(request, response);
		}else if(method.equals("add")) {
			request.getRequestDispatcher("dns_add.jsp").forward(request, response);
		}else if("dnsadd".equals(method)) {
			title = "新增一个语种";
			code = "promit";
			String action = "dns_add";
			String form_id = "dns_"+(new Random().nextInt(1000000));
			String[] inputType = {"text","text","text"};
			String[] inputName = {"dnsname","nameserver","nameserver"};
			String[] inputLabel = {"DNS账号","域名服务器",""};
			String[][] input = new String[inputName.length][3];
			for (int i = 0; i < input.length; i++) {
				input[i][0] = inputName[i];
				input[i][1] = inputType[i];
				input[i][2] = Validate.toEncodedUnicode(inputLabel[i], false);
			}
			jsonObject.put("form_input", input);
			jsonObject.put("action", action);
			jsonObject.put("form_id", form_id);
		}else if(method.equals("delete")) {
			String[] ids = request.getParameterValues("chk_list");
			if(ids==null) {
				msg = "请至少选择一项";
			}else {
				if("yes".equals(usr_confirm)) {
					DNSDAO da = new DNSDAO();
					for(String domainsid : ids) {
						int id = Integer.parseInt(domainsid);
						da.delete(id,userid);
					}
					type = "success";
					msg = "删除成功！";
					//String url = "servlet/StaticMain?page="+page;
					jsonObject.put("url", refurl);
				}else {
					code = "confirm";
					title = "确认删除";
					msg = "确认删除选中的"+ids.length+"条数据吗？";
					String data = "{chk_list : "+Arrays.toString(ids)+"}";
					jsonObject.put("data", data);
					jsonObject.put("url", request.getRequestURI()+"?method=delete&usr_confirm=yes");
				}
			}
		}else if(method.equals("detail")) {
			String dname = request.getParameter("dname");
			DNS dns = DnsDao.queryByName(dname,userid);
			request.setAttribute("dnsdetail", dns);
			request.getRequestDispatcher("dns.jsp").forward(request, response);
		}
		request.setAttribute("refurl", refurl);
		jsonObject.put("type", type);
		jsonObject.put("title", title);
		jsonObject.put("code", code);
		jsonObject.put("msg", msg);
		out.print(jsonObject);
		out.flush();
		out.close();
	}

}
