package com.tianrui.api.resp.front.vehicle;

/**
 * 车辆运力列表
 * 
 * @author lixp
 *
 */
public class VehicleRegVehiceListResp {

	private String vehicleNo;
	// 车辆类型
	private String vehicleType;
	// 车长
	private String vehicleLen;
	// 载重
	private String vehicleLoad;
	// 司机
	private String driverName;
	private String driverTelphone;
	// 随车电话
	private String vehiclePhone;
	// 所有人
	private String vehicleOwner;
	// 车辆状态
	private String vehickeStatus;
	// 运力来源
	private String vehicleSource;

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleLen() {
		return vehicleLen;
	}

	public void setVehicleLen(String vehicleLen) {
		this.vehicleLen = vehicleLen;
	}

	public String getVehicleLoad() {
		return vehicleLoad;
	}

	public void setVehicleLoad(String vehicleLoad) {
		this.vehicleLoad = vehicleLoad;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverTelphone() {
		return driverTelphone;
	}

	public void setDriverTelphone(String driverTelphone) {
		this.driverTelphone = driverTelphone;
	}

	public String getVehiclePhone() {
		return vehiclePhone;
	}

	public void setVehiclePhone(String vehiclePhone) {
		this.vehiclePhone = vehiclePhone;
	}

	public String getVehicleOwner() {
		return vehicleOwner;
	}

	public void setVehicleOwner(String vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}

	public String getVehickeStatus() {
		return vehickeStatus;
	}

	public void setVehickeStatus(String vehickeStatus) {
		this.vehickeStatus = vehickeStatus;
	}

	public String getVehicleSource() {
		return vehicleSource;
	}

	public void setVehicleSource(String vehicleSource) {
		this.vehicleSource = vehicleSource;
	}

}
