package com.tianrui.common.enums;
																																																																																																																																																																																																																																																																																																																			
/**
 * @修改人：tank
 * @修改时间：2016年4月14日下午5:33:18
 * @修改备注：
 *
 */
public enum DomainEnum {

	THISDOMAIN("本域"), OUTDOMAIN("外域");

	DomainEnum(String message) {
		this.message = message;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}