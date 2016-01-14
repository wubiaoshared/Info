package com.test.bean;

import java.util.List;
import java.util.Map;

public class Pager<T> {
	
	private int pageSize;	//每页条数
	private int pageCount;	//总页数
	private int totalCount;	//总条数
	private int prePage;	//上一页
	private int nextPage;	//下一页
	private int page;	//当前页
	private int currentCount;	//当前页面的数量
	private List<T> objlist;	//需要被分页的列表对象
	private String pageUrl;	//分页url
	private int pageNumber;	//分页编号
	
	public Pager() {
		super();
	}
	
	public Pager(int pageSize, int pageCount, int totalCount, int prePage,
			int nextPage, int page, List<T> objlist, int currentCount) {
		super();
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
		this.prePage = prePage;
		this.nextPage = nextPage;
		this.page = page;
		this.currentCount = currentCount;
		this.objlist = objlist;
	}



	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getObjlist() {
		return objlist;
	}
	public void setObjlist(List<T> objlist) {
		this.objlist = objlist;
	}
	
	

}
