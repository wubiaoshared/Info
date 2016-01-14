package com.test.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.test.bean.User;
import com.test.dao.UserDAO;
import com.test.util.Validate;

public class Login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String method = request.getParameter("method");
		String usr_confirm = request.getParameter("usr_confirm");
		String d = request.getParameter("d");
		HttpSession session = request.getSession();
		JSONObject jsonObject = new JSONObject();
		String msg = null;
		String type = "danger";	//默认为失败
		String title = "提示";
		String code = "alert";
		String time = String.valueOf(new Date().getTime()); //当前时间字符串
		String confirm_id = null;
		//设定随机时间 
		if(session.getAttribute("confirm_id")!=null) {
			confirm_id = (String) session.getAttribute("confirm_id");
		}else {
			confirm_id = time;
		}
		if("".equals(method)||method == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if("logout".equals(method)) {
			if("yes".equals(usr_confirm)&&confirm_id.equals(d)) {
				code = "redirect";
				session.removeAttribute("user");
				session.removeAttribute("confirm_id");
				session.invalidate();
				String url = "../user/login";
				jsonObject.put("url", url);
			}else if("".equals(usr_confirm)||usr_confirm==null) {
				session.setAttribute("confirm_id", time);
				code = "confirm";
				title = "确认注销";
				msg = "确认要进行注销操作吗？";
				System.out.println();
				jsonObject.put("url", request.getRequestURI()+"?method=logout&usr_confirm=yes&d="+time);
			}else {
				code = "alert";
				title = "失败";
				msg = "操作失败！";
			}
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
