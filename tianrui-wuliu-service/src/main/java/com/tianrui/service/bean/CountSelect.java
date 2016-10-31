package com.tianrui.service.bean;

public class CountSelect {

	private String id;
	
	private String status;
	
	private long selecttime;
	
	private long count;
	
	private Integer max;
	
	private Double totalplanned;
	
	private Double completed;
	
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getSelecttime() {
		return selecttime;
	}

	public void setSelecttime(long selecttime) {
		this.selecttime = selecttime;
	}

	public Integer getMax() {
		return max;
	}

	public Double getTotalplanned() {
		return totalplanned;
	}

	public void setTotalplanned(Double totalplanned) {
		this.totalplanned = totalplanned;
	}

	public Double getCompleted() {
		return completed;
	}

	public void setCompleted(Double completed) {
		this.completed = completed;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
}
