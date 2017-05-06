package com.tianrui.api.resp.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class AnlianBillReportResp {

	private String id;
	
	/** 纬线 提货地计划位置*/
	private Integer tjlat;
	/** 经线 提货地计划位置*/
    private Integer tjlon;
    /** 纬线 提货地实际位置*/
	private Integer tslat;
	/** 经线 提货地实际位置*/
    private Integer tslon;
    /** 提货地点偏差距离*/
    private Double tlength;
    
    /** 纬线 到货地计划位置*/
	private Integer djlat;
	/** 经线 到货地计划位置*/
    private Integer djlon;
    /** 纬线 到货地实际位置*/
	private Integer dslat;
	/** 经线 到货地实际位置*/
    private Integer dslon;
    /** 到货地点偏差距离*/
    private Double dlength;

    private String type;

    private Long createtime;
    
    private String createtimeStr;

    private String waybillno;

    private String drivername;

    private String drivertel;

    private String vehicleno;

    private String cargoname;

    private String routeid;
    
    private Long begintime;

    private String begintimeStr;
    
    private Long unloadtime;
    
    private String unloadtimeStr;

    private String weight;

    private Double trueweight;

    private Double pickupweight;

    private Double signweight;

    private Byte status;

    private String plancode;

    private String shippermerchant;

    private String consigneemerchant;

    private String shippermerchantname;

    private String consigneemerchantname;

    private String remarkname;

    private String routename;

    private String orgid;

    private String palnid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		switch (type) {
		case "wuliu":
			type="普通运单";
			break;
		case "anlian":
			type="开票运单";
			break;
		default:
			break;
		}
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getWaybillno() {
		if(StringUtils.isBlank(waybillno)){
			waybillno = "";
		}
		return waybillno;
	}

	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}

	public String getDrivername() {
		if(StringUtils.isBlank(drivername)){
			drivername = "";
		}
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDrivertel() {
		if(StringUtils.isBlank(drivertel)){
			drivertel = "";
		}
		return drivertel;
	}

	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}

	public String getVehicleno() {
		if(StringUtils.isBlank(vehicleno)){
			vehicleno = "";
		}
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getCargoname() {
		if(StringUtils.isBlank(cargoname)){
			cargoname = "";
		}
		return cargoname;
	}

	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}

	public String getRouteid() {
		if(StringUtils.isBlank(routeid)){
			routeid = "";
		}
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public Long getUnloadtime() {
		return unloadtime;
	}

	public void setUnloadtime(Long unloadtime) {
		this.unloadtime = unloadtime;
	}

	public String getWeight() {
		if(StringUtils.isBlank(weight)){
			weight = "";
		}
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Double getTrueweight() {
		return trueweight;
	}

	public void setTrueweight(Double trueweight) {
		this.trueweight = trueweight;
	}

	public Double getPickupweight() {
		return pickupweight;
	}

	public void setPickupweight(Double pickupweight) {
		this.pickupweight = pickupweight;
	}

	public Double getSignweight() {
		return signweight;
	}

	public void setSignweight(Double signweight) {
		this.signweight = signweight;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getPlancode() {
		if(StringUtils.isBlank(plancode)){
			plancode = "";
		}
		return plancode;
	}

	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}

	public String getShippermerchant() {
		if(StringUtils.isBlank(shippermerchant)){
			shippermerchant = "";
		}
		return shippermerchant;
	}

	public void setShippermerchant(String shippermerchant) {
		this.shippermerchant = shippermerchant;
	}

	public String getConsigneemerchant() {
		if(StringUtils.isBlank(consigneemerchant)){
			consigneemerchant = "";
		}
		return consigneemerchant;
	}

	public void setConsigneemerchant(String consigneemerchant) {
		this.consigneemerchant = consigneemerchant;
	}

	public String getShippermerchantname() {
		if(StringUtils.isBlank(shippermerchantname)){
			shippermerchantname = "";
		}
		return shippermerchantname;
	}

	public void setShippermerchantname(String shippermerchantname) {
		this.shippermerchantname = shippermerchantname;
	}

	public String getConsigneemerchantname() {
		if(StringUtils.isBlank(consigneemerchantname)){
			consigneemerchantname = "";
		}
		return consigneemerchantname;
	}

	public void setConsigneemerchantname(String consigneemerchantname) {
		this.consigneemerchantname = consigneemerchantname;
	}

	public String getRemarkname() {
		if(StringUtils.isBlank(remarkname)){
			remarkname = "";
		}
		return remarkname;
	}

	public void setRemarkname(String remarkname) {
		this.remarkname = remarkname;
	}

	public String getRoutename() {
		if(StringUtils.isBlank(routename)){
			routename = "";
		}
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public String getOrgid() {
		if(StringUtils.isBlank(orgid)){
			orgid = "";
		}
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getPalnid() {
		if(StringUtils.isBlank(palnid)){
			palnid = "";
		}
		return palnid;
	}

	public void setPalnid(String palnid) {
		this.palnid = palnid;
	}

	public String getCreatetimeStr() {
		if(createtime != null){
			createtimeStr =	new SimpleDateFormat("yyyy-MM-dd").format(new Date(createtime));
		}else{
			createtimeStr = "";
		}
		return createtimeStr;
	}

	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}

	public String getUnloadtimeStr() {
		if(unloadtime != null){
			unloadtimeStr =	new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(unloadtime));
		}else{
			unloadtimeStr = "";
		}
		return unloadtimeStr;
	}

	public void setUnloadtimeStr(String unloadtimeStr) {
		this.unloadtimeStr = unloadtimeStr;
	}

	public Long getBegintime() {
		return begintime;
	}

	public void setBegintime(Long begintime) {
		this.begintime = begintime;
	}

	public String getBegintimeStr() {
		if(begintime != null){
			begintimeStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(begintime));
		}else{
			begintimeStr = "";
		}
		return begintimeStr;
	}

	public void setBegintimeStr(String begintimeStr) {
		this.begintimeStr = begintimeStr;
	}

	public Integer getTjlat() {
		return tjlat;
	}

	public void setTjlat(Integer tjlat) {
		this.tjlat = tjlat;
	}

	public Integer getTjlon() {
		return tjlon;
	}

	public void setTjlon(Integer tjlon) {
		this.tjlon = tjlon;
	}

	public Integer getTslat() {
		return tslat;
	}

	public void setTslat(Integer tslat) {
		this.tslat = tslat;
	}

	public Integer getTslon() {
		return tslon;
	}

	public void setTslon(Integer tslon) {
		this.tslon = tslon;
	}

	public Integer getDjlat() {
		return djlat;
	}

	public void setDjlat(Integer djlat) {
		this.djlat = djlat;
	}

	public Integer getDjlon() {
		return djlon;
	}

	public void setDjlon(Integer djlon) {
		this.djlon = djlon;
	}

	public Integer getDslat() {
		return dslat;
	}

	public void setDslat(Integer dslat) {
		this.dslat = dslat;
	}

	public Integer getDslon() {
		return dslon;
	}

	public void setDslon(Integer dslon) {
		this.dslon = dslon;
	}

	
	public Double getTlength() {
		if(tslon!=null&&tjlon!=null&&tslat!=null&&tjlat!=null){
			tlength = getDistance(tslon*Math.pow(10,-6),tslat*Math.pow(10,-6),tjlon*Math.pow(10,-6),tjlat*Math.pow(10,-6));
		}
		return tlength;
	}

	public void setTlength(Double tlength) {
		this.tlength = tlength;
	}

	public Double getDlength() {
		if(dslon!=null&&djlon!=null&&dslat!=null&&djlat!=null){
			dlength = getDistance(dslon*Math.pow(10,-6),dslat*Math.pow(10,-6),djlon*Math.pow(10,-6),djlat*Math.pow(10,-6));
		}
		return dlength;
	}

	public void setDlength(Double dlength) {
		this.dlength = dlength;
	}

	public static double getDistance(double lng1, double lat1, double lng2, double lat2){  
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
       double a = radLat1 - radLat2;  
       double b = rad(lng1) - rad(lng2);  
       double s = 2 * Math.asin(  
            Math.sqrt(  
                Math.pow(Math.sin(a/2),2)   
                + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)  
            )  
        );  
       s = s * 6378137;  
       s = Math.round(s * 10000) / 10000;  
       return s;  
	 } 

	private static double rad(double d){  
	       return d * Math.PI / 180.0;  
	}
}