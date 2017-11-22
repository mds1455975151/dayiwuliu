package com.tianrui.service.bean;

public class OldBillMoney {
	
	private String payid;
	
	private String bankid;

	private String driverid;
	
	private String waybillno;
	
	private Long unloadtime;
	
	private Double price;
	
	private Double weight;

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getWaybillno() {
		return waybillno;
	}

	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}

	public Long getUnloadtime() {
		return unloadtime;
	}

	public void setUnloadtime(Long unloadtime) {
		this.unloadtime = unloadtime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
}
