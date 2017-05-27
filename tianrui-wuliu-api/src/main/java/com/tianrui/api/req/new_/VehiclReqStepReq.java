package com.tianrui.api.req.new_;

public class VehiclReqStepReq {

	private String id;
	//创建者id
	private String createrId;
	
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
	//宽
	private String vehicleWide;
	//高
	private String vehicleHigh;
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
	//认证类型   0:默认1:临时 2:完全    3:开票
	private short authType;
	//经营许可证号
	private String taxiLicenseNo;
	//道路运输证号
	private String roadTransportNo;
	//经营许可证证图片
	private String taxiLicenseImg;
	//经营许可证有效期
	private String taxiLicenseTerm;
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
	//性别   xy:男性 xx女性
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
	//司机省份证正面
	private String driverIdCard_A;
	//司机身份证反面
	private String driverIdCard_B;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreaterId() {
		return createrId;
	}
	public void setCreaterId(String createrId) {
		this.createrId = createrId;
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
	public String getVehicleWide() {
		return vehicleWide;
	}
	public void setVehicleWide(String vehicleWide) {
		this.vehicleWide = vehicleWide;
	}
	public String getVehicleHigh() {
		return vehicleHigh;
	}
	public void setVehicleHigh(String vehicleHigh) {
		this.vehicleHigh = vehicleHigh;
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
	public String getTaxiLicenseTerm() {
		return taxiLicenseTerm;
	}
	public void setTaxiLicenseTerm(String taxiLicenseTerm) {
		this.taxiLicenseTerm = taxiLicenseTerm;
	}
	public String getDriverIdCard_A() {
		return driverIdCard_A;
	}
	public void setDriverIdCard_A(String driverIdCard_A) {
		this.driverIdCard_A = driverIdCard_A;
	}
	public String getDriverIdCard_B() {
		return driverIdCard_B;
	}
	public void setDriverIdCard_B(String driverIdCard_B) {
		this.driverIdCard_B = driverIdCard_B;
	}
	
}
