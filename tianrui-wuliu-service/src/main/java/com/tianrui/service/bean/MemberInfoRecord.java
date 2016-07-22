package com.tianrui.service.bean;
/**
 * 
 * @类描述：会员认证信息
 * @创建人：lsj
 * @创建时间：2016年6月22日上午9:50:59
 *
 * @修改人：lsj
 * @修改时间：2016年6月22日上午9:50:59
 * @修改备注：
 *
 */
public class MemberInfoRecord {

	private String id;
	private String memberid;
	private String username;
	private String telphone;
	private String idcard;
	private String idcardimage;
	private Long submittime;
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
	private String driverimage;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDriverimage() {
		return driverimage;
	}
	public void setDriverimage(String driverimage) {
		this.driverimage = driverimage;
	}
	public Long getRegisttime() {
		return registtime;
	}
	public void setRegisttime(Long registtime) {
		this.registtime = registtime;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
}
