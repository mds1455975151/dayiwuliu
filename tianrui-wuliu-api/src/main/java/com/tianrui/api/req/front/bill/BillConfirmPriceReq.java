package com.tianrui.api.req.front.bill;

public class BillConfirmPriceReq {

	private String id;
	
	private String type;
	//单价
	private Double billPrice;
	//签收重量
	private Double billTrueWeight;
	//空重 扣杂
	private Double deduct_weight_misc;
	//扣款
	private Double deduct_money;
	//其它扣款
	private Double deduct_other;
	//油卡
	private Double deduct_oil_card;
	//附件
	private String appendix;
	//创建人
	private String creater;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getDeduct_weight_misc() {
		if(deduct_weight_misc==null){
			deduct_weight_misc = (double) 0;
		}
		return deduct_weight_misc;
	}
	public void setDeduct_weight_misc(Double deduct_weight_misc) {
		this.deduct_weight_misc = deduct_weight_misc;
	}
	public Double getDeduct_money() {
		if(deduct_money == null){
			deduct_money=(double) 0;
		}
		
		return deduct_money;
	}
	public void setDeduct_money(Double deduct_money) {
		this.deduct_money = deduct_money;
	}
	public Double getDeduct_other() {
		if(deduct_other==null){
			deduct_other = (double) 0;
		}
		return deduct_other;
	}
	public void setDeduct_other(Double deduct_other) {
		this.deduct_other = deduct_other;
	}
	public Double getDeduct_oil_card() {
		if(deduct_oil_card == null){
			deduct_oil_card = (double) 0;
		}
		return deduct_oil_card;
	}
	public void setDeduct_oil_card(Double deduct_oil_card) {
		this.deduct_oil_card = deduct_oil_card;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Double getBillPrice() {
		return billPrice;
	}
	public void setBillPrice(Double billPrice) {
		this.billPrice = billPrice;
	}
	public Double getBillTrueWeight() {
		return billTrueWeight;
	}
	public void setBillTrueWeight(Double billTrueWeight) {
		this.billTrueWeight = billTrueWeight;
	}
	public String getAppendix() {
		return appendix;
	}
	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}
}
