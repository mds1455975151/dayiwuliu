package com.tianrui.api.resp.pay;

import org.apache.commons.lang.StringUtils;

public class PayInvoiceDetailResp {

	private String id;

    private String payId;

    private String billId;

    private String billCode;

    private String cargoId;

    private String cargoCode;

    private String invoiceType;

    private String signTime;

    private Double billWeight;

    private Double billPrice;

    private Double billTotalPrice;

    private Byte isInvoice;

    private String orgid;

    private String venderName;

    private Byte venderType;

    private String venderCode;

    private String venderId;
   
    private String ownerId;
    
    private String cargoName;
    
    private String invoiceTypeName;
   
    private String taxRate;//税率

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPayId() {
		if(StringUtils.isBlank(payId)){
			payId = "";
		}
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public String getBillId() {
		if(StringUtils.isBlank(billId)){
			billId = "";
		}
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getBillCode() {
		if(StringUtils.isBlank(billCode)){
			billCode = "";
		}
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public String getCargoId() {
		if(StringUtils.isBlank(cargoId)){
			cargoId = "";
		}
		return cargoId;
	}

	public void setCargoId(String cargoId) {
		this.cargoId = cargoId;
	}

	public String getCargoCode() {
		if(StringUtils.isBlank(cargoCode)){
			cargoCode = "";
		}
		return cargoCode;
	}

	public void setCargoCode(String cargoCode) {
		this.cargoCode = cargoCode;
	}

	public String getInvoiceType() {
		if(StringUtils.isBlank(invoiceType)){
			invoiceType = "";
		}
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getSignTime() {
		if(StringUtils.isBlank(signTime)){
			signTime = "";
		}
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public Double getBillWeight() {
		if(billWeight == null){
			billWeight = (double) 0;
		}
		return billWeight;
	}

	public void setBillWeight(Double billWeight) {
		this.billWeight = billWeight;
	}

	public Double getBillPrice() {
		if(billPrice == null){
			billPrice = (double) 0;
		}
		return billPrice;
	}

	public void setBillPrice(Double billPrice) {
		this.billPrice = billPrice;
	}

	public Double getBillTotalPrice() {
		if(billTotalPrice == null){
			billTotalPrice = (double) 0;;
		}
		return billTotalPrice;
	}

	public void setBillTotalPrice(Double billTotalPrice) {
		this.billTotalPrice = billTotalPrice;
	}

	public Byte getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Byte isInvoice) {
		this.isInvoice = isInvoice;
	}

	public String getOrgid() {
		if(StringUtils.isBlank(orgid)){
			orgid = "";
		}
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getVenderName() {
		if(StringUtils.isBlank(venderName)){
			venderName = "";
		}
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public Byte getVenderType() {
		return venderType;
	}

	public void setVenderType(Byte venderType) {
		this.venderType = venderType;
	}

	public String getVenderCode() {
		if(StringUtils.isBlank(venderCode)){
			venderCode = "";
		}
		return venderCode;
	}

	public void setVenderCode(String venderCode) {
		this.venderCode = venderCode;
	}

	public String getVenderId() {
		if(StringUtils.isBlank(venderId)){
			venderId = "";
		}
		return venderId;
	}

	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}

	public String getOwnerId() {
		if(StringUtils.isBlank(ownerId)){
			ownerId = "";
		}
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getCargoName() {
		if(StringUtils.isBlank(cargoName)){
			cargoName = "";
		}
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getInvoiceTypeName() {
		if(StringUtils.isBlank(invoiceTypeName)){
			invoiceTypeName = "";
		}
		return invoiceTypeName;
	}

	public void setInvoiceTypeName(String invoiceTypeName) {
		this.invoiceTypeName = invoiceTypeName;
	}

	public String getTaxRate() {
		if(StringUtils.isBlank(taxRate)){
			taxRate = "";
		}
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
    
    
}
