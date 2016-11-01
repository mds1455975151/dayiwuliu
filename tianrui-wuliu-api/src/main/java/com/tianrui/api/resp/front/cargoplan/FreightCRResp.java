package com.tianrui.api.resp.front.cargoplan;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.util.DateTypeUtil;

public class FreightCRResp extends BaseResp{
	
	private static final long serialVersionUID = 1101315158367804953L;
	/**
     * id
     */
    private String id;
    /**
     * 所属组织id
     */
    private String organizationid;
    /**
     * 所属组织名称 
     */
    private String organizationname;
    /**
     /**
     * 运价状态（0-可用；1-暂不可用；2-已删除）
     */
    private String status;
    /** */
    private String frebilltype;
    
    /**
     * 货物状态
     */
    private String cStatus;
    /**
     * 路径状态
     */
    private String rStatus;

    /**
     * 货物id
     */
    private String cargoid;

    /**
     * 主计量单位
     */
    private String measure;

    /**
     * 路径id
     */
    private String routeid;

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
    
    private String taketimeStr;
    /** 生效时间*/
    private Long taketime;

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
    /**
     * 货物名称
     */
    private String cargoname;
    /**
     * 货物编码
     */
    private String cargono;
    /**
     * 起运地
     */
    private String oaddr;
    /**
     * 目的地
     */
    private String daddr;
    /**
     * 结算公里数
     */
    private Double distance;
    /**
     * 发货人
     */
    private String sendpersion;
    /**
     * 收货人
     */
    private String receivepersion;
    
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
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
	public String getFrebilltype() {
		return frebilltype;
	}
	public void setFrebilltype(String frebilltype) {
		this.frebilltype = frebilltype;
	}
	public void setFreightType(String freightType) {
		this.freightType = freightType;
	}
	public Double getTallage() {
		return DateTypeUtil.doubleType(tallage);
	}
	public void setTallage(Double tallage) {
		this.tallage = tallage;
	}
	public String getStatus() {
		return status;
	}
	public String getOrganizationname() {
		return organizationname;
	}
	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCargoid() {
		return cargoid;
	}
	public void setCargoid(String cargoid) {
		this.cargoid = cargoid;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getRouteid() {
		return routeid;
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
	public void setRouteid(String routeid) {
		this.routeid = routeid;
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
	public String getPriceunits() {
		return priceunits;
	}
	public void setPriceunits(String priceunits) {
		this.priceunits = priceunits;
	}
	public Double getPrice() {
		return DateTypeUtil.doubleType(price);
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaketimeStr() {
		if(taketime != null){
			taketimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(taketime));
		}
		return taketimeStr;
	}
	public void setTaketimeStr(String taketimeStr) {
		this.taketimeStr = taketimeStr;
	}
	public Long getTaketime() {
		return taketime;
	}
	public void setTaketime(Long taketime) {
		this.taketime = taketime;
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
	public String getOaddr() {
		return oaddr;
	}
	public void setOaddr(String oaddr) {
		this.oaddr = oaddr;
	}
	public String getDaddr() {
		return daddr;
	}
	public void setDaddr(String daddr) {
		this.daddr = daddr;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public String getSendpersion() {
		return sendpersion;
	}
	public void setSendpersion(String sendpersion) {
		this.sendpersion = sendpersion;
	}
	public String getReceivepersion() {
		return receivepersion;
	}
	public void setReceivepersion(String receivepersion) {
		this.receivepersion = receivepersion;
	}
	
}
