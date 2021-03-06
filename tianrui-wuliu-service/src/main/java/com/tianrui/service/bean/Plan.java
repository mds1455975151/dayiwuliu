package com.tianrui.service.bean;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "planTemplate")
public class Plan implements IModel{
    /**
     *  主键UUID
     */
    private String id;
    
    private String startOp;//启运省
	
	private String startOc;//启运市
	
	private String endOp;//到货省
	
	private String endOc;//到货市

    /**
     * 策略id
     */
    private String freightid;
    /** 发货方id*/
    private String shipperMerchant;
    /** 收货方id*/
    private String consigneeMerchant;
    //我的收货员id
    private String receiveid;
    //支付对象 1-司机 2-车主
    private String payment;
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
     * 货物id
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
     * 货运计划结束时间（为空表示不限）
     */
    private Long endtime;

    /**
     * 车主接受时间
     */
    private Long acceptedtime;

    /**
     * 计划状态（-2已停用 -1-已删除；0 新建；1-待接单；2-执行中；3-已完成）
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
     * 创建者 货主
     */
    private String creator;

    /**
     * 创建时间
     */
    private Long createtime;


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
    
    private Long createTimeBegin;
    private Long createTimeEnd;
    
    //是否为委派计划
    private String isAppoint;
    //委派计划父级id
    private String pid;
    
    private String desc1;
    private String desc2;
    private String desc3;
    private String desc4;
    
    private Integer start;
    private Integer limit;
    
    private String queryKey;
    //税率
    private Double tallage;
    //计划分配路径
    private String pathID;
    
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.ID
     *
     * @return the value of wuliu_plan.ID
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.ID
     *
     * @param id the value for wuliu_plan.ID
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.freightid
     *
     * @return the value of wuliu_plan.freightid
     *
     * @mbggenerated
     */
    public String getFreightid() {
        return freightid;
    }

    public String getShipperMerchant() {
		return shipperMerchant;
	}

	public void setShipperMerchant(String shipperMerchant) {
		this.shipperMerchant = shipperMerchant;
	}

	public String getConsigneeMerchant() {
		return consigneeMerchant;
	}

	public void setConsigneeMerchant(String consigneeMerchant) {
		this.consigneeMerchant = consigneeMerchant;
	}

	public String getCargoid() {
		return cargoid;
	}

