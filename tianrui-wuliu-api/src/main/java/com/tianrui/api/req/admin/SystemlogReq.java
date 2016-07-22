package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class SystemlogReq extends BaseReq {

	private static final long serialVersionUID = -7190183760043617974L;
	private String account;
	private String description;
	private String startTime;
	private String endTime;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	

}
