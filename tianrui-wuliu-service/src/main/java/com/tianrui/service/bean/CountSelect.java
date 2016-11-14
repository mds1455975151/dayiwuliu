package com.tianrui.service.bean;

public class CountSelect {

	private String id;
	
	private String status;
	
	private String op;
	
	private String opc;
	
	private long selecttime;
	
	private long count;
	
	private Integer max;
	
	private Double totalplanned;
	
	private Double completed;
	
	private Double sum;
	
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getSum() {
		sum = sum == null ? 0 : sum;
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getStatus() {
		return status;
	}

	public String getRemark() {
		return remark;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getOpc() {
		return opc;
	}

	public void setOpc(String opc) {
		this.opc = opc;
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
		totalplanned = totalplanned == null?0:totalplanned;
		return totalplanned;
	}

	public void setTotalplanned(Double totalplanned) {
		this.totalplanned = totalplanned;
	}

	public Double getCompleted() {
		completed = completed == null?0:completed;
		return completed;
	}

	public void setCompleted(Double completed) {
		this.completed = completed;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
}
