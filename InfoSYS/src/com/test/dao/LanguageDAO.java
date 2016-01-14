package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.Language;
import com.test.db.DB;

public class LanguageDAO {
	
	private DB db = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	String sql = null;
	private Connection conn = DB.getConn();
	
	public void add(long userid,String language) {
		sql = "insert into lan_tab(userid,language) values("+userid+",'"+language+"')";
		executeMSQL(sql,conn);
	}
	
	public List<Language> queryAll(long userid) {
		sql = "select * from lan_tab where userid="+userid;
		try {
			rs = executeQSQL(sql, conn);
			List<Language> lanlist = new ArrayList<Language>();
			Language lan = null;
			while(rs.next()) {
				int id = rs.getInt(1);
				userid = rs.getLong(2);
				String language = rs.getString(3);
				lan = new Language(id, userid, language);
				lanlist.add(lan);
			}
			return lanlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查询共有多少语种
	public int queryCount(long userid) {
		sql = "select count(*) as languageCount from lan_tab where userid="+userid;
		rs = executeQSQL(sql, conn);
		int count = 0;
		try {
			while(rs.next()) {
				String countStr = rs.getString(1);
				count = Integer.parseInt(countStr);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	//检查存在
	public boolean isExist(String language,long userid) {
		sql = "select * from lan_tab where language='"+language+"' and userid ="+userid;
		rs = executeQSQL(sql, conn);
		try {
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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
