package com.tianrui.api.resp.pay;

/** 支付单详情*/
public class PayAndBillDateilResp {
	//账单id
	private String id;
	//运单ID
	private String billId;
	//运单类型
	private String billOwerType;
	private Double pickupweight; 
    private String pickupimgurl;
    
    private Double signweight; 
    private String signimgurl;
    
    private Double trueweight;
    
    private String remarkImg;
	
	//账单编号
	private String payCode;
	//运单号
	private String billNo;
	//计划号
	private String plancode;
	//车牌号
	private String vehicleNo;
	//车辆ID
	private String vehicleID;
	//收货方
	private String conMer;
	private String conMerCode;
	//发货方
	private String shipMer;
	private String shipMerCode;
	//路线
	private String routeName;
	//货主账号
	private String ownerCellphone;
	private String ownerName;
	//车主账号
	private String venderCellphone;
	private String venderName;
	//司机账号
	private String driverCellphone;
	//司机ID
	private String driverId;
	private String driverName;
	private String driverAlcode;
	//收获人
	private String receiveCellphone;
	private String receiveName;
	//支付对象
	private String payMent;
	//银行卡id
	private String bankId;
	
	private String bankCellPhone;
	
	private String bankMember;
	//银行卡号
	private String bankcard;
	//银行名称
	private String bankname;
	//开户行名称
	private String bankAdreess;
	//发票类型CODE
    private String invoiceCode;
    //发票类型NAME
    private String invoiceName;
    //货物名称
    private String cargoName;
    //运单重量
    private Double billWeight;
    //运单单价
    private Double billPrice;
    private Double billweightB;
	private Double billpriceB;
    //税率
    private Double taxRate;
    //前台运单总价
    private Double receptionBillTotalPrice;
    //后台运单总价
    private Double backstageBillTotalPrice;
    //前台扣重扣杂
    private Double receptionDeductWeightMisc;
    //前台扣款
    private Double receptionDeductMoney;
    //前台其他扣款
    private Double receptionDeductOther;
    //前台油卡扣款
    private Double receptionDeductOilCard;
    //后台扣重扣杂
    private Double backstageDeductWeightMisc;
    //后台扣款
    private Double backstageDeductMoney;
    //后台其他扣款
    private Double backstageDeductOther;
    //后台油卡扣款
    private Double backstageDeductOilCard;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPayCode() {
		return payCode;
	}
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getPlancode() {
		return plancode;
	}
	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getOwnerCellphone() {
		return ownerCellphone;
	}
	public void setOwnerCellphone(String ownerCellphone) {
		this.ownerCellphone = ownerCellphone;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getVenderCellphone() {
		return venderCellphone;
	}
	public void setVenderCellphone(String venderCellphone) {
		this.venderCellphone = venderCellphone;
	}
	public String getVenderName() {
		return venderName;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	public String getDriverCellphone() {
		return driverCellphone;
	}
	public void setDriverCellphone(String driverCellphone) {
		this.driverCellphone = driverCellphone;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getReceiveCellphone() {
		return receiveCellphone;
	}
	public void setReceiveCellphone(String receiveCellphone) {
		this.receiveCellphone = receiveCellphone;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getPayMent() {
		return payMent;
	}
	public void setPayMent(String payMent) {
		this.payMent = payMent;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public Double getBillWeight() {
		return billWeight;
	}
	public void setBillWeight(Double billWeight) {
		this.billWeight = billWeight;
	}
	public Double getBillPrice() {
		return billPrice;
	}
	public void setBillPrice(Double billPrice) {
		this.billPrice = billPrice;
	}
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	public Double getReceptionBillTotalPrice() {
		return receptionBillTotalPrice;
	}
	public void setReceptionBillTotalPrice(Double receptionBillTotalPrice) {
		this.receptionBillTotalPrice = receptionBillTotalPrice;
	}
	public Double getBackstageBillTotalPrice() {
		return backstageBillTotalPrice;
	}
	public void setBackstageBillTotalPrice(Double backstageBillTotalPrice) {
		this.backstageBillTotalPrice = backstageBillTotalPrice;
	}
	public Double getReceptionDeductWeightMisc() {
		return receptionDeductWeightMisc;
	}
	public void setReceptionDeductWeightMisc(Double receptionDeductWeightMisc) {
		this.receptionDeductWeightMisc = receptionDeductWeightMisc;
	}
	public Double getReceptionDeductMoney() {
		return receptionDeductMoney;
	}
	public void setReceptionDeductMoney(Double receptionDeductMoney) {
		this.receptionDeductMoney = receptionDeductMoney;
	}
	public Double getReceptionDeductOther() {
		return receptionDeductOther;
	}
	public void setReceptionDeductOther(Double receptionDeductOther) {
		this.receptionDeductOther = receptionDeductOther;
	}
	public Double getReceptionDeductOilCard() {
		return receptionDeductOilCard;
	}
	public void setReceptionDeductOilCard(Double receptionDeductOilCard) {
		this.receptionDeductOilCard = receptionDeductOilCard;
	}
	public Double getBackstageDeductWeightMisc() {
		return backstageDeductWeightMisc;
	}
	public void setBackstageDeductWeightMisc(Double backstageDeductWeightMisc) {
		this.backstageDeductWeightMisc = backstageDeductWeightMisc;
	}
	public Double getBackstageDeductMoney() {
		return backstageDeductMoney;
	}
	public void setBackstageDeductMoney(Double backstageDeductMoney) {
		this.backstageDeductMoney = backstageDeductMoney;
	}
	public Double getBackstageDeductOther() {
		return backstageDeductOther;
	}
	public void setBackstageDeductOther(Double backstageDeductOther) {
		this.backstageDeductOther = backstageDeductOther;
	}
	public Double getBackstageDeductOilCard() {
		return backstageDeductOilCard;
	}
	public void setBackstageDeductOilCard(Double backstageDeductOilCard) {
		this.backstageDeductOilCard = backstageDeductOilCard;
	}
	public String getBankcard() {
		return bankcard;
	}
	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getDriverAlcode() {
		return driverAlcode;
	}
	public void setDriverAlcode(String driverAlcode) {
		this.driverAlcode = driverAlcode;
	}
	public Double getBillweightB() {
		return billweightB;
	}
	public void setBillweightB(Double billweightB) {
		this.billweightB = billweightB;
	}
	public Double getBillpriceB() {
		return billpriceB;
	}
	public void setBillpriceB(Double billpriceB) {
		this.billpriceB = billpriceB;
	}
	public String getConMer() {
		return conMer;
	}
	public void setConMer(String conMer) {
		this.conMer = conMer;
	}
	public String getShipMer() {
		return shipMer;
	}
	public void setShipMer(String shipMer) {
		this.shipMer = shipMer;
	}
	public String getConMerCode() {
		return conMerCode;
	}
	public void setConMerCode(String conMerCode) {
		this.conMerCode = conMerCode;
	}
	public String getShipMerCode() {
		return shipMerCode;
	}
	public void setShipMerCode(String shipMerCode) {
		this.shipMerCode = shipMerCode;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getBankAdreess() {
		return bankAdreess;
	}
	public void setBankAdreess(String bankAdreess) {
		this.bankAdreess = bankAdreess;
	}
	public Double getPickupweight() {
		return pickupweight;
	}
	public void setPickupweight(Double pickupweight) {
		this.pickupweight = pickupweight;
	}
	public String getPickupimgurl() {
		return pickupimgurl;
	}
	public void setPickupimgurl(String pickupimgurl) {
		this.pickupimgurl = pickupimgurl;
	}
	public Double getSignweight() {
		return signweight;
	}
	public void setSignweight(Double signweight) {
		this.signweight = signweight;
	}
	public String getSignimgurl() {
		return signimgurl;
	}
	public void setSignimgurl(String signimgurl) {
		this.signimgurl = signimgurl;
	}
	public Double getTrueweight() {
		return trueweight;
	}
	public void setTrueweight(Double trueweight) {
		this.trueweight = trueweight;
	}
	public String getRemarkImg() {
		return remarkImg;
	}
	public void setRemarkImg(String remarkImg) {
		this.remarkImg = remarkImg;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankCellPhone() {
		return bankCellPhone;
	}
	public void setBankCellPhone(String bankCellPhone) {
		this.bankCellPhone = bankCellPhone;
	}
	public String getBankMember() {
		return bankMember;
	}
	public void setBankMember(String bankMember) {
		this.bankMember = bankMember;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBillOwerType() {
		return billOwerType;
	}
	public void setBillOwerType(String billOwerType) {
		this.billOwerType = billOwerType;
	}
	public String getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	
}
