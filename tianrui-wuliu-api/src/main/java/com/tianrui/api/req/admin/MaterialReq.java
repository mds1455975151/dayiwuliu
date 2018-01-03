package com.tianrui.api.req.admin;

/**
 * 货物查询条件bean
 * 
 */
public class MaterialReq{

	/** 主键 */
	private String id;
	//物料id拼接的字符串id
	private String materialIds;
	/** 组织名称 */
	private String orgName;
	/** 组织类型 */
	private String orgType;
	/** 组织编码 */
	private String orgCode;
	/** 物料编码 */
	private String materCode;
	/** 物料名称 */
	private String cargoName;
	/** 创建人  修改人*/
	private String creator;
	
	private Integer pageNo;
	
	private Integer pageSize;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getMaterialIds() {
		return materialIds;
	}

	public void setMaterialIds(String materialIds) {
		this.materialIds = materialIds;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getMaterCode() {
		return materCode;
	}

	public void setMaterCode(String materCode) {
		this.materCode = materCode;
	}

	public String getCargoName() {
		return cargoName;
	}

	public void setCargoName(String cargoName) {
		this.cargoName = cargoName;
	}
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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
