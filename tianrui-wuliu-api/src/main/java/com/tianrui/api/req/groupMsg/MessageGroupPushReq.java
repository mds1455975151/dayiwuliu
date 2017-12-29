package com.tianrui.api.req.groupMsg;

public class MessageGroupPushReq {

	private Integer pageNo;
	
	private Integer pageSize;
	
	private String msgType;//1-手机(短信)  2-APP  3-电话通知
	
	private String groupType;//司机-GROUP_DRIVER   车主-GROUP_VENDER   货主-GROUP_OWNER

	private Long timeBegin;//开始时间
	
	private Long timeEnd;//结束时间
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public Long getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(Long timeBegin) {
		this.timeBegin = timeBegin;
	}

	public Long getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Long timeEnd) {
		this.timeEnd = timeEnd;
	}
	
}
