package com.tianrui.service.jtb;

public class BillMassageReq {
	/** 物流平台运单号*/
	private String originalDocumentNumber;
	/** 无车承运试点企业名称*/
	private String carrier;
	/** 无车承运人统一社会信用代码*/
	private String unifiedSocialCreditIdentifier;
	/** 无车承运人道路运输经营许可证*/
	private String permitNumber;
	/** 订单生成时间*/
	private String consignmentDateTime;
	/** 业务类型代码 _ 代码集4.2.2*/
	private String businessTypeCode;
	/** 发车时间*/
	private String despatchActualDateTime;
	/** 签收时间*/
	private String goodsReceiptDateTime;
	/** 发货方信息发货人*/
	private String consignor;
	/** 装货地 国家行政区域代码*/
	private String countrySubdivisionCode;
	/** 收货方信息*/
	private String consignee;
	/** 收获地 国际行政区域代码*/
	private String receiptCountrySubdivisionCode;
	/** 总金额 三位小数 整数 .000*/
	private String totalMonetaryAmount;
	/** 车辆牌照类型 代码集4.2.3*/
	private String licensePlateTypeCode;
	/** 车牌号*/
	private String vehicleNumber;
	/** 车辆分类 4.2.4*/
	private String vehicleClassificationCode;
	/** 车辆载重量 2位小数*/
	private String vehicleTonnage;
	/** 车辆道路运输经营许可证*/
	private String roadTransportCertificateNumber;
	/** 司机姓名*/
	private String nameOfPerson; 
	/**司机电话*/
	private String telephoneNumber;
	/** 货物名称*/
	private String descriptionOfGoods;
	/** 货物分类代码 4.2.5*/
	private String cargoTypeClassificationCode;
	/** 货物毛重 3位小数*/
	private String goodsItemGrossWeight;


	public String getOriginalDocumentNumber() {
		return originalDocumentNumber;
	}

	public void setOriginalDocumentNumber(String originalDocumentNumber) {
		this.originalDocumentNumber = originalDocumentNumber;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getUnifiedSocialCreditIdentifier() {
		return unifiedSocialCreditIdentifier;
	}

	public void setUnifiedSocialCreditIdentifier(String unifiedSocialCreditIdentifier) {
		this.unifiedSocialCreditIdentifier = unifiedSocialCreditIdentifier;
	}

	public String getPermitNumber() {
		return permitNumber;
	}

	public void setPermitNumber(String permitNumber) {
		this.permitNumber = permitNumber;
	}

	public String getConsignmentDateTime() {
		return consignmentDateTime;
	}

	public void setConsignmentDateTime(String consignmentDateTime) {
		this.consignmentDateTime = consignmentDateTime;
	}

	public String getBusinessTypeCode() {
		return businessTypeCode;
	}

	public void setBusinessTypeCode(String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	public String getDespatchActualDateTime() {
		return despatchActualDateTime;
	}

	public void setDespatchActualDateTime(String despatchActualDateTime) {
		this.despatchActualDateTime = despatchActualDateTime;
	}

	public String getGoodsReceiptDateTime() {
		return goodsReceiptDateTime;
	}

	public void setGoodsReceiptDateTime(String goodsReceiptDateTime) {
		this.goodsReceiptDateTime = goodsReceiptDateTime;
	}

	public String getConsignor() {
		return consignor;
	}

	public void setConsignor(String consignor) {
		this.consignor = consignor;
	}

	public String getCountrySubdivisionCode() {
		return countrySubdivisionCode;
	}

	public void setCountrySubdivisionCode(String countrySubdivisionCode) {
		this.countrySubdivisionCode = countrySubdivisionCode;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getReceiptCountrySubdivisionCode() {
		return receiptCountrySubdivisionCode;
	}

	public void setReceiptCountrySubdivisionCode(String receiptCountrySubdivisionCode) {
		this.receiptCountrySubdivisionCode = receiptCountrySubdivisionCode;
	}

	public String getTotalMonetaryAmount() {
		return totalMonetaryAmount;
	}

	public void setTotalMonetaryAmount(String totalMonetaryAmount) {
		this.totalMonetaryAmount = totalMonetaryAmount;
	}

	public String getLicensePlateTypeCode() {
		return licensePlateTypeCode;
	}

	public void setLicensePlateTypeCode(String licensePlateTypeCode) {
		this.licensePlateTypeCode = licensePlateTypeCode;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleClassificationCode() {
		return vehicleClassificationCode;
	}

	public void setVehicleClassificationCode(String vehicleClassificationCode) {
		this.vehicleClassificationCode = vehicleClassificationCode;
	}

	public String getVehicleTonnage() {
		return vehicleTonnage;
	}

	public void setVehicleTonnage(String vehicleTonnage) {
		this.vehicleTonnage = vehicleTonnage;
	}

	public String getRoadTransportCertificateNumber() {
		return roadTransportCertificateNumber;
	}

	public void setRoadTransportCertificateNumber(String roadTransportCertificateNumber) {
		this.roadTransportCertificateNumber = roadTransportCertificateNumber;
	}

	public String getNameOfPerson() {
		return nameOfPerson;
	}

	public void setNameOfPerson(String nameOfPerson) {
		this.nameOfPerson = nameOfPerson;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getDescriptionOfGoods() {
		return descriptionOfGoods;
	}

	public void setDescriptionOfGoods(String descriptionOfGoods) {
		this.descriptionOfGoods = descriptionOfGoods;
	}

	public String getCargoTypeClassificationCode() {
		return cargoTypeClassificationCode;
	}

	public void setCargoTypeClassificationCode(String cargoTypeClassificationCode) {
		this.cargoTypeClassificationCode = cargoTypeClassificationCode;
	}

	public String getGoodsItemGrossWeight() {
		return goodsItemGrossWeight;
	}

	public void setGoodsItemGrossWeight(String goodsItemGrossWeight) {
		this.goodsItemGrossWeight = goodsItemGrossWeight;
	}
}
