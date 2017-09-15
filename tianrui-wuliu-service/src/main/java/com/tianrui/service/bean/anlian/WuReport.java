package com.tianrui.service.bean.anlian;

public class WuReport {
	
	private String receiveMemberid;//收货人id
	private String driverid;//司机id
	private String ownerid;//货主id
	private String venderid;//车主id
	private String id;//运单id
	private String waybillno;//运单号
	private String vehicleno;//车牌号
	private String routename;//路线名称
	private double trueweight;//签收重量
	private Long ownerSigntime;//签收日期
	private double amountPayable;//支付金额
	private Integer payStatus;//支付状态
	private String  planCode;//计划编号
	private String  cargoname;//货物名称
	private String startcity;//始发地
	private String endcity;//目的地
	private String routeid;//策略id
	
	
	
	public String getRouteid() {
		return routeid;
	}
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	public String getReceiveMemberid() {
		return receiveMemberid;
	}
	public void setReceiveMemberid(String receiveMemberid) {
		this.receiveMemberid = receiveMemberid;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getVenderid() {
		return venderid;
	}
	public void setVenderid(String venderid) {
		this.venderid = venderid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getStartcity() {
		return startcity;
	}
	public void setStartcity(String startcity) {
		this.startcity = startcity;
	}
	public String getEndcity() {
		return endcity;
	}
	public void setEndcity(String endcity) {
		this.endcity = endcity;
	}
	
	
}
