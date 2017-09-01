package com.tianrui.service.bean;

public class AddVehicleBankCard implements IModel{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5295839138645407172L;

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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
