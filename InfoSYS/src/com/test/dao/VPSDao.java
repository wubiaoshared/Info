package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.test.bean.Domain;
import com.test.bean.Pager;
import com.test.bean.VPS;
import com.test.db.DB;

public class VPSDao {
	
	private DB db = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private Connection conn = DB.getConn();
	
	public Pager<VPS> queryAll(long userid,int currentPage,int pageSize) {
		try {
			int totalCount = queryCount(userid);
			int pageCount = 0;
			int prePage = 1;
			pageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize + 1;
			if(currentPage > pageCount) {
				currentPage = pageCount;
			}else if(currentPage <= 0) {
				currentPage = 1;
			}
			Pager<VPS> p = null;
			int maxCount = currentPage * pageSize;	//当前页要查询的最大下标
			int minCount = (currentPage-1) * pageSize;	//当前页要查询的最小下标
			String sql = "select * from vps_tab where userid="+userid+" limit "+minCount+","+maxCount+"";
			rs = executeQSQL(sql,conn);
			List<VPS> list = new ArrayList<VPS>();
			VPS vps = null;
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String vpsname = rs.getString(3);
				String vpspass = rs.getString(4);
				String ip = rs.getString(5);
				userid = rs.getLong(6);
				String position = rs.getString(7);
				String note = rs.getString(8);
				vps = new VPS(id, userid, name, vpsname, vpspass, ip, position, note);
				list.add(vps);
			}
			int nextPage = pageCount;
			if(currentPage > 1) {
				prePage = currentPage - 1;
			}
			if(currentPage < pageCount) {
				nextPage = currentPage + 1;
			}
			p = new Pager<VPS>(pageSize, pageCount, totalCount, prePage, nextPage, currentPage, list, list.size());
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int queryCount(long userid) {
		int count = 0;
		try {
			String sql = "select * from vps_tab where userid="+userid;
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
	public VPS queryByIp(String ip,long userid) {
		String sql = "select * from vps_tab where ip='"+ip+"' and userid="+userid;
		rs = executeQSQL(sql,conn);
		try {
			VPS vps = null;
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String vpsname = rs.getString(3);
				String vpspass = rs.getString(4);
				ip = rs.getString(5);
				userid = rs.getLong(6);
				String position = rs.getString(7);
				String note = rs.getString(8);
				vps = new VPS(id, userid, name, vpsname, vpspass, ip, position, note);
			}
			return vps;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public VPS queryById(int id,long userid) {
		String sql = "select * from vps_tab where id="+id+" and userid="+userid;
		rs = executeQSQL(sql,conn);
		try {
			VPS vps = null;
			while(rs.next()) {
				id = rs.getInt(1);
				String name = rs.getString(2);
				String vpsname = rs.getString(3);
				String vpspass = rs.getString(4);
				String ip = rs.getString(5);
				userid = rs.getLong(6);
				String position = rs.getString(7);
				String note = rs.getString(8);
				vps = new VPS(id, userid, name, vpsname, vpspass, ip, position, note);
			}
			return vps;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void insert(long userid,String name,String vpsname,String vpspass,String ip,String position,String note) {
		String sql = "insert into vps_tab(userid,name,vpsname,vpspass,ip,position,note) values("+userid+",'"+name+"','"+vpsname+"','"+vpspass+"','"+ip+"','"+position+"','"+note+"')";
		executeMSQL(sql,conn);
	}
	
	public void delete(int id,long userid) {
		String sql = "delete from vps_tab where id="+id+" and userid="+userid;
		executeMSQL(sql,conn);
	}
	
	public void update(String name,String vpsname,String vpspass,String ip,String position,String note,long userid) {
		String sql = "update vps_tab set name ='"+name+"',vpsname='"+vpsname+"',vpspass='"+vpspass+"',position='"+position+"',note='"+note+"' where ip='"+ip+"' and userid="+userid;
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

}
