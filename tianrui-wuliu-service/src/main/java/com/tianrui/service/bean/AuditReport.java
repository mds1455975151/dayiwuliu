package com.tianrui.service.bean;


public class AuditReport {
	/**
	 * 主键id
	 */
	private Long id;
	/**
	 * 用户审核失败数量
	 */
	private String userByNum;
	/**
	 * 用户审核成功数量
	 */
	private String userFailNum;
	/**
	 * 司机审核失败数量
	 */
	private String driverByNum;
	/**
	 * 司机审核成功数量
	 */
	private String driverFailNum;
	/**
	 * 车辆审核失败数量
	 */
	private String vehicleByNum;
	/**
	 * 车辆审核成功数量
	 */
	private String vehicleFailNum;
	/**
	 * 银行卡审核失败数量
	 */
	private String bankcardByNum;
	/**
	 * 银行卡审核成功数量
	 */
	private String bankcardFailNum;
	/**
	 * 大易/交通部运单未推送数量
	 */
	private String waybillByPushDJ;
	/**
	 * 大易/交通部运单已推送数量
	 */
	private String waybillFailPushDJ;
	/**
	 * 安联/交通部运单未推送数量
	 */
	private String waybillByPushAJ;
	/**
	 * 安联/交通部运单已推送数量
	 */
	private String waybillFailPushAJ;
	/**
	 * 审核时间
	 */
	private Long reviewTime;
	/**
	 * 创建时间
	 */
	private Long creatertime;
	/**
	 * 修改人
	 */
	private String modify;
	/**
	 * 修改时间
	 */
	private String modifytime;
	/**
	 * 开始查询时间
	 */
	private Long starttime;
	/**
	 * 结束查询时间
	 */
	private Long endtime;
	
	private int pageNo;
	/**
	 * 每页几条
	 */
	private int pageSize;
	private Integer limit;
	
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserByNum() {
		return userByNum;
	}
	public void setUserByNum(String userByNum) {
		this.userByNum = userByNum;
	}
	public String getUserFailNum() {
		return userFailNum;
	}
	public void setUserFailNum(String userFailNum) {
		this.userFailNum = userFailNum;
	}
	public String getDriverByNum() {
		return driverByNum;
	}
	public void setDriverByNum(String driverByNum) {
		this.driverByNum = driverByNum;
	}
	public String getDriverFailNum() {
		return driverFailNum;
	}
	public void setDriverFailNum(String driverFailNum) {
		this.driverFailNum = driverFailNum;
	}
	public String getVehicleByNum() {
		return vehicleByNum;
	}
	public void setVehicleByNum(String vehicleByNum) {
		this.vehicleByNum = vehicleByNum;
	}
	public String getVehicleFailNum() {
		return vehicleFailNum;
	}
	public void setVehicleFailNum(String vehicleFailNum) {
		this.vehicleFailNum = vehicleFailNum;
	}
	public String getBankcardByNum() {
		return bankcardByNum;
	}
	public void setBankcardByNum(String bankcardByNum) {
		this.bankcardByNum = bankcardByNum;
	}
	public String getBankcardFailNum() {
		return bankcardFailNum;
	}
	public void setBankcardFailNum(String bankcardFailNum) {
		this.bankcardFailNum = bankcardFailNum;
	}
	public String getWaybillByPushDJ() {
		return waybillByPushDJ;
	}
	public void setWaybillByPushDJ(String waybillByPushDJ) {
		this.waybillByPushDJ = waybillByPushDJ;
	}
	public String getWaybillFailPushDJ() {
		return waybillFailPushDJ;
	}
	public void setWaybillFailPushDJ(String waybillFailPushDJ) {
		this.waybillFailPushDJ = waybillFailPushDJ;
	}
	public String getWaybillByPushAJ() {
		return waybillByPushAJ;
	}
	public void setWaybillByPushAJ(String waybillByPushAJ) {
		this.waybillByPushAJ = waybillByPushAJ;
	}
	public String getWaybillFailPushAJ() {
		return waybillFailPushAJ;
	}
	public void setWaybillFailPushAJ(String waybillFailPushAJ) {
		this.waybillFailPushAJ = waybillFailPushAJ;
	}
	
	public Long getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Long reviewTime) {
		this.reviewTime = reviewTime;
	}
	public String getModify() {
		return modify;
	}
	public void setModify(String modify) {
		this.modify = modify;
	}
	public String getModifytime() {
		return modifytime;
	}
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}
	public Long getCreatertime() {
		return creatertime;
	}
	public void setCreatertime(Long creatertime) {
		this.creatertime = creatertime;
	}

	
	

	

	
	
}
