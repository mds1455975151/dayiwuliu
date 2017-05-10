package com.tianrui.api.resp.front.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.resp.BaseResp;

public class MemberInfoRecordResp extends BaseResp{

	private String id;
	
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
    
	private String memberid;
	private String username;
	private String telphone;
	private String idcard;
	private String idcardimage;
	private Long submittime;
	private String submittimeStr;
	private String companyname;
	private String companycontact;
	private String companytel;
	private String companyAddress;
	private String companycode;
	private String licenseImagePath;
	private String cellphone;
	private String companypercheck;
	private String driverpercheck;
	private String userpercheck;
	private String status;
	private Long registtime;
	private String registtimeStr;
	private String driverimage;
    private String rtblno;
    private String rtblimgurl;
    private String licenseType;
	
	public String getDriverimage() {
		return driverimage;
	}
	public void setDriverimage(String driverimage) {
		this.driverimage = driverimage;
	}
	public String getRegisttimeStr() {
		if(registtime != null){
			registtimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(registtime));
		}
		return registtimeStr;
	}
	public Long getRegisttime() {
		return registtime;
	}
	public void setRegisttime(Long registtime) {
		this.registtime = registtime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberid() {
		return memberid;
	}
	public String getSubmittimeStr() {
		if(submittime != null){
			submittimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(submittime));
		}
		return submittimeStr;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getIdcardimage() {
		return idcardimage;
	}
	public void setIdcardimage(String idcardimage) {
		this.idcardimage = idcardimage;
	}
	public Long getSubmittime() {
		return submittime;
	}
	public void setSubmittime(Long submittime) {
		this.submittime = submittime;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanycontact() {
		return companycontact;
	}
	public void setCompanycontact(String companycontact) {
		this.companycontact = companycontact;
	}
	public String getCompanytel() {
		return companytel;
	}
	public void setCompanytel(String companytel) {
		this.companytel = companytel;
	}
	
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public void setSubmittimeStr(String submittimeStr) {
		this.submittimeStr = submittimeStr;
	}
	public void setRegisttimeStr(String registtimeStr) {
		this.registtimeStr = registtimeStr;
	}
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public String getLicenseImagePath() {
		return licenseImagePath;
	}
	public void setLicenseImagePath(String licenseImagePath) {
		this.licenseImagePath = licenseImagePath;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getCompanypercheck() {
		return companypercheck;
	}
	public void setCompanypercheck(String companypercheck) {
		this.companypercheck = companypercheck;
	}
	public String getDriverpercheck() {
		return driverpercheck;
	}
	public void setDriverpercheck(String driverpercheck) {
		this.driverpercheck = driverpercheck;
	}
	public String getUserpercheck() {
		return userpercheck;
	}
	public void setUserpercheck(String userpercheck) {
		this.userpercheck = userpercheck;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRtblno() {
		return rtblno;
	}
	public void setRtblno(String rtblno) {
		this.rtblno = rtblno;
	}
	public String getRtblimgurl() {
		return rtblimgurl;
	}
	public void setRtblimgurl(String rtblimgurl) {
		this.rtblimgurl = rtblimgurl;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
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
