package com.tianrui.api.resp.front.bill;

public class BillPositionResp {

	private String id;
	/** 运单id*/
	private String billid;
	/** 纬线*/
	private Integer lat;
	/** 经线*/
    private Integer lon;
    /**1-起运地 2-提货地  3-卸货地  4-目的地*/
    private String status;
    /** 备注信息*/
    private String remark;
    /** 创建时间*/
    private Long createtime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public Integer getLat() {
		return lat;
	}
	public void setLat(Integer lat) {
		this.lat = lat;
	}
	public Integer getLon() {
		return lon;
	}
	public void setLon(Integer lon) {
		this.lon = lon;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
}
