package com.tianrui.api.resp.front.adminReport;

import org.apache.commons.lang.time.DateFormatUtils;

public class StatReportOfBillResp {

	private String id;
	private String planid;
	private String planCode;
	private String billCode;
	private String orgName;
	private String ownerName;
	private String venderName;
	private String driverName;
	private String drivertel;
	private String vehicleno;
	private String vehicletypeName;
	private String cargoName;
	private String routeName;
	private String price;
	private String priceUnits;
	private String measunit;
	private String startcity;
	private String endcity;
	private String distance;
	private String consignorname;
	private String consignortel;
	private String receivername;
	private String receivertel;
	private Long starttime;
	private String starttimeStr;
	private Long endtime;
	private String endtimeStr;
	private String weight;
	private String trueweight;
	private String pickupimgurl;
	private String pickupweight;
	private String signimgurl;
	private String signweight;
	private Long createtime;
	private String createtimeStr;
	private String isAppoint;
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlanid() {
		return planid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
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
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDrivertel() {
		return drivertel;
	}
	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getVehicletypeName() {
		return vehicletypeName;
	}
	public void setVehicletypeName(String vehicletypeName) {
		this.vehicletypeName = vehicletypeName;
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
	public String getMeasunit() {
		return measunit;
	}
	public void setMeasunit(String measunit) {
		this.measunit = measunit;
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
	public String getConsignorname() {
		return consignorname;
	}
	public void setConsignorname(String consignorname) {
		this.consignorname = consignorname;
	}
	public String getConsignortel() {
		return consignortel;
	}
	public void setConsignortel(String consignortel) {
		this.consignortel = consignortel;
	}
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}
	public String getReceivertel() {
		return receivertel;
	}
	public void setReceivertel(String receivertel) {
		this.receivertel = receivertel;
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getTrueweight() {
		return trueweight;
	}
	public void setTrueweight(String trueweight) {
		this.trueweight = trueweight;
	}
	public String getPickupimgurl() {
		return pickupimgurl;
	}
	public void setPickupimgurl(String pickupimgurl) {
		this.pickupimgurl = pickupimgurl;
	}
	public String getPickupweight() {
		return pickupweight;
	}
	public void setPickupweight(String pickupweight) {
		this.pickupweight = pickupweight;
	}
	public String getSignimgurl() {
		return signimgurl;
	}
	public void setSignimgurl(String signimgurl) {
		this.signimgurl = signimgurl;
	}
	public String getSignweight() {
		return signweight;
	}
	public void setSignweight(String signweight) {
		this.signweight = signweight;
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
	public String getIsAppoint() {
		return isAppoint;
	}
	public void setIsAppoint(String isAppoint) {
		this.isAppoint = isAppoint;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
