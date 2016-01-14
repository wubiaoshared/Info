package com.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DB {

	public static final String url = "jdbc:mysql://127.0.0.1/domains?useUnicode=true&characterEncoding=utf8";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "fadai1991";  
	
    private static DB db;
    
    public static Connection conn = null;  
    
    static {
    	try {
			Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public static Connection getConn() {
    	try {
			conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    private DB(){}
    
    public static synchronized DB getInstance() {
    	if(db == null) {
    		db = new DB();
    	}
    	return db;
    }
    
    public static void closeConn(ResultSet rs, PreparedStatement pst, Connection conn) {
		try {
			if (rs != null) {// 如果返回的结果集对象不能为空,就关闭连接
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (pst != null) {
				pst.close();// 关闭预编译对象
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (conn != null) {
				conn.close();// 关闭结果集对象
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
   
    
    public static void main(String[] args) {
		System.out.println(DB.getConn());
	}
}
