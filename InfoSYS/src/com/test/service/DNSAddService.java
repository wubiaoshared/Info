package com.test.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.test.bean.User;
import com.test.dao.DNSDAO;

public class DNSAddService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		long userid = user.getId();
		String type = "danger";	//默认为失败
		String title = "提示";
		String code = "alert";
		String url = request.getParameter("refurl");
		String dnsname = request.getParameter("dnsname").trim();
		String[] nameserver = request.getParameterValues("nameserver");
		JSONObject jsonObject = new JSONObject();
		DNSDAO dnsDao = new DNSDAO();
		String msg = null;
		StringBuffer sb = new StringBuffer();
		if(nameserver.length>=2) {
			for(int i=0;i<nameserver.length;i++) {
				if(nameserver[i].trim()==""||nameserver[i].trim()==null) {
					response.setHeader("L-InfoManage-Code", "Validate");
					msg = "添加失败,请检查信息是否填写完整！";
				}
			}
		}
		if(dnsname.trim()!=null&&dnsname.trim()!=""&&nameserver!=null) {
			for(int i=0;i<nameserver.length;i++) {
				sb.append(nameserver[i].trim()+",");
				//dnsDao.add(dnsname, nameserver[i]);
			}
			String nameserverStr = sb.toString().substring(0,sb.toString().length()-1);
			dnsDao.add(userid, dnsname, nameserverStr);
			response.setHeader("L-InfoManage-Code", "Redirect");
			msg = dnsname+" DNS记录添加成功！";
			type = "success";
			jsonObject.put("url", "dns");
		}else {
			response.setHeader("L-InfoManage-Code", "Validate");
			msg = "添加失败,请检查信息是否填写完整！";
		}
		jsonObject.put("type", type);
		jsonObject.put("title", title);
		jsonObject.put("code", code);
		jsonObject.put("msg", msg);
		out.print(jsonObject);
		out.flush();
		out.close();
	}

}
