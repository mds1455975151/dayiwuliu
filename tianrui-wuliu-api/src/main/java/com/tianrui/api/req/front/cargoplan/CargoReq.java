package com.tianrui.api.req.front.cargoplan;

import com.tianrui.api.req.BaseReq;

public class CargoReq extends BaseReq {

	private String id;
	 /**
    * 所属组织名称 
    */
    private String organizationname;

    /**
     * 所属组织id
     */
    private String organizationid;
    /**
     *  货物状态（0-可用；1-暂不可用；2-已删除）
     */
    private String status;
    /**
     * 货物名称
     */
    private String cargoname;
   
    /** 分页开始位置 */
    private Integer start;
    
    /** 分页数据行数 */
    private Integer limit;
    
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganizationname() {
		return organizationname;
	}
	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
   
   
}
