package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class RoleReq extends BaseReq{


	private static final long serialVersionUID = -7190183760043617974L;
	private String roleName;
	private String roleNumber;
	private Integer id;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
