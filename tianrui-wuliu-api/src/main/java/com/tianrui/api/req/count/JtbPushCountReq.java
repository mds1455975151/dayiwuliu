package com.tianrui.api.req.count;

public class JtbPushCountReq {
	//推送状态 1-已推送 2-未推送
	private String pustStatus;
	//查询开始时间
	private Long beginTime;
	//查询结束时间
	private Long endTime;
	public String getPustStatus() {
		return pustStatus;
	}
	public void setPustStatus(String pustStatus) {
		this.pustStatus = pustStatus;
	}
	public Long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	
}
