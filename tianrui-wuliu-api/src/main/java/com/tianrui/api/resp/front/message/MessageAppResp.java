package com.tianrui.api.resp.front.message;

public class MessageAppResp {

	private Long id;

    private Byte messageType;//消息类型1-货源需求，2-货运计划

    private String messageContent;//消息内容
    
    private String messageHeader;//消息列表展示缩略

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(String messageHeader) {
		this.messageHeader = messageHeader;
	}
    
}
