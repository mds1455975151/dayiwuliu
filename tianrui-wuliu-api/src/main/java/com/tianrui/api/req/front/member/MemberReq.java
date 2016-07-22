package com.tianrui.api.req.front.member;

import com.tianrui.api.req.BaseReq;

public class MemberReq extends BaseReq{

	private static final long serialVersionUID = 7072711650223001052L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 账号
	 */
	private String telnum;
	/**
	 * 密码
	 */
	private String passWord;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
