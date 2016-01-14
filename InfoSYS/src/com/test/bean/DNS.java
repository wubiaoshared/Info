package com.test.bean;

public class DNS {
	
	private int id;
	private long userid;
	private String dnsname;	//dns�˺�
	private String nameserver;	//���������
	
	
	
	public DNS() {
		super();
	}
	public DNS(int id, long userid, String dnsname, String nameserver) {
		super();
		this.id = id;
		this.userid = userid;
		this.dnsname = dnsname;
		this.nameserver = nameserver;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDnsname() {
		return dnsname;
	}
	public void setDnsname(String dnsname) {
		this.dnsname = dnsname;
	}
	public String getNameserver() {
		return nameserver;
	}
	public void setNameserver(String nameserver) {
		this.nameserver = nameserver;
	}
	

}
