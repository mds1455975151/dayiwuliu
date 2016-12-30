package com.tianrui.service.bean;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "anlian_position")
public class ApiPosition implements Serializable{

	private static final long serialVersionUID = 8379994076474746067L;
	private String id;
	private String lat;
	private String lng;
	private String trackingdate;
	private String trackingid;
	private Long createTime;
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
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
	public String getTrackingid() {
		return trackingid;
	}
	public void setTrackingid(String trackingid) {
		this.trackingid = trackingid;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
