package com.tianrui.api.req.money;

public class CapitalRecordReq {

	private String cellphone;//登录账号

    private String username;//用户姓名

    private String useryhno;//银行（NC）唯一编号，身份证号

    private Long availablemoney;//交易金额(单位：分)

    private String capitalno;//交易流水号
    
	public String getCapitalno() {
		return capitalno;
	}

	public void setCapitalno(String capitalno) {
		this.capitalno = capitalno;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseryhno() {
		return useryhno;
	}

	public void setUseryhno(String useryhno) {
		this.useryhno = useryhno;
	}

	public Long getAvailablemoney() {
		return availablemoney;
	}

	public void setAvailablemoney(Long availablemoney) {
		this.availablemoney = availablemoney;
	}
}
