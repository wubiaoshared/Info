package com.test.bean;

public class Language {
	
	private int id;
	private long userid;
	private String language;
	
	public Language() {
		super();
	}

	public Language(int id, long userid, String language) {
		super();
		this.id = id;
		this.userid = userid;
		this.language = language;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	

}
