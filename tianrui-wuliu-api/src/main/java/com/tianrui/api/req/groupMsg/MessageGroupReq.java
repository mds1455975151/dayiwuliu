package com.tianrui.api.req.groupMsg;

public class MessageGroupReq {
	
	private Integer pageNo;
	
	private Integer pageSize;
	
	private String groupType;//1-司机  2-车主  3-货主

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
}
