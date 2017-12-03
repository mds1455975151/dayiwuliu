package com.tianrui.api.req.money;

public class MessageRollingReq {

	private Byte messageType;//消息类型1-需求发布，2-委派计划，3-提货确认，4-卸货确认

    private String messageContent;//消息内容

    private Long createTime;//消息创建时间
    
    private String desc1;//显示时间

    private String desc2;//起运地

    private String desc3;//目的地

    private String desc4;

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

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	public String getDesc4() {
		return desc4;
	}

	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}
    
}
