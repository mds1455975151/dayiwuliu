package com.tianrui.api.req.front.cargoplan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.req.BaseReq;

/**
 * 
 * @类描述：货运计划
 * @创建人：tank
 * @创建时间：2016年4月22日下午4:44:20
 *
 * @修改人：tank
 * @修改时间：2016年4月22日下午4:44:20
 * @修改备注：
 *
 */
public class PlanReq extends BaseReq{
	private static final long serialVersionUID = -3942295359427263186L;

	/**
     *  主键UUID
     */
    private String id;

    /**
     * 策略id
     */
    private String freightid;

    /**
     * 路径id
     */
    private String routeid;

    /**
     * 计量单位
     */
    private String measure;

    /**
     *  货物名称
     */
    private String cargoname;
    /**
     *  货物id
     */
    private String cargoid;

    /**
     *  计价单位
     */
    private String priceunits;

    /**
     * 起始地
     */
    private String startcity;

    /**
     *  目的地
     */
    private String endcity;

    /**
     * 结算公里数
     */
    private Double distance;

    /**
     *  计划总量
     */
    private Double totalplanned;

    /**
     *  计划运价
     */
    private Double planprice;

    /**
     * 计划已完成量
     */
    private Double completed;

    /**
     *  单价
     */
    private Double price;

    /**
     * 是否模板，0-否；1-是
     */
    private Byte istemplate;

    /**
     * 计划类型，0-自由；1-合同
     */
    private Byte type;

    /**
     * 货运计划开始时间（为空表示不限）
     */
    private Long starttime;
    /**
     * 请求值
     */
    private String starttimeStr;
    /**
     * 货运计划结束时间（为空表示不限）
     */
    private Long endtime;
    /**
     * 请求值
     */
    private String endtimeStr;
    /**
     * 车主接受时间
     */
    private Long acceptedtime;

    /**
     * 计划状态（-2-已停用-1-已删除；0-待接单；1-已拒绝；2-执行中；3-已完成）
     */
    private Byte status;

    /**
     *  联系人姓名
     */
    private String linkman;

    /**
     *  联系人电话
     */
    private String telephone;

    /**
     * 车主id
     */
    private String vehicleownerid;

    /**
     * 车主姓名
     */
    private String vehicleownername;

    /**
     * 车主电话
     */
    private String vehicleownerphone;

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
     *  修改时间
     */
    private Long modifytime;
    
    /**
     * 发布对象 1：熟车，2：车主
     */
    private String desc1;
    /**
     * 计划编码
     */
    private String desc2;
    /**
     * 承运计划状态 -1:已删除，0:待接单，1-已拒绝，2:已接受，3:已完成
     */
    private String desc3;
    //查询关键字
	private String key;
    
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getId() {
		return id;
	}

	public String getStarttimeStr() {
		
		return starttimeStr;
	}

	public void setStarttimeStr(String starttimeStr) {
		this.starttimeStr = starttimeStr;
	}

	public String getEndtimeStr() {
		return endtimeStr;
	}

	public void setEndtimeStr(String endtimeStr) {
		this.endtimeStr = endtimeStr;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFreightid() {
		return freightid;
	}

	public void setFreightid(String freightid) {
		this.freightid = freightid;
	}

	public String getRouteid() {
		return routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getCargoname() {
		return cargoname;
	}

	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}

	public String getPriceunits() {
		return priceunits;
	}

	public void setPriceunits(String priceunits) {
		this.priceunits = priceunits;
	}

	public String getStartcity() {
		return startcity;
	}

	public void setStartcity(String startcity) {
		this.startcity = startcity;
	}

	public String getEndcity() {
		return endcity;
	}

	public void setEndcity(String endcity) {
		this.endcity = endcity;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getTotalplanned() {
		return totalplanned;
	}

	public void setTotalplanned(Double totalplanned) {
		this.totalplanned = totalplanned;
	}

	public Double getPlanprice() {
		return planprice;
	}

	public void setPlanprice(Double planprice) {
		this.planprice = planprice;
	}

	public Double getCompleted() {
		return completed;
	}

	public void setCompleted(Double completed) {
		this.completed = completed;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Byte getIstemplate() {
		return istemplate;
	}

	public void setIstemplate(Byte istemplate) {
		this.istemplate = istemplate;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Long getStarttime() throws ParseException {
		Long sta = null;
		if(starttimeStr != null&&!"".equals(starttimeStr)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(starttimeStr);
			sta = date.getTime();
		}
		return sta;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public Long getEndtime() throws ParseException {
		Long sta = null;
		if(endtimeStr != null&&!"".equals(endtimeStr)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(endtimeStr);
			sta = date.getTime();
		}
		return sta;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public Long getAcceptedtime() {
		return acceptedtime;
	}

	public void setAcceptedtime(Long acceptedtime) {
		this.acceptedtime = acceptedtime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getVehicleownerid() {
		return vehicleownerid;
	}

	public void setVehicleownerid(String vehicleownerid) {
		this.vehicleownerid = vehicleownerid;
	}

	public String getVehicleownername() {
		return vehicleownername;
	}

	public void setVehicleownername(String vehicleownername) {
		this.vehicleownername = vehicleownername;
	}

	public String getVehicleownerphone() {
		return vehicleownerphone;
	}

	public void setVehicleownerphone(String vehicleownerphone) {
		this.vehicleownerphone = vehicleownerphone;
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

	public String getCargoid() {
		return cargoid;
	}

	public void setCargoid(String cargoid) {
		this.cargoid = cargoid;
	}

	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
}