package com.tianrui.api.req.front.pay;

import com.tianrui.api.req.BaseReq;

public class PayInvoiceAdviceReq  extends BaseReq{

	private static final long serialVersionUID = -2462736229200203396L;

	private String curruId;
	
	private String id;

	public String getCurruId() {
		return curruId;
	}

	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
