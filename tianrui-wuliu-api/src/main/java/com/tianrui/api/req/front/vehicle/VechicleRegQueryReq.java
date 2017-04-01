package com.tianrui.api.req.front.vehicle;

import com.tianrui.api.req.BaseReq;

public class VechicleRegQueryReq extends BaseReq {
	
	private static final long serialVersionUID = -7190183760043617974L;
	
	private String vehicleNo;
	private String intertype;
	
	private String vehicleOwnerTel;

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleOwnerTel() {
		return vehicleOwnerTel;
	}

	public void setVehicleOwnerTel(String vehicleOwnerTel) {
		this.vehicleOwnerTel = vehicleOwnerTel;
	}

	public String getIntertype() {
		return intertype;
	}

	public void setIntertype(String intertype) {
		this.intertype = intertype;
	}
	
	
}
