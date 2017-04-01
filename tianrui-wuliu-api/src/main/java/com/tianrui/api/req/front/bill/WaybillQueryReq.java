package com.tianrui.api.req.front.bill;

import com.tianrui.api.req.BaseReq;

public class WaybillQueryReq extends BaseReq{

	private static final long serialVersionUID = 1021715600158173293L;
	
	private String id;

	private String currId;
	// 类型 1为货主 2为车主  3为司机
	private int queryType;
	//关键字查询
	private String key;
	//支付类型
	private String payType;
	//发票类型
	private String payDesc1;
	
	//运单编码
	private String billNo;
	//司机名称
	private String driverName;
	//司机电话
	private String driverTel;
	
	private String createTimeMin;
	private String createTimeMax;
	//状态查询
	private String status;
	// 1:待确认   2 以拒绝   3 已收回  4 进行中 5 已完成
	private String appStatus;
	private String planId;
	private String currOrgId;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurrId() {
		return currId;
	}

	public void setCurrId(String currId) {
		this.currId = currId;
	}

	public int getQueryType() {
		return queryType;
	}

	public void setQueryType(int queryType) {
		this.queryType = queryType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverTel() {
		return driverTel;
	}

	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}

	public String getCreateTimeMin() {
		return createTimeMin;
	}

	public void setCreateTimeMin(String createTimeMin) {
		this.createTimeMin = createTimeMin;
	}

	public String getCreateTimeMax() {
		return createTimeMax;
	}

	public void setCreateTimeMax(String createTimeMax) {
		this.createTimeMax = createTimeMax;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getCurrOrgId() {
		return currOrgId;
	}

	public void setCurrOrgId(String currOrgId) {
		this.currOrgId = currOrgId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayDesc1() {
		return payDesc1;
	}

	public void setPayDesc1(String payDesc1) {
		this.payDesc1 = payDesc1;
	}

}
