package com.tianrui.service.bean.alct;

public class BodyConsignorInfo {

	//发货人姓名
	private String consigner;
	//发货人单位
	private String customerName;
	//发货人识别号    发货人税务识别号
	private String identificationNumberOfTheTaxpayer;
	//发货人联系电话
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
	public String getConsigner() {
		return consigner;
	}
	public void setConsigner(String consigner) {
		this.consigner = consigner;
	}
}
