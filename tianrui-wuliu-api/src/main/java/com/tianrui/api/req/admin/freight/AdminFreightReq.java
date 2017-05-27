package com.tianrui.api.req.admin.freight;

import com.tianrui.api.req.BaseReq;

public class AdminFreightReq extends BaseReq{
	/**
     * id
     */
    private String id;
    /**
     * 所属组织id
     */
    private String organizationid;
    /**
     * 审核状态
     */
    private String auditstatus;
    private String payment;
    /**
     * 所属组织名称 
     */
    private String organizationname;
    /**
     /**
     * 运价状态（0-可用；1-暂不可用；2-已删除）
     */
    private String status;

    /**
     * 货物id
     */
    private String cargoid;
    /**
     * 货物状态
     */
    private String cStatus;

    /**
     * 主计量单位
     */
    private String measure;

    /**
     * 路径id
     */
    private String routeid;
   /**
    * 路径状态
    */
    private String rStatus;

    /**
     * 计价单位
     */
    private String priceunits;

    /**
     * 单价
     */
    private Double price;

    /**
     * 创建者 
     */
    private String creator;

    /**
     * 创建时间
     */
    private Long createtime;

    /**
     * 修改者 
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Long modifytime;

    /**
     * 运价名称
     */
    private String freightName;

    /**
     *	价格类型 0-自由；1-合同
     */
    private String freightType;
    /** 税率*/
    private Double tallage;
    
    private Integer limit;
   
    public Integer getLimit() {
    	limit = (pageNo-1)*pageSize;
    	return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getId() {
		return id;
	}
	
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}
	public String getCargoid() {
		return cargoid;
	}
	public Double getTallage() {
		return tallage;
	}
	public void setTallage(Double tallage) {
		this.tallage = tallage;
	}
	public String getFreightName() {
		return freightName;
	}
	public void setFreightName(String freightName) {
		this.freightName = freightName;
	}
	public String getFreightType() {
		return freightType;
	}
	public void setFreightType(String freightType) {
		this.freightType = freightType;
	}
	public String getOrganizationname() {
		return organizationname;
	}
	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}
	public String getcStatus() {
		return cStatus;
	}
	public void setcStatus(String cStatus) {
		this.cStatus = cStatus;
	}
	public String getrStatus() {
		return rStatus;
	}
	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}
	public void setCargoid(String cargoid) {
		this.cargoid = cargoid;
	}
	public String getRouteid() {
		return routeid;
	}
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getPriceunits() {
		return priceunits;
	}
	public void setPriceunits(String priceunits) {
		this.priceunits = priceunits;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Long getModifytime() {
		return modifytime;
	}
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
}
