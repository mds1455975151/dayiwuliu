package com.tianrui.api.req.money;

public class SaveBillMoneyReq {

	private String cellphone;//登录账号

    private String username;//用户姓名

    private String useryhno;//银行（NC）唯一编号，身份证号

    private String waybillno;//运单编号

    private Long pendingmoney;//预计收入金额

    private Long createtime;//创建时间（卸货完成时间）

    private String capitalno;//流水号

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

	public String getWaybillno() {
		return waybillno;
	}

	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}

	public Long getPendingmoney() {
		return pendingmoney;
	}

	public void setPendingmoney(Long pendingmoney) {
		this.pendingmoney = pendingmoney;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getCapitalno() {
		return capitalno;
	}

	public void setCapitalno(String capitalno) {
		this.capitalno = capitalno;
	}
    
}
