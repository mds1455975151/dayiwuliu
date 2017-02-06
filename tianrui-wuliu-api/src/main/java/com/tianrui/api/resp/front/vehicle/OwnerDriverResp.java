package com.tianrui.api.resp.front.vehicle;

import org.apache.commons.lang.StringUtils;

import com.tianrui.api.resp.BaseResp;

public class OwnerDriverResp extends BaseResp{
	
	private static final long serialVersionUID = -7190183760043617974L;
	
	/**
    * ID
    */
    private String id;

    /**
     * 车主(会员)id
     */
    private String vehicleownerid;

    /**
     * 司机ID
     */
    private String driverid;
    
    private String aldriverid;

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
    private Long createtime;
    /**
     * 绑定车辆数目
     */
    private String count;
    
	/**
	 * 头像路径
	 */
	private String avatarsPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getVehicleownerid() {
		return vehicleownerid;
	}

	public void setVehicleownerid(String vehicleownerid) {
		this.vehicleownerid = vehicleownerid;
	}

	public String getAldriverid() {
		if(StringUtils.isBlank(aldriverid)){
			aldriverid = "";
		}
		return aldriverid;
	}

	public void setAldriverid(String aldriverid) {
		this.aldriverid = aldriverid;
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

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public String getAvatarsPath() {
		return avatarsPath;
	}

	public void setAvatarsPath(String avatarsPath) {
		this.avatarsPath = avatarsPath;
	}
	    
	    
}
