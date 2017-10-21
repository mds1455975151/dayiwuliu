package com.tianrui.api.req.front.member;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.tianrui.api.req.BaseReq;

public class MemberFindReq extends BaseReq{

	/**
	 * 会员名
	 */
	private String userName;
	/**
	 * 公司名称
	 */
	private String companyName;
	/** 运力类型*/
	private String capaType;
	/** 审核状态 0-未审 1-已审*/
	private String percheck;
	/**
	 * 会员状态
	 */
	private String status;
	/**
	 * 注册手机号
	 */
	private String cellPhone;
	 /**
     * 联系手机(明文)
     */
    private String phone;
    /**
	 * 个人认证
	 */
	private Short userpercheck;
	/**
	 * 司机认证
	 */
	private Short driverpercheck;
	/**
	 * 企业认证
	 */
	private Short companypercheck;
	/**
	 * 用户来源
	 */
	private String sourcetype;
	/**
	 * 注册时间始
	 */
	private String registtimeForStr;
	
	private Long registtimeFor;
	/**
	 * 注册时间末
	 */
	private String registtimeEndStr;
	
	private Long registtimeEnd;
	/**
	 * 认证时间始
	 */
	private String submitdateForStr;
	
	private Long submitdateFor;
	
	/**
	 * 认证时间末
	 */
	private String submitdateEndStr;
	
	private Long submitdateEnd;
	/**
	 * 0-查询普通用户, 1-查询司机用户 ,2-查询车主用户
	 */
	private String userType;
	/**
	 * 2-个人账户， 1-企业账户
	 */
	private String personalType;
	/**
	 * NC推送状态
	 */
	private Integer ncStatus;
	private Integer limit;
	
	private String telphone;//联系方式
	private String auditName;//审核人
	private String aldriverid;//安联账号
	private String starttime;
	private String endtime;
	private Long starttimes;
	private Long endtimes;
	
	private String drivername;//司机姓名
	private String vehicleno;//车票号码
	
	
	
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getAldriverid() {
		return aldriverid;
	}
	public void setAldriverid(String aldriverid) {
		this.aldriverid = aldriverid;
	}
	public Integer getNcStatus() {
		return ncStatus;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	public void setNcStatus(Integer ncStatus) {
		this.ncStatus = ncStatus;
	}
	public Integer getLimit() {
		limit = (pageNo-1)*pageSize;
		return limit;
	}
	public Long getRegisttimeFor() throws ParseException {
		if(StringUtils.isNotBlank(registtimeForStr)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(registtimeForStr);
			registtimeFor = date.getTime();
		}
		return registtimeFor;
	}

	public Long getRegisttimeEnd() throws ParseException {
		if(StringUtils.isNotBlank(registtimeEndStr)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(registtimeEndStr);
			registtimeEnd = date.getTime();
		}
		return registtimeEnd;
	}

	public String getCapaType() {
		return capaType;
	}
	public void setCapaType(String capaType) {
		this.capaType = capaType;
	}
	public Long getSubmitdateFor() throws ParseException {
		if(StringUtils.isNotBlank(submitdateForStr)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(submitdateForStr);
			submitdateFor = date.getTime();
		}
		return submitdateFor;
	}

	public Long getSubmitdateEnd() throws ParseException {
		if(StringUtils.isNotBlank(submitdateEndStr)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(submitdateEndStr);
			submitdateEnd = date.getTime();
		}
		return submitdateEnd;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getUserType() {
		return userType;
	}
	
	public String getPersonalType() {
		return personalType;
	}
	public void setPersonalType(String personalType) {
		this.personalType = personalType;
	}
	public String getRegisttimeForStr() {
		return registtimeForStr;
	}
	public void setRegisttimeForStr(String registtimeForStr) {
		this.registtimeForStr = registtimeForStr;
	}
	public String getRegisttimeEndStr() {
		return registtimeEndStr;
	}
	public void setRegisttimeEndStr(String registtimeEndStr) {
		this.registtimeEndStr = registtimeEndStr;
	}
	public String getSubmitdateForStr() {
		return submitdateForStr;
	}
	public void setSubmitdateForStr(String submitdateForStr) {
		this.submitdateForStr = submitdateForStr;
	}
	public String getSubmitdateEndStr() {
		return submitdateEndStr;
	}
	public void setSubmitdateEndStr(String submitdateEndStr) {
		this.submitdateEndStr = submitdateEndStr;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPercheck() {
		return percheck;
	}
	public void setPercheck(String percheck) {
		this.percheck = percheck;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
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
	public String getSourcetype() {
		return sourcetype;
	}
	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	
	public Long getStarttimes() throws ParseException {
		if(starttime!=null&&starttime!=""){
			starttimes =(new SimpleDateFormat("yyyy-MM-dd").parse(starttime)).getTime();
			return starttimes;
		}
		return starttimes;
	}
	public Long getEndtimes() throws ParseException {
		if(endtime!=null&&endtime!=""){
			endtimes =(new SimpleDateFormat("yyyy-MM-dd").parse(endtime)).getTime()+24*60*60*1000;
			return endtimes;
		}
		return endtimes;
	}

	
	
}
