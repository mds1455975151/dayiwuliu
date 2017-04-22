package com.tianrui.api.req.front.adminReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class AnlianBillReportReq {

	private String starttimeStr;
	
	private Long starttime;
	
	private String endtimeStr;
	
	private Long endtime;
	
	private String remarkname;
	
	private String vehicleno;
	
	private String cargoname;
	
	private String orgid;
	
	private Byte status;
	
	private Integer pageNo;
	
	private Integer pageSize;

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

	public String getRemarkname() {
		return remarkname;
	}

	public void setRemarkname(String remarkname) {
		this.remarkname = remarkname;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getCargoname() {
		return cargoname;
	}

	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getStarttimeStr() {
		return starttimeStr;
	}

	public void setStarttimeStr(String starttimeStr) {
		this.starttimeStr = starttimeStr;
	}

	public String getEndtimeStr() {
		return endtimeStr;
	}

	public void setEndtimeStr(String endtimeStr) {
		this.endtimeStr = endtimeStr;
	}

	public Long getStarttime() {
		if(StringUtils.isNotBlank(starttimeStr)){
			starttime = dateExchange(starttimeStr).getTime();
		}
		return starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	
	public Long getEndtime() {
		if(StringUtils.isNotBlank(endtimeStr)){
			endtime = dateExchange(endtimeStr).getTime();
		}
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public Date dateExchange(String timeStrs){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(timeStrs);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
}