package com.tianrui.service.admin.bean;
/**合单各项费用计算*/
public class PayPriceSave {
	
    //前台运单总价
    private Double receptionBillTotalPrice;
    //前台扣重扣杂
    private Double receptionDeductWeightMisc;
    //前台扣款
    private Double receptionDeductMoney;
    //前台其他扣款
    private Double receptionDeductOther;
    //前台油卡扣款
    private Double receptionDeductOilCard;
    //后台运单总价
    private Double backstageBillTotalPrice;
    //后台扣重扣杂
    private Double backstageDeductWeightMisc;
    //后台扣款
    private Double backstageDeductMoney;
    //后台其他扣款
    private Double backstageDeductOther;
    //后台油卡扣款
    private Double backstageDeductOilCard;
	public Double getReceptionBillTotalPrice() {
		return receptionBillTotalPrice;
	}
	public void setReceptionBillTotalPrice(Double receptionBillTotalPrice) {
		this.receptionBillTotalPrice = receptionBillTotalPrice;
	}
	public Double getReceptionDeductWeightMisc() {
		return receptionDeductWeightMisc;
	}
	public void setReceptionDeductWeightMisc(Double receptionDeductWeightMisc) {
		this.receptionDeductWeightMisc = receptionDeductWeightMisc;
	}
	public Double getReceptionDeductMoney() {
		return receptionDeductMoney;
	}
	public void setReceptionDeductMoney(Double receptionDeductMoney) {
		this.receptionDeductMoney = receptionDeductMoney;
	}
	public Double getReceptionDeductOther() {
		return receptionDeductOther;
	}
	public void setReceptionDeductOther(Double receptionDeductOther) {
		this.receptionDeductOther = receptionDeductOther;
	}
	public Double getReceptionDeductOilCard() {
		return receptionDeductOilCard;
	}
	public void setReceptionDeductOilCard(Double receptionDeductOilCard) {
		this.receptionDeductOilCard = receptionDeductOilCard;
	}
	public Double getBackstageBillTotalPrice() {
		return backstageBillTotalPrice;
	}
	public void setBackstageBillTotalPrice(Double backstageBillTotalPrice) {
		this.backstageBillTotalPrice = backstageBillTotalPrice;
	}
	public Double getBackstageDeductWeightMisc() {
		return backstageDeductWeightMisc;
	}
	public void setBackstageDeductWeightMisc(Double backstageDeductWeightMisc) {
		this.backstageDeductWeightMisc = backstageDeductWeightMisc;
	}
	public Double getBackstageDeductMoney() {
		return backstageDeductMoney;
	}
	public void setBackstageDeductMoney(Double backstageDeductMoney) {
		this.backstageDeductMoney = backstageDeductMoney;
	}
	public Double getBackstageDeductOther() {
		return backstageDeductOther;
	}
	public void setBackstageDeductOther(Double backstageDeductOther) {
		this.backstageDeductOther = backstageDeductOther;
	}
	public Double getBackstageDeductOilCard() {
		return backstageDeductOilCard;
	}
	public void setBackstageDeductOilCard(Double backstageDeductOilCard) {
		this.backstageDeductOilCard = backstageDeductOilCard;
	}
}