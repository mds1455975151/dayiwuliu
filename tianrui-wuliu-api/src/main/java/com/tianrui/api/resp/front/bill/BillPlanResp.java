package com.tianrui.api.resp.front.bill;

import com.tianrui.api.resp.BaseResp;

/**
 * 
  * @ClassName: BillPlanResp 
  * @Description: 运单里面包含的计划信息 只为了发布计划页面展现时实用
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月7日 下午3:34:43 
  *
 */
public class BillPlanResp extends BaseResp{

	
	   
	private static final long serialVersionUID = 1L;

	
	private String planId;
	/**
     * 起始地
     */
    private String startcity;

    /**
     *  目的地
     */
    private String endcity;
    /**
     *  货物名称
     */
    private String cargoname;
    
    /**
     * 距离
     */
    private Double distance;
    
    /**
     * 计量单位
     */
    private String measure;
    /**
     * 计价单位
     */
    private String priceunits;
    
    private String sendpersion;
    private String sendpersionphone;
    private String receivepersion;
    private String receivepersionphone;
    
    private String startTime;
    private String endeTime;
    
    private String price;
    
    private String vender;
    private Double tallage;
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
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getPriceunits() {
		return priceunits;
	}
	public void setPriceunits(String priceunits) {
		this.priceunits = priceunits;
	}
	public String getSendpersion() {
		return sendpersion;
	}
	public void setSendpersion(String sendpersion) {
		this.sendpersion = sendpersion;
	}
	public String getSendpersionphone() {
		return sendpersionphone;
	}
	public void setSendpersionphone(String sendpersionphone) {
		this.sendpersionphone = sendpersionphone;
	}
	public String getReceivepersion() {
		return receivepersion;
	}
	public void setReceivepersion(String receivepersion) {
		this.receivepersion = receivepersion;
	}
	public String getReceivepersionphone() {
		return receivepersionphone;
	}
	public void setReceivepersionphone(String receivepersionphone) {
		this.receivepersionphone = receivepersionphone;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndeTime() {
		return endeTime;
	}
	public void setEndeTime(String endeTime) {
		this.endeTime = endeTime;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getVender() {
		return vender;
	}
	public void setVender(String vender) {
		this.vender = vender;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Double getTallage() {
		return tallage;
	}
	public void setTallage(Double tallage) {
		this.tallage = tallage;
	}
    
}
