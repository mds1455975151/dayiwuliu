package com.tianrui.api.req.front.bill;

import com.tianrui.api.req.BaseReq;

public class WuReportReq extends BaseReq{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 5442750145458629760L;
	private String type;//收货人：1 ，发货人、2，车主、3， 司机、4
	private String receiveMemberid;//收货人id
	private String driverid;//司机id
	private String ownerid;//货主id
	private String venderid;//车主id
	private String id;//运单id
	private String waybillno;//运单号
	private String vehicleno;//车牌号
	private String routename;//路线名称
	private double trueweight;//签收重量
	private Long ownerSigntime;//签收日期
	private double amountPayable;//支付金额
	private Integer payStatus;//支付状态
	private String  planCode;//计划编号
	private String  cargoname;//货物名称
	
	private Integer start;
	
    private Integer limit;
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getVenderid() {
		return venderid;
	}
	public void setVenderid(String venderid) {
		this.venderid = venderid;
	}
	public String getReceiveMemberid() {
		return receiveMemberid;
	}
	public void setReceiveMemberid(String receiveMemberid) {
		this.receiveMemberid = receiveMemberid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWaybillno() {
		return waybillno;
	}
	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	public double getTrueweight() {
		return trueweight;
	}
	public void setTrueweight(double trueweight) {
		this.trueweight = trueweight;
	}
	public Long getOwnerSigntime() {
		return ownerSigntime;
	}
	public void setOwnerSigntime(Long ownerSigntime) {
		this.ownerSigntime = ownerSigntime;
	}
	public double getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(double amountPayable) {
		this.amountPayable = amountPayable;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	
	
}
