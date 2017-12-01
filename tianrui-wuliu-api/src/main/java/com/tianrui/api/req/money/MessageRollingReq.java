package com.tianrui.api.req.money;

public class MessageRollingReq {

	private Byte messageType;//消息类型1-需求发布，2-委派计划，3-提货确认，4-卸货确认

    private String messageContent;//消息内容

    private Long createTime;//消息创建时间

	public Byte getMessageType() {
		return messageType;
	}

	public void setMessageType(Byte messageType) {
		this.messageType = messageType;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
    
    
}
