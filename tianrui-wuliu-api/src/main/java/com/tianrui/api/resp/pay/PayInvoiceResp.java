package com.tianrui.api.resp.pay;

import java.util.List;

import com.tianrui.api.resp.BaseResp;

public class PayInvoiceResp extends BaseResp{

	private static final long serialVersionUID = 1L;
	private List<PayInvoiceDetailResp> items;

	public List<PayInvoiceDetailResp> getItems() {
		return items;
	}

	public void setItems(List<PayInvoiceDetailResp> items) {
		this.items = items;
	}
	
	
}
