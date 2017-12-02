package com.tianrui.api.resp.money;

public class MessageRollingResp {
	private Long id;

    private String messageContent;//消息内容

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
    
    
}
