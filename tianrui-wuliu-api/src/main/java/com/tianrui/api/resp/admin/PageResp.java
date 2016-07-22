package com.tianrui.api.resp.admin;

import java.util.List;

public class PageResp<T> {

	/**
	 * 第几页
	 */
	private Integer pageNo;
	/**
	 * 每页几条数据
	 */
	private Integer pageSize;
	/**
	 * 共几页
	 */
	private Integer pageCount;
	/**
	 * 数据总条数
	 */
	private Long count;
	/**
	 * 数据集合
	 */
	private List<T> list;
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public Integer getPageCount() {
		Integer co = null;
		Integer on = new Long(count).intValue();  
		if(pageNo!=null){
			co = on/pageSize;
			if(on%pageSize!=0){
				co = co +1;
			}
		}
		return co;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
