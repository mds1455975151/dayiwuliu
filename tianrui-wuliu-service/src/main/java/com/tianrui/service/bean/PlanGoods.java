package com.tianrui.service.bean;

public class PlanGoods {
	
	private Integer pageNo;
	
	private Integer pageSize;
	
	private String searchKey;
	
	private String startOp;//启运省
	
	private String startOc;//启运市
	
	private String endOp;//到货省
	
	private String endOc;//到货市
	
    private String id;

    private String freightid;

    private String routeid;

    private String measure;

    private String cargoid;

    private String cargoname;

    private String priceunits;

    private String startcity;

    private String endcity;

    private Double distance;

    private Double totalplanned;

    private Double planprice;

    private Double completed;

    private Double price;

    private Byte istemplate;

    private Byte type;

    private Long starttime;

    private Long endtime;

    private Long acceptedtime;

    private Byte status;

    private String linkman;

    private String telephone;

    private String vehicleownerid;

    private String vehicleownername;

    private String vehicleownerphone;

    private String creator;

    private Long createtime;

    private String modifier;

    private Long modifytime;

    private Byte isfamily;

    private String plancode;

    private Byte ownerdelflag;

    private Byte venderdelflag;

    private String refusereson;

    private String refuseresontype;

    private String sendperson;

    private String sendpersonphone;

    private String receiveperson;

    private String receivepersonphone;

    private String cargocode;

    private String freightname;

    private String orgid;

    private String isappoint;

    private String pid;

    private Double tallage;

    private String pathid;

    private String desc1;

    private String desc2;

    private String desc3;

    private String desc4;

    private String shippermerchant;

    private String consigneemerchant;

    private String receiveid;

    private String payment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFreightid() {
        return freightid;
    }

    public void setFreightid(String freightid) {
        this.freightid = freightid == null ? null : freightid.trim();
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid == null ? null : routeid.trim();
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure == null ? null : measure.trim();
    }

    public String getCargoid() {
        return cargoid;
    }

    public void setCargoid(String cargoid) {
        this.cargoid = cargoid == null ? null : cargoid.trim();
    }

    public String getCargoname() {
        return cargoname;
    }

    public void setCargoname(String cargoname) {
        this.cargoname = cargoname == null ? null : cargoname.trim();
    }

    public String getPriceunits() {
        return priceunits;
    }

    public void setPriceunits(String priceunits) {
        this.priceunits = priceunits == null ? null : priceunits.trim();
    }

    public String getStartcity() {
        return startcity;
    }

    public void setStartcity(String startcity) {
        this.startcity = startcity == null ? null : startcity.trim();
    }

    public String getEndcity() {
        return endcity;
    }

    public void setEndcity(String endcity) {
        this.endcity = endcity == null ? null : endcity.trim();
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

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime;
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
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getVehicleownerid() {
        return vehicleownerid;
    }

    public void setVehicleownerid(String vehicleownerid) {
        this.vehicleownerid = vehicleownerid == null ? null : vehicleownerid.trim();
    }

    public String getVehicleownername() {
        return vehicleownername;
    }

    public void setVehicleownername(String vehicleownername) {
        this.vehicleownername = vehicleownername == null ? null : vehicleownername.trim();
    }

    public String getVehicleownerphone() {
        return vehicleownerphone;
    }

    public void setVehicleownerphone(String vehicleownerphone) {
        this.vehicleownerphone = vehicleownerphone == null ? null : vehicleownerphone.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Long getModifytime() {
        return modifytime;
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
        this.plancode = plancode == null ? null : plancode.trim();
    }

    public Byte getOwnerdelflag() {
        return ownerdelflag;
    }

    public void setOwnerdelflag(Byte ownerdelflag) {
        this.ownerdelflag = ownerdelflag;
    }

    public Byte getVenderdelflag() {
        return venderdelflag;
    }

    public void setVenderdelflag(Byte venderdelflag) {
        this.venderdelflag = venderdelflag;
    }

    public String getRefusereson() {
        return refusereson;
    }

    public void setRefusereson(String refusereson) {
        this.refusereson = refusereson == null ? null : refusereson.trim();
    }

    public String getRefuseresontype() {
        return refuseresontype;
    }

    public void setRefuseresontype(String refuseresontype) {
        this.refuseresontype = refuseresontype == null ? null : refuseresontype.trim();
    }

    public String getSendperson() {
        return sendperson;
    }

    public void setSendperson(String sendperson) {
        this.sendperson = sendperson == null ? null : sendperson.trim();
    }

    public String getSendpersonphone() {
        return sendpersonphone;
    }

    public void setSendpersonphone(String sendpersonphone) {
        this.sendpersonphone = sendpersonphone == null ? null : sendpersonphone.trim();
    }

    public String getReceiveperson() {
        return receiveperson;
    }

    public void setReceiveperson(String receiveperson) {
        this.receiveperson = receiveperson == null ? null : receiveperson.trim();
    }

    public String getReceivepersonphone() {
        return receivepersonphone;
    }

    public void setReceivepersonphone(String receivepersonphone) {
        this.receivepersonphone = receivepersonphone == null ? null : receivepersonphone.trim();
    }

    public String getCargocode() {
        return cargocode;
    }

    public void setCargocode(String cargocode) {
        this.cargocode = cargocode == null ? null : cargocode.trim();
    }

    public String getFreightname() {
        return freightname;
    }

    public void setFreightname(String freightname) {
        this.freightname = freightname == null ? null : freightname.trim();
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getIsappoint() {
        return isappoint;
    }

    public void setIsappoint(String isappoint) {
        this.isappoint = isappoint == null ? null : isappoint.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Double getTallage() {
        return tallage;
    }

    public void setTallage(Double tallage) {
        this.tallage = tallage;
    }

    public String getPathid() {
        return pathid;
    }

    public void setPathid(String pathid) {
        this.pathid = pathid == null ? null : pathid.trim();
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    public String getDesc3() {
        return desc3;
    }

    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    public String getDesc4() {
        return desc4;
    }

    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
    }

    public String getShippermerchant() {
        return shippermerchant;
    }

    public void setShippermerchant(String shippermerchant) {
        this.shippermerchant = shippermerchant == null ? null : shippermerchant.trim();
    }

    public String getConsigneemerchant() {
        return consigneemerchant;
    }

    public void setConsigneemerchant(String consigneemerchant) {
        this.consigneemerchant = consigneemerchant == null ? null : consigneemerchant.trim();
    }

    public String getReceiveid() {
        return receiveid;
    }

    public void setReceiveid(String receiveid) {
        this.receiveid = receiveid == null ? null : receiveid.trim();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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