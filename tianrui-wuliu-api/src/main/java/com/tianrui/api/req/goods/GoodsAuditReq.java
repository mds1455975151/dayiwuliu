package com.tianrui.api.req.goods;

public class GoodsAuditReq {

	private String id;//货源id
	
	private byte messageType;
	
	private byte audType;//审核类型 -1 删除；0 待审核；1-审核通过；2-发单中；3-已完成  4-已关闭 9-审核失败',
	
	private String audStatus;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAudStatus() {
		return audStatus;
	}
	public void setAudStatus(String audStatus) {
		this.audStatus = audStatus;
	}
	public byte getAudType() {
		return audType;
	}
	public void setAudType(byte audType) {
		this.audType = audType;
	}
	public byte getMessageType() {
		return messageType;
	}
	public void setMessageType(byte messageType) {
		this.messageType = messageType;
	}
}
