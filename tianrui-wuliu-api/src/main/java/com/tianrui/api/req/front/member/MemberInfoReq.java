package com.tianrui.api.req.front.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.req.BaseReq;


public class MemberInfoReq extends BaseReq{
	
	private String id ;
	/**
	 * 认证编号
	 */
	private String infoCode;
	/**
	 * 会员表主键
	 */
	private String memberId;
	
	private String userpercheck;

    private String driverpercheck;

    private String companypercheck;
	/**
	 * 电话
	 */
	private String telphone;	
	/**
	 * 真实姓名
	 */
	private String userName;	
	/**
	 * 身份证号码
	 */
	private String identityCard;	
	/**
	 *身份证正面图片路径 
	 */
	private String idcardsImagePath;	
	/**
	 * 驾驶证正面图片路径
	 */
	private String driveImagePath;	
	/**
	 *企业名称 
	 */
	private String companyName;		
	/**
	 * 公司所在地
	 */
	private String companyAddress;		
	/**
	 * 公司联系人
	 */
	private String companyContact ;	
	/**
	 * 营业执照号
	 */
	private String companycode;
	/**
	 * 联系人手机号
	 */
	private String contactTel;		
	/**
	 * 营业执照正面图片路径
	 */
	private String licenseImagePath;
	
	/**
	 * 审核不通过类型
	 */
	private String rejectType; 
	/**
	 * 审核不通过具体原因
	 */
	private String rejectReason; 
	
	private String auditid;

    private String auditname;

    private Long audittime;
    
    private String rtblimgurl;
    private String rtblno;
    //准驾车型
	private String licenseType;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInfoCode() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss_");
		String dateString = formatter.format(new Date());
		long ln = System.currentTimeMillis();
		String tm = ln+"";
		infoCode = dateString+tm.substring(tm.length()-5,tm.length());
		return infoCode;
	}
	
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}
	public String getMemberId() {
		return memberId;
	}
	public String getAuditid() {
		return auditid;
	}
	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}
	public String getAuditname() {
		return auditname;
	}
	public String getUserpercheck() {
		return userpercheck;
	}
	public void setUserpercheck(String userpercheck) {
		this.userpercheck = userpercheck;
	}
	public String getDriverpercheck() {
		return driverpercheck;
	}
	public void setDriverpercheck(String driverpercheck) {
		this.driverpercheck = driverpercheck;
	}
	public String getCompanypercheck() {
		return companypercheck;
	}
	public void setCompanypercheck(String companypercheck) {
		this.companypercheck = companypercheck;
	}
	public void setAuditname(String auditname) {
		this.auditname = auditname;
	}
	public Long getAudittime() {
		return audittime;
	}
	public void setAudittime(Long audittime) {
		this.audittime = audittime;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
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
	public String getIdcardsImagePath() {
		return idcardsImagePath;
	}
	public void setIdcardsImagePath(String idcardsImagePath) {
		this.idcardsImagePath = idcardsImagePath;
	}
	public String getDriveImagePath() {
		return driveImagePath;
	}
	public void setDriveImagePath(String driveImagePath) {
		this.driveImagePath = driveImagePath;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyContact() {
		return companyContact;
	}
	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getLicenseImagePath() {
		return licenseImagePath;
	}
	public void setLicenseImagePath(String licenseImagePath) {
		this.licenseImagePath = licenseImagePath;
	}
	public String getRejectType() {
		return rejectType;
	}
	public void setRejectType(String rejectType) {
		this.rejectType = rejectType;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public String getRtblimgurl() {
		return rtblimgurl;
	}
	public void setRtblimgurl(String rtblimgurl) {
		this.rtblimgurl = rtblimgurl;
	}
	public String getRtblno() {
		return rtblno;
	}
	public void setRtblno(String rtblno) {
		this.rtblno = rtblno;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	
}
