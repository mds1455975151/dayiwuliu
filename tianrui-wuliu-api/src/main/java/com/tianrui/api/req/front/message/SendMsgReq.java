package com.tianrui.api.req.front.message;

import java.util.List;

import com.tianrui.api.req.BaseReq;
import com.tianrui.common.enums.MessageCodeEnum;

public class SendMsgReq extends BaseReq{

	private static final long serialVersionUID = -4960451080515640721L;




	/**
     * 消息类型,1-系统，2-会员
     */
    private String type;

    private String URI;
    private List<String> params;

    
    private MessageCodeEnum codeEnum;

    /**
     * 消息关联数据库主键
     */
    private String keyid;

    /**
     * 发信人id
     */
    private String sendid;

    /**
     * 发信人姓名
     */
    private String sendname;

    /**
     *收信人id
     */
    private String recid;

    /**
     * 收信人姓名
     */
    private String recname;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public MessageCodeEnum getCodeEnum() {
		return codeEnum;
	}

	public void setCodeEnum(MessageCodeEnum codeEnum) {
		this.codeEnum = codeEnum;
	}

	public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getSendid() {
		return sendid;
	}

	public void setSendid(String sendid) {
		this.sendid = sendid;
	}

	public String getSendname() {
		return sendname;
	}

	public void setSendname(String sendname) {
		this.sendname = sendname;
	}

	public String getRecid() {
		return recid;
	}

	public void setRecid(String recid) {
		this.recid = recid;
	}

	public String getRecname() {
		return recname;
	}

	public void setRecname(String recname) {
		this.recname = recname;
	}

   

    
    
}
