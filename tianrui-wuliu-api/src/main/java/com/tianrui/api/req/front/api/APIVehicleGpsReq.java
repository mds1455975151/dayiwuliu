package com.tianrui.api.req.front.api;

import java.io.Serializable;

public class APIVehicleGpsReq implements Serializable {

	private static final long serialVersionUID = 8492180621540829387L;
	//查询条件
	private String vehicleNO;
	private String beginTime;
	private String endTime;
	
	//时间戳
	private String time;
	//用户私钥
	private String token;
	public String getVehicleNO() {
		return vehicleNO;
	}
	public void setVehicleNO(String vehicleNO) {
		this.vehicleNO = vehicleNO;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
	
	
}
