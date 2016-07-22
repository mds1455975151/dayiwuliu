package com.tianrui.api.resp.front.cargoplan;

public class CargoResp {

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
    /**
     * 货物编码
     */
    private String cargono;
    /**
     * 计量单位
     */
    private String measure;
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCargono() {
		return cargono;
	}
	public void setCargono(String cargono) {
		this.cargono = cargono;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
    
    
}
