package com.tianrui.api.req.money;

public class MessagePushReq {

	private Byte messageType;//消息类型1-货源需求，2-货运计划

    private Byte channel;//推送渠道，1-APP,2-短信，3-全部

    private Long createTime;//消息创建时间
    
    private String messageContent;//消息内容

	public Byte getMessageType() {
		return messageType;
	}

	public void setMessageType(Byte messageType) {
		this.messageType = messageType;
	}

	public Byte getChannel() {
		return channel;
	}

	public void setChannel(Byte channel) {
		this.channel = channel;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
    
}
