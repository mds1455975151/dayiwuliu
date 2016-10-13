package com.tianrui.api.resp.front.bill;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.common.utils.DateUtil;

public class WaybillResp extends BaseResp{

	private static final long serialVersionUID = 3588964458488414595L;

	private String id;
    private String planid;
    private String vehicleid;
    private String driverid;
    private String routeid;
    private String drivername;
    private String drivertel;
    private String vehicleno;
    private String vehicletypename;
    private String cargoname;
    private String startcity;
    private String consignorname;
    private String consignortel;
    private String endtime;
    private String starttime;
    private String endcity;
    private String receivername;
    private String receivertel;
    private String priceunits;
    private Double distance;
    private Double price;
    private Byte type;
    private Byte status;
    private String waybillno;
    private Double weight;
    private Double trueweight;
    private String ownerid;
    private String venderid;
    private Byte venderdelflag;
    private Byte ownerdelflag;
    private Byte driverdelflag;
    private String creator;
    private Long createtime;
    private String modifier;
    private Long modifytime;
    private String signimgurl;
    private String desc1;
    private String desc4;
    private Double tallage;
    private String pickupimgurl;
    /**
     * 剩余运输量
     */
    private Double overweight;
    /** 是否已结算 0-未结算 1-已结算*/
    private String isClearing;
	//拒绝原因类型
	private String refuseType;
	//拒绝原因
	private String refuseReson;
    private String orgid;
    
    //货主组织机构名称
    private String orgName;
    //承运商名称
    private String venderName;
    //承运商电话
    private String venderTel;
    private String plancode;
    
    //总趟数
    private String totalnumber;
    //剩余趟数
    private String overnumber;
    //卸货完成时间
    private Long unloadtime;
    
    private List<BillTrackResp> billTrackList;

    //计划总量
    private Double planWeight;
    
    //计划完成量
    private Double planCompleteWeight;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlanid() {
		return planid;
	}
	public void setPlanid(String planid) {
		this.planid = planid;
	}
	public String getVehicleid() {
		return vehicleid;
	}
	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}
	public String getDriverid() {
		return driverid;
	}
	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}
	public String getRouteid() {
		return routeid;
	}
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getDrivertel() {
		return drivertel;
	}
	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getVehicletypename() {
		return vehicletypename;
	}
	public void setVehicletypename(String vehicletypename) {
		this.vehicletypename = vehicletypename;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	public String getStartcity() {
		return startcity;
	}
	public void setStartcity(String startcity) {
		this.startcity = startcity;
	}
	public String getConsignorname() {
		return consignorname;
	}
	public void setConsignorname(String consignorname) {
		this.consignorname = consignorname;
	}
	public String getConsignortel() {
		return consignortel;
	}
	public void setConsignortel(String consignortel) {
		this.consignortel = consignortel;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndcity() {
		return endcity;
	}
	public void setEndcity(String endcity) {
		this.endcity = endcity;
	}
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}
	public String getReceivertel() {
		return receivertel;
	}
	public void setReceivertel(String receivertel) {
		this.receivertel = receivertel;
	}
	public String getPriceunits() {
		return priceunits;
	}
	public void setPriceunits(String priceunits) {
		this.priceunits = priceunits;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getWaybillno() {
		return waybillno;
	}
	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getTrueweight() {
		return trueweight;
	}
	public void setTrueweight(Double trueweight) {
		this.trueweight = trueweight;
	}
	public String getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}
	public String getVenderid() {
		return venderid;
	}
	public String getIsClearing() {
		return isClearing;
	}
	public void setIsClearing(String isClearing) {
		this.isClearing = isClearing;
	}
	public void setVenderid(String venderid) {
		this.venderid = venderid;
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
	public Byte getDriverdelflag() {
		return driverdelflag;
	}
	public void setDriverdelflag(Byte driverdelflag) {
		this.driverdelflag = driverdelflag;
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
	public List<BillTrackResp> getBillTrackList() {
		return billTrackList;
	}
	public void setBillTrackList(List<BillTrackResp> billTrackList) {
		this.billTrackList = billTrackList;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getModifytimeStr() {
		if( this.modifytime !=null ){
			return DateUtil.getDateString(new Date(this.modifytime), "yyyy-MM-dd HH:mm");
		}
		return "";
	}
	
	
	public String getCreatetimeStr() {
		if( this.createtime !=null ){
			return DateUtil.getDateString(new Date(this.createtime), "yyyy-MM-dd HH:mm");
		}
		return "";
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getVenderName() {
		return venderName;
	}
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getPlancode() {
		return plancode;
	}
	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}
	public String getSignimgurl() {
		return signimgurl;
	}
	public void setSignimgurl(String signimgurl) {
		this.signimgurl = signimgurl;
	}
	public String getRefuseType() {
		return refuseType;
	}
	public void setRefuseType(String refuseType) {
		this.refuseType = refuseType;
	}
	public String getRefuseReson() {
		return refuseReson;
	}
	public void setRefuseReson(String refuseReson) {
		this.refuseReson = refuseReson;
	}
	public Double getTallage() {
		return tallage;
	}
	public void setTallage(Double tallage) {
		this.tallage = tallage;
	}
	public String getOvernumber() {
		return overnumber;
	}
	public void setOvernumber(String overnumber) {
		this.overnumber = overnumber;
	}
	public Double getOverweight() {
		return overweight;
	}
	public void setOverweight(Double overweight) {
		this.overweight = overweight;
	}
	public String getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(String totalnumber) {
		this.totalnumber = totalnumber;
	}
	public String getVenderTel() {
		return venderTel;
	}
	public void setVenderTel(String venderTel) {
		this.venderTel = venderTel;
	}
	public String getDesc4() {
		return desc4;
	}
	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}
	public String getPickupimgurl() {
		if(StringUtils.isNotBlank(pickupimgurl)){
			return pickupimgurl;
		}else{
			return "";
		}
	}
	public void setPickupimgurl(String pickupimgurl) {
		this.pickupimgurl = pickupimgurl;
	}
	public Long getUnloadtime() {
		return unloadtime;
	}
	public void setUnloadtime(Long unloadtime) {
		this.unloadtime = unloadtime;
	}
	public Double getPlanWeight() {
		return planWeight;
	}
	public void setPlanWeight(Double planWeight) {
		this.planWeight = planWeight;
	}
	public Double getPlanCompleteWeight() {
		return planCompleteWeight;
	}
	public void setPlanCompleteWeight(Double planCompleteWeight) {
		this.planCompleteWeight = planCompleteWeight;
	}
	
}
