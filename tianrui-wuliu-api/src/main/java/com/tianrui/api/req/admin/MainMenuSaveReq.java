package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class MainMenuSaveReq extends BaseReq {

	private static final long serialVersionUID = -7190183760043617974L;

	private String nodename;
	private String afterurl;
	private String afternodename;
	private String createtime;
	private Integer parentnodeid;
	private Integer order1;


	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
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

	public Integer getParentnodeid() {
		return parentnodeid;
	}

	public void setParentnodeid(Integer parentnodeid) {
		this.parentnodeid = parentnodeid;
	}

	public Integer getOrder1() {
		return order1;
	}

	public void setOrder1(Integer order1) {
		this.order1 = order1;
	}


}
