package com.tianrui.common.enums;
																																																																																																																																																																																																																																																																																																																			
/**
 * @修改人：tank
 * @修改时间：2016年4月14日下午5:33:18
 * @修改备注：
 *
 */
public enum ViewOperationEnum {

	YES("页面中操作"), NO("非页面操作");

	ViewOperationEnum(String message) {
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