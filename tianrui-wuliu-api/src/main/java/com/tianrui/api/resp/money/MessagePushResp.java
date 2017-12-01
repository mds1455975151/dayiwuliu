package com.tianrui.api.resp.money;

public class MessagePushResp {

	private Long id;

    private String messageContent;//消息内容

    private Byte channel;//推送渠道，1-APP,2-短信，3-全部

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

	public Byte getChannel() {
		return channel;
	}

	public void setChannel(Byte channel) {
		this.channel = channel;
	}

}
