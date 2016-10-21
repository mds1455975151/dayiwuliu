package com.tianrui.api.resp.front.report;

import java.io.Serializable;
/**
 * @author zhanggaohao
 * @createtime 2016年10月18日 下午4:50:51
 * @classname ReportResp.java
 */
public class ReportResp implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 2570597644491069905L;
	//运单id
	private String id;
	//运单生成时间
	private Long billcreatetime;
	//运单生成时间字符串
	private String billcreatetimeStr;
	//计划生成时间
	private Long plancreatetime;
	//计划生成时间字符串
	private String plancreatetimeStr;
	//计划开始时间
	private Long planstarttime;
	//计划开始时间字符串
	private String planstarttimeStr;
	//计划结束时间
	private Long planendtime;
	//计划结束时间字符串
	private String planendtimeStr;
	//运单单号
	private String waybillno;
	//计划编号
	private String planCode;
	//货物名称
	private String cargoname;
	//路线名称
	private String routename;
	//起运地
	private String startcity;
	//目的地
	private String endcity;
	//预提量
	private Double weight;
	//签收量
	private Double trueweight;
	//计划总运输量
	private Double totalplanned;
	//运单状态
	private String billStatus;
	//支付状态
	private String payStatus;
	//是否为委派运单（0：否，1：是）
	private String isAppoint;
	//车牌号
	private String vehicleno;
	//是否已结算（0：未结算，1：已结算）
	private String isClearing;
	//含税单价
	private Double price;
	//税率
	private Double tallage;
	//卸货完成时间
	private Long unloadtime;
	//司机名称
	private String drivername;
	//车主名称
	private String vendername;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getBillcreatetime() {
		return billcreatetime;
	}
	public void setBillcreatetime(Long billcreatetime) {
		this.billcreatetime = billcreatetime;
	}
	public String getBillcreatetimeStr() {
		return billcreatetimeStr;
	}
	public void setBillcreatetimeStr(String billcreatetimeStr) {
		this.billcreatetimeStr = billcreatetimeStr;
	}
	public Long getPlancreatetime() {
		return plancreatetime;
	}
	public void setPlancreatetime(Long plancreatetime) {
		this.plancreatetime = plancreatetime;
	}
	public String getPlancreatetimeStr() {
		return plancreatetimeStr;
	}
	public void setPlancreatetimeStr(String plancreatetimeStr) {
		this.plancreatetimeStr = plancreatetimeStr;
	}
	public Long getPlanstarttime() {
		return planstarttime;
	}
	public void setPlanstarttime(Long planstarttime) {
		this.planstarttime = planstarttime;
	}
	public String getPlanstarttimeStr() {
		return planstarttimeStr;
	}
	public void setPlanstarttimeStr(String planstarttimeStr) {
		this.planstarttimeStr = planstarttimeStr;
	}
	public Long getPlanendtime() {
		return planendtime;
	}
	public void setPlanendtime(Long planendtime) {
		this.planendtime = planendtime;
	}
	public String getPlanendtimeStr() {
		return planendtimeStr;
	}
	public void setPlanendtimeStr(String planendtimeStr) {
		this.planendtimeStr = planendtimeStr;
	}
	public String getWaybillno() {
		return waybillno;
	}
	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
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
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
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
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getTrueweight() {
		return trueweight;
	}
	public void setTrueweight(Double trueweight) {
		this.trueweight = trueweight;
	}
	public Double getTotalplanned() {
		return totalplanned;
	}
	public void setTotalplanned(Double totalplanned) {
		this.totalplanned = totalplanned;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getIsAppoint() {
		return isAppoint;
	}
	public void setIsAppoint(String isAppoint) {
		this.isAppoint = isAppoint;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getIsClearing() {
		return isClearing;
	}
	public void setIsClearing(String isClearing) {
		this.isClearing = isClearing;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTallage() {
		return tallage;
	}
	public void setTallage(Double tallage) {
		this.tallage = tallage;
	}
	public Long getUnloadtime() {
		return unloadtime;
	}
	public void setUnloadtime(Long unloadtime) {
		this.unloadtime = unloadtime;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getVendername() {
		return vendername;
	}
	public void setVendername(String vendername) {
		this.vendername = vendername;
	}
	
}