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
import com.test.dao.UserDAO;
import com.test.util.Validate;

public class UserInfo extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String method = request.getParameter("method");
		String msg = null;
		JSONObject jsonObject = new JSONObject();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String idStr = request.getParameter("id");
		String name = request.getParameter("name");
		String[] passArr = request.getParameterValues("pass");
		String note = request.getParameter("note");
		String pass = null;
		UserDAO userDao = new UserDAO();
		String type = "danger";	//默认为失败
		String title = "提示";
		String code = "alert";
		long id = 0;
		if("".equals(method)||method == null) {
			User user = userDao.query(u.getId());
			request.setAttribute("user", user);
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		}else if("save".equals(method)) {
			boolean isInteger = Validate.isInteger(idStr);
			if(isInteger) {
				id = Long.parseLong(idStr);	//获取id
				if(passArr.length==3) {
					if(passArr[0].trim()==""&&passArr[1].trim()==""&&passArr[2].trim()=="") {
						if(u.getId()==id) {
							userDao.update(id, name, note);
							msg = "用户信息更新成功！";
							type = "success";
							String url = "profile";
							jsonObject.put("url", url);
						}else {
							msg = "用户名禁止修改！";
						}
					}else {
						if(passArr[0].length()>=6&&passArr[0].length()<=14) {
							if(passArr[1].equals(passArr[2])) {
								if(passArr[1].length()>=6&&passArr[2].length()<=14) {
									if(passArr[1].equals(userDao.query(u.getId()).getPass())) {
										msg = "与旧密码一致，请重新输入！";
									}else {
										if(u.getId()==id) {
											if(userDao.query(u.getId()).getPass().equals(passArr[0])) {
												pass = passArr[1];
												userDao.update(id, name, pass, note);
												String url = "servlet/UserInfo";
												msg = "修改成功！";
												jsonObject.put("url", url);
											}else {
												msg = "原密码输入不正确,请重新输入！";
											}
										}else {
											msg = "用户名禁止修改！";
										}
									}
								}else {
									msg = "密码至少为6位！";
								}
							}else {
								msg = "两次密码输入不一致！";
							}
						}else {
							msg = "新密码至少为6位！";
						}
					}
					
				}else {
					msg = "密码设置出现错误,修改失败！";
				}
			}else {
				msg = "用户名禁止修改！";
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
