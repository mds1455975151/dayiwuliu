package com.tianrui.api.req.front.adminReport;

public class AnlianBillReportReq {

	private String remarkname;
	
	private String vehicleno;
	
	private String cargoname;
	
	private String orgid;
	
	private Byte status;
	
	private Integer pageNo;
	
	private Integer pageSize;

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
	
}