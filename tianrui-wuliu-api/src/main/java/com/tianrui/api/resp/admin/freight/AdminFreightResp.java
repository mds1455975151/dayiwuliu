package com.tianrui.api.resp.admin.freight;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.util.DateTypeUtil;

public class AdminFreightResp extends BaseResp{
	/**
     * id
     */
    private String id;
    
    private String infoid;

    private Long modifytime;
    
    private String taketimeStr;
    private String taketimeStrinfo;
    
    private Long taketime;
    private Long taketimeinfo;
    /** 生效前单价*/
    private Double oldprice;
    /** 生效前税率*/
    private Double oldtallage;
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
    private String frebilltype;

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
    private Double priceInfo;

    /**
     * 运价名称
     */
    private String freightName;

    /**
     *	价格类型 0-自由；1-合同
     */
    private String freightType;
    private String freightTypeInfo;
    
    /** 税率*/
    private Double tallage;
    private Double tallageInfo;
    /** 调价原因*/
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

	public String getInfoid() {
		return infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}

	public String getFrebilltype() {
		if(StringUtils.isBlank(frebilltype)){
			frebilltype = "";
		}
		return frebilltype;
	}

	public void setFrebilltype(String frebilltype) {
		this.frebilltype = frebilltype;
	}

	public Double getPriceInfo() {
		return priceInfo;
	}

	public void setPriceInfo(Double priceInfo) {
		this.priceInfo = priceInfo;
	}

	public String getFreightTypeInfo() {
		return freightTypeInfo;
	}

	public void setFreightTypeInfo(String freightTypeInfo) {
		this.freightTypeInfo = freightTypeInfo;
	}

	public Double getTallageInfo() {
		return DateTypeUtil.doubleType(tallageInfo);
	}

	public void setTallageInfo(Double tallageInfo) {
		this.tallageInfo = tallageInfo;
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

	public Long getModifytime() {
		return modifytime;
	}

	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
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

	public Long getTaketimeinfo() {
		return taketimeinfo;
	}

	public void setTaketimeinfo(Long taketimeinfo) {
		this.taketimeinfo = taketimeinfo;
	}

	public Double getOldprice() {
		return DateTypeUtil.doubleType(oldprice);
	}

	public void setOldprice(Double oldprice) {
		this.oldprice = oldprice;
	}

	public Double getOldtallage() {
		return DateTypeUtil.doubleType(oldtallage);
	}

	public String getTaketimeStrinfo() {
		if(taketimeinfo != null){
			taketimeStrinfo = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(taketimeinfo));
		}
		return taketimeStrinfo;
	}

	public void setTaketimeStrinfo(String taketimeStrinfo) {
		this.taketimeStrinfo = taketimeStrinfo;
	}

	public void setOldtallage(Double oldtallage) {
		this.oldtallage = oldtallage;
	}
}
