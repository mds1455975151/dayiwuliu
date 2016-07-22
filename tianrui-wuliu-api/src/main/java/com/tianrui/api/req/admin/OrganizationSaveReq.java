package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class OrganizationSaveReq extends BaseReq{
	
	private static final long serialVersionUID = -7190183760043617974L;
	
	private String organizationname;
	
	private String organizationtype;
	
	private String organizationno;
	
	private String status;
	
	private String creator;
	
	private String createtime;

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}

	public String getOrganizationtype() {
		return organizationtype;
	}

	public void setOrganizationtype(String organizationtype) {
		this.organizationtype = organizationtype;
	}

	public String getOrganizationno() {
		return organizationno;
	}

	public void setOrganizationno(String organizationno) {
		this.organizationno = organizationno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
}
