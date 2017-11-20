package com.tianrui.api.req.money;

public class SaveWithdrawReq {

	private String cellphone;//登录账号---必传

    private String useryhno;//银行（NC）唯一编号

    private Long money;//提现金额

    private String expectpaycompany;//支付渠道

    private String bankname;//银行名称

    private String bankcode;//银行编码

    private String bankcodeno;//银行卡号

    private Long begintime;//提现申请时间

    private String capitalno;//交易流水号

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getUseryhno() {
		return useryhno;
	}

	public void setUseryhno(String useryhno) {
		this.useryhno = useryhno;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getExpectpaycompany() {
		return expectpaycompany;
	}

	public void setExpectpaycompany(String expectpaycompany) {
		this.expectpaycompany = expectpaycompany;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getBankcodeno() {
		return bankcodeno;
	}

	public void setBankcodeno(String bankcodeno) {
		this.bankcodeno = bankcodeno;
	}

	public Long getBegintime() {
		return begintime;
	}

	public void setBegintime(Long begintime) {
		this.begintime = begintime;
	}

	public String getCapitalno() {
		return capitalno;
	}

	public void setCapitalno(String capitalno) {
		this.capitalno = capitalno;
	}
    
}
