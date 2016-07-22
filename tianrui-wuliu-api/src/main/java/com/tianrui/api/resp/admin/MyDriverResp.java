package com.tianrui.api.resp.admin;

import com.tianrui.api.resp.BaseResp;

public class MyDriverResp extends BaseResp{

	private String id;
	private String memberid;
	private String drivertel;
	private String drivername;
	private String vehilength;
	private String vehiweight;
	private String vehicletype;
	private String vehicleno;
	private String vehicleprefix;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getDrivertel() {
		return drivertel;
	}
	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getVehilength() {
		return vehilength;
	}
	public void setVehilength(String vehilength) {
		this.vehilength = vehilength;
	}
	public String getVehiweight() {
		return vehiweight;
	}
	public void setVehiweight(String vehiweight) {
		this.vehiweight = vehiweight;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getVehicleprefix() {
		return vehicleprefix;
	}
	public void setVehicleprefix(String vehicleprefix) {
		this.vehicleprefix = vehicleprefix;
	}
	
}
