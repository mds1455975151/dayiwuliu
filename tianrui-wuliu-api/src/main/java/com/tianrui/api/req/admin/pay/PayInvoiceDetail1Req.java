package com.tianrui.api.req.admin.pay;
/**
 * @Description 运费结算Bean
 * @author zhanggaohao
 * @version 2017年6月20日 下午3:14:02
 */
public class PayInvoiceDetail1Req {
	
	private String id;
	
	private Integer pageNo;
	
	private Integer pageSize;
    //单据编号
    private String code;
    //账单ID
    private String payInvoiceId;
    //司机ID
    private String driverId;
    //司机证件号码
    private String driverIdNo;
    //车主ID
    private String venderId;
    //车主证件号码
    private String venderIdNo;
    //货主ID
    private String ownerId;
    //货主证件号码
    private String ownerIdNo;
    //发票类型CODE
    private String invoiceCode;
    //发票类型NAME
    private String invoiceName;
    //运单ID
    private String billId;
    //运单编码
    private String billCode;
    //运单身份（1：司机运单，2：车主运单）
    private Integer billType;
    //物料编码
    private String materialCode;
    //物料名称
    private String materialName;
    //货物名称
    private String cargoName;
    //运单重量
    private Double billWeight;
    //运单单价
    private Double billPrice;
    //税率
    private Double taxRate;
    //前台运单总价
    private Double receptionBillTotalPrice;
    //前台扣重扣杂
    private Double receptionDeductWeightMisc;
    //前台扣款
    private Double receptionDeductMoney;
    //前台其他扣款
    private Double receptionDeductOther;
    //前台油卡扣款
    private Double receptionDeductOilCard;
    //后台运单总价
    private Double backstageBillTotalPrice;
    //后台扣重扣杂
    private Double backstageDeductWeightMisc;
    //后台扣款
    private Double backstageDeductMoney;
    //后台其他扣款
    private Double backstageDeductOther;
    //后台油卡扣款
    private Double backstageDeductOilCard;
    //是否合单（0：否，1：是）
    private Boolean whetherClose;
    //组织ID
    private String orgid;
    //组织名称
    private String orgname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPayInvoiceId() {
		return payInvoiceId;
	}
	public void setPayInvoiceId(String payInvoiceId) {
		this.payInvoiceId = payInvoiceId;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getDriverIdNo() {
		return driverIdNo;
	}
	public void setDriverIdNo(String driverIdNo) {
		this.driverIdNo = driverIdNo;
	}
	public String getVenderId() {
		return venderId;
	}
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}
	public String getVenderIdNo() {
		return venderIdNo;
	}
	public void setVenderIdNo(String venderIdNo) {
		this.venderIdNo = venderIdNo;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerIdNo() {
		return ownerIdNo;
	}
	public void setOwnerIdNo(String ownerIdNo) {
		this.ownerIdNo = ownerIdNo;
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
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public Integer getBillType() {
		return billType;
	}
	public void setBillType(Integer billType) {
		this.billType = billType;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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
	public Boolean getWhetherClose() {
		return whetherClose;
	}
	public void setWhetherClose(Boolean whetherClose) {
		this.whetherClose = whetherClose;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
    
}