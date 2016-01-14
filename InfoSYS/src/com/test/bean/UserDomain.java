package com.test.bean;

public class UserDomain {
	
	private int id;
	private String email;
	private String password;
	private String domain;
	private String year;
	private String regdate;
	private String customerid;
	private String contactid;
	private String d1;
	private String d2;
	private boolean df;
	
	public UserDomain() {
		super();
	}
	
	public UserDomain(int id, String email, String password, String domain,
			String year, String regdate, String customerid, String contactid,
			String d1, String d2,boolean df) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.domain = domain;
		this.year = year;
		this.regdate = regdate;
		this.customerid = customerid;
		this.contactid = contactid;
		this.d1 = d1;
		this.d2 = d2;
		this.df = df;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getContactid() {
		return contactid;
	}
	public void setContactid(String contactid) {
		this.contactid = contactid;
	}
	public String getD1() {
		return d1;
	}
	public void setD1(String d1) {
		this.d1 = d1;
	}
	public String getD2() {
		return d2;
	}
	public void setD2(String d2) {
		this.d2 = d2;
	}

	public boolean isDf() {
		return df;
	}

	public void setDf(boolean df) {
		this.df = df;
	}
	
}
