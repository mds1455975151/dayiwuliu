package com.tianrui.api.resp.front.vehicle;

import com.tianrui.api.resp.BaseResp;

/**
 * 车辆司机关系对应响应类
 * <p>
 * @author guyuke
 * @time 2016年6月6日 上午9:07:42
 */
public class VehicleDriverResp extends BaseResp{

	private static final long serialVersionUID = -7190183760043617974L;
	
	/** 主键 */
	private String id;
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
	/**
	 * 获取主键
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置主键
	 * @param newId
	 */
	public void setId(String newId) {
		this.id = newId;
	}
	
	/**
	 * 获取司机主键
	 * @return driverId
	 */
	public String getDriverId() {
		return driverId;
	}

	/**
	 * 设置司机主键
	 * @param newDriverId
	 */
	public void setDriverId(String newDriverId) {
		this.driverId = newDriverId;
	}

	/**
	 * 获取车辆主键
	 * @return vehicleId
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * 设置车辆主键
	 * @param newVehicleId
	 */
	public void setVehicleId(String newVehicleId) {
		this.vehicleId = newVehicleId;
	}

	/**
	 * 获取车牌号
	 * @return vehicleNo
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}

	/**
	 * 设置车牌号
	 * @param newVehicleNo
	 */
	public void setVehicleNo(String newVehicleNo) {
		this.vehicleNo = newVehicleNo;
	}

	/**
	 * 获取车型
	 * @return vehicleTypeName
	 */
	public String getVehicleTypeName() {
		return vehicleTypeName;
	}

	/**
	 * 设置车型
	 * @param newVehicleTypeName
	 */
	public void setVehicleTypeName(String newVehicleTypeName) {
		this.vehicleTypeName = newVehicleTypeName;
	}

	/**
	 * 获取车辆备注名
	 * @return vehicleRemark
	 */
	public String getVehicleRemark() {
		return vehicleRemark;
	}

	/**
	 * 设置车辆备注名
	 * @param newVehicleRemark
	 */
	public void setVehicleRemark(String newVehicleRemark) {
		this.vehicleRemark = newVehicleRemark;
	}

	/**
	 * 获取司机姓名
	 * @return driverName
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * 设置司机姓名
	 * @param newDriverName
	 */
	public void setDriverName(String newDriverName) {
		this.driverName = newDriverName;
	}

	/**
	 * 获取司机电话
	 * @return driverTel
	 */
	public String getDriverTel() {
		return driverTel;
	}

	/**
	 * 设置司机电话
	 * @param newDriverTel
	 */
	public void setDriverTel(String newDriverTel) {
		this.driverTel = newDriverTel;
	}

	/**
	 * 获取司机备注名
	 * @return driverRemark
	 */
	public String getDriverRemark() {
		return driverRemark;
	}

	/**
	 * 设置司机备注名
	 * @param newDriverRemark
	 */
	public void setDriverRemark(String newDriverRemark) {
		this.driverRemark = newDriverRemark;
	}

	/**
	 * 获取创建人
	 * @return creator
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * 设置创建人
	 * @param newCreator
	 */
	public void setCreator(String newCreator) {
		this.creator = newCreator;
	}
	
	/**
	 * 获取创建时间
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置创建时间
	 * @param newCreateTime
	 */
	public void setCreateTime(String newCreateTime) {
		this.createTime = newCreateTime;
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
	
	
}
