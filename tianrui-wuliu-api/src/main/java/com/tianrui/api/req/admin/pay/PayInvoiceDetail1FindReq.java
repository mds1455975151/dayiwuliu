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
	
	private Integer pageNo;
	
	private Integer pageSize;
    //账单ID
    private String payInvoiceId;
    //车主ID
    private String venderId;
    
  //运单身份（1：司机运单，2：车主运单）
    private Integer billType;
    
   
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
}