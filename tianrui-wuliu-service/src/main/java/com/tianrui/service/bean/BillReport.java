package com.tianrui.service.bean;

import java.io.Serializable;
/**
 * @author zhanggaohao
 * @createtime 2016年10月18日 下午4:50:27
 * @classname 运单报表
 */
public class BillReport implements Serializable {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 2570597644491069905L;
	//视图运单id
	private String id;
	//司机id
	private String driverid;
	//车主id
	private String venderid;
	//货主id
	private String ownerid;
	//运单生成时间
	private Long billcreatetime;
	//计划生成时间
	private Long plancreatetime;
	//计划开始时间
	private Long planstarttime;
	//计划结束时间
	private Long planendtime;
	//运单单号
	private String waybillno;
	//计划编号
	private String planCode;
	//货物名称
	private String cargoname;
	//运价策略id
	private String freightid;
	//路线名称
	private String routename;
	//起运地
	private String startcity;
	//目的地
	private String endcity;
	//预提量
	private Double weight;
	//签收量
	private Double trueweight;
	//计划总运输量
	private Double totalplanned;
	//运单状态
	private String billStatus;
	//支付状态
	private String payStatus;
	//是否为委派运单（0：否，1：是）
	private String isAppoint;
	//车牌号
	private String vehicleno;
	//是否已结算（0：未结算，1：已结算）
	private String isClearing;
	//含税单价
	private Double price;
	//税率
	private Double tallage;
	//卸货完成时间
	private Long unloadtime;
	//司机名称
	private String drivername;
	//车主名称
	private String vendername;
	//计划路径（发起人id逗号分割，层及排序）
	private String pathID;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getVenderid() {
		return venderid;
	}
	public void setVenderid(String venderid) {
		this.venderid = venderid;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public Long getBillcreatetime() {
		return billcreatetime;
	}
	public void setBillcreatetime(Long billcreatetime) {
		this.billcreatetime = billcreatetime;
	}
	public Long getPlancreatetime() {
		return plancreatetime;
	}
	public void setPlancreatetime(Long plancreatetime) {
		this.plancreatetime = plancreatetime;
	}
	public Long getPlanstarttime() {
		return planstarttime;
	}
	public void setPlanstarttime(Long planstarttime) {
		this.planstarttime = planstarttime;
	}
	public Long getPlanendtime() {
		return planendtime;
	}
	public void setPlanendtime(Long planendtime) {
		this.planendtime = planendtime;
	}
	public String getWaybillno() {
		return waybillno;
	}
	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
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
	public String getFreightid() {
		return freightid;
	}
	public void setFreightid(String freightid) {
		this.freightid = freightid;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	public String getStartcity() {
		return startcity;
	}
	public void setStartcity(String startcity) {
		this.startcity = startcity;
	}
	public String getEndcity() {
		return endcity;
	}
	public void setEndcity(String endcity) {
		this.endcity = endcity;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getTrueweight() {
		return trueweight;
	}
	public void setTrueweight(Double trueweight) {
		this.trueweight = trueweight;
	}
	public Double getTotalplanned() {
		return totalplanned;
	}
	public void setTotalplanned(Double totalplanned) {
		this.totalplanned = totalplanned;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getIsAppoint() {
		return isAppoint;
	}
	public void setIsAppoint(String isAppoint) {
		this.isAppoint = isAppoint;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getIsClearing() {
		return isClearing;
	}
	public void setIsClearing(String isClearing) {
		this.isClearing = isClearing;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTallage() {
		return tallage;
	}
	public void setTallage(Double tallage) {
		this.tallage = tallage;
	}
	public Long getUnloadtime() {
		return unloadtime;
	}
	public void setUnloadtime(Long unloadtime) {
		this.unloadtime = unloadtime;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getVendername() {
		return vendername;
	}
	public void setVendername(String vendername) {
		this.vendername = vendername;
	}
	public String getPathID() {
		return pathID;
	}
	public void setPathID(String pathID) {
		this.pathID = pathID;
	}
	
}