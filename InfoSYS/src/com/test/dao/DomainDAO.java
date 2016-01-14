package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.test.bean.Domain;
import com.test.bean.Pager;
import com.test.db.DB;

public class DomainDAO {
	private DB db = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private Connection conn = DB.getConn();
	private String sql = null;
	
	//分页方法
	public Pager<Domain> queryAll(int currentPage,int pageSize,long userid,boolean online,String order) {
		try {
			int totalCount = queryCount(userid, online);
			int pageCount = 0;
			int prePage = 1;
			pageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize + 1;
			if(currentPage > pageCount) {
				currentPage = pageCount;
			}else if(currentPage <= 0) {
				currentPage = 1;
			}
			Pager<Domain> p = null;
			int minCount = (currentPage-1) * pageSize;	//当前页要查询的最小下标
			String sql1 = "";	//排序
			if(order != null) {
				sql1 = " order by "+order+" ";
			}else {
				sql1 = " order by sdate desc";
			}
			
			sql = "select * from domain_tab where userid="+userid+" and online="+online+sql1+" limit "+minCount+","+pageSize+"";
			rs = executeQSQL(sql,conn);
			List<Domain> list = new ArrayList<Domain>();
			Domain domain = null;
			while(rs.next()) {
				int id = rs.getInt(1);
				String domainName = rs.getString(3);
				String dns = rs.getString(4);
				String vps = rs.getString(5);
				String sdomain = rs.getString(6);
				String language = rs.getString(7);
				Date sdate = rs.getDate(8);
				userid = rs.getLong(2);
				Date addtime = rs.getTimestamp(9);
				online = rs.getBoolean(10);
				int active = rs.getInt(11);
				Date dietime = rs.getDate(12);
				String note = rs.getString(13);
				Date notedate = rs.getDate(14);
				domain = new Domain(id,userid,domainName, dns, vps, sdomain, language, sdate,addtime,online,active,dietime,note,notedate);
				list.add(domain);
			}
			
			int nextPage = pageCount;
			if(currentPage > 1) {
				prePage = currentPage - 1;
			}
			if(currentPage < pageCount) {
				nextPage = currentPage + 1;
			}
			p = new Pager<Domain>(pageSize, pageCount, totalCount, prePage, nextPage, currentPage, list,list.size());
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeConn(rs, pst, conn);
		}
		return null;
	}
	
