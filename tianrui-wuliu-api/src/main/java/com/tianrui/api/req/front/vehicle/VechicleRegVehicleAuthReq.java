package com.tianrui.api.req.front.vehicle;

import com.tianrui.api.req.BaseReq;

public class VechicleRegVehicleAuthReq extends BaseReq {
	
	private static final long serialVersionUID = -7190183760043617974L;
	private String id ;
	//认证类型   0:默认  1:完全  2:临时  3:开票
	private short authType;
	//营运证号
	private String taxiLicenseNo;
	//道路运输证号
	private String roadTransportNo;
	//营运证图片
	private String taxiLicenseImg;
	//车辆图片
	private String vehicleImg;
	//行驶证
	private String drivingLicenseNo;
	//行驶证图片
	private String drivingLicenseImg;
	//车辆登记证
	private String vehicleGradeNo;
	//车辆登记证图片
	private String vehicleGradeImg;
	public short getAuthType() {
		return authType;
	}
	public void setAuthType(short authType) {
		this.authType = authType;
	}
	public String getTaxiLicenseNo() {
		return taxiLicenseNo;
	}
	public void setTaxiLicenseNo(String taxiLicenseNo) {
		this.taxiLicenseNo = taxiLicenseNo;
	}
	public String getRoadTransportNo() {
		return roadTransportNo;
	}
	public void setRoadTransportNo(String roadTransportNo) {
		this.roadTransportNo = roadTransportNo;
	}
	public String getTaxiLicenseImg() {
		return taxiLicenseImg;
	}
	public void setTaxiLicenseImg(String taxiLicenseImg) {
		this.taxiLicenseImg = taxiLicenseImg;
	}
	public String getVehicleImg() {
		return vehicleImg;
	}
	public void setVehicleImg(String vehicleImg) {
		this.vehicleImg = vehicleImg;
	}
	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}
	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}
	public String getDrivingLicenseImg() {
		return drivingLicenseImg;
	}
	public void setDrivingLicenseImg(String drivingLicenseImg) {
		this.drivingLicenseImg = drivingLicenseImg;
	}
	public String getVehicleGradeNo() {
		return vehicleGradeNo;
	}
	public void setVehicleGradeNo(String vehicleGradeNo) {
		this.vehicleGradeNo = vehicleGradeNo;
	}
	public String getVehicleGradeImg() {
		return vehicleGradeImg;
	}
	public void setVehicleGradeImg(String vehicleGradeImg) {
		this.vehicleGradeImg = vehicleGradeImg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	    
}
