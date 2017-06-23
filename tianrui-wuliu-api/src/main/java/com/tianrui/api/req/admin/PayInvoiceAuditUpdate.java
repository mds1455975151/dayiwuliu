package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

/**
 * @Description 账单审核数据
 * @author zhanggaohao
 * @version 2017年6月20日 上午11:25:56
 */
public class PayInvoiceAuditUpdate extends BaseReq {

	private static final long serialVersionUID = 4715061147059379090L;
	
	private String id;
    //后台扣重扣杂
    private Double deductWeightMisc;
    //后台扣款
    private Double deductMoney;
    //后台其他扣款
    private Double deductOther;
    //后台油卡扣款
    private Double deductOilCard;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getDeductWeightMisc() {
		return deductWeightMisc;
	}
	public void setDeductWeightMisc(Double deductWeightMisc) {
		this.deductWeightMisc = deductWeightMisc;
	}
	public Double getDeductMoney() {
		return deductMoney;
	}
	public void setDeductMoney(Double deductMoney) {
		this.deductMoney = deductMoney;
	}
	public Double getDeductOther() {
		return deductOther;
	}
	public void setDeductOther(Double deductOther) {
		this.deductOther = deductOther;
	}
	public Double getDeductOilCard() {
		return deductOilCard;
	}
	public void setDeductOilCard(Double deductOilCard) {
		this.deductOilCard = deductOilCard;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PayInvoiceAuditUpdate [id=" + id + ", deductWeightMisc=" + deductWeightMisc
				+ ", deductMoney=" + deductMoney + ", deductOther=" + deductOther
				+ ", deductOilCard=" + deductOilCard + "]";
	}
	
}