package com.tianrui.api.req.front.pay;

import com.tianrui.api.req.BaseReq;

public class PayInvoiceGenalReq  extends BaseReq{

	private static final long serialVersionUID = -1534731198372183597L;

	private String curruId;
	
	private String ids;

	public String getCurruId() {
		return curruId;
	}

	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	
	
	
	
	
}
