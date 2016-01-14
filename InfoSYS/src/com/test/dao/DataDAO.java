package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.UserDomain;
import com.test.db.DB;

public class DataDAO {
	
	private DB db = null;
	private ResultSet rs = null;
	public PreparedStatement pst = null;
	Connection conn = DB.getConn();
	
	
	public void add(String email,String password,String domain,String year,String regdate,String customerid,String contactid,String d1,String d2,String df) {
		String sql = "insert into user_tab(email,password,domain,year,regdate,customerid,contactid,d1,d2,df) values ('"+email+"','"+password+"','"+domain+"','"+year+"','"+regdate+"','"+customerid+"','"+contactid+"','"+d1+"','"+d2+"','"+df+"')";
		executeMSQL(sql,conn);
	}
	public void delete(int id) {
		String sql = "delete from user_tab where id="+id;
		executeMSQL(sql,conn);
	}
	
	public void deleteByEmail(String email) {
		String sql = "delete from user_tab where email="+email;
		executeMSQL(sql,conn);
	}
	
	public void deleteAll() {
		String sql = "delete from user_tab";
		executeMSQL(sql,conn);
	}
	
	public UserDomain queryByEmail(String email) {
		String sql = "select * from user_tab where email='" + email +"'";
		rs = executeQSQL(sql,conn);
		return queryUser(rs);
	}
	
	public UserDomain queryByDomain(String domain) {
		String sql = "select * from user_tab where domain='"+domain+"'";
		rs = executeQSQL(sql,conn);
		return queryUser(rs);
	}
	
	public List<UserDomain> queryAll() {
		String sql = "select * from user_tab";
		rs = executeQSQL(sql,conn);
		List<UserDomain> list = new ArrayList<UserDomain>();
		UserDomain user = null;
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String email = rs.getString(2);
				String password = rs.getString(3);
				String domain = rs.getString(4);
				String year = rs.getString(5);
				String regdate = rs.getString(6);
				String customerid = rs.getString(7);
				String contactid = rs.getString(8);
				String d1 = rs.getString(9);
				String d2 = rs.getString(10);
				boolean df = rs.getBoolean(11);
				user = new UserDomain(id, email, password, domain, year, regdate, customerid, contactid, d1, d2,df);
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateByEmail(String email,String domain,String regdate,String customerid,String contactid,String d1,String d2) {
		String sql = "update user_tab set domain='"+domain+"',regdate='"+regdate+"',customerid='"+customerid+"',contactid='"+contactid+"',d1='"+d1+"',d2='"+d2+"'where email='"+email+"'";
		executeMSQL(sql,conn);
	}
	public void updateByDomain(String domain,String email,String regdate,String customerid,String contactid,String d1,String d2) {
		String sql = "update user_tab set email='"+email+"',regdate='"+regdate+"',customerid='"+customerid+"',contactid='"+contactid+"',d1='"+d1+"',d2='"+d2+"'where domain='"+domain+"'";
		executeMSQL(sql,conn);
	}
	
	public UserDomain queryUser(ResultSet rs) {
		try {
			UserDomain user = null;
			while (rs.next()) {
				int id = rs.getInt(1);
				String email = rs.getString(2);
				String password = rs.getString(3);
				String domain = rs.getString(4);
				String year = rs.getString(5);
				String regdate = rs.getString(6);
				String customerid = rs.getString(7);
				String contactid = rs.getString(8);
				String d1 = rs.getString(9);
				String d2 = rs.getString(10);
				boolean df = rs.getBoolean(11);
				user = new UserDomain(id, email, password, domain, year, regdate, customerid, contactid, d1, d2,df);
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		//new DataDAO().add("1", "111", "mccaindia.in", "111", "111", "111", "111", "111", "111",true);
		//new DataDAO().deleteByEmail("11521");
		//new DataDAO().deleteAll();
		//System.out.println(new DataDAO().queryByEmail("kary").getDomain());;
		//new DataDAO().updateByEmail("kary", "163.com", "sad", "sad", "sad", "sad", "sad");
		/*List<User> list = new DataDAO().queryAll();
		for(User u : list) {
			System.out.println(u.getEmail());
		}*/
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
