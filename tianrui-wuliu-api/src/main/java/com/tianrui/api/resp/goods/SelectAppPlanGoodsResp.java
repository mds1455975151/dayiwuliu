package com.tianrui.api.resp.goods;

import java.util.Date;

import com.tianrui.api.resp.admin.RoutePosition;
import com.tianrui.common.utils.DateUtil;

public class SelectAppPlanGoodsResp {

	private String id;
	
	private String startCity;

	private String startName;
	
	private String startLon;
	
	private String startLat;
	
	private String endCity;
	
	private String endName;
	
	private String endLon;
	
	private String endLat;

    private String measure;

    private String cargoname;

    private String priceunits;

    private Double distance;

    private Double price;

    private Long starttime;

    private Long endtime;

    private Long createtime;

    private String plancode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
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

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getPlancode() {
		return plancode;
	}

	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getStartName() {
		return startName;
	}

	public void setStartName(String startName) {
		this.startName = startName;
	}

	public String getStartLon() {
		return startLon;
	}

	public void setStartLon(String startLon) {
		this.startLon = startLon;
	}

	public String getStartLat() {
		return startLat;
	}

	public void setStartLat(String startLat) {
		this.startLat = startLat;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public String getEndName() {
		return endName;
	}

	public void setEndName(String endName) {
		this.endName = endName;
	}

	public String getEndLon() {
		return endLon;
	}

	public void setEndLon(String endLon) {
		this.endLon = endLon;
	}

	public String getEndLat() {
		return endLat;
	}

	public void setEndLat(String endLat) {
		this.endLat = endLat;
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
