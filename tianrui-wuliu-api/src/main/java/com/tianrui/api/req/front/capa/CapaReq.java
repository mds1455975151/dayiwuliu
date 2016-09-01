package com.tianrui.api.req.front.capa;

import com.tianrui.api.req.BaseReq;

public class CapaReq extends BaseReq{

	private String id;
	
	private String memberid;
	
	private String cellphone;
	
	private String vehicleid;
	
	private String vehicleno;

	private String ownerid;
	
	private String search;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getSearch() {
		return search;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
}
