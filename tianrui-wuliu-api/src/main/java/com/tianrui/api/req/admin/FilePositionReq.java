package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class FilePositionReq extends BaseReq{

	private static final long serialVersionUID = -7190183760043617974L;

	private String id;
	private String name;

	private String opc;
	private String op;
	private String occ;
	private String oc;
	private String oac;
	private String oa;
	private String addr;
	private Integer lat;
	private Integer lng;
	
	//当前登录用户组织id
	private String currOrgId;
	//当前登录用户
	private String currUser;
	
	private String  status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpc() {
		return opc;
	}
	public void setOpc(String opc) {
		this.opc = opc;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getOcc() {
		return occ;
	}
	public void setOcc(String occ) {
		this.occ = occ;
	}
	public String getOc() {
		return oc;
	}
	public void setOc(String oc) {
		this.oc = oc;
	}
	public String getOac() {
		return oac;
	}
	public void setOac(String oac) {
		this.oac = oac;
	}
	public String getOa() {
		return oa;
	}
	public void setOa(String oa) {
		this.oa = oa;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Integer getLat() {
		return lat;
	}
	public void setLat(Integer lat) {
		this.lat = lat;
	}
	public Integer getLng() {
		return lng;
	}
	public void setLng(Integer lng) {
		this.lng = lng;
	}
	public String getCurrOrgId() {
		return currOrgId;
	}
	public void setCurrOrgId(String currOrgId) {
		this.currOrgId = currOrgId;
	}
	public String getCurrUser() {
		return currUser;
	}
	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

  
}
