package com.tianrui.api.req.admin.pay;
/**
 * @Description 运费结算Bean
 * @author zhanggaohao
 * @version 2017年6月20日 下午3:14:02
 */
public class PayInvoiceDetail1FindReq {
	
	private String id;
	//多条id
	private String idStr;
	
	private String searchKey;
	//运价确认状态 1-未确认  2-已确认  3-已合单
	private String billPayStatus;
	//单据编号
    private String code;
    //发票类型NAME
    private String invoiceName;
    //说明al-安联运单  dy-大易运单
    private String remark;
    //货物名称
    private String cargoName;
    
    private Boolean whetherClose;
	
	private Integer pageNo;
	
	private Integer pageSize;
    //账单ID
    private String payInvoiceId;
    //车主ID
    private String venderId;
    //司机ID
    private String driverId;
    //运单身份（1：司机运单，2：车主运单）
    private Integer billType;
	
    public Boolean getWhetherClose() {
		return whetherClose;
	}
	public void setWhetherClose(Boolean whetherClose) {
		this.whetherClose = whetherClose;
	}
	public Integer getBillType() {
		return billType;
	}
	public void setBillType(Integer billType) {
		this.billType = billType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdStr() {
		return idStr;
	}
	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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
	public String getPayInvoiceId() {
		return payInvoiceId;
	}
	public void setPayInvoiceId(String payInvoiceId) {
		this.payInvoiceId = payInvoiceId;
	}
	public String getVenderId() {
		return venderId;
	}
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInvoiceName() {
		return invoiceName;
	}
	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCargoName() {
		return cargoName;
	}
	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	public String getDriverId() {
		return driverId;
	}
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}
	public String getBillPayStatus() {
		return billPayStatus;
	}
	public void setBillPayStatus(String billPayStatus) {
		this.billPayStatus = billPayStatus;
	}
}