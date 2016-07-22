package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class UserReq extends BaseReq {

	private static final long serialVersionUID = -7190183760043617974L;
	
	private Integer id;
	
	private String userName ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
