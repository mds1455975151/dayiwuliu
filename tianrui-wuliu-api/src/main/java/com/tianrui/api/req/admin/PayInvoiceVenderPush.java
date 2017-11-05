package com.tianrui.api.req.admin;

/**
 * @Description 车主账单推送
 * @author zhanggaohao
 * @version 2017年6月22日 上午11:17:47
 */
public class PayInvoiceVenderPush {
	//支付申请单id
	private String id;
	//流水单ID
	private String payInvoiceMsgId;
	//支付申请单单据号
	private String billNo;
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
	//银行类别ID
	private String bankTypeId;
	//收货方组织编码
	private String consigneeNO;
	//收货方组织名称
	private String consigneeName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPayInvoiceMsgId() {
		return payInvoiceMsgId;
	}
	public void setPayInvoiceMsgId(String payInvoiceMsgId) {
		this.payInvoiceMsgId = payInvoiceMsgId;
	}
	public String getBankTypeId() {
		return bankTypeId;
	}
	public void setBankTypeId(String bankTypeId) {
		this.bankTypeId = bankTypeId;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
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
	
	public String getConsigneeNO() {
		return consigneeNO;
	}
	public void setConsigneeNO(String consigneeNO) {
		this.consigneeNO = consigneeNO;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	@Override
	public String toString() {
		return "PayInvoiceVenderPush [id=" + id + ", billNo=" + billNo + ", invoiceType=" + invoiceType
				+ ", supplierId=" + supplierId + ", name=" + name + ", vbusinlicense=" + vbusinlicense + ", applyDate="
				+ applyDate + ", payDealPrice=" + payDealPrice + ", bankCard=" + bankCard + ", bankCardId=" + bankCardId
				+ "]";
	}
	
}