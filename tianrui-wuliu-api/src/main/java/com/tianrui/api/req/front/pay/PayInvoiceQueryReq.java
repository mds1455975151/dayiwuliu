package com.tianrui.api.req.front.pay;

import com.tianrui.api.req.BaseReq;

public class PayInvoiceQueryReq  extends BaseReq{

	private static final long serialVersionUID = -3452402415759870263L;

	private String curruId;
	private String id;
	/** 账单编号*/
	private String paycode;
	/** 申请时间*/
	private String applytime;
	/** 账单状态*/
	private String paystatus;
	/** 审核状态*/
	private String adviceStatus;

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

	public String getPaycode() {
		return paycode;
	}

	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}

	public String getApplytime() {
		return applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}

	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}

	public String getAdviceStatus() {
		return adviceStatus;
	}

	public void setAdviceStatus(String adviceStatus) {
		this.adviceStatus = adviceStatus;
	}
	
}
