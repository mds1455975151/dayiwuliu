package com.tianrui.api.req.front.bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SignerBillFindReq {
    private String id;
    private String billtype;
    private String cargoname;
    private String vehicleno;
    private String billno;
    private Integer pageNo;
    private String billstatus;
    
    private Integer pageSize;

    private String searchKey;
    //接收人id
    private String receiveMemberid;
    
    private String paystatus;
    
    private String starttime;
    private String endtime;
    private Long starttimes;
    private Long endtimes;
    
    
	public Long getStarttimes() throws ParseException {
		if(starttime!=null&&starttime!=""){
			starttimes =(new SimpleDateFormat("yyyy-MM-dd").parse(starttime)).getTime()+24*60*60*1000;
			return starttimes;
		}
		return starttimes;
	}

	public Long getEndtimes() throws ParseException {
		if(endtime!=null&&endtime!=""){
			endtimes =(new SimpleDateFormat("yyyy-MM-dd").parse(endtime)).getTime()+24*60*60*1000;
			return endtimes;
		}
		return endtimes;
	}
	public String getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}

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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getReceiveMemberid() {
		return receiveMemberid;
	}

	public void setReceiveMemberid(String receiveMemberid) {
		this.receiveMemberid = receiveMemberid;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public String getCargoname() {
		return cargoname;
	}

	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getBillstatus() {
		return billstatus;
	}

	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
    
}