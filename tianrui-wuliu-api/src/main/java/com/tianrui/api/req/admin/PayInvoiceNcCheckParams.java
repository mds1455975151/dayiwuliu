package com.tianrui.api.req.admin;

import com.tianrui.api.req.BasePage;

/**
 * @annotation nc检查账单参数
 * @author zhanggaohao
 * @date 2017年7月7日 上午9:51:39
 */
public class PayInvoiceNcCheckParams extends BasePage {
	
	private static final long serialVersionUID = 2843777331828431823L;
	//账单主键ID
	private String id;
    //应付金额
    private String amountPayable;
    //收款人名称
    private String payeeName;
    //收款人有效证件号码
    private String payeeIdNo;
    //收款人银行卡号
    private String payeeBankCardNumber;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAmountPayable() {
		return amountPayable;
	}
	public void setAmountPayable(String amountPayable) {
		this.amountPayable = amountPayable;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getPayeeIdNo() {
		return payeeIdNo;
	}
	public void setPayeeIdNo(String payeeIdNo) {
		this.payeeIdNo = payeeIdNo;
	}
	public String getPayeeBankCardNumber() {
		return payeeBankCardNumber;
	}
	public void setPayeeBankCardNumber(String payeeBankCardNumber) {
		this.payeeBankCardNumber = payeeBankCardNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}