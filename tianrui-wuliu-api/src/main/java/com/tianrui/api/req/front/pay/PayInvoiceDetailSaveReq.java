package com.tianrui.api.req.front.pay;

import com.tianrui.api.req.BaseReq;

public class PayInvoiceDetailSaveReq  extends BaseReq{

	private static final long serialVersionUID = -3939878278008736899L;

	private String curruId;
	
	private String billId;

	public String getCurruId() {
		return curruId;
	}

	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}
	
	
}
