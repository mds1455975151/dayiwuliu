package com.tianrui.service.bean.alct;

import com.tianrui.common.utils.UUIDUtil;

public class ShipmentsHead {
	
	//报文的唯一标识符 uuid long-200
	private String messageReferenceNumber=UUIDUtil.getId();
	//文档类型  固定值 shipment  long-36
	private String documentName="shipment";
	//文档版本号 目前位1.0 long-12
	private String documentVersionNumber="1.0";
	//企业编号 E0000109
	private String senderCode="E0000109";
	//接受方 固定值 alct
	private String recipientCode="alct";
	//发送时间  选填
	private String messageSendingDateTime;
	//方法名称  两个固定值	create update 
	private String messageFunctionCode="create";

	public String getMessageReferenceNumber() {
		return messageReferenceNumber;
	}

	public void setMessageReferenceNumber(String messageReferenceNumber) {
		this.messageReferenceNumber = messageReferenceNumber;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentVersionNumber() {
		return documentVersionNumber;
	}

	public void setDocumentVersionNumber(String documentVersionNumber) {
		this.documentVersionNumber = documentVersionNumber;
	}

	public String getSenderCode() {
		return senderCode;
	}

	public void setSenderCode(String senderCode) {
		this.senderCode = senderCode;
	}

	public String getRecipientCode() {
		return recipientCode;
	}

	public void setRecipientCode(String recipientCode) {
		this.recipientCode = recipientCode;
	}

	public String getMessageSendingDateTime() {
		return messageSendingDateTime;
	}

	public void setMessageSendingDateTime(String messageSendingDateTime) {
		this.messageSendingDateTime = messageSendingDateTime;
	}

	public String getMessageFunctionCode() {
		return messageFunctionCode;
	}

	public void setMessageFunctionCode(String messageFunctionCode) {
		this.messageFunctionCode = messageFunctionCode;
	}
}
