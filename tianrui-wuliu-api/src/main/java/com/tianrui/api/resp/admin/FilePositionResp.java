package com.tianrui.api.resp.admin;

import com.tianrui.api.resp.BaseResp;

/**
 * 
 * @author Administrator
 *
 */
public class FilePositionResp extends BaseResp {

	private static final long serialVersionUID = -7190183760043617974L;

	private String id;
	private String name;
	private String count;
	private String opc;
	private String op;
	private String occ;
	private String oc;
	private String oac;
	private String oa;
	private String addr;
	private Integer lat;
	private Integer lng;
	private String creator;
	private String orgId;
	private short status;
	private String createTime;
	/** 备注*/
    private String remark;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpc() {
		return opc;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
