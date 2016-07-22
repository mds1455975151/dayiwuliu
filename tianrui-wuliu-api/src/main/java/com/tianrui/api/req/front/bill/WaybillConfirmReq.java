package com.tianrui.api.req.front.bill;

import com.tianrui.api.req.BaseReq;

public class WaybillConfirmReq extends BaseReq{

	private static final long serialVersionUID = 1021715600158173293L;
	//运单id
	private String id;
	private String imgdata;
	
	//拒绝原因类型
	private String refuseType;
	//拒绝原因
	private String refuseReson;
	
	//当前登录用户
	private String curruId;
	
	private String weight;
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
	
	
}
