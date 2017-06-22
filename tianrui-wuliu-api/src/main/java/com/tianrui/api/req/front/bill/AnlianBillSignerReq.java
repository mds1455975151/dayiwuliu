package com.tianrui.api.req.front.bill;

public class AnlianBillSignerReq {

	private String id;
	    
    private Double pickupweight; 
    private String pickupimgurl;
    
    private Double signweight; 
    private String signimgurl;
    //签收量
    private Double trueweight;
    private Long signtime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getPickupweight() {
		return pickupweight;
	}
	public void setPickupweight(Double pickupweight) {
		this.pickupweight = pickupweight;
	}
	public String getPickupimgurl() {
		return pickupimgurl;
	}
	public void setPickupimgurl(String pickupimgurl) {
		this.pickupimgurl = pickupimgurl;
	}
	public Double getSignweight() {
		return signweight;
	}
	public void setSignweight(Double signweight) {
		this.signweight = signweight;
	}
	public String getSignimgurl() {
		return signimgurl;
	}
	public void setSignimgurl(String signimgurl) {
		this.signimgurl = signimgurl;
	}
	public Long getSigntime() {
		return signtime;
	}
	public void setSigntime(Long signtime) {
		this.signtime = signtime;
	}
	public Double getTrueweight() {
		return trueweight;
	}
	public void setTrueweight(Double trueweight) {
		this.trueweight = trueweight;
	}
}
