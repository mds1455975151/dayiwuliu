package com.tianrui.service.bean.alct;

public class BodyGoodsInfo {

	//货物描述
	private String descriptionOfGoods;
	//货物分类代码  参考下表货物分类代码表
	private int goodsPropertyTypeCode;
	//单位重量   单位 千克
	private double unitWight;
	//单位体积   单位 立方米
	private double unitVol;
	//货物单位  例如：车，箱，件
	private String Unit;
	//货物数量
	private int Quantity;
	
	
	public String getDescriptionOfGoods() {
		return descriptionOfGoods;
	}
	public void setDescriptionOfGoods(String descriptionOfGoods) {
		this.descriptionOfGoods = descriptionOfGoods;
	}
	public int getGoodsPropertyTypeCode() {
		return goodsPropertyTypeCode;
	}
	public void setGoodsPropertyTypeCode(int goodsPropertyTypeCode) {
		this.goodsPropertyTypeCode = goodsPropertyTypeCode;
	}
	public double getUnitWight() {
		return unitWight;
	}
	public void setUnitWight(double unitWight) {
		this.unitWight = unitWight;
	}
	public double getUnitVol() {
		return unitVol;
	}
	public void setUnitVol(double unitVol) {
		this.unitVol = unitVol;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
}
