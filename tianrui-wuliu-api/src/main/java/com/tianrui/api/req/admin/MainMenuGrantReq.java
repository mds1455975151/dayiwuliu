package com.tianrui.api.req.admin;

import java.util.List;

import com.tianrui.api.req.BaseReq;

public class MainMenuGrantReq extends BaseReq{


	private static final long serialVersionUID = -7190183760043617974L;
	private Integer id;
	private List<Integer> roleIds;

	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	
}
