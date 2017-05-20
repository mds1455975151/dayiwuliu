package com.tianrui.api.req.admin.vehicle_new;

/** 司机mongodb查询类*/
public class VehicleMGoReq {

	private String id;
	
	private String vheicleNo;
	
	private Integer pageNo;
	
	private Integer pageSize;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVheicleNo() {
		return vheicleNo;
	}

	public void setVheicleNo(String vheicleNo) {
		this.vheicleNo = vheicleNo;
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
}
