package com.tianrui.api.req;

public class BasePage extends BaseReq {
	
	private static final long serialVersionUID = 1L;
	//排序字段
	private String orderColumn = "ID";
	//排序方式 DESC: 倒序, ASC: 正序
	private String orderAsc = "DESC";
	//页码
	private Integer start;
	//每页条数
	private Integer limit;
	
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderAsc() {
		return orderAsc;
	}
	public void setOrderAsc(String orderAsc) {
		this.orderAsc = orderAsc;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
