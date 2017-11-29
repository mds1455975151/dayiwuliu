package com.tianrui.common.enums;
/***
 * 支付密码校验
 * @author jh
 *
 */
public enum MoenyPWCheckEnum {
	checkType1("1","密码支付"),
	checkType2("2","手势密码"),
	gestureStatus1("1","开启手势支付"),
	gestureStatus0("0","未开启手势支付");
	
	
	private MoenyPWCheckEnum(String status, String remark) {
		this.status = status;
		this.remark = remark;
	}
	
	private String status;
	private String remark;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
