package com.tianrui.common.constants;

public enum ApiErrorCode {

	//系统异常
	API_SYSTEM_ERROR("E000001","参数为空."),
	API_POSITION_PARAM_ERROR1("E000101","参数已经被篡改."),
	API_POSITION_PARAM_ERROR2("E000102","参数密钥不对."),
	API_POSITION_PARAM_ERROR3("E000103","参数纬度异常."),
	API_POSITION_PARAM_ERROR4("E000104","参数经度异常."),
	API_POSITION_PARAM_ERROR5("E000105","参数trackingid异常."),
	API_POSITION_PARAM_ERROR6("E000106","参数调用时间异常."),
	
	API_SYSTEM_SUCCESS("000000","操作成功.");

	
	private String code;
	private String msg;
	
	private ApiErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
