package com.tianrui.api.req.goods;

public class GoodsTOPlanReq {

	private String userId;
	private String goodsid;//货源id
	private String venderid;//车主id
	private Double price;//单价
	private Double weight;//重量
	
	private byte isfamily;
	private byte messageType;
	
	public String getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}
	public String getVenderid() {
		return venderid;
	}
	public void setVenderid(String venderid) {
		this.venderid = venderid;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public byte getIsfamily() {
		return isfamily;
	}
	public void setIsfamily(byte isfamily) {
		this.isfamily = isfamily;
	}
	public byte getMessageType() {
		return messageType;
	}
	public void setMessageType(byte messageType) {
		this.messageType = messageType;
	}
}
