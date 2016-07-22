package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class UserLoginReq extends BaseReq {

	private static final long serialVersionUID = -7190183760043617974L;
	//用户
	private String account;
	//密码
	private String password;
	//验证码
	private String authCode;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	


}
