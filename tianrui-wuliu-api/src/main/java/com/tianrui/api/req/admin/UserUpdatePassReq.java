package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class UserUpdatePassReq extends BaseReq {

	private static final long serialVersionUID = -7190183760043617974L;

	private String passWord;
	private String tel;
	private String usertype;
	private Integer id;
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

}
