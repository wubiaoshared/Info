package com.test.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.bean.User;
import com.test.dao.UserDAO;

public class LoginFilter implements Filter {
	FilterConfig config;
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		//获取字符集
		String charSet = config.getInitParameter("charset");
		if(charSet==null) {
			charSet = "utf-8";
		}
		request.setCharacterEncoding(charSet);
		response.setCharacterEncoding(charSet);
		//排除不需要过滤的页面
		String accept = config.getInitParameter("accept");
		String requestURI = req.getRequestURI();
		if(accept == null) {
			accept = "";
		}else {
			String[] acceptPages = accept.split(";");
			for (String acceptPage : acceptPages) {
				if(requestURI.endsWith(acceptPage)) {
					chain.doFilter(request, response);
					return;
				}
			}
		}
		User u = (User) session.getAttribute("user");
		UserDAO ud = new UserDAO();
		//session检测	
		if(null==u) {
			((HttpServletResponse)response).sendRedirect("/InfoSYS/user/login");
			return;
		}else if(ud.query(u.getId()).getAllow().equals("forbid")) {
			session.setAttribute("user", null);
			session.invalidate();
		}else {
			chain.doFilter(request, response);
		}
		//chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		System.out.println("init LoginFilter");
	}

}
