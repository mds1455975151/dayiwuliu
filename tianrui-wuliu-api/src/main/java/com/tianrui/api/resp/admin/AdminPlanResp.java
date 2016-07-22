package com.tianrui.api.resp.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminPlanResp {

	private String id;
	private String plancode;
	private String creatorid;
	private String creatorname;
	private Long createtime;
	private String createtimeStr;
	private String cargoname;
	private String cargoid;
	private String vehicleownerid;
	private String vehicleownername;
	private String organizationid;
	private String organizationname;
	private String startcity;
	private String endcity;
	private String measure;
	private String priceUnits;
	private String freightname;
	private Double distance;
	private Double totalplanned;
	private Double planprice;
	private String linkman;
	private String telephone;
	private Long starttime;
	private String starttimeStr;
	private Long endtime;
	private String endtimeStr;
	private String desc1;
	private String status;
	private Double price;
	public String dataStr(Long timeString){
		String rs = "";
		if(timeString != null){
			rs =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeString));
		}
		return rs;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCreatetimeStr() {
		return dataStr(createtime);
	}
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}
	public String getStarttimeStr() {
		return dataStr(starttime);
	}
	public void setStarttimeStr(String starttimeStr) {
		this.starttimeStr = starttimeStr;
	}
	public String getEndtimeStr() {
		return dataStr(endtime);
	}
	public void setEndtimeStr(String endtimeStr) {
		this.endtimeStr = endtimeStr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPlancode() {
		return plancode;
	}
	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}
	public String getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}
	public String getCreatorname() {
		return creatorname;
	}
	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	public String getCargoid() {
		return cargoid;
	}
	public void setCargoid(String cargoid) {
		this.cargoid = cargoid;
	}
	public String getVehicleownerid() {
		return vehicleownerid;
	}
	public void setVehicleownerid(String vehicleownerid) {
		this.vehicleownerid = vehicleownerid;
	}
	public String getVehicleownername() {
		return vehicleownername;
	}
	public void setVehicleownername(String vehicleownername) {
		this.vehicleownername = vehicleownername;
	}
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}
	public String getOrganizationname() {
		return organizationname;
	}
	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
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
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getPriceUnits() {
		return priceUnits;
	}
	public void setPriceUnits(String priceUnits) {
		this.priceUnits = priceUnits;
	}
	public String getFreightname() {
		return freightname;
	}
	public void setFreightname(String freightname) {
		this.freightname = freightname;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Double getTotalplanned() {
		return totalplanned;
	}
	public void setTotalplanned(Double totalplanned) {
		this.totalplanned = totalplanned;
	}
	public Double getPlanprice() {
		return planprice;
	}
	public void setPlanprice(Double planprice) {
		this.planprice = planprice;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	
}
