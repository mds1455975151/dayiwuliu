package com.tianrui.api.req.front.position;

import com.tianrui.api.req.BaseReq;

public class PositionSaveReq extends BaseReq{

	private static final long serialVersionUID = 4275930110752749399L;
	private Integer lat;
	private Integer lon;
	private String proxyGps;
	private String currId;
	public Integer getLat() {
		return lat;
	}
	public void setLat(Integer lat) {
		this.lat = lat;
	}
	public Integer getLon() {
		return lon;
	}
	public void setLon(Integer lon) {
		this.lon = lon;
	}
	public String getProxyGps() {
		return proxyGps;
	}
	public void setProxyGps(String proxyGps) {
		this.proxyGps = proxyGps;
	}
	public String getCurrId() {
		return currId;
	}
	public void setCurrId(String currId) {
		this.currId = currId;
	}
	
}
