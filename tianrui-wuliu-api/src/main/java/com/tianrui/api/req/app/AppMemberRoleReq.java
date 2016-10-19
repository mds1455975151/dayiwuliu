package com.tianrui.api.req.app;

import com.tianrui.api.req.BaseReq;

public class AppMemberRoleReq extends BaseReq{

	private static final long serialVersionUID = 1L;
	private String role;
	private String currId;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCurrId() {
		return currId;
	}
	public void setCurrId(String currId) {
		this.currId = currId;
	}
	
	
	
}
