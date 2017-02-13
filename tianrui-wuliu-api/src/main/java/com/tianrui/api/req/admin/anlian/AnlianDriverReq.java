package com.tianrui.api.req.admin.anlian;

/**
 * 安联 司机实体
 * @author jh
 *
 */
public class AnlianDriverReq{
	/**司机id*/
	private String driverid;
	/** 认证记录id*/
	private String recorid;
	/** 车牌号*/
	private String vehicleNo;

	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getRecorid() {
		return recorid;
	}
	public void setRecorid(String recorid) {
		this.recorid = recorid;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

}
