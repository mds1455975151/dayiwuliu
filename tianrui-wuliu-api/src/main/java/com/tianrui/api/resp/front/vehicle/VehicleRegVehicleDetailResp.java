package com.tianrui.api.resp.front.vehicle;

import org.apache.commons.lang.StringUtils;

/**
 * 我的车辆详情
 * 
 * @author Administrator
 *
 */
public class VehicleRegVehicleDetailResp {
	
	private String id;

	// 车牌号码
	private String vehicleNo;
	// 随车电话,账户
	private String vehicleMobile;
	// 认证时间
	private String authTime;
	// 车辆类型
	private String vehicleType;
	// 车长
	private String vehicleLen;
	// 载重
	private String vehicleLoad;
	// 所有人姓名
	private String vehicleOwner;
	// 联系电话
	private String vehicleOwnerTel;
	/**
	 * 认证信息
	 */
	// 认证状态 临时认证 ,完全认证中,完全认证中,开票认证中,开票认证
	private String authstatus;
	//认证类型
	private String authType;
	// 营运证号
	private String taxiLicenseNo;
	// 道路运输证号
	private String roadTransportNo;
	// 营运证图片
	private String taxiLicenseImg;
	// 车辆图片
	private String vehicleImg;
	// 行驶证
	private String drivingLicenseNo;
	// 行驶证图片
	private String drivingLicenseImg;
	// 车辆登记证
	private String vehicleGradeNo;
	// 车辆登记证图片
	private String vehicleGradeImg;
	
	/**
	 * 开票认证
	 */
	//使用性质 1-营运 2-非营运
	private String nature;
	//总质量
	private String quality;
	//证件号码
	private String idcardno;
	//登记证书编号
	private String certificateno;
	//检验有效期止
	private String expirydata;
	//车辆识别码
	private String identification;
	//发动机号
	private String motor;
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
	public String getAuthTime() {
		return authTime;
	}
	public void setAuthTime(String authTime) {
		this.authTime = authTime;
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
	public String getVehicleOwnerTel() {
		return vehicleOwnerTel;
	}
	public void setVehicleOwnerTel(String vehicleOwnerTel) {
		this.vehicleOwnerTel = vehicleOwnerTel;
	}
	public String getAuthstatus() {
		return authstatus;
	}
	public void setAuthstatus(String authstatus) {
		this.authstatus = authstatus;
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
	
	public String getAuthType() {
		return authType;
	}
	public String getAuthTypeStr() {
		String rs="";
		if( StringUtils.equals("1", this.getAuthType()) ){
			rs="临时认证";
		}else if( StringUtils.equals("2", this.getAuthType()) ){
			rs="完全认证";
		}else if(StringUtils.equals("3", this.getAuthType())){
			rs="开票认证";
		}
		return rs;
	}
	public String getAuthstatusStr(){
		String rs="";
		if( StringUtils.equals("1", this.getAuthstatus()) ){
			rs="通过";
		}else if( StringUtils.equals("2", this.getAuthstatus()) ){
			rs="中";
		}else if(StringUtils.equals("3", this.getAuthstatus())){
			rs="失败 ";
		}
		return rs;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getIdcardno() {
		return idcardno;
	}
	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}
	public String getCertificateno() {
		return certificateno;
	}
	public void setCertificateno(String certificateno) {
		this.certificateno = certificateno;
	}
	public String getExpirydata() {
		return expirydata;
	}
	public void setExpirydata(String expirydata) {
		this.expirydata = expirydata;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getMotor() {
		return motor;
	}
	public void setMotor(String motor) {
		this.motor = motor;
	}
	
	
}
