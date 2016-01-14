package com.test.bean;

import java.util.Date;

public class Domain {
	private int id;
	private long userid;
	private String domain;//����
	private String dns;
	private String dnsname;
	private String vps;
	private String vpsname;
	private String sdomain;//��������
	private String language;
	private Date sdate;
	private Date addtime;
	private boolean isOnline;	//是否上线
	private int active;	//域名当前状态	0代表已挂  1代表活着  2代表待定
	private Date dietime;	//死亡日期
	private String note;	//备注
	private Date notedate;	//备注添加日期
	
	public Domain() {
		super();
	}
	

	public Domain(int id, long userid, String domain, String dns, String vps,
			String sdomain, String language, Date sdate, Date addtime,
			boolean isOnline, int active, Date dietime, String note,
			Date notedate) {
		super();
		this.id = id;
		this.userid = userid;
		this.domain = domain;
		this.dns = dns;
		this.vps = vps;
		this.sdomain = sdomain;
		this.language = language;
		this.sdate = sdate;
		this.addtime = addtime;
		this.isOnline = isOnline;
		this.active = active;
		this.dietime = dietime;
		this.note = note;
		this.notedate = notedate;
	}


	public Domain(int id, long userid, String domain, String dns, String vps,
			String sdomain, String language, Date sdate, Date addtime) {
		super();
		this.id = id;
		this.userid = userid;
		this.domain = domain;
		this.dns = dns;
		this.vps = vps;
		this.sdomain = sdomain;
		this.language = language;
		this.sdate = sdate;
		this.addtime = addtime;
	}

	
	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public Date getNotedate() {
		return notedate;
	}



	public void setNotedate(Date notedate) {
		this.notedate = notedate;
	}



	public Date getDietime() {
		return dietime;
	}

	public void setDietime(Date dietime) {
		this.dietime = dietime;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getDnsname() {
		return dnsname;
	}

	public void setDnsname(String dnsname) {
		this.dnsname = dnsname;
	}

	public String getVpsname() {
		return vpsname;
	}

	public void setVpsname(String vpsname) {
		this.vpsname = vpsname;
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getDns() {
		return dns;
	}
	public void setDns(String dns) {
		this.dns = dns;
	}
	public String getVps() {
		return vps;
	}
	public void setVps(String vps) {
		this.vps = vps;
	}
	public String getSdomain() {
		return sdomain;
	}
	public void setSdomain(String sdomain) {
		this.sdomain = sdomain;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	
	
	
}
