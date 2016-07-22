package com.tianrui.api.resp.admin;

import com.tianrui.api.resp.BaseResp;

public class SystemlogResp extends BaseResp{


	private static final long serialVersionUID = -7190183760043617974L;
	private String subCode;
	private String subitemCode;
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getSubitemCode() {
		return subitemCode;
	}
	public void setSubitemCode(String subitemCode) {
		this.subitemCode = subitemCode;
	}

	
	
}
