package com.tianrui.api.resp.front.member;

import com.tianrui.api.resp.BaseResp;

public class SystemMemberResp extends BaseResp{

	private String id;
    
	private String cellphone;
    /**
     * 推荐人
     */
    private String referrer;
    
    private String password;

    private String status;

    private Long registtime;

    private Long lasttime;

    private String orgid;

    private String nickname;

    private String loginname;

    private String sex;

    private String avatarspath;

    private Short sourcetype;

    private Short userpercheck;

    private Short driverpercheck;

    private Short companypercheck;

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

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRegisttime() {
		return registtime;
	}

	public void setRegisttime(Long registtime) {
		this.registtime = registtime;
	}

	public Long getLasttime() {
		return lasttime;
	}

	public void setLasttime(Long lasttime) {
		this.lasttime = lasttime;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getAvatarspath() {
		return avatarspath;
	}

	public void setAvatarspath(String avatarspath) {
		this.avatarspath = avatarspath;
	}

	public Short getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(Short sourcetype) {
		this.sourcetype = sourcetype;
	}

	public Short getUserpercheck() {
		return userpercheck;
	}

	public void setUserpercheck(Short userpercheck) {
		this.userpercheck = userpercheck;
	}

	public Short getDriverpercheck() {
		return driverpercheck;
	}

	public void setDriverpercheck(Short driverpercheck) {
		this.driverpercheck = driverpercheck;
	}

	public Short getCompanypercheck() {
		return companypercheck;
	}

	public void setCompanypercheck(Short companypercheck) {
		this.companypercheck = companypercheck;
	}
    
}
