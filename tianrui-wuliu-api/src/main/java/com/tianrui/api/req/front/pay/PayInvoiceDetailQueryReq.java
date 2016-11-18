package com.tianrui.api.req.front.pay;

import com.tianrui.api.req.BaseReq;

public class PayInvoiceDetailQueryReq  extends BaseReq{

	private static final long serialVersionUID = -1534731198372183597L;

	private String curruId;
	
	private String ownername;
	private String status;
	private String isInvoice;
	private String payId;
	private String billNO;
	private String signTime;
	private String cargoName;
	private String invoiceType;
	private String id;
	private String ids;
	//司机
	private String driverId;
	//  0：在线支付(司机)，1：发票单支付(车主)'
	private String payownertype;
	
	
	
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
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getIsInvoice() {
		return isInvoice;
	}
	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getPayownertype() {
		return payownertype;
	}
	public void setPayownertype(String payownertype) {
		this.payownertype = payownertype;
	}
	
	
}
