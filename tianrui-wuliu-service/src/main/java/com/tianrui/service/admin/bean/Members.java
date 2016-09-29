package com.tianrui.service.admin.bean;


public class Members{

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 注册手机号（登录账号1）
	 */
	private String cellPhone;
	/** 添加运力数量*/
	private String capacount;
	/** 车辆司机绑定关系数量*/
	private String vdcount;
	/** 我的车主数量*/
	private String mocount;
	/** 仅在后台会员管理使用*/
	private String remarkname;
	/**
	 * 认证信息id
	 */
	private String infoid;
	/**
	 * 组织id
	 */
	private String organizationid;
	/**
	 * 登录名称（登录账号2）
	 */
	private String loginName;
	/**
	 * 平台昵称
	 */
	private String userName;
	/**
	 * 认证手机
	 */
	private String telphone;
	/**
	 * 审核时间
	 */
	private Long auditTime;
	/**
	 * 审核人
	 */
	private String auditName;
	/**
	 * 审核不通过原因
	 */
	private String rejectReason;
	/**
     *  会员状态0-失效,1-正常
     * @mbggenerated
     */
	private String status;
	
	/**
	 * 个人用户状态：0：未认证， 1：认证通过 ，2：认证中，3：认证失败   
	 */
	private Short userpercheck;  
	/**
	 * 司机用户状态：0：未认证， 1：认证通过 ，2：认证中，3：认证失败   
	 */
	private Short driverpercheck;  
	/**
	 * 企业用户状态：0：未认证， 1：认证通过 ，2：认证中，3：认证失败   
	 */
	private Short companypercheck;  
	/**
	 * 用户来源 0:注册 1：系统导入或者内部添加 
	 */
	private Short sourceType; 
	/**
	 * 注册时间
	 */
	private Long registtime;
	/**
	 * 最后登录时间
	 */
	private Long lastTime;
	/**
	 * 认证时间
	 */
	private Long submitDate;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 身份证号
	 */
	private String identityCard;
	/**
	 * 驾驶证照片
	 */
	private String driveImagePath;
	/**
	 * 身份证照片
	 */
	private String idcardsImagePath;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 公司联系人
	 */
	private String companyContact;
	/**
	 * 营业执照号
	 */
	private String companycode;
	/**
	 * 公司地址
	 */
	private String companyAddress;
	/**
	 * 公司联系人电话
	 */
	private String contactTel;
	/**
	 * 营业照照片
	 */
	private String licenseImagePath;
	

	public String getRemarkname() {
		return remarkname;
	}

	public void setRemarkname(String remarkname) {
		this.remarkname = remarkname;
	}

	public String getLicenseImagePath() {
		return licenseImagePath;
	}

	public void setLicenseImagePath(String licenseImagePath) {
		this.licenseImagePath = licenseImagePath;
	}

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	public Long getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Long auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getInfoid() {
		return infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}
	public String getTelphone() {
		return telphone;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
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
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Short getSourceType() {
		return sourceType;
	}
	public void setSourceType(Short sourceType) {
		this.sourceType = sourceType;
	}

	public Short getUserpercheck() {
		return userpercheck;
	}

	public Long getRegisttime() {
		return registtime;
	}

	public void setRegisttime(Long registtime) {
		this.registtime = registtime;
	}

	public Long getLastTime() {
		return lastTime;
	}

	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}

	public void setUserpercheck(Short userpercheck) {
		this.userpercheck = userpercheck;
	}

	public Short getDriverpercheck() {
		return driverpercheck;
	}

	public void setDriverpercheck(Short driverpercheck) {
		this.driverpercheck = driverpercheck;
	}

	public Short getCompanypercheck() {
		return companypercheck;
	}

	public String getCapacount() {
		return capacount;
	}

	public void setCapacount(String capacount) {
		this.capacount = capacount;
	}

	public String getVdcount() {
		return vdcount;
	}

	public void setVdcount(String vdcount) {
		this.vdcount = vdcount;
	}

	public String getMocount() {
		return mocount;
	}

	public void setMocount(String mocount) {
		this.mocount = mocount;
	}

	public void setCompanypercheck(Short companypercheck) {
		this.companypercheck = companypercheck;
	}

	public Long getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Long submitDate) {
		this.submitDate = submitDate;
	}
	
}
