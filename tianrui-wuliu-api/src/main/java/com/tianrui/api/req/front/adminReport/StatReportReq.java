package com.tianrui.api.req.front.adminReport;

import com.tianrui.api.req.BaseReq;
import com.tianrui.common.utils.DateUtil;

public class StatReportReq extends BaseReq {

	private static final long serialVersionUID = 8492180621540829387L;
	
	private Long starttime;
	//开始时间
	private String starttimeStr;
	
	private Long endtime;
	//结束时间
	private String endtimeStr;
	//货物名称
	private String cargoname;
	//运输路线
	private String routename;
	//计划编号
	private String plancode;
	//组织id
	private String orgid;
	//起始条数
	private int start;
	//查询条数
	private int limit;
	
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public String getStarttimeStr() {
		return starttimeStr;
	}
	public void setStarttimeStr(String starttimeStr) {
		this.starttimeStr = starttimeStr;
		this.setStarttime(DateUtil.parse(starttimeStr, "yyyy-MM-dd"));
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public String getEndtimeStr() {
		return endtimeStr;
	}
	public void setEndtimeStr(String endtimeStr) {
		this.endtimeStr = endtimeStr;
		this.setEndtime(DateUtil.parse(endtimeStr, "yyyy-MM-dd"));
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	public String getPlancode() {
		return plancode;
	}
	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
