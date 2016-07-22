package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class FileRouteReq extends BaseReq{


	private static final long serialVersionUID = -7190183760043617974L;

    private String routename;
    private String organizationid;
    private String id;
    
	//当前登录用户组织id
	private String currOrgId;
	//当前登录用户
	private String currUser;
	
	private String  status;
    
	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public String getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrUser() {
		return currUser;
	}

	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}

	public String getCurrOrgId() {
		return currOrgId;
	}

	public void setCurrOrgId(String currOrgId) {
		this.currOrgId = currOrgId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
