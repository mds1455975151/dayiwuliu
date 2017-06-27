package com.tianrui.api.req.front.bill;

public class SignerBillFindReq {
    private String id;
    
    private Integer pageNo;
    
    private Integer pageSize;

    private String searchKey;
    //接收人id
    private String receiveMemberid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getReceiveMemberid() {
		return receiveMemberid;
	}

	public void setReceiveMemberid(String receiveMemberid) {
		this.receiveMemberid = receiveMemberid;
	}
    
}