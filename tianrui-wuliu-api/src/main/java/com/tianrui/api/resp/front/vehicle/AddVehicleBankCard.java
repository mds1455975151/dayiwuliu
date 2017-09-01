package com.tianrui.api.resp.front.vehicle;

import com.tianrui.api.resp.BaseResp;

public class AddVehicleBankCard extends BaseResp{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5265729846997539447L;
	private String id;
	private String driverid;
	private String vehicleownerid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
