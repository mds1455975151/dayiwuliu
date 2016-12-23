package com.tianrui.api.req.front.bill;

import org.springframework.web.multipart.MultipartFile;

import com.tianrui.api.req.BaseReq;

public class WaybillConfirmReq extends BaseReq{

	private static final long serialVersionUID = 1021715600158173293L;
	//运单id
	private String id;
	private String imgdata;
	
	/** 纬线*/
	private Integer lat;
	/** 经线*/
    private Integer lon;
    /**1-起运地 2-提货地  3-卸货地  4-目的地*/
    private String status;
    /** 备注信息*/
    private String remark;
	
	//拒绝原因类型
	private String refuseType;
	//拒绝原因
	private String refuseReson;
	
	//当前登录用户
	private String curruId;
	
	private String weight;
	
	//运单类型
	private String type;
	
	private MultipartFile file;

    //  原发（取货量） / 实收（卸货量）
    private Double psweight;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCurruId() {
		return curruId;
	}

	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getImgdata() {
		return imgdata;
	}

	public void setImgdata(String imgdata) {
		this.imgdata = imgdata;
	}

	public String getRefuseType() {
		return refuseType;
	}

	public void setRefuseType(String refuseType) {
		this.refuseType = refuseType;
	}

	public String getRefuseReson() {
		return refuseReson;
	}

	public void setRefuseReson(String refuseReson) {
		this.refuseReson = refuseReson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Double getPsweight() {
		return psweight;
	}

	public void setPsweight(Double psweight) {
		this.psweight = psweight;
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

}
