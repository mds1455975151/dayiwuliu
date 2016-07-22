package com.tianrui.api.req.front.member;

import com.tianrui.api.req.BaseReq;

public class MemberSaveReq extends BaseReq{

	private static final long serialVersionUID = 7072711650223001052L;
	/**
	 * 注册手机号
	 */
	private String cellphone;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 推荐人
	 * 推荐人手机号
	 */
	private String referrer;
	/**
	 * 注册时间
	 */
	private String registtime;
	/**
	 * 会员状态
	 * 0-失效,1-正常
	 */
	private String status;
	/**
	 * 用户状态：0：未认证， 1：认证通过 ，2：认证中，3：认证失败   
	 */
	private short percheckstatus;
	/**
	 * 用户来源 0:注册 1：系统导入或者内部添加
	 */
	private short sourcetype;
	
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	public String getRegisttime() {
		return registtime;
	}
	public void setRegisttime(String registtime) {
		this.registtime = registtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public short getPercheckstatus() {
		return percheckstatus;
	}
	public void setPercheckstatus(short percheckstatus) {
		this.percheckstatus = percheckstatus;
	}
	public short getSourcetype() {
		return sourcetype;
	}
	public void setSourcetype(short sourcetype) {
		this.sourcetype = sourcetype;
	}
	
}
