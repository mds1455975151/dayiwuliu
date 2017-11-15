package com.tianrui.api.resp.LED;

import java.util.List;
import java.util.Map;

public class LEDRouteResp {

	private Map<String,Object>  geoCoordMap;
	
	private List<List<Map<String,Object>>>  flyData;
	
	private List<Map<String,Object>> fiy;

	public List<List<Map<String, Object>>> getFlyData() {
		return flyData;
	}
	public void setFlyData(List<List<Map<String, Object>>> flyData) {
		this.flyData = flyData;
	}
	public Map<String, Object> getGeoCoordMap() {
		return geoCoordMap;
	}
	public void setGeoCoordMap(Map<String, Object> geoCoordMap) {
		this.geoCoordMap = geoCoordMap;
	}
	public List<Map<String, Object>> getFiy() {
		return fiy;
	}
	public void setFiy(List<Map<String, Object>> fiy) {
		this.fiy = fiy;
	}
}
