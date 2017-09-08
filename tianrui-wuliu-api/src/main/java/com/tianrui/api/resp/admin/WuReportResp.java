package com.tianrui.api.resp.admin;

public class WuReportResp {
	
	private String waybillno;//运单号
	private String vehicleno;//车牌号
	private String routename;//路线名称
	private double trueweight;//签收重量
	private Long ownerSigntime;//签收日期
	private double amountPayable;//支付金额
	private Integer payStatus;//支付状态
	private String  planCode;//计划编号
	private String  cargoname;//货物名称
	public String getWaybillno() {
		return waybillno;
	}
	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	public double getTrueweight() {
		return trueweight;
	}
	public void setTrueweight(double trueweight) {
		this.trueweight = trueweight;
	}
	public Long getOwnerSigntime() {
		return ownerSigntime;
	}
	public void setOwnerSigntime(Long ownerSigntime) {
		this.ownerSigntime = ownerSigntime;
	}
	public double getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	
	
}
