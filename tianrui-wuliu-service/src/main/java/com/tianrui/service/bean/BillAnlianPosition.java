package com.tianrui.service.bean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bill_anlian_position")
public class BillAnlianPosition implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String shipmentno;
	private String trackingid;
	private String lat;
	private String lng;
	private String trackingdate;
	private String status;
	private String error;
	private Long createtime;
	
	public String getShipmentno() {
		return shipmentno;
	}

	public void setShipmentno(String shipmentno) {
		this.shipmentno = shipmentno;
	}

	public String getTrackingid() {
		return trackingid;
	}

	public void setTrackingid(String trackingid) {
		this.trackingid = trackingid;
	}

	public String getLat() {
		lat = StringUtils.isBlank(lat)?"":lat;
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		lng = StringUtils.isBlank(lng)?"":lng;
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getTrackingdate() {
		return trackingdate;
	}

	public void setTrackingdate(String trackingdate) {
		this.trackingdate = trackingdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	
	
}
