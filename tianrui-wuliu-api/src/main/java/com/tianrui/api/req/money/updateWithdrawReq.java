package com.tianrui.api.req.money;

public class updateWithdrawReq {
	
	private String cellphone;//登录账号---必传
	
	private String capitalno;//交易流水号

    private Long endtime;//提现完成时间
    
    private boolean flag;//提现结果
    
    private Long actualamount;//实际到账金额

    private String errorcode;//错误码

    private String errormessage;//错误码描述

	public String getCapitalno() {
		return capitalno;
	}

	public void setCapitalno(String capitalno) {
		this.capitalno = capitalno;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public Long getActualamount() {
		return actualamount;
	}

	public void setActualamount(Long actualamount) {
		this.actualamount = actualamount;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
}
