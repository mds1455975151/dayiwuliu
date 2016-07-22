package com.tianrui.api.req.admin;

import java.util.List;

import com.tianrui.api.req.BaseReq;

public class UserUpdateReq extends BaseReq {

	private static final long serialVersionUID = -7190183760043617974L;

    private Integer id;
    private String account;
    private String usertype;
    private String telnum;
    private Integer status;
    private String password;
    
    private List<Integer> rIdList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Integer> getrIdList() {
		return rIdList;
	}

	public void setrIdList(List<Integer> rIdList) {
		this.rIdList = rIdList;
	}

}
