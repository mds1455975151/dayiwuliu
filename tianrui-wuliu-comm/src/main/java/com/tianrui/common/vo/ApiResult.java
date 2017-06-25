package com.tianrui.common.vo;

import com.tianrui.common.constants.ErrorCode;

public class ApiResult {

	private String code;
	
	private String message;
	
	private Object data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public ApiResult() {
		super();
	}
	public ApiResult(String code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	@Override
	public String toString() {
		return "ApiResult [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
	public static ApiResult getSuccessResult(){
		ApiResult apiResult = new ApiResult();
		apiResult.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return apiResult;
	}
	public static ApiResult getErrorResult(){
		ApiResult apiResult = new ApiResult();
		apiResult.setErrorCode(ErrorCode.SYSTEM_ERROR);
		return apiResult;
	}
	public void setErrorCode(ErrorCode errorCode){
		this.code = errorCode.getCode();
		this.message = errorCode.getMsg();
	}
}
