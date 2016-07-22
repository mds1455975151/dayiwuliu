package com.tianrui.api.req.front.member;

import com.tianrui.api.req.BaseReq;

public class MemberUpdateReq extends BaseReq{
	
	private static final long serialVersionUID = 7072711650223001052L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 电话
	 */
	private String cellphone;
	/**
	 * 登陆名称
	 */
	private String loginname;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 平台昵称
	 */
	private String username;
	
	private String phone;
	/**
	 *  会员状态0-失效,1-正常
	 */
	private String status;
	/**
	 * 用户状态：0：未认证， 1：认证通过 ，2：认证中，3：认证失败   
	 */
	private String percheckstatus;
	/**
	 * * 1：企业用户，2：  个人用户
	 */
	private String usertype;
	/**
	 * * 审核不通过类型
	 */
	private String rejecttype;
	/**
	 * 审核不通过具体原因
	 */
	private String rejectreason;
	/**
	 * 头像路径
	 */
	private String avatarspath;
	/**
	 * 组织id
	 */
	private String orgid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCellphone() {
		return cellphone;
	}


	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPercheckstatus() {
		return percheckstatus;
	}

	public void setPercheckstatus(String percheckstatus) {
		this.percheckstatus = percheckstatus;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getRejecttype() {
		return rejecttype;
	}

	public void setRejecttype(String rejecttype) {
		this.rejecttype = rejecttype;
	}

	public String getRejectreason() {
		return rejectreason;
	}

	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}

	public String getAvatarspath() {
		return avatarspath;
	}

	public void setAvatarspath(String avatarspath) {
		this.avatarspath = avatarspath;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
