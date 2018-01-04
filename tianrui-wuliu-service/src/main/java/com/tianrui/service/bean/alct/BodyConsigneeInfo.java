package com.tianrui.service.bean.alct;

public class BodyConsigneeInfo {

	//收货人姓名
	private String consignee;
	//收货人单位
	private String customerName;
	//收货人识别号    发货人税务识别号
	private String identificationNumberOfTheTaxpayer;
	//收货人联系电话
	private String telephone;
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getIdentificationNumberOfTheTaxpayer() {
		return identificationNumberOfTheTaxpayer;
	}
	public void setIdentificationNumberOfTheTaxpayer(String identificationNumberOfTheTaxpayer) {
		this.identificationNumberOfTheTaxpayer = identificationNumberOfTheTaxpayer;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
}
