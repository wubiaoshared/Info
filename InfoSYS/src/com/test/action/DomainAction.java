/*package com.test.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.test.bean.DNS;
import com.test.bean.Domain;
import com.test.bean.Pager;
import com.test.bean.VPS;
import com.test.dao.DNSDAO;
import com.test.dao.DomainDAO;
import com.test.dao.VPSDao;

public class DomainAction extends ActionSupport {
	
	private List<Domain> domains;
	
	private Pager<Domain> pager;
	
	private int page;
	
	private List<VPS> vpslist;
	
	private List<DNS> dnslist;
	
	private Domain domain;
	
	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public List<VPS> getVpslist() {
		return vpslist;
	}

	public void setVpslist(List<VPS> vpslist) {
		this.vpslist = vpslist;
	}

	public List<DNS> getDnslist() {
		return dnslist;
	}

	public void setDnslist(List<DNS> dnslist) {
		this.dnslist = dnslist;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Domain> getDomains() {
		return domains;
	}

	public void setDomains(List<Domain> domains) {
		this.domains = domains;
	}

	public Pager<Domain> getPager() {
		return pager;
	}

	public void setPager(Pager<Domain> pager) {
		this.pager = pager;
	}
	
	
	//已上线
	public String online() {
		DomainDAO dd = new DomainDAO();
		DNSDAO dnsDao = new DNSDAO();
		VPSDao vpsdao = new VPSDao();
		pager = dd.queryAll(page, 20, 110120, true);
		List<Domain> domainlists = pager.getObjlist();
		domains = new ArrayList<Domain>();
		for(Domain domain : domainlists) {
			String dnsName = dnsDao.queryByServer(domain.getDns(), 110120).getDnsname();
			domain.setDnsname(dnsName);
			String VpsName = vpsdao.queryByIp(domain.getVps(), 110120).getName();
			domain.setVpsname(VpsName);
			domains.add(domain);
		}
		request.setAttribute("domainlist", domains);
		request.setAttribute("pager", pager);
		return SUCCESS;
	}
	
	//未上线
	public String offline() {
		DomainDAO dd = new DomainDAO();
		DNSDAO dnsDao = new DNSDAO();
		pager = dd.queryAll(page, 20, 110120, false);
		List<Domain> domainlists = pager.getObjlist();
		domains = new ArrayList<Domain>();
		for(Domain domain : domainlists) {
			String dnsName = null;
			if(domain.getDns()!=null) {
				dnsName = dnsDao.queryByServer(domain.getDns(), 110120).getDnsname();
			}
			domain.setDnsname(dnsName);
			domains.add(domain);
		}
		request.setAttribute("domainlist", domains);
		request.setAttribute("pager", pager);
		return SUCCESS;
	}
	
	public String getDomainMsg() {
		DomainDAO dd = new DomainDAO();
		DNSDAO dnsDao = new DNSDAO();
		VPSDao vpsdao = new VPSDao();
		
		Pager<DNS> dnspager = dnsDao.queryAll(110120, 1, 100);
		dnslist = dnspager.getObjlist();
		
		Pager<VPS> vpspager = vpsdao.queryAll(110120, 1, 100);
		vpslist = vpspager.getObjlist();
		
		return SUCCESS;
	}
	
	public String add() {
		
		return SUCCESS;
	}

	
}
*/