package com.tianrui.common.enums;

public enum PayStatusEnum {
	
	create((byte)0,"新建"),
	pushed((byte)1,"已推送"),
	paying((byte)2,"支付中"),
	paydone((byte)3,"支付完成");
	

	private byte status;
	private String desc;
	
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	private PayStatusEnum(byte status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	
	
	
	
	
}
