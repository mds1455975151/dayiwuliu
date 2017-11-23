package com.tianrui.api.req.money;

public class FindWithdrawRecordReq {

	private Integer pageNo;
	
	private Integer pageSize;
	//登录账号
	private String cellPhone;
	//身份证号
	private String useryhno;

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
	
	
}
