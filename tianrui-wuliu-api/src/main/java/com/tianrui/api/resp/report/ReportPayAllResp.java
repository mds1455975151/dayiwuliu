package com.tianrui.api.resp.report;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportPayAllResp {
    private String id;

    private Long payCreateTime;
    private String  payCreateTimeStr;
    private String payCode;

    private String payMent;

    private String planCode;

    private String routeName;

    private String sendMan;

    private String sendPersian;

    private String venderName;

    private String receiptMan;

    private String receiptPerson;

    private String vehicleNo;

    private Long billTime;
    
    private String billTimeStr;
    private String billNo;

    private String cargoName;

    private String trueWeight;

    private String price;

    private String totalPrice;

    private String oilCard;

    private String weightMisc;

    private String deductMoney;

    private String deductOther;

    private String amountPayable;

    private String paidAmount;

    private String payStatus;

    private String payType;

    private String payPerson;

    private String payBankName;

    private String payBankCode;

    private String desc1;

    private String desc2;

    private String desc3;

    private String desc4;

    private String payMentId;
    
    private String payDriverId;
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getPayCreateTime() {
        return payCreateTime;
    }

    public void setPayCreateTime(Long payCreateTime) {
        this.payCreateTime = payCreateTime;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode == null ? null : payCode.trim();
    }

    public String getPayMent() {
        return payMent;
    }

    public void setPayMent(String payMent) {
        this.payMent = payMent == null ? null : payMent.trim();
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode == null ? null : planCode.trim();
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public String getSendMan() {
        return sendMan;
    }

    public void setSendMan(String sendMan) {
        this.sendMan = sendMan == null ? null : sendMan.trim();
    }

    public String getSendPersian() {
        return sendPersian;
    }

    public void setSendPersian(String sendPersian) {
        this.sendPersian = sendPersian == null ? null : sendPersian.trim();
    }

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName == null ? null : venderName.trim();
    }

    public String getReceiptMan() {
        return receiptMan;
    }

    public void setReceiptMan(String receiptMan) {
        this.receiptMan = receiptMan == null ? null : receiptMan.trim();
    }

    public String getReceiptPerson() {
        return receiptPerson;
    }

    public void setReceiptPerson(String receiptPerson) {
        this.receiptPerson = receiptPerson == null ? null : receiptPerson.trim();
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo == null ? null : vehicleNo.trim();
    }

    public Long getBillTime() {
        return billTime;
    }

    public void setBillTime(Long billTime) {
        this.billTime = billTime;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName == null ? null : cargoName.trim();
    }

    public String getTrueWeight() {
        return trueWeight;
    }

    public void setTrueWeight(String trueWeight) {
        this.trueWeight = trueWeight == null ? null : trueWeight.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice == null ? null : totalPrice.trim();
    }

    public String getOilCard() {
        return oilCard;
    }

    public void setOilCard(String oilCard) {
        this.oilCard = oilCard == null ? null : oilCard.trim();
    }

    public String getWeightMisc() {
        return weightMisc;
    }

    public void setWeightMisc(String weightMisc) {
        this.weightMisc = weightMisc == null ? null : weightMisc.trim();
    }

    public String getDeductMoney() {
        return deductMoney;
    }

    public void setDeductMoney(String deductMoney) {
        this.deductMoney = deductMoney == null ? null : deductMoney.trim();
    }

    public String getDeductOther() {
        return deductOther;
    }

    public void setDeductOther(String deductOther) {
        this.deductOther = deductOther == null ? null : deductOther.trim();
    }

    public String getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(String amountPayable) {
        this.amountPayable = amountPayable == null ? null : amountPayable.trim();
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount == null ? null : paidAmount.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getPayPerson() {
        return payPerson;
    }

    public void setPayPerson(String payPerson) {
        this.payPerson = payPerson == null ? null : payPerson.trim();
    }

    public String getPayBankName() {
        return payBankName;
    }

    public void setPayBankName(String payBankName) {
        this.payBankName = payBankName == null ? null : payBankName.trim();
    }

    public String getPayBankCode() {
        return payBankCode;
    }

    public void setPayBankCode(String payBankCode) {
        this.payBankCode = payBankCode == null ? null : payBankCode.trim();
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    public String getDesc4() {
        return desc4;
    }

    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
    }

	public String getPayCreateTimeStr() {
		if(payCreateTime != null){
			payCreateTimeStr =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(payCreateTime));
		}
		return payCreateTimeStr;
	}


	public String getBillTimeStr() {
		if(billTime!= null){
			billTimeStr =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(billTime));
		}
		return billTimeStr;
	}

	public String getPayMentId() {
		return payMentId;
	}

	public void setPayMentId(String payMentId) {
		this.payMentId = payMentId;
	}

	public String getPayDriverId() {
		return payDriverId;
	}

	public void setPayDriverId(String payDriverId) {
		this.payDriverId = payDriverId;
	}

    
    
}