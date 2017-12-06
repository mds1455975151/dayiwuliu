package com.tianrui.api.req.money;

public class FindWithdrawRecordReq {

	private Integer pageNo;
	
	private Integer pageSize;
	//提现申请开始时间
	private Long timeBegin;
	//提现申请结束时间
	private Long timeEnd;
	//登录账号
	private String cellPhone;
	//身份证号
	private String useryhno;
	
	private String bankcodeno;//银行卡号
	
	private String capitalno;//交易流水号
	
	private String expectpaycompany;//支付渠道 
	
	private Short transactionstate;//交易状态 1-处理中 2-成功 3-失败 4-未知

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

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getUseryhno() {
		return useryhno;
	}

	public void setUseryhno(String useryhno) {
		this.useryhno = useryhno;
	}

	public Short getTransactionstate() {
		return transactionstate;
	}

	public void setTransactionstate(Short transactionstate) {
		this.transactionstate = transactionstate;
	}

	public String getBankcodeno() {
		return bankcodeno;
	}

	public void setBankcodeno(String bankcodeno) {
		this.bankcodeno = bankcodeno;
	}

	public String getCapitalno() {
		return capitalno;
	}

	public void setCapitalno(String capitalno) {
		this.capitalno = capitalno;
	}

	public String getExpectpaycompany() {
		return expectpaycompany;
	}

	public void setExpectpaycompany(String expectpaycompany) {
		this.expectpaycompany = expectpaycompany;
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
