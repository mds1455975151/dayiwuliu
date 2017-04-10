package com.tianrui.api.req.front.vehicle;

import com.tianrui.api.req.BaseReq;

public class VechicleRegVenderSaveReq extends BaseReq {
	
	private static final long serialVersionUID = -7190183760043617974L;
	
	//车辆ID
	private String vehicleId;
	//当前的登录用户id
	private String currId;
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getCurrId() {
		return currId;
	}
	public void setCurrId(String currId) {
		this.currId = currId;
	}


	
	
	

	
	
	    
}
