package com.tianrui.service.admin.bean;
/**
 * @annotation 账单支付信息日志表
 * @author zhanggaohao
 * @date 2017年7月5日 下午1:52:55
 */
public class PayInvoiceMsg {
	
    private String id;
    //账单ID
    private String payInvoiceId;
    //收款人ID
    private String payeeId;
    //收款人银行卡ID
    private String payeeBankCardId;
    //收款人银行卡号
    private String payeeBankCardNumber;
    //支行ID
    private String payeeBankSubbranchId;
    //支行名称
    private String payeeBankSubbranchName;
    //应付金额
    private Double amountPayable;
    //已付金额
    private Double paidAmount;
    //支付状态（0：支付失败，1：支付中，2：支付成功）
    private Integer payStatus;
    //支付时间
    private Long payTime;
    //支付状态信息
    private String payStatusMsg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPayInvoiceId() {
        return payInvoiceId;
    }

    public void setPayInvoiceId(String payInvoiceId) {
        this.payInvoiceId = payInvoiceId == null ? null : payInvoiceId.trim();
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId == null ? null : payeeId.trim();
    }

    public String getPayeeBankCardId() {
        return payeeBankCardId;
    }

    public void setPayeeBankCardId(String payeeBankCardId) {
        this.payeeBankCardId = payeeBankCardId == null ? null : payeeBankCardId.trim();
    }

    public String getPayeeBankCardNumber() {
        return payeeBankCardNumber;
    }

    public void setPayeeBankCardNumber(String payeeBankCardNumber) {
        this.payeeBankCardNumber = payeeBankCardNumber == null ? null : payeeBankCardNumber.trim();
    }

    public String getPayeeBankSubbranchId() {
        return payeeBankSubbranchId;
    }

    public void setPayeeBankSubbranchId(String payeeBankSubbranchId) {
        this.payeeBankSubbranchId = payeeBankSubbranchId == null ? null : payeeBankSubbranchId.trim();
    }

    public String getPayeeBankSubbranchName() {
        return payeeBankSubbranchName;
    }

    public void setPayeeBankSubbranchName(String payeeBankSubbranchName) {
        this.payeeBankSubbranchName = payeeBankSubbranchName == null ? null : payeeBankSubbranchName.trim();
    }

    public Double getAmountPayable() {
        return amountPayable;
    }

    public void setAmountPayable(Double amountPayable) {
        this.amountPayable = amountPayable;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public String getPayStatusMsg() {
        return payStatusMsg;
    }

    public void setPayStatusMsg(String payStatusMsg) {
        this.payStatusMsg = payStatusMsg == null ? null : payStatusMsg.trim();
    }

	public PayInvoiceMsg() {
		super();
	}

	public PayInvoiceMsg(String id, String payInvoiceId, String payeeId, String payeeBankCardId,
			String payeeBankCardNumber, String payeeBankSubbranchId, String payeeBankSubbranchName,
			Double amountPayable, Double paidAmount, Integer payStatus, Long payTime, String payStatusMsg) {
		super();
		this.id = id;
		this.payInvoiceId = payInvoiceId;
		this.payeeId = payeeId;
		this.payeeBankCardId = payeeBankCardId;
		this.payeeBankCardNumber = payeeBankCardNumber;
		this.payeeBankSubbranchId = payeeBankSubbranchId;
		this.payeeBankSubbranchName = payeeBankSubbranchName;
		this.amountPayable = amountPayable;
		this.paidAmount = paidAmount;
		this.payStatus = payStatus;
		this.payTime = payTime;
		this.payStatusMsg = payStatusMsg;
	}

	@Override
	public String toString() {
		return "PayInvoiceMsg [id=" + id + ", payInvoiceId=" + payInvoiceId + ", payeeId=" + payeeId
				+ ", payeeBankCardId=" + payeeBankCardId + ", payeeBankCardNumber=" + payeeBankCardNumber
				+ ", payeeBankSubbranchId=" + payeeBankSubbranchId + ", payeeBankSubbranchName="
				+ payeeBankSubbranchName + ", amountPayable=" + amountPayable + ", paidAmount=" + paidAmount
				+ ", payStatus=" + payStatus + ", payTime=" + payTime + ", payStatusMsg=" + payStatusMsg + "]";
	}
    
}