package com.tianrui.api.req.front.vehicle;

import com.tianrui.api.req.BaseReq;

public class VechicleRegStep3Req extends BaseReq {
	
	private static final long serialVersionUID = -7190183760043617974L;
	
	private String id;
	/**
	 * 驾驶员信息
	 */
	//姓名
	private String driverName;
	//性别
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
