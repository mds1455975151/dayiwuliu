package com.tianrui.api.req.admin;

public class OrganizationUpdateReq {
	
	private static final long serialVersionUID = -7190183760043617974L;
	
	private String id;
	
	private String organizationname;
	
	private String organizationtype;
	
	private String organizationno;
	
	private String status;
	
	private String modifier;
	
	private String modifytime;

	public String getOrganizationname() {
		return organizationname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}
	
	
}
