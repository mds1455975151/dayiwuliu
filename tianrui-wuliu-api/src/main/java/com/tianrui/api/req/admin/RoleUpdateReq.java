package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class RoleUpdateReq extends BaseReq{


	private static final long serialVersionUID = -7190183760043617974L;
	private Integer id;
	
	private String roleName;
	private String roleNumber;
	private String roleDescription;
	private String creator;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleNumber() {
		return roleNumber;
	}
	public void setRoleNumber(String roleNumber) {
		this.roleNumber = roleNumber;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	
}
