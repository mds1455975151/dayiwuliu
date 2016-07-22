package com.tianrui.api.req.admin;

import java.util.List;

import com.tianrui.api.req.BaseReq;

public class UserSaveReq extends BaseReq {

	private static final long serialVersionUID = -7190183760043617974L;

    private String account;
    private String password;
    private String usertype;
    private String telnum;
    private Integer status;
    private String createtime;
    /**
     * 组织id
     */
    private String orgid;
    /**
     * 组织姓名
     */
    private String orgname;
    /** 自定义项1 */
    private String desc1;
    /** 自定义项2 */
    private String desc2;
    /** 自定义项2 */
    private String desc3;
    
    private List<Integer> roleIds;
	public String getAccount() {
		return account;
	}
	
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
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
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getTelnum() {
		return telnum;
	}
	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	public Integer getStatus() {
		return status;
	}
	public String getDesc3() {
		return desc3;
	}
	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
	/**
	 * 获取自定义项1
	 * @return imgPath
	 */
	public String getDesc1() {
		return desc1;
	}
	
	/**
	 * 设置自定义项1
	 * @param newDesc1
	 */
	public void setDesc1(String newDesc1) {
		this.desc1 = newDesc1;
	}

	/**
	 * 获取自定义项2
	 * @return imgPath
	 */
	public String getDesc2() {
		return desc2;
	}
	
	/**
	 * 设置自定义项2
	 * @param newDesc2
	 */
	public void setDesc2(String newDesc2) {
		this.desc2 = newDesc2;
	}

}
