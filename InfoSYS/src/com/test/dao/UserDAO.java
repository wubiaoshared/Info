package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.test.bean.User;
import com.test.db.DB;

public class UserDAO {
	
	private DB db = null;
	private ResultSet rs = null;
	public PreparedStatement pst = null;
	Connection conn = DB.getConn();
	
	public void insert(long id,String pass,String role,String allow,String note) {
		String sql = "insert into user(id,password,role,allow,note) values('"+id+"','"+pass+"','"+role+"','"+allow+"','"+note+"')";
		executeMSQL(sql, conn);
	}
	
	//更新用户登录ip地址 
	public void updateIp(long id,String ip,String loginTime) {
		String sql = "update user set ip='"+ip+"',logintime='"+loginTime+"' where id="+id;
		executeMSQL(sql, conn);
	}
	
	public User query(long id) {
		String sql = "select * from user where id="+id;
		rs = executeQSQL(sql, conn);
		User u = null;
		try {
			while(rs.next()) {
				id = rs.getLong(1);
				String name = rs.getString(2);
				String pass = rs.getString(3);
				String role = rs.getString(4);
				String allow = rs.getString(5);
				String note = rs.getString(6);
				String ip = rs.getString(7);
				Date loginTime = rs.getTimestamp(8);
				u = new User(id, name, pass, role, allow,note,ip,loginTime);
			}
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public void update(long id, String name, String pass,String note) {
		String sql = "update user set username='"+name+"',password='"+pass+"',note='"+note+"' where id="+id;
		executeMSQL(sql, conn);
	}
	
	public void update(long id, String name,String note) {
		String sql = "update user set username='"+name+"',note='"+note+"' where id="+id;
		executeMSQL(sql, conn);
	}
	
	//修改登录权限
	public void updateAllow(long id,String allow) {
		String sql = "update user set allow='"+allow+"' where id="+id;
		executeMSQL(sql, conn);
	}
	
	public void delete(long id) {
		String sql = "delete from user where id="+id;
		executeMSQL(sql, conn);
	}
	
	public void executeMSQL(String sql,Connection conn) {
		try {
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public ResultSet executeQSQL(String sql,Connection conn) {
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			return rs;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
