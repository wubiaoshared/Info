package com.test.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class PostTypeFilter implements Filter {
	
	private FilterConfig config;

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) arg0;
		String posttype = req.getMethod();
		//System.out.println(posttype);
		String postPageStr = config.getInitParameter("postPage");
		String[] postPages = postPageStr.split(",");
		for (int i = 0; i < postPages.length; i++) {
			if(!req.getRequestURI().endsWith(postPages[i])) {
				arg2.doFilter(arg0, arg1);
			}else {
				if(posttype.equalsIgnoreCase("post")) {
					arg2.doFilter(arg0, arg1);
				}
			}
		}
		
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

}
