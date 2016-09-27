package com.tianrui.api.req.front.pay;

import com.tianrui.api.req.BaseReq;

public class PayInvoiceReq extends BaseReq{
    private String id;
    
    private String curruId;
    
    private Byte payStatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurruId() {
		return curruId;
	}
	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}
	public Byte getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
	}
	
}
