package com.test.bean;

public class VPS {
	
	private int id;
	private long userid;
	private String name;
	private String vpsname;
	private String vpspass;
	private String ip;
	private String position;
	private String note;
	
	public VPS() {
		super();
	}
	public VPS(int id, long userid, String name, String vpsname,
			String vpspass, String ip, String position, String note) {
		super();
		this.id = id;
		this.userid = userid;
		this.name = name;
		this.vpsname = vpsname;
		this.vpspass = vpspass;
		this.ip = ip;
		this.position = position;
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVpsname() {
		return vpsname;
	}
	public void setVpsname(String vpsname) {
		this.vpsname = vpsname;
	}
	public String getVpspass() {
		return vpspass;
	}
	public void setVpspass(String vpspass) {
		this.vpspass = vpspass;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
