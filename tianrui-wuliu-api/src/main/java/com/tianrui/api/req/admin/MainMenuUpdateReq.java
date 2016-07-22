package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class MainMenuUpdateReq extends BaseReq {

	private static final long serialVersionUID = -7190183760043617974L;

	
	private Integer id;
	private String afterurl;
	private String afternodename;
	private String createtime;
	private Integer order1;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAfterurl() {
		return afterurl;
	}
	public void setAfterurl(String afterurl) {
		this.afterurl = afterurl;
	}
	public String getAfternodename() {
		return afternodename;
	}
	public void setAfternodename(String afternodename) {
		this.afternodename = afternodename;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getOrder1() {
		return order1;
	}
	public void setOrder1(Integer order1) {
		this.order1 = order1;
	}


	


}
