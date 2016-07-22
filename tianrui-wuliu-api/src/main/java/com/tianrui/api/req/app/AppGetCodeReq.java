package com.tianrui.api.req.app;

import com.tianrui.api.req.BaseReq;

public class AppGetCodeReq extends BaseReq{
	//手机号
	private String account;
	// 0-注册；1-密码找回;2-登录验证码
	private String type;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
		
}
