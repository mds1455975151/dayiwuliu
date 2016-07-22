package com.tianrui.api.resp.front.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.resp.BaseResp;

public class MemberResp extends BaseResp{

	private static final long serialVersionUID = 7072711650223001052L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 注册手机号（登录账号1）
	 */
	private String cellPhone;
	/**
	 * 组织id
	 */
	private String organizationid;
	/**
	 * 组织状态
	 */
	private String orgStatus;
	/**
	 * 备用字段
	 */
	private String loginName;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 认证名称
	 */
	private String userName;
	/**
	 * 认证手机
	 */
	private String telphone;
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
//	/**
//	 * 用户状态：0：未认证， 1：认证通过 ，2：认证中，3：认证失败   
//	 */
//	private Short perCheckStatus;  
	/**
	 * 用户来源 0:注册 1：系统导入或者内部添加 
	 */
	private Short sourceType; 
	/**
	 * 注册时间
	 */
	private Long registtime;
	/**
	 * 注册时间
	 */
	private String registtimeStr;
	/**
	 * 最后登录时间
	 */
	private Long lastTime;
	/**
	 * 最后登录时间
	 */
	private String lastTimeStr;
	/**
	 * 密码
	 */
	private String passWord;
	/**
	 * 认证时间
	 */
	private Long submitDate;
	/**
	 * 认证时间
	 */
	private String submitDateStr;
	/**
	 * 头像路径
	 */
	private String avatarspath;
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
	 * 公司联系人电话
	 */
	private String contactTel;
	/**
	 * 营业执照号
	 */
	private String companycode;
	/**
	 * 营业照照片
	 */
	private String licenseImagePath;
	/**
	 * 是否为车主 0-不是车主
	 */
	private Long vehicleOwnerState;
	
	public String getTelphone() {
		return telphone;
	}
	
	public String getRegisttimeStr() {
		if(registtime != null){
			registtimeStr =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(registtime));
		}
		return registtimeStr;
	}

	public String getNickname() {
		return nickname;
	}

	public String getCompanycode() {
		return companycode;
	}

	public Long getVehicleOwnerState() {
		return vehicleOwnerState;
	}

	public String getOrgStatus() {
		return orgStatus;
	}

	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}

	public void setVehicleOwnerState(Long vehicleOwnerState) {
		this.vehicleOwnerState = vehicleOwnerState;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}

	public String getLastTimeStr() {
		if(lastTime != null){
			lastTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(lastTime));
		}
		return lastTimeStr;
	}

	public String getSubmitDateStr() {
		if(submitDate != null){
			submitDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(submitDate));
		}
		return submitDateStr;
	}

	public String getLicenseImagePath() {
		return licenseImagePath;
	}
	public void setLicenseImagePath(String licenseImagePath) {
		this.licenseImagePath = licenseImagePath;
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
	public String getCompanyName() {
		return companyName;
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
	public Long getRegisttime() {
		return registtime;
	}
	public void setRegisttime(Long registtime) {
		this.registtime = registtime;
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
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getAvatarspath() {
		return avatarspath;
	}
	public void setAvatarspath(String avatarspath) {
		this.avatarspath = avatarspath;
	}
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}
	public Long getLastTime() {
		return lastTime;
	}
	public void setLastTime(Long lastTime) {
		this.lastTime = lastTime;
	}
	public Long getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Long submitDate) {
		this.submitDate = submitDate;
	}
	public Short getUserpercheck() {
		return userpercheck;
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
	public void setCompanypercheck(Short companypercheck) {
		this.companypercheck = companypercheck;
	}
	
	
}
