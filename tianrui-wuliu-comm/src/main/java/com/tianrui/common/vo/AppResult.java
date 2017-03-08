package com.tianrui.common.vo;

import org.apache.commons.lang.StringUtils;

import com.tianrui.common.constants.ErrorCode;

public class AppResult {

	private String code;
	
	private String message;
	
	private Object returnData;
	
	private Integer total;
	private long serviceTime=System.currentTimeMillis();
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
	public Object getReturnData() {
		return returnData;
	}
	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public long getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(long serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	public AppResult() {
		super();
	}
	public AppResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public static AppResult valueOf(Result rs){
		AppResult appResult = null;
		if( rs !=null ){
			appResult = new AppResult();
			appResult.setCode(rs.getCode());
			appResult.setMessage(rs.getError());
			appResult.setReturnData(rs.getData());
		}
		return appResult;
	}
	
	public static AppResult valueOf(ErrorCode errorCode){
		AppResult appResult = null;
		if( errorCode !=null ){
			appResult = new AppResult();
			appResult.setCode(errorCode.getCode());
			appResult.setMessage(errorCode.getMsg());
		}
		return appResult;
	}
	
}
