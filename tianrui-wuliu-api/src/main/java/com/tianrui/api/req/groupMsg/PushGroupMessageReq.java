package com.tianrui.api.req.groupMsg;

public class PushGroupMessageReq {

	private Long id;//处理数据id
	
	private String msgType;//消息类型  1-手机(短信)  2-APP   3-电话通知
	
	private String msgTxt;//消息文本
	
	private String groupType;//分组对象1-司机  2-车主  3-货主  4-用户
	
	private String memberId;//用户ID (groupType=4,memberId不能为空)
	
	private String sysUser;//操作员
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSysUser() {
		return sysUser;
	}

	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}
}
