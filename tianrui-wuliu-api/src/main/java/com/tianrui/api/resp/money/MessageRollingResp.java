package com.tianrui.api.resp.money;

public class MessageRollingResp {
	private Long id;

    private String messageContent;//消息内容
    
    private String time;//时间
    
    private String departure;//起运地
    
    private String Unloading;//卸货地

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getUnloading() {
		return Unloading;
	}

	public void setUnloading(String unloading) {
		Unloading = unloading;
	}
    
}
