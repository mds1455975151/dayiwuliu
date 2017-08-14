package com.tianrui.api.resp.front.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

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
	/** 微信唯一标识*/
	private String openid;
	/** 准驾车型*/
	private String licenseType;
	
	private String capacount;
	/** 车辆司机绑定关系数量*/
	private String vdcount;
	/** 我的车主数量*/
	private String mocount;
	/** 尽后台会员管理使用*/
	private String remarkname;
	/**身份证正面*/
    private String positive;
    /** 身份证反面*/
    private String opposite;
	
	private String aldriverid;
	
	private String rtblimgurl;
	
	private String rtblno;
	
	private String sex;
    private String birthday;
    private String firstlicens;
    private String licenceorg;
    private String starttime;
    private String usefullife;
    private String idcardaddress;
    //审核人
    private String auditName;
    //审核时间
    private String auditTimeStr;
    //审核时间
    private Long auditTime;
    //审核失败原因
    private String rejectReason;
	
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
	//推送状态（0：未推送，1：推送中，2：已推送）
    private Integer pushStatus;
    //NC推送返回状态（1：供应商不存在，2：未审核，3：审核未通过，4：审核中，5：审核通过，但组织未分配，6：审核通过，且组织已分配）
    private Integer ncStatus;
	
	public Integer getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}

	public Integer getNcStatus() {
		return ncStatus;
	}

	public void setNcStatus(Integer ncStatus) {
		this.ncStatus = ncStatus;
	}

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

	public String getRemarkname() {
		return remarkname;
	}

	public void setRemarkname(String remarkname) {
		this.remarkname = remarkname;
	}

	public String getCompanycode() {
		return companycode;
	}

	public Long getVehicleOwnerState() {
		return vehicleOwnerState;
	}

	public String getAldriverid() {
		return aldriverid;
	}

	public void setAldriverid(String aldriverid) {
		this.aldriverid = aldriverid;
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

	public String getCapacount() {
		return capacount;
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

	public void setLastTimeStr(String lastTimeStr) {
		this.lastTimeStr = lastTimeStr;
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

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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
	public String getRtblimgurl() {
		rtblimgurl = StringUtils.isBlank(rtblimgurl)?"":rtblimgurl;
		return rtblimgurl;
	}

	public void setRtblimgurl(String rtblimgurl) {
		this.rtblimgurl = rtblimgurl;
	}

	public String getRtblno() {
		rtblno = StringUtils.isBlank(rtblno)?"":rtblno;
		return rtblno;
	}

	public void setRtblno(String rtblno) {
		this.rtblno = rtblno;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getDriveImagePath() {
		driveImagePath = StringUtils.isBlank(driveImagePath)?"":driveImagePath;
		return driveImagePath;
	}
	public void setDriveImagePath(String driveImagePath) {
		this.driveImagePath = driveImagePath;
	}
	public String getIdentityCard() {
		identityCard = StringUtils.isBlank(identityCard)?"":identityCard.toUpperCase();
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

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getAuditTimeStr() {
		if(auditTime != null){
			auditTimeStr =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(auditTime));
		}
		return auditTimeStr;
	}

	public Long getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Long auditTime) {
		this.auditTime = auditTime;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	
	
	

	
}
