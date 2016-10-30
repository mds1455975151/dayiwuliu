package com.tianrui.api.req.count;

public class CountSelectReq {

private String id;
	
	private String status;
	
	private long selecttime;
	
	private Integer max;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public void setMax(Integer max) {
		this.max = max;
	}
}
