package com.tianrui.api.req.front.member;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 认证
 * @author jh
 *
 */
public class MemberAuthenReq {
	
	private String id;
	private String userName;
	private String identityCard;
	private String telphone;
	private String licenseType;
	private String type;//2-驾驶证；1-身份证
	private String rtblno;//道路运输许可证号 
	private String file;//2-驾驶证；1-身份证 图片留
	private String rtblimg;//道路运输许可证号  图片
	
	private String sex;
    private String birthday;
    private String firstlicens;
    private String licenceorg;
    private String starttime;
    private String usefullife;
    private String idcardaddress;
    
    /**身份证正面*/
    private String positive;
    /** 身份证反面*/
    private String opposite;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRtblno() {
		return rtblno;
	}
	public void setRtblno(String rtblno) {
		this.rtblno = rtblno;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getRtblimg() {
		return rtblimg;
	}
	public void setRtblimg(String rtblimg) {
		this.rtblimg = rtblimg;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getFirstlicens() {
		return firstlicens;
	}
	public void setFirstlicens(String firstlicens) {
		this.firstlicens = firstlicens;
	}
	public String getLicenceorg() {
		return licenceorg;
	}
	public void setLicenceorg(String licenceorg) {
		this.licenceorg = licenceorg;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getUsefullife() {
		return usefullife;
	}
	public void setUsefullife(String usefullife) {
		this.usefullife = usefullife;
	}
	public String getIdcardaddress() {
		return idcardaddress;
	}
	public void setIdcardaddress(String idcardaddress) {
		this.idcardaddress = idcardaddress;
	}
	public String getPositive() {
		return positive;
	}
	public void setPositive(String positive) {
		this.positive = positive;
	}
	public String getOpposite() {
		return opposite;
	}
	public void setOpposite(String opposite) {
		this.opposite = opposite;
	}
}
