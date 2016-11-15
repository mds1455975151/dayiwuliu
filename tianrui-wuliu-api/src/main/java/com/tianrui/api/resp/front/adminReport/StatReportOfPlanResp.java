package com.tianrui.api.resp.front.adminReport;

import org.apache.commons.lang.time.DateFormatUtils;

public class StatReportOfPlanResp {

	private String id;
	private String planCode;
	private String orgName;
	private String ownerName;
	private String venderName;
	private String cargoName;
	private String routeName;
	private Long createtime;
	private String createtimeStr;
	private String price;
	private String priceUnits;
	private String startcity;
	private String endcity;
	private String distance;
	private String sendPerson;
	private String sendPersonPhone;
	private String receivePerson;
	private String receivePersonPhone;
	private Long starttime;
	private String starttimeStr;
	private Long endtime;
	private String endtimeStr;
	private String totalplanned;
	private String completed;
	private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getVenderName() {
		return venderName;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
		this.setCreatetimeStr(DateFormatUtils.format(createtime, "yyyy-MM-dd HH:mm:ss"));
	}
	public String getCreatetimeStr() {
		return createtimeStr;
	}
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPriceUnits() {
		return priceUnits;
	}
	public void setPriceUnits(String priceUnits) {
		this.priceUnits = priceUnits;
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
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getSendPerson() {
		return sendPerson;
	}
	public void setSendPerson(String sendPerson) {
		this.sendPerson = sendPerson;
	}
	public String getSendPersonPhone() {
		return sendPersonPhone;
	}
	public void setSendPersonPhone(String sendPersonPhone) {
		this.sendPersonPhone = sendPersonPhone;
	}
	public String getReceivePerson() {
		return receivePerson;
	}
	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}
	public String getReceivePersonPhone() {
		return receivePersonPhone;
	}
	public void setReceivePersonPhone(String receivePersonPhone) {
		this.receivePersonPhone = receivePersonPhone;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
		this.setStarttimeStr(DateFormatUtils.format(starttime, "yyyy-MM-dd HH:mm:ss"));
	}
	public String getStarttimeStr() {
		return starttimeStr;
	}
	public void setStarttimeStr(String starttimeStr) {
		this.starttimeStr = starttimeStr;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
		this.setEndtimeStr(DateFormatUtils.format(endtime, "yyyy-MM-dd HH:mm:ss"));
	}
	public String getEndtimeStr() {
		return endtimeStr;
	}
	public void setEndtimeStr(String endtimeStr) {
		this.endtimeStr = endtimeStr;
	}
	public String getTotalplanned() {
		return totalplanned;
	}
	public void setTotalplanned(String totalplanned) {
		this.totalplanned = totalplanned;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
