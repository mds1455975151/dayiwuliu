package com.tianrui.api.req.front.adminReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class AnlianBillReportReq {

	private String starttimeStr;
	
	private String type;
	//运单号
	private String waybillno;
	//计划号
	private String plancode;
	//是否有提货地 1-有
	private String t_position;
	//是否有到货地
	private String d_position;
	//业务日期开始
	private Long begintime_0;
	//业务日期结束
	private Long begintime_1;
    //货运时间
    private String interTimeStr;
    //货运距离
    private String interDistanceStr;
    //签收时间起
    private String ownerSigntime_0Str;
    private Long ownerSigntime_0;
    //签收时间止
    private String ownerSigntime_1Str;
    private Long ownerSigntime_1;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
			endtime = endtime+24*60*60*1000;
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

	public String getWaybillno() {
		return waybillno;
	}

	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}

	public String getPlancode() {
		return plancode;
	}

	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}

	public String getT_position() {
		return t_position;
	}

	public void setT_position(String t_position) {
		this.t_position = t_position;
	}

	public String getD_position() {
		return d_position;
	}

	public void setD_position(String d_position) {
		this.d_position = d_position;
	}

	public Long getBegintime_0() {
		return begintime_0;
	}

	public void setBegintime_0(Long begintime_0) {
		this.begintime_0 = begintime_0;
	}

	public Long getBegintime_1() {
		return begintime_1;
	}

	public void setBegintime_1(Long begintime_1) {
		this.begintime_1 = begintime_1;
	}

	public String getInterTimeStr() {
		return interTimeStr;
	}

	public void setInterTimeStr(String interTimeStr) {
		this.interTimeStr = interTimeStr;
	}

	public String getInterDistanceStr() {
		return interDistanceStr;
	}

	public void setInterDistanceStr(String interDistanceStr) {
		this.interDistanceStr = interDistanceStr;
	}

	public Long getOwnerSigntime_0() {
		if(StringUtils.isNotBlank(ownerSigntime_0Str)){
			ownerSigntime_0 = dateExchange(ownerSigntime_0Str).getTime();
		}
		return ownerSigntime_0;
	}

	public void setOwnerSigntime_0(Long ownerSigntime_0) {
		this.ownerSigntime_0 = ownerSigntime_0;
	}

	public Long getOwnerSigntime_1() {
		if(StringUtils.isNotBlank(ownerSigntime_1Str)){
			ownerSigntime_1 = dateExchange(ownerSigntime_1Str).getTime();
			ownerSigntime_1 = ownerSigntime_1+24*60*60*1000;
		}
		return ownerSigntime_1;
	}

	public void setOwnerSigntime_1(Long ownerSigntime_1) {
		this.ownerSigntime_1 = ownerSigntime_1;
	}

	public String getOwnerSigntime_0Str() {
		return ownerSigntime_0Str;
	}

	public void setOwnerSigntime_0Str(String ownerSigntime_0Str) {
		this.ownerSigntime_0Str = ownerSigntime_0Str;
	}

	public String getOwnerSigntime_1Str() {
		return ownerSigntime_1Str;
	}

	public void setOwnerSigntime_1Str(String ownerSigntime_1Str) {
		this.ownerSigntime_1Str = ownerSigntime_1Str;
	}
	
}