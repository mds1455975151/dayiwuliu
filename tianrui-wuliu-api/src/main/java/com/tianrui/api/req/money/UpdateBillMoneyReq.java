package com.tianrui.api.req.money;

public class UpdateBillMoneyReq {

	private String cellphone;//登录账号---必传
	
	private String waybillno;//运单编号

    private Long paidmoney;//实际收入金额

    private Long deductionmoney;//其他抵扣金额合计（油卡等）

    private Long paidtime;//到账时间

    private String capitalno;//交易流水号

	public String getWaybillno() {
		return waybillno;
	}

	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}

	public Long getPaidmoney() {
		return paidmoney;
	}

	public void setPaidmoney(Long paidmoney) {
		this.paidmoney = paidmoney;
	}

	public Long getDeductionmoney() {
		return deductionmoney;
	}

	public void setDeductionmoney(Long deductionmoney) {
		this.deductionmoney = deductionmoney;
	}

	public Long getPaidtime() {
		return paidtime;
	}

	public void setPaidtime(Long paidtime) {
		this.paidtime = paidtime;
	}

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
    
}
