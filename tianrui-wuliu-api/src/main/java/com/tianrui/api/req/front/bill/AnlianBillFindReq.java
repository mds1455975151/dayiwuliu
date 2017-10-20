package com.tianrui.api.req.front.bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AnlianBillFindReq {

	private String id;
	
	private String searchKey;
	
	private String type;
	
	private String billno;

    private String ownerid;

    private String driverid;

    private String venderid;
    
    private String desc4;
    
    private Long createtime;
    
    private Integer pageNo;
    
    private Integer pageSize;
    
    private String shr;//收货人
    private String starttime;
	private String endtime;
	private Long starttimes;
	private Long endtimes;
   // private String createtimeStr;//创建时间

	public String getBillno() {
		return billno;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getVenderid() {
		return venderid;
	}
	public void setVenderid(String venderid) {
		this.venderid = venderid;
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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc4() {
		return desc4;
	}

	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
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
	
	
	public Long getStarttimes() throws ParseException {
		if(starttime!=null&&starttime!=""){
			starttimes =(new SimpleDateFormat("yyyy-MM-dd").parse(starttime)).getTime();
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
	
}
