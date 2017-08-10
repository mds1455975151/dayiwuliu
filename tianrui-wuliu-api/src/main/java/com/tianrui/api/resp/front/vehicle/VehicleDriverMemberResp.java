package com.tianrui.api.resp.front.vehicle;

import com.tianrui.api.resp.BaseResp;

public class VehicleDriverMemberResp extends BaseResp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6681360513035271574L;
	
	/** 主键 */
	private String id;
	private String ids;
	private String vehicleprefix;
	/** 司机主键 */
	private String driverId;
	/** 车辆主键 */
	private String vehicleId;
	/** 车牌号 */
	private String vehicleNo;
	/** 车型 */
	private String vehicleTypeName;
	/** 车辆备注名 */
	private String vehicleRemark;
	/** 司机姓名 */
	private String driverName;
	/** 司机电话 */
	private String driverTel;
	/** 司机备注名 */
	private String driverRemark;
	/** 创建人 */
	private String creator;
	/** 创建时间 */
	private String createTime;
	private String aldriverid;
	private String desc1;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getVehicleTypeName() {
		return vehicleTypeName;
	}
	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}
	public String getVehicleRemark() {
		return vehicleRemark;
	}
	public void setVehicleRemark(String vehicleRemark) {
		this.vehicleRemark = vehicleRemark;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverTel() {
		return driverTel;
	}
	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}
	public String getDriverRemark() {
		return driverRemark;
	}
	public void setDriverRemark(String driverRemark) {
		this.driverRemark = driverRemark;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAldriverid() {
		return aldriverid;
	}
	public void setAldriverid(String aldriverid) {
		this.aldriverid = aldriverid;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getVehicleprefix() {
		return vehicleprefix;
	}
	public void setVehicleprefix(String vehicleprefix) {
		this.vehicleprefix = vehicleprefix;
	}
	
	
}
