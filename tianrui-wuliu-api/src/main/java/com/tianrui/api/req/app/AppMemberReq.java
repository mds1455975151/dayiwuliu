package com.tianrui.api.req.app;

import com.tianrui.api.req.BaseReq;

public class AppMemberReq extends BaseReq{

	private static final long serialVersionUID = 1L;
	//登录手机号
	private String account;
	//密码加密串
	private String pswdMd5;
	//验证码
	private String authCode;
	// 0:密码登录  1:验证码登录
	private int loginType=0;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPswdMd5() {
		return pswdMd5;
	}
	public void setPswdMd5(String pswdMd5) {
		this.pswdMd5 = pswdMd5;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public int getLoginType() {
		return loginType;
	}
	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}
	
	
}
