package com.tianrui.service.bean.alct;

import java.util.List;

public class ShipmentsBody {

	//客户运单编号
	private String shippingNoteNumber;
	//运单创建时间
	private String consignmentDateTime;
	//业务类型 固定为 10表示 公路 整车
	private int businessTypeCode = 10;
	//提货地址
	private String pickupLocation;
	//提货地区编码
	private int pickupCountrySubdivisionCode;
	//卸货地址
	private String unloadLocation;
	//卸货地区编码
	private int unloadCountrySubdivisionCode;
	//运输距离  单位公里
	private double distance;
	//要求提货最早时间  yyyyMMDDThhmmss
	private String planPickupTimeStart;
	//要求提货最晚时间 yyyyMMDDThhmmss
	private String planPickupTimeEnd;
	//要求卸货最早时间
	private String planUnloadTimeStart;
	//要求卸货最晚时间
	private String planUnloadTimeEnd;
	//货物类型代码  10-普货   20-冷冻品    30–危险品
	private int cargoTypeClassificationCode;
	//司机身份证号  必须是已在陆交平台上注册的司机
	private String driverIdentification;
	//车牌号  必须是已在陆交平台上注册的车辆，且车辆与司机有关联关系。
	private String vehicleNumber;
	//总重量 单位 吨
	private double totalWeight;
	//总体积 单位 立方米
	private double totalCube;
	//价格模块
	private BodyPriceInfo priceInfo;
	//货物模块
	private List<BodyGoodsInfo> goodsInfo;
	//发货人模块
	private BodyConsignorInfo consignorInfo;
	//收货人模块
	private BodyConsigneeInfo consigneeInfo;
	
	public String getShippingNoteNumber() {
		return shippingNoteNumber;
	}
	public void setShippingNoteNumber(String shippingNoteNumber) {
		this.shippingNoteNumber = shippingNoteNumber;
	}
	public String getConsignmentDateTime() {
		return consignmentDateTime;
	}
	public void setConsignmentDateTime(String consignmentDateTime) {
		this.consignmentDateTime = consignmentDateTime;
	}
	public int getBusinessTypeCode() {
		return businessTypeCode;
	}
	public void setBusinessTypeCode(int businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}
	public String getPickupLocation() {
		return pickupLocation;
	}
	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}
	public int getPickupCountrySubdivisionCode() {
		return pickupCountrySubdivisionCode;
	}
	public void setPickupCountrySubdivisionCode(int pickupCountrySubdivisionCode) {
		this.pickupCountrySubdivisionCode = pickupCountrySubdivisionCode;
	}
	public String getUnloadLocation() {
		return unloadLocation;
	}
	public void setUnloadLocation(String unloadLocation) {
		this.unloadLocation = unloadLocation;
	}
	public int getUnloadCountrySubdivisionCode() {
		return unloadCountrySubdivisionCode;
	}
	public void setUnloadCountrySubdivisionCode(int unloadCountrySubdivisionCode) {
		this.unloadCountrySubdivisionCode = unloadCountrySubdivisionCode;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getPlanPickupTimeStart() {
		return planPickupTimeStart;
	}
	public void setPlanPickupTimeStart(String planPickupTimeStart) {
		this.planPickupTimeStart = planPickupTimeStart;
	}
	public String getPlanPickupTimeEnd() {
		return planPickupTimeEnd;
	}
	public void setPlanPickupTimeEnd(String planPickupTimeEnd) {
		this.planPickupTimeEnd = planPickupTimeEnd;
	}
	public String getPlanUnloadTimeStart() {
		return planUnloadTimeStart;
	}
	public void setPlanUnloadTimeStart(String planUnloadTimeStart) {
		this.planUnloadTimeStart = planUnloadTimeStart;
	}
	public String getPlanUnloadTimeEnd() {
		return planUnloadTimeEnd;
	}
	public void setPlanUnloadTimeEnd(String planUnloadTimeEnd) {
		this.planUnloadTimeEnd = planUnloadTimeEnd;
	}
	public int getCargoTypeClassificationCode() {
		return cargoTypeClassificationCode;
	}
	public void setCargoTypeClassificationCode(int cargoTypeClassificationCode) {
		this.cargoTypeClassificationCode = cargoTypeClassificationCode;
	}
	public String getDriverIdentification() {
		return driverIdentification;
	}
	public void setDriverIdentification(String driverIdentification) {
		this.driverIdentification = driverIdentification;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public double getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}
	public double getTotalCube() {
		return totalCube;
	}
	public void setTotalCube(double totalCube) {
		this.totalCube = totalCube;
	}
	public BodyPriceInfo getPriceInfo() {
		return priceInfo;
	}
	public void setPriceInfo(BodyPriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}
	public List<BodyGoodsInfo> getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(List<BodyGoodsInfo> goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public BodyConsignorInfo getConsignorInfo() {
		return consignorInfo;
	}
	public void setConsignorInfo(BodyConsignorInfo consignorInfo) {
		this.consignorInfo = consignorInfo;
	}
	public BodyConsigneeInfo getConsigneeInfo() {
		return consigneeInfo;
	}
	public void setConsigneeInfo(BodyConsigneeInfo consigneeInfo) {
		this.consigneeInfo = consigneeInfo;
	}
}
