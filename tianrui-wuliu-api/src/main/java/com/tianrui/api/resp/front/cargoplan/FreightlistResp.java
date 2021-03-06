package com.tianrui.api.resp.front.cargoplan;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.util.DateTypeUtil;

public class FreightlistResp extends BaseResp{
	/**
     * id
     */
    private String id;

    /**
     * 运价状态（0-可用；1-暂不可用；2-已删除）
     */
    private String status;
    /**
     * 被调用次数
     */
    private String count;
    /**
     * 审核状态
     */
    private String auditstatus;
    /**
     * 审核原因
     */
    private String auditreason;
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
     * 运价名称
     */
    private String freightName;

    /**
     *	价格类型 0-自由；1-合同
     */
    private String freightType;
    
    /** 税率*/
    private Double tallage;
    private String desc1;
    
    /**
    *货物状态
    */
   private String desc3;

   /**
    *路径状态
    */
   private String desc4;

	public String getId() {
		return id;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	public String getDesc4() {
		return desc4;
	}

	public String getAuditstatus() {
		return auditstatus;
	}

	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}

	public String getAuditreason() {
		return auditreason;
	}

	public void setAuditreason(String auditreason) {
		this.auditreason = auditreason;
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

	public Double getTallage() {
		return DateTypeUtil.doubleType(tallage);
	}

	public void setTallage(Double tallage) {
		this.tallage = tallage;
	}

	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}

	public String getStatus() {
		return status;
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

	public void setRouteid(String routeid) {
		this.routeid = routeid;
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

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
    
    
}
