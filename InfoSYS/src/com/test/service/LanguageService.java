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
import com.test.dao.LanguageDAO;

public class LanguageService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String url = request.getHeader("Referer");	//获取url地址
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		long userid = user.getId();
		String language = request.getParameter("language");
		String type = "danger";	//默认为失败
		String title = "提示";
		String code = "alert";
		String msg = "";
		JSONObject jsonObject = new JSONObject();
		if(!"".equals(language)&&language!=null&&!"".equals(language.trim())) {
			language = language.trim();
			LanguageDAO ld = new LanguageDAO();
			if(ld.isExist(language, userid)) {
				msg = "该语种已经存在！";
			}else {
				ld.add(userid, language);
				type = "success";
				msg = "添加成功！";
				jsonObject.put("url", url);
			}
			
		}else {
			msg = "添加失败,您还没有填写内容!";
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
