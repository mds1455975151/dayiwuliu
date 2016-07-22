package com.tianrui.api.req.front.message;

import com.tianrui.api.req.BaseReq;

public class MessageReplayReq extends BaseReq{
	
	private static final long serialVersionUID = 1L;
	private String id;
    /**
     * 是否回复  1:已回复同意 2:已回复拒绝
     */
    private String isreply;
	private String curruId;

	public String getCurruId() {
		return curruId;
	}

	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsreply() {
		return isreply;
	}

	public void setIsreply(String isreply) {
		this.isreply = isreply;
	}
	
	
    
    
}
