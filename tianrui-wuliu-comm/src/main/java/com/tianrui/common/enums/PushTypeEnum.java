package com.tianrui.common.enums;

public enum PushTypeEnum {

	PUSH_PHONE_MSG((byte)1,"手机(短信)"),
	PUSH_APP((byte)2,"APP"),
	PUSH_TEL((byte)3,"电话通知");
	
	private byte type;
	private String remark;
	
	private PushTypeEnum(byte type,String remark){
		this.type = type;
		this.remark = remark;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
