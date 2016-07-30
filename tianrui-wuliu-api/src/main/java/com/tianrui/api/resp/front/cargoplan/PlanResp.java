package com.tianrui.api.resp.front.cargoplan;

import java.util.Date;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.common.utils.DateUtil;

public class PlanResp extends BaseResp{

	private static final long serialVersionUID = 1L;

	private String id;

    private String freightid;
    private String routeid;
    private String cargoid;

    private String cargoname;
    private String measure;
    private String priceunits;

    private String startcity;
    private String endcity;

    private Double distance;
    //计划总量
    private Double totalplanned;
    // 计划运价
    private Double planprice;
    // 计划已完成量
    private Double completed;
    //  单价
    private Double price;
    // 是否模板，0-否；1-是
    private Byte istemplate;

    // 计划类型，0-自由；1-合同
    private Byte type;

    private Long starttime;
    private Long endtime;

    //车主接受时间
    private Long acceptedtime;

    // 计划状态（-2已停用 -1-已删除；0 新建；1-待接单；2-执行中；3-已完成）
    private Byte status;
    //联系人信息
    private String linkman;
    private String telephone;
    //车主信息
    private String vehicleownerid;
    private String vehicleownername;
    private String vehicleownerphone;
    //货主信息
    private String creator;
    private Long createtime;
    //创建时间
    private String modifier;
    private Long modifytime;
    //是否为熟车运单 0不是  1熟车运单
    private Byte isfamily;
    
    //计划编码
    private String plancode;
    //车主删除标记
    private Byte venderdelflag;
    //货主删除标记
    private Byte ownerdelflag;
    //车主拒绝原因
    private String refusereson;
    //车主拒绝原因类型
    private String refuseresontype;
    //发货人信息
    private String sendperson;
    private String sendpersonphone;
    //收货人信息
    private String receiveperson;
    private String receivepersonphone;
    
    //货物编码
    private String cargocode;
    //策略名称
    private String freightname;
    //组织id
    private String orgid;
    private String orgname;
    private String ownerName;
    private Double tallage;
	public String getId() {
		return id;
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
	public String getCargoid() {
		return cargoid;
	}
	public void setCargoid(String cargoid) {
		this.cargoid = cargoid;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
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
	public Long getStarttime() {
		return starttime;
	}
	public String getStarttimeStr() {
		if(starttime !=null  ){
			return DateUtil.getDateString(new Date(starttime), "yyyy-MM-dd HH:mm");
		}
		return "";
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public String getEndtimeStr() {
		if(endtime !=null  ){
			return DateUtil.getDateString(new Date(endtime), "yyyy-MM-dd HH:mm");
		}
		return "";
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
	public String getCreatetimeStr() {
		if(createtime !=null  ){
			return DateUtil.getDateString(new Date(createtime), "yyyy-MM-dd HH:mm");
		}
		return "";
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
	public String getModifytimeStr() {
		if(modifytime !=null  ){
			return DateUtil.getDateString(new Date(modifytime), "yyyy-MM-dd HH:mm");
		}
		return "";
	}
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
	public Byte getIsfamily() {
		return isfamily;
	}
	public void setIsfamily(Byte isfamily) {
		this.isfamily = isfamily;
	}
	public String getPlancode() {
		return plancode;
	}
	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}
	public Byte getVenderdelflag() {
		return venderdelflag;
	}
	public void setVenderdelflag(Byte venderdelflag) {
		this.venderdelflag = venderdelflag;
	}
	public Byte getOwnerdelflag() {
		return ownerdelflag;
	}
	public void setOwnerdelflag(Byte ownerdelflag) {
		this.ownerdelflag = ownerdelflag;
	}
	public String getRefusereson() {
		return refusereson;
	}
	public void setRefusereson(String refusereson) {
		this.refusereson = refusereson;
	}
	public String getRefuseresontype() {
		return refuseresontype;
	}
	public void setRefuseresontype(String refuseresontype) {
		this.refuseresontype = refuseresontype;
	}
	public String getSendperson() {
		return sendperson;
	}
	public void setSendperson(String sendperson) {
		this.sendperson = sendperson;
	}
	public String getSendpersonphone() {
		return sendpersonphone;
	}
	public void setSendpersonphone(String sendpersonphone) {
		this.sendpersonphone = sendpersonphone;
	}
	public String getReceiveperson() {
		return receiveperson;
	}
	public void setReceiveperson(String receiveperson) {
		this.receiveperson = receiveperson;
	}
	public String getReceivepersonphone() {
		return receivepersonphone;
	}
	public void setReceivepersonphone(String receivepersonphone) {
		this.receivepersonphone = receivepersonphone;
	}
	public String getCargocode() {
		return cargocode;
	}
	public void setCargocode(String cargocode) {
		this.cargocode = cargocode;
	}
	public String getFreightname() {
		return freightname;
	}
	public void setFreightname(String freightname) {
		this.freightname = freightname;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public Double getTallage() {
		return tallage;
	}
	public void setTallage(Double tallage) {
		this.tallage = tallage;
	}
}