	public void setCargoid(String cargoid) {
		this.cargoid = cargoid;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.freightid
     *
     * @param freightid the value for wuliu_plan.freightid
     *
     * @mbggenerated
     */
    public void setFreightid(String freightid) {
        this.freightid = freightid == null ? null : freightid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.routeid
     *
     * @return the value of wuliu_plan.routeid
     *
     * @mbggenerated
     */
    public String getRouteid() {
        return routeid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.routeid
     *
     * @param routeid the value for wuliu_plan.routeid
     *
     * @mbggenerated
     */
    public void setRouteid(String routeid) {
        this.routeid = routeid == null ? null : routeid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.measure
     *
     * @return the value of wuliu_plan.measure
     *
     * @mbggenerated
     */
    public String getMeasure() {
        return measure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.measure
     *
     * @param measure the value for wuliu_plan.measure
     *
     * @mbggenerated
     */
    public void setMeasure(String measure) {
        this.measure = measure == null ? null : measure.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.cargoname
     *
     * @return the value of wuliu_plan.cargoname
     *
     * @mbggenerated
     */
    public String getCargoname() {
        return cargoname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.cargoname
     *
     * @param cargoname the value for wuliu_plan.cargoname
     *
     * @mbggenerated
     */
    public void setCargoname(String cargoname) {
        this.cargoname = cargoname == null ? null : cargoname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.priceUnits
     *
     * @return the value of wuliu_plan.priceUnits
     *
     * @mbggenerated
     */
    public String getPriceunits() {
        return priceunits;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.priceUnits
     *
     * @param priceunits the value for wuliu_plan.priceUnits
     *
     * @mbggenerated
     */
    public void setPriceunits(String priceunits) {
        this.priceunits = priceunits == null ? null : priceunits.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.startcity
     *
     * @return the value of wuliu_plan.startcity
     *
     * @mbggenerated
     */
    public String getStartcity() {
        return startcity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.startcity
     *
     * @param startcity the value for wuliu_plan.startcity
     *
     * @mbggenerated
     */
    public void setStartcity(String startcity) {
        this.startcity = startcity == null ? null : startcity.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.endcity
     *
     * @return the value of wuliu_plan.endcity
     *
     * @mbggenerated
     */
    public String getEndcity() {
        return endcity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.endcity
     *
     * @param endcity the value for wuliu_plan.endcity
     *
     * @mbggenerated
     */
    public void setEndcity(String endcity) {
        this.endcity = endcity == null ? null : endcity.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.distance
     *
     * @return the value of wuliu_plan.distance
     *
     * @mbggenerated
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.distance
     *
     * @param distance the value for wuliu_plan.distance
     *
     * @mbggenerated
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.totalplanned
     *
     * @return the value of wuliu_plan.totalplanned
     *
     * @mbggenerated
     */
    public Double getTotalplanned() {
        return totalplanned;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.totalplanned
     *
     * @param totalplanned the value for wuliu_plan.totalplanned
     *
     * @mbggenerated
     */
    public void setTotalplanned(Double totalplanned) {
        this.totalplanned = totalplanned;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.planprice
     *
     * @return the value of wuliu_plan.planprice
     *
     * @mbggenerated
     */
    public Double getPlanprice() {
        return planprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.planprice
     *
     * @param planprice the value for wuliu_plan.planprice
     *
     * @mbggenerated
     */
    public void setPlanprice(Double planprice) {
        this.planprice = planprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.completed
     *
     * @return the value of wuliu_plan.completed
     *
     * @mbggenerated
     */
    public Double getCompleted() {
        return completed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.completed
     *
     * @param completed the value for wuliu_plan.completed
     *
     * @mbggenerated
     */
    public void setCompleted(Double completed) {
        this.completed = completed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.price
     *
     * @return the value of wuliu_plan.price
     *
     * @mbggenerated
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.price
     *
     * @param price the value for wuliu_plan.price
     *
     * @mbggenerated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.istemplate
     *
     * @return the value of wuliu_plan.istemplate
     *
     * @mbggenerated
     */
    public Byte getIstemplate() {
        return istemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.istemplate
     *
     * @param istemplate the value for wuliu_plan.istemplate
     *
     * @mbggenerated
     */
    public void setIstemplate(Byte istemplate) {
        this.istemplate = istemplate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.type
     *
     * @return the value of wuliu_plan.type
     *
     * @mbggenerated
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.type
     *
     * @param type the value for wuliu_plan.type
     *
     * @mbggenerated
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.starttime
     *
     * @return the value of wuliu_plan.starttime
     *
     * @mbggenerated
     */
    public Long getStarttime() {
        return starttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.starttime
     *
     * @param starttime the value for wuliu_plan.starttime
     *
     * @mbggenerated
     */
    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.endtime
     *
     * @return the value of wuliu_plan.endtime
     *
     * @mbggenerated
     */
    public Long getEndtime() {
        return endtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.endtime
     *
     * @param endtime the value for wuliu_plan.endtime
     *
     * @mbggenerated
     */
    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.acceptedtime
     *
     * @return the value of wuliu_plan.acceptedtime
     *
     * @mbggenerated
     */
    public Long getAcceptedtime() {
        return acceptedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.acceptedtime
     *
     * @param acceptedtime the value for wuliu_plan.acceptedtime
     *
     * @mbggenerated
     */
    public void setAcceptedtime(Long acceptedtime) {
        this.acceptedtime = acceptedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.status
     *
     * @return the value of wuliu_plan.status
     *
     * @mbggenerated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.status
     *
     * @param status the value for wuliu_plan.status
     *
     * @mbggenerated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.linkman
     *
     * @return the value of wuliu_plan.linkman
     *
     * @mbggenerated
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.linkman
     *
     * @param linkman the value for wuliu_plan.linkman
     *
     * @mbggenerated
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.telephone
     *
     * @return the value of wuliu_plan.telephone
     *
     * @mbggenerated
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.telephone
     *
     * @param telephone the value for wuliu_plan.telephone
     *
     * @mbggenerated
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.vehicleownerid
     *
     * @return the value of wuliu_plan.vehicleownerid
     *
     * @mbggenerated
     */
    public String getVehicleownerid() {
        return vehicleownerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.vehicleownerid
     *
     * @param vehicleownerid the value for wuliu_plan.vehicleownerid
     *
     * @mbggenerated
     */
    public void setVehicleownerid(String vehicleownerid) {
        this.vehicleownerid = vehicleownerid == null ? null : vehicleownerid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.vehicleownername
     *
     * @return the value of wuliu_plan.vehicleownername
     *
     * @mbggenerated
     */
    public String getVehicleownername() {
        return vehicleownername;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.vehicleownername
     *
     * @param vehicleownername the value for wuliu_plan.vehicleownername
     *
     * @mbggenerated
     */
    public void setVehicleownername(String vehicleownername) {
        this.vehicleownername = vehicleownername == null ? null : vehicleownername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.vehicleownerphone
     *
     * @return the value of wuliu_plan.vehicleownerphone
     *
     * @mbggenerated
     */
    public String getVehicleownerphone() {
        return vehicleownerphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.vehicleownerphone
     *
     * @param vehicleownerphone the value for wuliu_plan.vehicleownerphone
     *
     * @mbggenerated
     */
    public void setVehicleownerphone(String vehicleownerphone) {
        this.vehicleownerphone = vehicleownerphone == null ? null : vehicleownerphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.creator
     *
     * @return the value of wuliu_plan.creator
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.creator
     *
     * @param creator the value for wuliu_plan.creator
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.createtime
     *
     * @return the value of wuliu_plan.createtime
     *
     * @mbggenerated
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.createtime
     *
     * @param createtime the value for wuliu_plan.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.modifier
     *
     * @return the value of wuliu_plan.modifier
     *
     * @mbggenerated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.modifier
     *
     * @param modifier the value for wuliu_plan.modifier
     *
     * @mbggenerated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.modifytime
     *
     * @return the value of wuliu_plan.modifytime
     *
     * @mbggenerated
     */
    public Long getModifytime() {
        return modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.modifytime
     *
     * @param modifytime the value for wuliu_plan.modifytime
     *
     * @mbggenerated
     */
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.DESC1
     *
     * @return the value of wuliu_plan.DESC1
     *
     * @mbggenerated
     */
    public String getDesc1() {
        return desc1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.DESC1
     *
     * @param desc1 the value for wuliu_plan.DESC1
     *
     * @mbggenerated
     */
    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.DESC2
     *
     * @return the value of wuliu_plan.DESC2
     *
     * @mbggenerated
     */
    public String getDesc2() {
        return desc2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.DESC2
     *
     * @param desc2 the value for wuliu_plan.DESC2
     *
     * @mbggenerated
     */
    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.DESC3
     *
     * @return the value of wuliu_plan.DESC3
     *
     * @mbggenerated
     */
    public String getDesc3() {
        return desc3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.DESC3
     *
     * @param desc3 the value for wuliu_plan.DESC3
     *
     * @mbggenerated
     */
    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wuliu_plan.DESC4
     *
     * @return the value of wuliu_plan.DESC4
     *
     * @mbggenerated
     */
    public String getDesc4() {
        return desc4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wuliu_plan.DESC4
     *
     * @param desc4 the value for wuliu_plan.DESC4
     *
     * @mbggenerated
     */
    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
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

	public Long getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(Long createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	public Long getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Long createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getIsAppoint() {
		return isAppoint;
	}

	public void setIsAppoint(String isAppoint) {
		this.isAppoint = isAppoint;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getReceiveid() {
		return receiveid;
	}

	public void setReceiveid(String receiveid) {
		this.receiveid = receiveid;
	}

	public Double getTallage() {
		return tallage;
	}

	public void setTallage(Double tallage) {
		this.tallage = tallage;
	}

	public String getPathID() {
		return pathID;
	}

	public void setPathID(String pathID) {
		this.pathID = pathID;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
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