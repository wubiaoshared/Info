package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.DNS;
import com.test.bean.Domain;
import com.test.bean.Pager;
import com.test.bean.UserDomain;
import com.test.db.DB;

public class DNSDAO {
	private DB db = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private Connection conn = DB.getConn();
	
	public void add(long userid,String dnsname,String nameserver) {
		String sql = "insert into dns_tab(userid,dnsname,nameserver) values("+userid+",'"+dnsname+"','"+nameserver+"')";
		executeMSQL(sql,conn);
	}
	
	public Pager<DNS> queryAll(long userid,int currentPage,int pageSize) {
		int totalCount = queryCount(userid);
		int pageCount = 0;
		int prePage = 1;
		pageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize + 1;
		if(currentPage > pageCount) {
			currentPage = pageCount;
		}else if(currentPage <= 0) {
			currentPage = 1;
		}
		Pager p = null;
		int maxCount = currentPage * pageSize;	//当前页要查询的最大下标
		int minCount = (currentPage-1) * pageSize;	//当前页要查询的最小下标
		String sql = "select * from dns_tab where userid="+userid+" limit "+minCount+","+maxCount+"";
		rs = executeQSQL(sql,conn);
		List<DNS> list = new ArrayList<DNS>();
		DNS dns = null;
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				userid = rs.getLong(2);
				String dnsname = rs.getString(3);
				String nameserver = rs.getString(4);
				dns = new DNS(id, userid, dnsname, nameserver);
				list.add(dns);
			}
			int nextPage = pageCount;
			if(currentPage > 1) {
				prePage = currentPage - 1;
			}
			if(currentPage < pageCount) {
				nextPage = currentPage + 1;
			}
			p = new Pager<DNS>(pageSize, pageCount, totalCount, prePage, nextPage, currentPage, list, list.size());
			return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int queryCount(long userid) {
		int count = 0;
		try {
			String sql = "select * from dns_tab where userid="+userid;
			rs = executeQSQL(sql, conn);
			while(rs.next()) {
				count ++;
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public DNS queryByServer(String server,long userid) {
		String sql = "select * from dns_tab where nameserver='"+server+"' and userid="+userid;
		rs = executeQSQL(sql,conn);
		DNS dns = null;
		try {
			while(rs.next()) {
				int id = rs.getInt(1);
				userid = rs.getLong(2);
				String dnsname = rs.getString(3);
				String nameserver = rs.getString(4);
				dns = new DNS(id, userid, dnsname, nameserver);
			}
			return dns;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public DNS queryByName(String name,long userid) {
		String sql = "select * from dns_tab where dnsname='"+name+"' and userid="+userid;
		rs = executeQSQL(sql,conn);
		DNS dns = null;
		try {
			while(rs.next()) {
				int id = rs.getInt(1);
				userid = rs.getLong(2);
				String dnsname = rs.getString(3);
				String nameserver = rs.getString(4);
				dns = new DNS(id, userid, dnsname, nameserver);
			}
			return dns;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public void delete(int id,long userid) {
		String sql = "delete from dns_tab where id="+id+" and userid="+userid;
		System.out.println(sql);
		executeMSQL(sql,conn);
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
	
	
	public static void main(String[] args) {
		//System.out.println(new DNSDAO().queryAll(110120));;
	}
}