	public List<Domain> queryAll(String orderby,long userid,boolean online) {
		
		//String sql = "select * from domain_tab limit 0,10";
		try {
			sql = "select * from domain_tab LEFT JOIN lan_tab on domain_tab.id=lan_tab.domainid where userid="+userid+" and online="+online+" order by " + orderby;
			//sql = "select * from domain_tab where userid="+userid+" and online="+online+" order by " + orderby;
			rs = executeQSQL(sql,conn);
			List<Domain> list = new ArrayList<Domain>();
			Domain domain = null;
			while(rs.next()) {
				int id = rs.getInt(1);
				String domainName = rs.getString(3);
				String dns = rs.getString(4);
				String vps = rs.getString(5);
				String sdomain = rs.getString(6);
				//String language = rs.getString(7);
				Date sdate = rs.getDate(8);
				userid = rs.getLong(2);
				Date addtime = rs.getTimestamp(9);
				online = rs.getBoolean(10);
				int active = rs.getInt(11);
				Date dietime = rs.getDate(12);
				String note = rs.getString(13);
				Date notedate = rs.getDate(14);
				String language = rs.getString(7);
				domain = new Domain(id,userid,domainName, dns, vps, sdomain, language, sdate,addtime,online,active,dietime,note,notedate);
				list.add(domain);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeConn(rs, pst, conn);
		}
		return null;
	}
	//已上线
	public int queryCount(long userid,boolean online) {
		int count = 0;
		sql = "select count(*) as count1 from domain_tab where userid="+userid+" and online="+online;
		rs = executeQSQL(sql,conn);
		try {
			while(rs.next()) {
				String str = rs.getString("count1");
				count = Integer.parseInt(str);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	//全部
	public int queryCount(long userid) {
		int count = 0;
		sql = "select count(*) as count1 from domain_tab where userid="+userid+"";
		rs = executeQSQL(sql,conn);
		try {
			while(rs.next()) {
				String str = rs.getString("count1");
				count = Integer.parseInt(str);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public Domain queryByDomainName(String domainName,long userid) {
		sql = "select * from domain_tab where domain='"+domainName+"' and userid="+userid;
		rs = executeQSQL(sql,conn);
		try {
			Domain domain = null;
			while(rs.next()) {
				int id = rs.getInt(1);
				domainName = rs.getString(3);
				String dns = rs.getString(4);
				String vps = rs.getString(5);
				String sdomain = rs.getString(6);
				String language = rs.getString(7);
				java.sql.Date sdate = rs.getDate(8);
				userid = rs.getLong(2);
				Date addtime = rs.getDate(9);
				domain = new Domain(id,userid, domainName, dns, vps, sdomain, language, sdate, addtime);
			}
			return domain;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Domain queryById(int id,long userid) {
		sql = "select * from domain_tab where id="+id+" and userid="+userid;
		rs = executeQSQL(sql,conn);
		try {
			Domain domain = null;
			while(rs.next()) {
				id = rs.getInt(1);
				String domainName = rs.getString(3);
				String dns = rs.getString(4);
				String vps = rs.getString(5);
				String sdomain = rs.getString(6);
				String language = rs.getString(7);
				java.sql.Date sdate = rs.getDate(8);
				userid = rs.getLong(2);
				Date addtime = rs.getDate(9);
				boolean online = rs.getBoolean(10);
				int active = rs.getInt(11);
				Date dietime = rs.getDate(12);
				String note = rs.getString(13);
				Date notedate = rs.getDate(14);
				domain = new Domain(id,userid,domainName, dns, vps, sdomain, language, sdate,addtime,online,active,dietime,note,notedate);
			}
			return domain;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	//返回每个语种的域名数量
	public int queryLanguageCount(String language,long userid) {
		sql = "select count(*) as lancount from domain_tab where userid="+userid+" and language='"+language+"' and active != 0";
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
	/**
	 * 
	 * @param userid
	 * @param domainStatus
	 * @param stime
	 * @param kws
	 * @param currentPage
	 * @param pageSize
	 * @param onlinestatus	0未上线  1已上线  2所有状态
	 * @param searchtype	搜索字段类型
	 * @param ends	域名后缀
	 * @return
	 */
	
	public Pager<Domain> search(long userid,int domainStatus,String[] stime,String kws,int currentPage,int pageSize,int onlinestatus,String searchtype,String[] ends,String order) {
		try {
			Pager<Domain> p = null;
			
			String sql1 = null;	//状态部分
			String sql2 = null;	//时间部分
			String sql3 = "";	//查询字段
			String sql4 = null;	//上线与否
			String sql5 = "";	//是否排序
			if(domainStatus == 3) {
				sql1 = "";
			}else {
				sql1 = "active="+domainStatus+" and ";
			}
			if(stime == null) {
				sql2 = "";
			}else {
				String s1 = stime[0].trim();	//起始时间
				String s2 = stime[1].trim();	//结束时间
				sql2 = "sdate between '"+s1+"' and '"+s2+"' and ";
			}
			
			if(ends == null) {
				if("domain".equals(searchtype)) {
					sql3 = "domain like '%"+kws+"%' ";
				}else if("dns".equals(searchtype)) {
					sql3 = "dns like '%"+kws+"%' ";
				}else if("language".equals(searchtype)) {
					sql3 = "language like '%"+kws+"%' ";
				}else if("vps".equals(searchtype)) {
					sql3 = "vps like '%"+kws+"%' ";
				}
			}else {
				sql3 += "(domain REGEXP '"+ends[0]+"$' or ";
				for (int i = 1; i < ends.length-1; i++) {
					sql3 += "domain REGEXP '"+ends[i]+"$' or ";
				}
				sql3 += "domain REGEXP '"+ends[ends.length-1]+"$') ";
			}
			
			
			if(onlinestatus == 0) {
				sql4 = "online = false and ";
			}else if(onlinestatus == 1) {
				sql4 = "online = true and ";
			}else if(onlinestatus == 2) {
				sql4 = "";
			}
			
			if(order != null) {
				sql5 = "order by "+order+" ";
			}else {
				sql5 = "order by sdate desc";
			}
			//获取搜索结果
			String searchSql = "select count(*) as searchCount from domain_tab where "+sql1+ sql2+sql4+"userid="+userid+" and "+sql3+"";
			rs = executeQSQL(searchSql, conn);
			int totalCount = 0;	//搜索查询出的条数总数
			while(rs.next()) {
				String countStr = rs.getString("searchCount");
				totalCount = Integer.parseInt(countStr);
			}
			int pageCount = 0;
			int prePage = 1;
			pageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize + 1;
			if(currentPage > pageCount) {
				currentPage = pageCount;
			}else if(currentPage <= 0) {
				currentPage = 1;
			}
			int minCount = (currentPage-1) * pageSize;	//当前页要查询的最小下标
			if(minCount < 0) {
				minCount = 0;
			}
			sql = "select * from domain_tab where "+sql1+ sql2+sql4+"userid="+userid+" and "+sql3+sql5+" limit "+minCount+","+pageSize+"";
			rs = executeQSQL(sql,conn);
			List<Domain> list = new ArrayList<Domain>();
			Domain domain = null;
			while(rs.next()) {
				int id = rs.getInt(1);
				String domainName = rs.getString(3);
				String dns = rs.getString(4);
				String vps = rs.getString(5);
				String sdomain = rs.getString(6);
				String language = rs.getString(7);
				Date sdate = rs.getDate(8);
				userid = rs.getLong(2);
				Date addtime = rs.getTimestamp(9);
				boolean online = rs.getBoolean(10);
				int active = rs.getInt(11);
				Date dietime = rs.getDate(12);
				String note = rs.getString(13);
				Date notedate = rs.getDate(14);
				domain = new Domain(id,userid,domainName, dns, vps, sdomain, language, sdate,addtime,online,active,dietime,note,notedate);
				list.add(domain);
			}
			
			int nextPage = pageCount;
			if(currentPage > 1) {
				prePage = currentPage - 1;
			}
			if(currentPage < pageCount) {
				nextPage = currentPage + 1;
			}
			p = new Pager<Domain>(pageSize, pageCount, totalCount, prePage, nextPage, currentPage, list,list.size());
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeConn(rs, pst, conn);
		}
		return null;
	}
	public List<Domain> queryCurrentDayDomains(long userid) {
		sql = "select * from domain_tab where date(addtime)=curdate() and userid="+userid;
		rs = executeQSQL(sql,conn);
		List<Domain> list = new ArrayList<Domain>();
		Domain domain = null;
		try {
			while(rs.next()) {
				int id = rs.getInt(1);
				String domainName = rs.getString(3);
				String dns = rs.getString(4);
				String vps = rs.getString(5);
				String sdomain = rs.getString(6);
				String language = rs.getString(7);
				Date sdate = rs.getDate(8);
				userid = rs.getLong(2);
				Date addtime = rs.getTimestamp(9);
				boolean online = rs.getBoolean(10);
				int active = rs.getInt(11);
				Date dietime = rs.getDate(12);
				String note = rs.getString(13);
				Date notedate = rs.getDate(14);
				domain = new Domain(id,userid,domainName, dns, vps, sdomain, language, sdate,addtime,online,active,dietime,note,notedate);
				list.add(domain);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(long userid,String domain,String dns,String vps,String sdomain,String domainlan,String sdate,String note) {
		/*StringBuffer sb = new StringBuffer();
		for(int i=0;i<dns.length;i++) {
			sb.append(dns[i]+",");
		}
		String dnsStr = sb.toString().substring(0,sb.toString().length()-1);*/
		String addTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date().getTime());
		String notedate = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());	//备注日期
		if(note.trim() != null && !"".equals(note.trim())) {
			sql = "insert into domain_tab(userid,domain,dns,vps,sdomain,language,sdate,addtime,online,active,note,notedate) values("+userid+",'"+domain+"','"+dns+"','"+vps+"','"+sdomain+"','"+domainlan+"','"+sdate+"','"+addTime+"',"+true+",1,'"+note+"','"+notedate+"')";
		}else {
			sql = "insert into domain_tab(userid,domain,dns,vps,sdomain,language,sdate,addtime,online,active) values("+userid+",'"+domain+"','"+dns+"','"+vps+"','"+sdomain+"','"+domainlan+"','"+sdate+"','"+addTime+"',"+true+",1)";
		}
		executeMSQL(sql,conn);
	}
	
	public void insert(long userid,String domain,String dns,String note) {
		String addTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date().getTime());
		String notedate = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());
		if(note.trim() != null && !"".equals(note.trim())) {
			if(dns==null) {
				sql = "insert into domain_tab(userid,domain,online,addtime,active,note,notedate) values("+userid+",'"+domain+"',"+false+",'"+addTime+"',1,'"+note+"','"+notedate+"')";
			}else {
				sql = "insert into domain_tab(userid,domain,online,addtime,dns,active,note,notedate) values("+userid+",'"+domain+"',"+false+",'"+addTime+"','"+dns+"',1,'"+note+"','"+notedate+"')";
			}
		}else {
			if(dns==null) {
				sql = "insert into domain_tab(userid,domain,online,addtime,active) values("+userid+",'"+domain+"',"+false+",'"+addTime+"',1)";
			}else {
				sql = "insert into domain_tab(userid,domain,online,addtime,dns,active) values("+userid+",'"+domain+"',"+false+",'"+addTime+"','"+dns+"',1)";
			}
		}
		executeMSQL(sql, conn);
	}
	//上线(100%)
	public void update(String domain,String[] dns,String vps,String sdomain,String slan,String sdate,String note,long userid) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<dns.length;i++) {
			sb.append(dns[i]+",");
		}
		String dnsStr = sb.toString().substring(0,sb.toString().length()-1);
		String notedate = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());
		if(note.trim() != null && !"".equals(note.trim())) {
			sql = "update domain_tab set online=true, dns='"+dnsStr+"',vps='"+vps+"',sdomain='"+sdomain+"',language='"+slan+"',sdate='"+sdate+"',note='"+note+"',notedate='"+notedate+"' where domain='"+domain+"' and userid="+userid;
		}else {
			sql = "update domain_tab set online=true, dns='"+dnsStr+"',vps='"+vps+"',sdomain='"+sdomain+"',language='"+slan+"',sdate='"+sdate+"' where domain='"+domain+"' and userid="+userid;
		}
		executeMSQL(sql,conn);
	}
	public void update(long id,String domain,String[] dns,String note) {
		try {
			String dnsStr = null;
			if(dns != null) {
				StringBuffer sb = new StringBuffer();
				for(int i=0;i<dns.length;i++) {
					sb.append(dns[i]+",");
				}
				dnsStr = sb.toString().substring(0,sb.toString().length()-1);
				if(note.trim() != null && !"".equals(note.trim())) {
					String notedate = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());
					sql = "update domain_tab set dns='"+dnsStr+"',note='"+note+"',notedate='"+notedate+"' where userid="+id+" and domain='"+domain+"'";
				}else {
					sql = "update domain_tab set dns='"+dnsStr+"' where userid="+id+" and domain='"+domain+"'";
				}
			}else {
				if(note.trim() != null && !"".equals(note.trim())) {
					String notedate = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());
					sql = "update domain_tab set note='"+note+"',notedate='"+notedate+"' where userid="+id+" and domain='"+domain+"'";
				}else {
					return;
				}
			}
			executeMSQL(sql, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//修改域名死亡状态
	public void update(long id,String domain,int active) {
		if(active==0) {
			//添加死亡时间
			String dietime = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());
			sql = "update domain_tab set active="+active+",dietime='"+dietime+"' where userid="+id+" and domain='"+domain+"'";
		}else {
			sql = "update domain_tab set active="+active+" where userid="+id+" and domain='"+domain+"'";
		}
		executeMSQL(sql, conn);
	}
	
	public void delete(int id,long userid) {
		sql = "delete from domain_tab where id="+id+" and userid="+userid;
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
