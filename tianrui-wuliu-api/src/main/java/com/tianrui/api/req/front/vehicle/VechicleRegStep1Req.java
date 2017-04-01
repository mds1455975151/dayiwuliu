package com.tianrui.api.req.front.vehicle;

import com.tianrui.api.req.BaseReq;

public class VechicleRegStep1Req extends BaseReq {
	
	private static final long serialVersionUID = -7190183760043617974L;
	
	//车牌号码
	private String vehicleNo;
	//随车电话,账户
	private String vehicleMobile;
	//车辆类型
	private String vehicleType;
	//车长
	private String vehicleLen;
	//载重
	private String vehicleLoad;
	//所有人姓名
	private String vehicleOwner;
	//所有人身份证号
	private String vehicleOwnerIdCard;
	//联系电话
	private String vehicleOwnerTel;
	
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getVehicleMobile() {
		return vehicleMobile;
	}
	public void setVehicleMobile(String vehicleMobile) {
		this.vehicleMobile = vehicleMobile;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleLen() {
		return vehicleLen;
	}
	public void setVehicleLen(String vehicleLen) {
		this.vehicleLen = vehicleLen;
	}
	public String getVehicleLoad() {
		return vehicleLoad;
	}
	public void setVehicleLoad(String vehicleLoad) {
		this.vehicleLoad = vehicleLoad;
	}
	public String getVehicleOwner() {
		return vehicleOwner;
	}
	public void setVehicleOwner(String vehicleOwner) {
		this.vehicleOwner = vehicleOwner;
	}
	public String getVehicleOwnerIdCard() {
		return vehicleOwnerIdCard;
	}
	public void setVehicleOwnerIdCard(String vehicleOwnerIdCard) {
		this.vehicleOwnerIdCard = vehicleOwnerIdCard;
	}
	public String getVehicleOwnerTel() {
		return vehicleOwnerTel;
	}
	public void setVehicleOwnerTel(String vehicleOwnerTel) {
		this.vehicleOwnerTel = vehicleOwnerTel;
	}
	
	
	    
}
