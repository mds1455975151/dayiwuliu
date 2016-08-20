package com.tianrui.api.resp.front.bill;

//运单车辆信息
public class BillVehicleResp {

	private String id;
	/** 司机姓名 */
	private String driverName;
	/** 司机电话 */
	private String driverTel;
	/** 车辆类型 */
	private String vehicleTypeName;
	/** 载重 */
	private String vehiweight;
	/** 车辆状态 */
	private String billstatus;
	/** 剩余趟数 */
	private String overnumber;
	
	private String vehicleNo;
	//1 该车辆已被实用 0该车辆未被实用
	private int isUsed =0;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverTel() {
		return driverTel;
	}
	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public int getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	public String getVehicleTypeName() {
		return vehicleTypeName;
	}
	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}
	public String getVehiweight() {
		return vehiweight;
	}
	public void setVehiweight(String vehiweight) {
		this.vehiweight = vehiweight;
	}
	public String getBillstatus() {
		return billstatus;
	}
	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}
	public String getOvernumber() {
		return overnumber;
	}
	public void setOvernumber(String overnumber) {
		this.overnumber = overnumber;
	}
	
}
