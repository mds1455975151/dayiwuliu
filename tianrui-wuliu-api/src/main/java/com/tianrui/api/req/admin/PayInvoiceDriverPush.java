package com.tianrui.api.req.admin;

/**
 * @Description 司机账单推送
 * @author zhanggaohao
 * @version 2017年6月22日 上午9:16:38
 */
public class PayInvoiceDriverPush {
	//支付申请单id
	private String id;
	//支付申请单单据号
	private String billNo;
	//供应商主键
	private String supplierId;
	//供应商名称
	private String name;
	//银行卡主键
	private String bankCardId;
	//银行卡号
	private String bankCard;
	//司机身份信息（身份证号）
	private String drivercode;
	//物料信息（CODE）
	private String invoiceType;
	//支付金额
	private String billTotalPrice;
	//支付单日期
	private String signTime;
	//银行类别ID
	private String bankTypeId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
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

	public String getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getDrivercode() {
		return drivercode;
	}

	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getBillTotalPrice() {
		return billTotalPrice;
	}

	public void setBillTotalPrice(String billTotalPrice) {
		this.billTotalPrice = billTotalPrice;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getBankTypeId() {
		return bankTypeId;
	}

	public void setBankTypeId(String bankTypeId) {
		this.bankTypeId = bankTypeId;
	}

	@Override
	public String toString() {
		return "PayInvoiceDriverPush [id=" + id + ", billNo=" + billNo + ", supplierId=" + supplierId + ", name=" + name
				+ ", bankCardId=" + bankCardId + ", bankCard=" + bankCard + ", drivercode=" + drivercode
				+ ", invoiceType=" + invoiceType + ", billTotalPrice=" + billTotalPrice + ", signTime=" + signTime
				+ ", bankTypeId=" + bankTypeId + "]";
	}

}