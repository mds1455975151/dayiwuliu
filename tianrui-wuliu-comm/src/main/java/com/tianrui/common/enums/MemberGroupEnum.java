package com.tianrui.common.enums;

public enum MemberGroupEnum {

	GROUP_DRIVER((byte)1,"司机","GROUP_DRIVER"),
	GROUP_VENDER((byte)2,"车主","GROUP_VENDER"),
	GROUP_OWNER((byte)3,"货主","GROUP_OWNER");
	
	private byte type;
	private String remark;
	private String code;
	
	private MemberGroupEnum(byte type,String remark,String code){
		this.type = type;
		this.remark = remark;
		this.code = code;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
