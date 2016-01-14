package com.test.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.test.bean.User;
import com.test.util.Validate;

public class LanguageMain extends HttpServlet {

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
		String method = request.getParameter("method");
		String type = "danger";	//默认为失败
		String title = "提示";
		String code = "alert";
		String s=null,msg=null;
		JSONObject jsonObject = new JSONObject();
		if("add".equals(method)) {
			title = "新增一个语种";
			code = "promit";
			String action = "language_add";
			String form_id = "lan_"+(new Random().nextInt(1000000));
			String[] inputType = {"text"};
			String[] inputName = {"language"};
			String[] inputLabel = {"语种"};
			String[][] input = new String[inputName.length][3];
			for (int i = 0; i < input.length; i++) {
				input[i][0] = inputName[i];
				input[i][1] = inputType[i];
				input[i][2] = Validate.toEncodedUnicode(inputLabel[i], false);
			}
			jsonObject.put("form_input", input);
			jsonObject.put("action", action);
			jsonObject.put("form_id", form_id);
			//request.getRequestDispatcher("lan_add.jsp").forward(request, response);
			
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
