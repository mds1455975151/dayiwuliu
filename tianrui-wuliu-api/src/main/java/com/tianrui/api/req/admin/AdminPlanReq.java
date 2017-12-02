package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

/**
 * 后台货运计划管理请求数据
 * @类描述：
 * @创建人：lsj
 * @创建时间：2016年6月13日上午8:48:40
 *
 * @修改人：lsj
 * @修改时间：2016年6月13日上午8:48:40
 * @修改备注：
 *
 */
public class AdminPlanReq extends BaseReq{
	
	private static final long serialVersionUID = -4843347847943484730L;

	private String id;
	
	private String plancode;
	
	private String cargoname;

	private String status;
	
	private String createtimeForStr;
	
	private String createtimeEndStr;
	
	private String goodsId;//货源id
	
	
	private String orgId;
	
	private String 	venderName;
	

	public String getPlancode() {
		return plancode;
	}

	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}

	public String getCargoname() {
		return cargoname;
	}

	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatetimeForStr() {
		return createtimeForStr;
	}

	public void setCreatetimeForStr(String createtimeForStr) {
		this.createtimeForStr = createtimeForStr;
	}

	public String getCreatetimeEndStr() {
		return createtimeEndStr;
	}

	public void setCreatetimeEndStr(String createtimeEndStr) {
		this.createtimeEndStr = createtimeEndStr;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getVenderName() {
		return venderName;
	}

	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	
	
}
