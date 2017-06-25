package com.tianrui.api.req.bankcard;
/**
 * @Description 推送NC银行卡信息
 * @author zhanggaohao
 * @version 2017年6月24日 下午5:21:18
 */
public class PushNCBankCard {
	//银行卡主键ID
	private String id;
	//银行卡卡号
	private String bankCardNo;
	//开户人ID
	private String accountPersonId;
	//开户人名称
	private String accountPersonName;
	//开户人身份（1：个人，2：公司）
	private String accountPersonIdentity;
	//银行类别ID
	private String bankTypeId;
	//银行支行ID
	private String bankSubbranchId;
	//银行支行名称
	private String bankSubbranchName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getAccountPersonId() {
		return accountPersonId;
	}
	public void setAccountPersonId(String accountPersonId) {
		this.accountPersonId = accountPersonId;
	}
	public String getAccountPersonName() {
		return accountPersonName;
	}
	public void setAccountPersonName(String accountPersonName) {
		this.accountPersonName = accountPersonName;
	}
	public String getAccountPersonIdentity() {
		return accountPersonIdentity;
	}
	public void setAccountPersonIdentity(String accountPersonIdentity) {
		this.accountPersonIdentity = accountPersonIdentity;
	}
	public String getBankTypeId() {
		return bankTypeId;
	}
	public void setBankTypeId(String bankTypeId) {
		this.bankTypeId = bankTypeId;
	}
	public String getBankSubbranchId() {
		return bankSubbranchId;
	}
	public void setBankSubbranchId(String bankSubbranchId) {
		this.bankSubbranchId = bankSubbranchId;
	}
	public String getBankSubbranchName() {
		return bankSubbranchName;
	}
	public void setBankSubbranchName(String bankSubbranchName) {
		this.bankSubbranchName = bankSubbranchName;
	}
	public PushNCBankCard() {
		super();
	}
	public PushNCBankCard(String id, String bankCardNo, String accountPersonId, String accountPersonName,
			String accountPersonIdentity, String bankTypeId, String bankSubbranchId, String bankSubbranchName) {
		super();
		this.id = id;
		this.bankCardNo = bankCardNo;
		this.accountPersonId = accountPersonId;
		this.accountPersonName = accountPersonName;
		this.accountPersonIdentity = accountPersonIdentity;
		this.bankTypeId = bankTypeId;
		this.bankSubbranchId = bankSubbranchId;
		this.bankSubbranchName = bankSubbranchName;
	}
	@Override
	public String toString() {
		return "PushNCBankCard [id=" + id + ", bankCardNo=" + bankCardNo + ", accountPersonId=" + accountPersonId
				+ ", accountPersonName=" + accountPersonName + ", accountPersonIdentity=" + accountPersonIdentity
				+ ", bankTypeId=" + bankTypeId + ", bankSubbranchId=" + bankSubbranchId + ", bankSubbranchName="
				+ bankSubbranchName + "]";
	}
}
