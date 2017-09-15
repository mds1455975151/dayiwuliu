package com.tianrui.api.resp.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AnReportResp {
	
	private String billno;//运单号
	private String vehicleno;//车牌号
	private String routename;//路线名称
	private double trueweight;//签收重量
	private Long signtime;//签收日期
	private double amountPayable;//支付金额
	private Integer payStatus;//支付状态
	private String  planCode;//计划编号
	private String  cargoname;//货物名称
	private String startcity;//始发地
	private String endcity;//目的地
	private String signtimeStr;//签收日期(字符串格式)
	private String billType;//(1、开票运单 2、普通运单)
	private String routeid;//
	
	
	
	public String getRouteid() {
		return routeid;
	}
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getSigntimeStr() {
		if(signtime != null){
			signtimeStr =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(signtime));
		}
		return signtimeStr;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	public double getTrueweight() {
		return trueweight;
	}
	public void setTrueweight(double trueweight) {
		this.trueweight = trueweight;
	}

	public double getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}
	public Long getSigntime() {
		return signtime;
	}
	public void setSigntime(Long signtime) {
		this.signtime = signtime;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getStartcity() {
		return startcity;
	}
	public void setStartcity(String startcity) {
		this.startcity = startcity;
	}
	public String getEndcity() {
		return endcity;
	}
	public void setEndcity(String endcity) {
		this.endcity = endcity;
	}
	
	
	
}