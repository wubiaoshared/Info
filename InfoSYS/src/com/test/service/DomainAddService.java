package com.test.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.test.bean.DNS;
import com.test.bean.User;
import com.test.dao.DNSDAO;
import com.test.dao.DomainDAO;

public class DomainAddService extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		DomainDAO dd = new DomainDAO();
		DNSDAO dnsDao = new DNSDAO();
		String method = request.getParameter("method");
		String domainName = request.getParameter("domain");
		String sdomain = request.getParameter("sdomain");
		String domainlan = request.getParameter("language");
		String sdate = request.getParameter("sdate");
		String note = request.getParameter("note");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		long userid = /*user.getId()*/110120;
		String formdns = request.getParameter("dns");
		String refurl = request.getHeader("Referer");	//获取url地址
		String refpageUrl = request.getParameter("refurl");
		DNS sdns = dnsDao.queryByName(formdns,userid);
		String type = "danger";	//默认为失败
		String title = "提示";
		String code = "alert";
		String msg = null;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", "domain");
		
		//修改信息
		if("edit".equals(method)) {
			if(dd.queryByDomainName(domainName,userid)!=null) {
				Pattern p = Pattern.compile("([\\s\\S]*?)\\([\\s\\S]*?\\)");
				/*String dns1 = request.getParameter("dns1");
				String dns2 = request.getParameter("dns2");
				String dns[] = {dns1,dns2};*/
				String dnsTemp = request.getParameter("dns");
				DNS dnsTemp1 = dnsDao.queryByName(dnsTemp,userid);	//dns信息
				String dns[] = null;
				String dnsRecord=null;
				if(dnsTemp1!=null) {
					dnsRecord = dnsTemp1.getNameserver();
				}
				if(sdomain==null&&domainlan==null&&sdate==null) {
					if(dnsRecord != null) {
						dns = dnsRecord.split(",");
					}
					dd.update(userid, domainName, dns, note);
					msg = "域名：<b>"+domainName+"</b> 更新成功";
					type = "success";
					String url = refpageUrl;
					jsonObject.put("url", url);
				}else {
					if(dnsRecord!=null) {
						dns = dnsRecord.split(",");
						String vpsn = request.getParameter("vps");
						Matcher m = p.matcher(vpsn);
						String vps = null;
						while(m.find()) {
							vps = m.group(1);
						}
						if(domainName.trim().length()>0&&dnsTemp.length()>0&&sdomain.trim().length()>0&&domainlan.trim().length()>0&&sdate.trim().length()>0&&vps!=null) {
							dd.update(domainName, dns, vps, sdomain, domainlan, sdate, note, userid);
							msg = "域名：<b>"+domainName+"</b> 更新成功";
							type = "success";
							String url = refpageUrl;
							jsonObject.put("url", url);
						}else {
							msg = "请检查信息填写是否完整！";
						}
					}else {
						msg = "DNS信息不能为空";
					}
				}
			}else {
				title = "更新失败";
				msg = "<b>"+domainName+"</b>  不存在,更新失败!";
			}
		}else {
			if(sdomain==null&&domainlan==null&&sdate==null) {
				if(domainName.trim().length()>0) {
					domainName = domainName.trim();
					if(dd.queryByDomainName(domainName,userid)==null) {
						String dnsRecord=null;
						if(sdns!=null) {
							dnsRecord = sdns.getNameserver();
						}
						dd.insert(userid, domainName,dnsRecord,note);
						msg = "域名：<b>"+domainName+"</b> 添加成功";
						jsonObject.put("url", refpageUrl);
						type = "success";
					}else {
						msg = "域名已经存在！";
					}
				}else {
					msg = "域名不能为空！";
				}
			}else if(dd.queryByDomainName(domainName,userid)==null) {
				Pattern p = Pattern.compile("([\\s\\S]*?)\\([\\s\\S]*?\\)");
				/*String dns1 = request.getParameter("dns1");
				String dns2 = request.getParameter("dns2");
				String dns[] = {dns1,dns2};*/
				String dnsRecord=null;
				if(sdns!=null) {
					dnsRecord = sdns.getNameserver();
				}
				String dns[] = null;
				String  dnsStr = null;
				if(dnsRecord!=null) {
					dns = dnsRecord.split(",");
					StringBuffer sb = new StringBuffer();
					for(int i=0;i<dns.length;i++) {
						sb.append(dns[i]+",");
					}
					dnsStr = sb.toString().substring(0,sb.toString().length()-1);
					String vpsn = request.getParameter("vps");
					Matcher m = p.matcher(vpsn);
					String vps = null;
					while(m.find()) {
						vps = m.group(1);
					}
					if(domainName.trim().length()>0&&formdns.length()>0&&sdomain.trim().length()>0&&domainlan.trim().length()>0&&sdate.trim().length()>0&&vps!=null) {
						dd.insert(userid,domainName, dnsStr, vps, sdomain, domainlan, sdate,note);
						msg = "域名：<b>"+domainName+"</b> 添加成功";
						String url = request.getContextPath()+"/admin/statistics";
						type = "success";
						jsonObject.put("url", url);
					}else {
						msg = "请检查信息填写是否完整！";
					}
				}else {
					msg = "DNS信息不能为空";
				}
			}else {
				msg = domainName+"已存在!";
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
