package com.tianrui.api.req.front.api;

import java.io.Serializable;

import com.tianrui.api.req.BaseReq;

public class APIPositionReq implements Serializable {

	private static final long serialVersionUID = 8492180621540829387L;
	
	private String lat;
	private String lng;
	private String trackingdate;
	private String trackingid;
	private String time;
	
	
	private String key;
	private String md5;
	
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	
	
}
