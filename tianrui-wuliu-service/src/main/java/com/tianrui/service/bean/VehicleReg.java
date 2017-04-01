package com.tianrui.service.bean;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vehicle_reg_info")
public class VehicleReg implements Serializable{

	private static final long serialVersionUID = 8379994076474746067L;
	private String id;
	
	/**
	 * 车辆信息
	 */
	//车牌号码
	private String vehicleNo;
	//随车电话,账户
	private String vehicleMobile;
	//车辆类型
	private String vehicleType;
	//车长
	private String vehicleLen;
	//载重
	private String vehicleLoad;
	//所有人姓名
	private String vehicleOwner;
	//所有人身份证号
	private String vehicleOwnerIdCard;
	//联系电话
	private String vehicleOwnerTel;
	
	
	/**
	 * 认证信息
	 */
	//认证类型   0:默认 1:完全  2:临时  3:开票
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
	
	/**
	 * 驾驶员信息
	 */
	//姓名
	private String driverName;
	//性别   1:男性 2女性
	private String driverSex;
	//身份证号
	private String driverIdCard;
	//出生日期
	private String driverBirthDate;
	//联系电话
	private String driverLinkTel;
	//身份证地址
	private String driverIdCardAddr;
	//初次领证日期
	private String driverCardFirstlicens;
	//发证机关
	private String driverCardLicenceorg;
	//驾驶证注册时间
	private String driverCardRegDate;
	//有效年限
	private String driverCardUsefullife;
	//准架车型
	private String driverCardType;
	//驾驶证图片
	private String driverCardImg;
	
	
	/**
	 * 状态信息
	 * 
	 */
	//随车账户是否注册 0:默认  1:已注册   2:未注册
	private short vehicleOwnerTelRegStatus;
	//注册步骤 0:默认 1:第一步   2:第二部   3:第三部
	private short regStepStatus;
	//审核状态  0:未审核,1:通过审核,2:审核失败  
	private short checkStatus;
	//创建时间
	private long createTime;
	//最后更新时间
	private long lastUpdateTime;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getVehicleMobile() {
		return vehicleMobile;
	}
	public void setVehicleMobile(String vehicleMobile) {
		this.vehicleMobile = vehicleMobile;
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
	public String getVehicleOwner() {
		return vehicleOwner;
	}
	public void setVehicleOwner(String vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}
	public String getVehicleOwnerIdCard() {
		return vehicleOwnerIdCard;
	}
	public void setVehicleOwnerIdCard(String vehicleOwnerIdCard) {
		this.vehicleOwnerIdCard = vehicleOwnerIdCard;
	}
	public String getVehicleOwnerTel() {
		return vehicleOwnerTel;
	}
	public void setVehicleOwnerTel(String vehicleOwnerTel) {
		this.vehicleOwnerTel = vehicleOwnerTel;
	}
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
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverSex() {
		return driverSex;
	}
	public void setDriverSex(String driverSex) {
		this.driverSex = driverSex;
	}
	public String getDriverIdCard() {
		return driverIdCard;
	}
	public void setDriverIdCard(String driverIdCard) {
		this.driverIdCard = driverIdCard;
	}
	public String getDriverBirthDate() {
		return driverBirthDate;
	}
	public void setDriverBirthDate(String driverBirthDate) {
		this.driverBirthDate = driverBirthDate;
	}
	public String getDriverLinkTel() {
		return driverLinkTel;
	}
	public void setDriverLinkTel(String driverLinkTel) {
		this.driverLinkTel = driverLinkTel;
	}
	public String getDriverIdCardAddr() {
		return driverIdCardAddr;
	}
	public void setDriverIdCardAddr(String driverIdCardAddr) {
		this.driverIdCardAddr = driverIdCardAddr;
	}
	public String getDriverCardFirstlicens() {
		return driverCardFirstlicens;
	}
	public void setDriverCardFirstlicens(String driverCardFirstlicens) {
		this.driverCardFirstlicens = driverCardFirstlicens;
	}
	public String getDriverCardLicenceorg() {
		return driverCardLicenceorg;
	}
	public void setDriverCardLicenceorg(String driverCardLicenceorg) {
		this.driverCardLicenceorg = driverCardLicenceorg;
	}
	public String getDriverCardRegDate() {
		return driverCardRegDate;
	}
	public void setDriverCardRegDate(String driverCardRegDate) {
		this.driverCardRegDate = driverCardRegDate;
	}
	public String getDriverCardUsefullife() {
		return driverCardUsefullife;
	}
	public void setDriverCardUsefullife(String driverCardUsefullife) {
		this.driverCardUsefullife = driverCardUsefullife;
	}
	public String getDriverCardType() {
		return driverCardType;
	}
	public void setDriverCardType(String driverCardType) {
		this.driverCardType = driverCardType;
	}
	public String getDriverCardImg() {
		return driverCardImg;
	}
	public void setDriverCardImg(String driverCardImg) {
		this.driverCardImg = driverCardImg;
	}
	public short getVehicleOwnerTelRegStatus() {
		return vehicleOwnerTelRegStatus;
	}
	public void setVehicleOwnerTelRegStatus(short vehicleOwnerTelRegStatus) {
		this.vehicleOwnerTelRegStatus = vehicleOwnerTelRegStatus;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public short getRegStepStatus() {
		return regStepStatus;
	}
	public void setRegStepStatus(short regStepStatus) {
		this.regStepStatus = regStepStatus;
	}
	public short getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(short checkStatus) {
		this.checkStatus = checkStatus;
	}
	

	
	
	
}
