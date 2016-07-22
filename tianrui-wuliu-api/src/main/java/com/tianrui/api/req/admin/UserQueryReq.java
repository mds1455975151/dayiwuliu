package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class UserQueryReq extends BaseReq {

	private static final long serialVersionUID = -7190183760043617974L;

	private String username; 
	private String name; 
	private String tel;
	/**
     * 组织id
     */
    private String orgid;
	// 自定义项1
	private String desc1;
	
	public String getUsername() {
		return username;
	}
	
	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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

}
