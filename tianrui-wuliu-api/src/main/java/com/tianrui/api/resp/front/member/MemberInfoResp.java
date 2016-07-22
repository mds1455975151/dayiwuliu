package com.tianrui.api.resp.front.member;


import com.tianrui.api.resp.BaseResp;


public class MemberInfoResp extends BaseResp{

	private static final long serialVersionUID = 7072711650223001052L;
	
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 会员名/平台昵称
	 */
	private String memberName;
	/**
	 * 审核人id
	 */
	private String auditId;
	/**
	 * 审核人姓名
	 */
	private String auditName;
	/**
	 * 会员表主键
	 */
	private String memberId;
	/**
	 * 登陆账号
	 */
	private String cellPhone;
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
	/**
	 * 提交认证时间
	 */
	private Long submitDate;
	/**
	 * 审核时间
	 */
	private Long auditTime;
	/**
	 * 注册时间
	 */
	private Long registTime;
	/**
	 * 会员状态
	 */
	private String perCheckStatus;
	
	public String getPerCheckStatus() {
		return perCheckStatus;
	}
	public void setPerCheckStatus(String perCheckStatus) {
		this.perCheckStatus = perCheckStatus;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	public String getMemberId() {
		return memberId;
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
	public Long getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Long submitDate) {
		this.submitDate = submitDate;
	}
	public Long getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Long auditTime) {
		this.auditTime = auditTime;
	}
	public Long getRegistTime() {
		return registTime;
	}
	public void setRegistTime(Long registTime) {
		this.registTime = registTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
