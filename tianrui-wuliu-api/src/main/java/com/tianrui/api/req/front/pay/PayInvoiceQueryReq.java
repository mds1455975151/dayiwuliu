package com.tianrui.api.req.front.pay;

import com.tianrui.api.req.BaseReq;

public class PayInvoiceQueryReq  extends BaseReq{

	private static final long serialVersionUID = -3452402415759870263L;

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
	
	
}
