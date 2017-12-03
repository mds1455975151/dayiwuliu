package com.tianrui.api.req.goods;

public class PlanGoodsReq {

	private Integer pageNo;
	
	private Integer pageSize;
	
	private String searchKey;
	
	private String cargoname;
	
	private String startOp;//启运省
	
	private String startOc;//启运市
	
	private String endOp;//到货省
	
	private String endOc;//到货市
	
	private String plancode;
	
	private Byte status;
	
	private Byte isfamily;
	
	private String freightname;
	
	private String creator;

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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getCargoname() {
		return cargoname;
	}

	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}

	public String getPlancode() {
		return plancode;
	}

	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}

	public String getFreightname() {
		return freightname;
	}

	public void setFreightname(String freightname) {
		this.freightname = freightname;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getIsfamily() {
		return isfamily;
	}

	public void setIsfamily(Byte isfamily) {
		this.isfamily = isfamily;
	}

	public String getStartOp() {
		return startOp;
	}

	public void setStartOp(String startOp) {
		this.startOp = startOp;
	}

	public String getStartOc() {
		return startOc;
	}

	public void setStartOc(String startOc) {
		this.startOc = startOc;
	}

	public String getEndOp() {
		return endOp;
	}

	public void setEndOp(String endOp) {
		this.endOp = endOp;
	}

	public String getEndOc() {
		return endOc;
	}

	public void setEndOc(String endOc) {
		this.endOc = endOc;
	}
}
