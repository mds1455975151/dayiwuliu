package com.tianrui.service.bean.alct;

public class BodyPriceInfo {

	//运单运费   单位分
	private long price;
	//计价方式   按体积：VOL   	按总量：WT
	private String measurementFunctionCode;
	//价格货币代码   固定值	人民币：CNY
	private String priceCurrencyCode="CNY";
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getMeasurementFunctionCode() {
		return measurementFunctionCode;
	}
	public void setMeasurementFunctionCode(String measurementFunctionCode) {
		this.measurementFunctionCode = measurementFunctionCode;
	}
	public String getPriceCurrencyCode() {
		return priceCurrencyCode;
	}
	public void setPriceCurrencyCode(String priceCurrencyCode) {
		this.priceCurrencyCode = priceCurrencyCode;
	}
}
