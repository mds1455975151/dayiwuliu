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
    //支付状态（0：未支付，1：支付中，2：已支付, 3:支付失败）
    private Integer payStatus;
    //账单状态
    private String payInvoiceStatus;
    //收款人名称
    private String payeeName;
    //收款人银行卡号
    private String payeeBankCardNumber;
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
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public Integer getPushStatus() {
		return pushStatus;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setBtnOpts(Integer btnOpts) {
		this.btnOpts = btnOpts;
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
	public String getPayeeBankCardNumber() {
		return payeeBankCardNumber;
	}
	public void setPayeeBankCardNumber(String payeeBankCardNumber) {
		this.payeeBankCardNumber = payeeBankCardNumber;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public void setPushStatus(Integer pushStatus) {
		this.pushStatus = pushStatus;
	}
	public String getPayInvoiceStatus() {
		if(auditStatus==Constant.NOT_AUDIT){
			//未审核
			this.payInvoiceStatus = "用户未审核";
		}else if(auditStatus == Constant.YES_AUDIT&&pushStatus == Constant.NOT_PUSH){
			//已审核，未推送
			this.payInvoiceStatus = "用户未推送";
		}else if(pushStatus == Constant.PUSH_ING&&payStatus == Constant.NOT_PAY){
			//推送中
			this.payInvoiceStatus = "未支付";
		}else if(pushStatus == Constant.YES_PUSH&&payStatus == Constant.PAY_ING){
			//支付中
			this.payInvoiceStatus = "支付中";
		}else if (payStatus == Constant.YES_PAY){
			//已支付
			this.payInvoiceStatus = "已支付";
		} else if (payStatus == Constant.THREE) {
			//支付失败
			this.payInvoiceStatus = "支付失败";
		} else {
			this.payInvoiceStatus = "";
		}
		return payInvoiceStatus;
	}
	public void setPayInvoiceStatus(String payInvoiceStatus) {
		this.payInvoiceStatus = payInvoiceStatus;
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
		//前台已审核，已推后台
		if (this.auditStatus == Constant.YES_AUDIT
				&& this.pushStatus == Constant.PUSH_ING) {
			this.btnOpts = 2;
		} else if (this.auditStatus == Constant.YES_AUDIT
				&& this.pushStatus == Constant.YES_PUSH
				&& this.payStatus != Constant.THREE) {
			this.btnOpts = 3;
		} else if (this.auditStatus == Constant.YES_AUDIT
				&& this.pushStatus == Constant.YES_PUSH
				&& this.payStatus == Constant.THREE) {
			this.btnOpts = 2;
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