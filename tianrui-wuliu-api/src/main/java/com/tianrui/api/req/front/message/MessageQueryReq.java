package com.tianrui.api.req.front.message;

import com.tianrui.api.req.BaseReq;

public class MessageQueryReq extends BaseReq{
	
	private static final long serialVersionUID = 1L;
	private String curruId;
	private int rectype;

	public String getCurruId() {
		return curruId;
	}

	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}

	public int getRectype() {
		return rectype;
	}

	public void setRectype(int rectype) {
		this.rectype = rectype;
	}
    
}
