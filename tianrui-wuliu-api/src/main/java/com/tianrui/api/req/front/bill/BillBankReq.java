package com.tianrui.api.req.front.bill;
/***
 * 运单银行卡
 * @author jh
 *
 */
public class BillBankReq {
	//运单id
	private String billId;
	//银行卡id
	private String bankId;
	//0-引用银行卡 1-添加银行卡
	private String bankType;
	//司机id
	private String driverId;

	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
}
