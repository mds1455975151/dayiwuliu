package com.tianrui.api.resp.front.cargoplan;

import com.tianrui.api.resp.BaseResp;

public class FreightsResp extends BaseResp{

	private String id;
	private Double price;
	private String cargoname;
	private String cargono;
	private String dAddr;
	private String oAddr;
	private String receivePersion;
	private String sendPersion;
	private Double distance;
	private String measureName;
	private String measureCode;
	private String priceUnits;
	 /**
     * 货物id
     */
    private String cargoid;
    /**
     * 路径id
     */
    private String routeid;
    
    private String routename;
    
    /**
     * 运价名称
     */
    private String desc1;

    /**
     *	价格类型 0-自由；1-合同
     */
    private String desc2;
    
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	public String getCargoid() {
		return cargoid;
	}
	public void setCargoid(String cargoid) {
		this.cargoid = cargoid;
	}
	public String getRouteid() {
		return routeid;
	}
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	
	public String getPriceUnits() {
		return priceUnits;
	}
	public void setPriceUnits(String priceUnits) {
		this.priceUnits = priceUnits;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	public String getCargono() {
		return cargono;
	}
	public void setCargono(String cargono) {
		this.cargono = cargono;
	}
	public String getdAddr() {
		return dAddr;
	}
	public void setdAddr(String dAddr) {
		this.dAddr = dAddr;
	}
	public String getoAddr() {
		return oAddr;
	}
	public void setoAddr(String oAddr) {
		this.oAddr = oAddr;
	}
	public String getReceivePersion() {
		return receivePersion;
	}
	public void setReceivePersion(String receivePersion) {
		this.receivePersion = receivePersion;
	}
	public String getSendPersion() {
		return sendPersion;
	}
	public void setSendPersion(String sendPersion) {
		this.sendPersion = sendPersion;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getMeasureName() {
		return measureName;
	}
	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}
	public String getMeasureCode() {
		return measureCode;
	}
	public void setMeasureCode(String measureCode) {
		this.measureCode = measureCode;
	}
	
}
