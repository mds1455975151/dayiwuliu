package com.tianrui.api.req.money;

public class CapitalAccountReq {

	private String cellphone;//登录账号

    private String username;//用户姓名

    private String useryhno;//银行（NC）唯一编号，身份证号

    private Long availablemoney;//可用余额变化量(单位：分),不变传0

    private Long lockmoney;//冻结金额变化量,不变传0

    private Long pendingmoney;//未到账金额变化量,不变传0

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

	public Long getLockmoney() {
		return lockmoney;
	}

	public void setLockmoney(Long lockmoney) {
		this.lockmoney = lockmoney;
	}

	public Long getPendingmoney() {
		return pendingmoney;
	}

	public void setPendingmoney(Long pendingmoney) {
		this.pendingmoney = pendingmoney;
	}

}
