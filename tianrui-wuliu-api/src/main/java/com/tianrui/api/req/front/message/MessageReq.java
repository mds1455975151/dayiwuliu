package com.tianrui.api.req.front.message;

import com.tianrui.api.req.BaseReq;
import com.tianrui.common.enums.MessageCodeEnum;

public class MessageReq extends BaseReq{
	 /**
     * ID
     */
    private String id;

    /**
     * 消息类型,1-系统，2-会员
     */
    private String type;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息状态 1:未读 2：已读
     */
    private String status;

    /**
     * 消息对应的模板code标识,1-司机添加；2-车主添加；3-计划发送；4-运单发送；5-拒绝；6-同意
     */
    private String code;
    
    
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

    /**
     * 发送时间
     */
    private Long sendtime;

    /**
     * 消息备注
     */
    private String remark;

    /**
     * 是否回复0:未回复 1:已回复同意 2:已回复拒绝 3：已失效
     */
    private String isreply;

    private String desc1;

    private String desc2;

    private String desc3;

    private String desc4;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Long getSendtime() {
		return sendtime;
	}

	public void setSendtime(Long sendtime) {
		this.sendtime = sendtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsreply() {
		return isreply;
	}

	public void setIsreply(String isreply) {
		this.isreply = isreply;
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

	public MessageCodeEnum getCodeEnum() {
		return codeEnum;
	}

	public void setCodeEnum(MessageCodeEnum codeEnum) {
		this.codeEnum = codeEnum;
	}
    
    
}
