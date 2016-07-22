package com.tianrui.api.resp.admin;

import java.util.List;

import com.tianrui.api.resp.BaseResp;

public class UserResp extends BaseResp{


	private static final long serialVersionUID = -7190183760043617974L;

	private String id ;
	private String account ;
	private String tel ;
	private String name ;
	private String status ;
	
	private String role;
	/**
     * 组织id
     */
    private String orgid;

    /**
     * 组织名称
     */
    private String orgname;
	
	
	private List<RoleResp> roleList;


	public List<RoleResp> getRoleList() {
		return roleList;
	}


	public void setRoleList(List<RoleResp> roleList) {
		this.roleList = roleList;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getOrgid() {
		return orgid;
	}


	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}


	public String getOrgname() {
		return orgname;
	}


	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
}
