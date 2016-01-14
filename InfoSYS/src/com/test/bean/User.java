package com.test.bean;

import java.util.Date;

public class User {
	private long id;
	private String name;
	private String pass;
	private String role;
	private String allow;
	private String note;	//个人备注
	private String ip;
	private Date loginTime;	//登录时间
	
	
	public User() {
		super();
	}
	
	public User(long id, String name, String pass, String role, String allow,
			String note, String ip, Date loginTime) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.role = role;
		this.allow = allow;
		this.note = note;
		this.ip = ip;
		this.loginTime = loginTime;
	}


	public User(long id, String name, String pass, String role, String allow,
			String note) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.role = role;
		this.allow = allow;
		this.note = note;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public String getAllow() {
		return allow;
	}
	public void setAllow(String allow) {
		this.allow = allow;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	

}
