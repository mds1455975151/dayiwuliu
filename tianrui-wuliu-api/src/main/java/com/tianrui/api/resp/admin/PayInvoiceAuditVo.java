package com.tianrui.api.resp.admin;

import com.tianrui.api.resp.BaseResp;

/**
 * @Description 账单结果Vo
 * @author zhanggaohao
 * @version 2017年6月20日 上午11:25:56
 */
public class PayInvoiceAuditVo extends BaseResp {

	private static final long serialVersionUID = 4715061147059379090L;
	
	private String id;
    //收款人名称
	private String payeeName;
	//收款人银行卡号
	private String payeeBankCardNumber;
	//后台运单总价
	private String billTotalPrice;
    //后台扣重扣杂
    private String deductWeightMisc;
    //后台扣款
    private String deductMoney;
    //后台其他扣款
    private String deductOther;
    //后台油卡扣款
    private String deductOilCard;
    //应付金额
    private String amountPayable;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getPayeeBankCardNumber() {
		return payeeBankCardNumber;
	}
	public void setPayeeBankCardNumber(String payeeBankCardNumber) {
		this.payeeBankCardNumber = payeeBankCardNumber;
	}
	public String getBillTotalPrice() {
		return billTotalPrice;
	}
	public void setBillTotalPrice(String billTotalPrice) {
		this.billTotalPrice = billTotalPrice;
	}
	public String getDeductWeightMisc() {
		return deductWeightMisc;
	}
	public void setDeductWeightMisc(String deductWeightMisc) {
		this.deductWeightMisc = deductWeightMisc;
	}
	public String getDeductMoney() {
		return deductMoney;
	}
	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}
	public String getDeductOther() {
		return deductOther;
	}
	public void setDeductOther(String deductOther) {
		this.deductOther = deductOther;
	}
	public String getDeductOilCard() {
		return deductOilCard;
	}
	public void setDeductOilCard(String deductOilCard) {
		this.deductOilCard = deductOilCard;
	}
	public String getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(String amountPayable) {
		this.amountPayable = amountPayable;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PayInvoiceAuditVo [id=" + id + ", payeeName=" + payeeName + ", payeeBankCardNumber="
				+ payeeBankCardNumber + ", billTotalPrice=" + billTotalPrice + ", deductWeightMisc=" + deductWeightMisc
				+ ", deductMoney=" + deductMoney + ", deductOther=" + deductOther + ", deductOilCard=" + deductOilCard
				+ ", amountPayable=" + amountPayable + "]";
	}
    
}