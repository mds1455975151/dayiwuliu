package com.tianrui.api.req.front.bill;

public class AnlianBillSaveReq {

	private String planid;
	private String weight;
	
	private String billStartTime;
	private String billEndTime;
	/** 车辆司机关系表主键id*/
	private String vehicleDrvierid;
	 //支付对象 1-司机 2-车主
    private String payment;
    //收货人用户id
    private String receive_memberid;
	
	private String price;
	/** 体积*/
	private String volume;
	/**总件数*/
	private String size;
	
	private String vehicleid;
	private String driverid;
	
	private String ownerid;
	private String venderid;
	
	
	
	public String getPlanid() {
		return planid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
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
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
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
	public String getVehicleDrvierid() {
		return vehicleDrvierid;
	}
	public void setVehicleDrvierid(String vehicleDrvierid) {
		this.vehicleDrvierid = vehicleDrvierid;
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
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getReceive_memberid() {
		return receive_memberid;
	}
	public void setReceive_memberid(String receive_memberid) {
		this.receive_memberid = receive_memberid;
	}
}
