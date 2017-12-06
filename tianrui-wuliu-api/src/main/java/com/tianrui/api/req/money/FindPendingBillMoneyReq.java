package com.tianrui.api.req.money;

public class FindPendingBillMoneyReq {
	
	private Integer pageNo;
	
	private Integer pageSize;
	//运单号
	private String waybillno;
	//身份证号
	private String useryhno;
	//登录账号
	private String cellphone;
	//卸货开始时间
	private Long timeBegin;
	//卸货结束时间
	private Long timeEnd;
	
	private Short ifpaid;//是否到账(0-未到账，1-已到账)

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

	public String getWaybillno() {
		return waybillno;
	}

	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}

	public String getUseryhno() {
		return useryhno;
	}

	public void setUseryhno(String useryhno) {
		this.useryhno = useryhno;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Short getIfpaid() {
		return ifpaid;
	}

	public void setIfpaid(Short ifpaid) {
		this.ifpaid = ifpaid;
	}

	public Long getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(Long timeBegin) {
		this.timeBegin = timeBegin;
	}

	public Long getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Long timeEnd) {
		this.timeEnd = timeEnd;
	}

}
