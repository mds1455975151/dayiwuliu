package com.tianrui.api.req.front.pay;

import com.tianrui.api.req.BaseReq;

public class PayInvoiceGenalReq  extends BaseReq{

	private static final long serialVersionUID = -1534731198372183597L;

	private String curruId;
	
	private String ids;
	//账单编号
	private String payCode;
	//请求类型，0-计算 1保存
	private String qtype;

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

	public String getQtype() {
		return qtype;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}
}
