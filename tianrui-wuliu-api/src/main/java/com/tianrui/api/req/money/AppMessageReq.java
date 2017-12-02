package com.tianrui.api.req.money;

public class AppMessageReq  {

	private static final long serialVersionUID = -4575104474259141337L;
	
	private String messageType;////消息类型1-货源需求，2-货运计划
	
	private String channel = "1";//推送渠道
	
	private Integer pageNo ;
		
	private Integer pageSize;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
}
