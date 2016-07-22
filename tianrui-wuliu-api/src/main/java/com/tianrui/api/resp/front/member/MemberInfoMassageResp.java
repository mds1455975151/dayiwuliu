package com.tianrui.api.resp.front.member;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberInfoMassageResp {

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 认证时间
	 */
	private Long submitDate;
	
	private String submitDateStr;
	/**
	 * 审核时间
	 */
	private Long auditTime;
	
	private String auditTimeStr;
	/**
	 * 审核人
	 */
	private String auditName;
	/**
	 * 审核不通过原因
	 */
	private String rejectReason;
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
	 * 身份证号
	 */
	private String identityCard;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSubmitDateStr() {
		if(submitDate!=null){
			submitDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(submitDate));
		}
		return submitDateStr;
	}
	public String getAuditTimeStr() {
		if(auditTime!=null){
			auditTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(auditTime));
		}
		return auditTimeStr;
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
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
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
