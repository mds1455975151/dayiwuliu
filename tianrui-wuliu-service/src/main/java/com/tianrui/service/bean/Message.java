package com.tianrui.service.bean;

public class Message {
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
     * 消息状态 1:未读 2：已读 -1:删除
     */
    private String status;

    /**
     * 消息对应的模板code标识,1-司机添加；2-车主添加；3-计划发送；4-运单发送
     */
    private String code;

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
    
    private String uri;
    /**
     * 1 发送给司机 2 发送给车主 3 发送给货主 0通用
     */
    private int rectype;

    private String desc1;

    private String desc2;

    private String desc3;

    private String desc4;
    
    private Integer start;
    private Integer limit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid == null ? null : keyid.trim();
    }

    public String getSendid() {
        return sendid;
    }

    public void setSendid(String sendid) {
        this.sendid = sendid == null ? null : sendid.trim();
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname == null ? null : sendname.trim();
    }

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid == null ? null : recid.trim();
    }

    public String getRecname() {
        return recname;
    }

    public void setRecname(String recname) {
        this.recname = recname == null ? null : recname.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsreply() {
        return isreply;
    }

    public void setIsreply(String isreply) {
        this.isreply = isreply == null ? null : isreply.trim();
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    public String getDesc4() {
        return desc4;
    }

    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
    }

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public int getRectype() {
		return rectype;
	}

	public void setRectype(int rectype) {
		this.rectype = rectype;
	}
    
    
}