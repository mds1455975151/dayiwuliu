package com.tianrui.api.req.front.vehicle;

import com.tianrui.api.req.BaseReq;

public class AddVehicleBankCardReq extends BaseReq{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -780216028598796363L;
	private String id;
	private String driverid;
	private String vehicleownerid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getVehicleownerid() {
		return vehicleownerid;
	}
	public void setVehicleownerid(String vehicleownerid) {
		this.vehicleownerid = vehicleownerid;
	}
	
	

}
