package com.tianrui.common.enums;

/**
 * 交易类型枚举类
 * @author LENOVO123
 *
 */
public enum TransactionType {
	
	PENDING((byte)1,"运费待收入"),
	
	PAID((byte)11,"收入运费"),
	RECYCLING((byte)19,"资金回流"),
	
	TXSQ((byte)21,"提现申请"),
	TXSUCESS((byte)22,"提现成功");
	

	private byte type;
	private String desc;
	
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	private TransactionType(byte type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	
	
	
	
}
