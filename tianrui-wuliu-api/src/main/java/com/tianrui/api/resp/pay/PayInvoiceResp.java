package com.tianrui.api.resp.pay;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tianrui.api.util.DateTypeUtil;

public class PayInvoiceResp {


	private String id;

    private String invoiceType;

    private Double payDealPrice;

    private Double paidPrice;

    private Byte adviceStatus;

    private Long adviceTime;

    private Byte payStatus;

    private String orgid;

    private String orgName;

    private String venderName;

    private Byte venderType;

    private String venderCode;

    private String venderId;
   
    private String ownerId;
   
    private String applyDate;
    
    private String invoiceTypeName;
    
    private String payCode;
    
    private List<PayInvoiceDetailResp> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPayCode() {
		if(StringUtils.isBlank(payCode)){
			payCode = "";
		}
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
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

	public Double getPayDealPrice() {
		return DateTypeUtil.doubleType(payDealPrice);
	}

	public void setPayDealPrice(Double payDealPrice) {
		this.payDealPrice = payDealPrice;
	}

	public Double getPaidPrice() {
		return DateTypeUtil.doubleType(paidPrice);
	}

	public void setPaidPrice(Double paidPrice) {
		this.paidPrice = paidPrice;
	}

	public Byte getAdviceStatus() {
		return adviceStatus;
	}

	public void setAdviceStatus(Byte adviceStatus) {
		this.adviceStatus = adviceStatus;
	}

	public Long getAdviceTime() {
		if(adviceTime == null){
			adviceTime = (long) 0;
		}
		return adviceTime;
	}

	public void setAdviceTime(Long adviceTime) {
		this.adviceTime = adviceTime;
	}

	public Byte getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Byte payStatus) {
		this.payStatus = payStatus;
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

	public String getOrgName() {
		if(StringUtils.isBlank(orgName)){
			orgName = "";
		}
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getApplyDate() {
		if(StringUtils.isBlank(applyDate)){
			applyDate = "";
		}
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public List<PayInvoiceDetailResp> getItems() {
		return items;
	}

	public void setItems(List<PayInvoiceDetailResp> items) {
		this.items = items;
	}
}
