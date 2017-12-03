package com.tianrui.api.resp.goods;

import java.util.Date;

import com.tianrui.common.constants.Constant;
import com.tianrui.common.utils.DateUtil;

public class SelectPlanGoodsResp {

	private String id;
	
	private String shipper;
	
	private String ownerName;
	
	private String ownerCellphone;

    private String routeid;

    private String measure;

    private String cargoname;

    private String priceunits;

    private String startcity;

    private String endcity;

    private Double distance;

    private Double totalplanned;

    private Double planprice;

    private Double completed;

    private Double price;

    private Long starttime;

    private Long endtime;

    private Long acceptedtime;

    private Byte status;

    private String linkman;

    private String telephone;

    private Long createtime;

    private Byte isfamily;

    private String plancode;

    private String sendperson;

    private String sendpersonphone;

    private String receiveperson;

    private String receivepersonphone;

    private String freightname;

    private String shippermerchant;

    private String consigneemerchant;

    private String receiveid;

    private String payment;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getCargoname() {
		return cargoname;
	}

	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}

	public String getPriceunits() {
		return priceunits;
	}

	public void setPriceunits(String priceunits) {
		this.priceunits = priceunits;
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

	public Double getCompleted() {
		return completed;
	}

	public void setCompleted(Double completed) {
		this.completed = completed;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	public Long getAcceptedtime() {
		return acceptedtime;
	}

	public void setAcceptedtime(Long acceptedtime) {
		this.acceptedtime = acceptedtime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
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


	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}


	public Byte getIsfamily() {
		return isfamily;
	}

	public void setIsfamily(Byte isfamily) {
		this.isfamily = isfamily;
	}

	public String getPlancode() {
		return plancode;
	}

	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}


	public String getSendperson() {
		return sendperson;
	}

	public void setSendperson(String sendperson) {
		this.sendperson = sendperson;
	}

	public String getSendpersonphone() {
		return sendpersonphone;
	}

	public void setSendpersonphone(String sendpersonphone) {
		this.sendpersonphone = sendpersonphone;
	}

	public String getReceiveperson() {
		return receiveperson;
	}

	public void setReceiveperson(String receiveperson) {
		this.receiveperson = receiveperson;
	}

	public String getReceivepersonphone() {
		return receivepersonphone;
	}

	public void setReceivepersonphone(String receivepersonphone) {
		this.receivepersonphone = receivepersonphone;
	}

	public String getFreightname() {
		return freightname;
	}

	public void setFreightname(String freightname) {
		this.freightname = freightname;
	}



	public String getShippermerchant() {
		return shippermerchant;
	}

	public void setShippermerchant(String shippermerchant) {
		this.shippermerchant = shippermerchant;
	}

	public String getConsigneemerchant() {
		return consigneemerchant;
	}

	public void setConsigneemerchant(String consigneemerchant) {
		this.consigneemerchant = consigneemerchant;
	}

	public String getReceiveid() {
		return receiveid;
	}

	public void setReceiveid(String receiveid) {
		this.receiveid = receiveid;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerCellphone() {
		return ownerCellphone;
	}

	public void setOwnerCellphone(String ownerCellphone) {
		this.ownerCellphone = ownerCellphone;
	}
	public String getStarttimeStr() {
		if(starttime !=null  ){
			return DateUtil.getDateString(new Date(starttime), "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}
	public String getEndtimeStr() {
		if(endtime !=null  ){
			return DateUtil.getDateString(new Date(endtime), "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

}
