package com.tianrui.api.req.groupMsg;

public class GroupMsgSaveReq {

	private String groupType;//1-司机  2-车主  3-货主
	
	private byte msgType;//1-手机(短信) 2-APP 3-电话通知
	
	private String msgTxt;

	public byte getMsgType() {
		return msgType;
	}

	public void setMsgType(byte msgType) {
		this.msgType = msgType;
	}

	public String getMsgTxt() {
		return msgTxt;
	}

	public void setMsgTxt(String msgTxt) {
		this.msgTxt = msgTxt;
	}
	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
}
