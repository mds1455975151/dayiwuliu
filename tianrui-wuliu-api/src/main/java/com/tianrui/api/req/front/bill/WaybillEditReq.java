package com.tianrui.api.req.front.bill;

import com.tianrui.api.req.BaseReq;

public class WaybillEditReq extends BaseReq{

	private static final long serialVersionUID = 1021715600158173293L;

	private String id;
	private String planId;
	
	private String billStartTime;
	private String billEndTime;
	
	private String weight;
	private String price;
	
	private String vehicleId;
	private String driverId;
	
	private String curruId;
	//总趟数
	private String totalnumber;
	//剩余趟数
	private String overnumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getBillStartTime() {
		return billStartTime;
	}

	public void setBillStartTime(String billStartTime) {
		this.billStartTime = billStartTime;
	}

	public String getBillEndTime() {
		return billEndTime;
	}

	public void setBillEndTime(String billEndTime) {
		this.billEndTime = billEndTime;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurruId() {
		return curruId;
	}

	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getOvernumber() {
		return overnumber;
	}

	public void setOvernumber(String overnumber) {
		this.overnumber = overnumber;
	}

	public String getTotalnumber() {
		return totalnumber;
	}

	public void setTotalnumber(String totalnumber) {
		this.totalnumber = totalnumber;
	}
	
}
