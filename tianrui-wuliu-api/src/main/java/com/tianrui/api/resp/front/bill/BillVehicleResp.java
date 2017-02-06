package com.tianrui.api.resp.front.bill;

import org.apache.commons.lang.StringUtils;

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
	/** 总趟数 */
	private String totalnumber;
	/** 剩余趟数 */
	private String overnumber;
	
	private String vehicleNo;
	/** 开票认证状态*/
	private String desc1;
	/** 司机认证*/
	private String aldriverid;
	
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
	public String getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(String totalnumber) {
		this.totalnumber = totalnumber;
	}
	public String getDesc1() {
		desc1 = StringUtils.isBlank(desc1)?"":desc1;
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getAldriverid() {
		aldriverid = StringUtils.isBlank(aldriverid)?"":aldriverid;
		return aldriverid;
	}
	public void setAldriverid(String aldriverid) {
		this.aldriverid = aldriverid;
	}
	
}
