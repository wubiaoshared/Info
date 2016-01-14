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

public class LoginService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ServletContext application = request.getSession().getServletContext();
		HttpSession session = request.getSession();
		String nameStr = request.getParameter("name");
		String pass = request.getParameter("pass");
		String msg = null;
		String ip = Validate.getIpAddr(request);	//用户ip地址
		String rb = request.getParameter("RememeberMe");
		JSONObject jsonObject = new JSONObject();
		int name = 0;
		if(pass!=null&&nameStr!=null&&pass.trim().length()>=6 && nameStr.trim().length()>=6) {
			if(Validate.isInteger(nameStr)) {
				name = Integer.parseInt(nameStr);
				UserDAO userDao = new UserDAO();
				User u = userDao.query(name);
				if(u!=null&&name==u.getId()&&pass.equals(u.getPass())) {
					String allow = u.getAllow();
					if(allow.equals("allow")) {
						if("on".equals(rb)) {
							Cookie cookie = new Cookie("umsg", nameStr+","+pass);
							URLEncoder.encode(nameStr, "utf-8");
							cookie.setMaxAge(60*60*24*7);	//cookie有效期默认为7天
							response.addCookie(cookie);
						}
						msg = "";
						String loginTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date().getTime());	//用户当前登录时间
						userDao.updateIp(u.getId(), ip,loginTime);	//保存ip地址
						String url = null;
						if(u.getRole().equals("admin")) {
							url = "../admin/statistics";
						}else {
							url = "../admin/statistics";
						}
						session.setAttribute("user", u);
						session.setMaxInactiveInterval(60*60*24*365);	//有效期一年
						jsonObject.put("url", url);
					}else if(allow.equals("forbid")) {
						msg = "登录失败! 你的账号已被禁止登录，详情请联系管理员！";
					}
					//response.sendRedirect("../servlet/StaticMain");
				}else {
					msg = "登录失败! 用户名或密码错误！";
					//msg = "<span class='text-danger'>用户名或密码错误</span>";
				}
			}else {
				msg = "登录失败! 用户名只能输入数字！";;
			}
		}else {
			msg = "登录失败! 用户名或密码至少为6位!";;
		}
		jsonObject.put("msg", msg);
		out.print(jsonObject);
		out.flush();
		out.close();
	}

}
