package com.tianrui.api.resp.admin;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.common.constants.Constant;

/**
 * @Description 账单结果Vo
 * @author zhanggaohao
 * @version 2017年6月20日 上午11:25:56
 */
public class PayInvoiceVo extends BaseResp {
	
	private static final long serialVersionUID = -5734564963486255823L;
	
	private String id;
    //账单编码
    private String code;
    //发票类型NAME
    private String invoiceName;
    //应付金额
    private String amountPayable;
    //已付金额
    private String paidAmount;
    //审核状态（0：未审核，1：审核中，2：已审核）
    private Integer auditStatus;
    //推单状态（0：未推单，1：退单中，2已退单）
    private Integer pushStatus;
    //支付状态（0：未支付，1：支付中，2：已支付）
    private Integer payStatus;
    //账单状态
    private String payInvoiceStatus;
    //收款人名称
    private String payeeName;
    //操作按钮（1：审核，2：修改和提交，3：无）
    private Integer btnOpts;
    //申请时间
    private Long applicationTime;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(String amountPayable) {
		this.amountPayable = amountPayable;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}
	public String getPayInvoiceStatus() {
		return payInvoiceStatus;
	}
	public void setPayInvoiceStatus(String payInvoiceStatus) {
		if (payStatus == Constant.NOT_PAY){
			this.payInvoiceStatus = "未支付";
		} else if (payStatus == Constant.PAY_ING){
			this.payInvoiceStatus = "支付中";
		} else if (payStatus == Constant.YES_PAY){
			this.payInvoiceStatus = "已支付";
		} else {
			this.payInvoiceStatus = "";
		}
	}
	public Long getApplicationTime() {
		return applicationTime;
	}
	public void setApplicationTime(Long applicationTime) {
		this.applicationTime = applicationTime;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public Integer getBtnOpts() {
		if (this.auditStatus == Constant.NOT_AUDIT
				&& this.pushStatus == Constant.NOT_PUSH) {
			this.btnOpts = 1;
		} else if (this.auditStatus == Constant.YES_AUDIT
				&& this.pushStatus == Constant.NOT_PUSH) {
			this.btnOpts = 2;
		} else if (this.auditStatus == Constant.YES_AUDIT
				&& this.pushStatus == Constant.YES_PUSH) {
			this.btnOpts = 3;
		}
		return btnOpts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PayInvoiceVo [id=" + id + ", code=" + code + ", invoiceName=" + invoiceName + ", amountPayable="
				+ amountPayable + ", paidAmount=" + paidAmount + ", auditStatus=" + auditStatus + ", pushStatus="
				+ pushStatus + ", payStatus=" + payStatus + ", payeeName=" + payeeName + ", btnOpts=" + btnOpts + "]";
	}

}