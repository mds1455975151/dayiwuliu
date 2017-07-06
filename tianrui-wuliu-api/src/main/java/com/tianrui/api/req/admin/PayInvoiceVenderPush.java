package com.tianrui.api.req.admin;

/**
 * @Description 车主账单推送
 * @author zhanggaohao
 * @version 2017年6月22日 上午11:17:47
 */
public class PayInvoiceVenderPush {
	//支付申请单id
	private String id;
	//物料信息（CODE）
	private String invoiceType;
	//供应商主键
	private String supplierId;
	//供应商名称
	private String name;
	//营业执照号
	private String vbusinlicense;
	//支付单日期
	private String applyDate;
	//支付金额
	private String payDealPrice;
	//银行卡号
	private String bankCard;
	//银行卡主键
	private String bankCardId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVbusinlicense() {
		return vbusinlicense;
	}
	public void setVbusinlicense(String vbusinlicense) {
		this.vbusinlicense = vbusinlicense;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getPayDealPrice() {
		return payDealPrice;
	}
	public void setPayDealPrice(String payDealPrice) {
		this.payDealPrice = payDealPrice;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getBankCardId() {
		return bankCardId;
	}
	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}
	@Override
	public String toString() {
		return "PayInvoiceVenderPush [id=" + id + ", invoiceType=" + invoiceType + ", supplierId=" + supplierId
				+ ", name=" + name + ", vbusinlicense=" + vbusinlicense + ", applyDate=" + applyDate + ", payDealPrice="
				+ payDealPrice + ", bankCard=" + bankCard + ", bankCardId=" + bankCardId + "]";
	}
	
}