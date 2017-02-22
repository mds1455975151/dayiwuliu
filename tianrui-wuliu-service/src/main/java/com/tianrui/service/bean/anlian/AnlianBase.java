package com.tianrui.service.bean.anlian;

import com.tianrui.common.constants.Constant;

public class AnlianBase {
	private String username=Constant.ANLIAN_USERNAME;
	
	private String pwd=Constant.ANLIAN_PASSWORD;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
