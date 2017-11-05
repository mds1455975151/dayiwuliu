package com.tianrui.service.bean;

public class LEDCount {

	/** 查询开始时间*/
	private Long timeBegin;
	/** 查询结束时间*/
	private Long timeEnd;
	/** 最大值*/
	private Integer max;
	/** 最小值*/
	private Integer min;
	/** 统计数量*/
	private Double countNum;
	/** 统计名称*/
	private String countName;
	/** 备注名称*/
	private String remark;
	
	private Integer pageNo;
	
	private Integer pageSize;
	public Long getTimeBegin() {
		return timeBegin;
	}
	public void setTimeBegin(Long timeBegin) {
		this.timeBegin = timeBegin;
	}
	public Long getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(Long timeEnd) {
		this.timeEnd = timeEnd;
	}
	public Double getCountNum() {
		return countNum;
	}
	public void setCountNum(Double countNum) {
		this.countNum = countNum;
	}
	public String getCountName() {
		return countName;
	}
	public void setCountName(String countName) {
		this.countName = countName;
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
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
	public Integer getMin() {
		return min;
	}
	public void setMin(Integer min) {
		this.min = min;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
