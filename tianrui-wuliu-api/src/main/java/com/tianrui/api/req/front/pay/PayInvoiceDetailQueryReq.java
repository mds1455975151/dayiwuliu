package com.tianrui.api.req.front.pay;

import com.tianrui.api.req.BaseReq;

public class PayInvoiceDetailQueryReq  extends BaseReq{

	private static final long serialVersionUID = -1534731198372183597L;

	private String curruId;
	
	private String status;
	private String billNO;
	private String signTime;
	private String cargoName;
	private String id;
	
	
	
	public String getCurruId() {
		return curruId;
	}
	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBillNO() {
		return billNO;
	}
	public void setBillNO(String billNO) {
		this.billNO = billNO;
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
