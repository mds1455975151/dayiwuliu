package com.tianrui.api.resp.front.vehicle;

/**
 * 我的驾驶员列表
 * 
 * @author lixp
 *
 */
public class VehicleRegDriverListResp {
	private String id;
	// 名称
	private String name;
	// 电话号码
	private String telphone;
	// 身份证号
	private String idcard;
	// 选中状态 0未选中 1已选中
	private String checkStatus;
	// 认证状态 1 认证通过 2认证中 3认证失败
	private String authSatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getAuthSatus() {
		return authSatus;
	}

	public void setAuthSatus(String authSatus) {
		this.authSatus = authSatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
