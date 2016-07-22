package com.tianrui.api.req.front.vehicle;

import com.tianrui.api.req.BaseReq;

public class OwnerDriverReq extends BaseReq {
	
	private static final long serialVersionUID = -7190183760043617974L;
	
	/**
     * ID
     */
    private String id;
    /**
     * app搜索字段
     */
    private String search;

    /**
     * 车主(会员)id//pc端传值
     */
    private String vehicleownerid;
    /**
     * 车主(会员)id//移动端传值
     */
    private String memberId;

    /**
     * 司机ID
     */
    private String driverid;

    /**
     * 司机姓名
     */
    private String drivername;

    /**
     * 司机电话
     */
    private String drivertel;

    /**
     * 备注名
     */
    private String remarkname;
    
    /** 状态(0-待回复，1-已同意，2-已拒绝，3-已失效) */
    private String status;
    
    /**
     * 创建人
     */

    private String creator;

    /**
     * 创建时间
     */
    private String createtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVehicleownerid() {
		return vehicleownerid;
	}

	public void setVehicleownerid(String vehicleownerid) {
		this.vehicleownerid = vehicleownerid;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDrivertel() {
		return drivertel;
	}

	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}

	public String getRemarkname() {
		return remarkname;
	}

	public void setRemarkname(String remarkname) {
		this.remarkname = remarkname;
	}

	/**
	 * 获取状态
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * 设置状态
	 * @param newStatus
	 */
	public void setStatus(String newStatus) {
		this.status = newStatus;
	}
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatetime() {
		return createtime;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	    
}
