package com.tianrui.service.bean;

public class MemberCapaList {

	private String id;
	private String vehicledriverid;
	private String status;
	private String driverid;
	private String vehicleid;
	private String vehicleno;
	private String drivername;
	private String drivertel;
	private String vehicletype;
	private String billstatus;
	private String weight;
	private String length;
	private String vehfix;
	private String vehno;
	private String username;
	private String telphone;
	private String companyname;
	private String companytel;
	
	private String desc1;
	private String aldriverid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicleno() {
		vehicleno = vehfix + vehno;
		return vehicleno;
	}
	
	public String getVehicledriverid() {
		return vehicledriverid;
	}
	public void setVehicledriverid(String vehicledriverid) {
		this.vehicledriverid = vehicledriverid;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getVehfix() {
		return vehfix;
	}
	public void setVehfix(String vehfix) {
		this.vehfix = vehfix;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getAldriverid() {
		return aldriverid;
	}
	public void setAldriverid(String aldriverid) {
		this.aldriverid = aldriverid;
	}
	public String getVehno() {
		return vehno;
	}
	public void setVehno(String vehno) {
		this.vehno = vehno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getDrivertel() {
		return drivertel;
	}
	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanytel() {
		return companytel;
	}
	public void setCompanytel(String companytel) {
		this.companytel = companytel;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getBillstatus() {
		return billstatus;
	}
	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	
	
}
