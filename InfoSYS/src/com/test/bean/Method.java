package com.test.bean;

public enum Method {
	
	add,delete,edit,add2,domain2,detail;
	
	
	public static Method getMethod(String str) {
		
		try {
			return valueOf(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	

}
