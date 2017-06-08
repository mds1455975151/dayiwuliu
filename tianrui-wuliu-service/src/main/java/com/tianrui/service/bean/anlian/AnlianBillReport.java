package com.tianrui.service.bean.anlian;

public class AnlianBillReport {
	
	private Integer pageNo;
	
	private Integer pageSize;
	
    private Long starttime;
    
    //货运时间
    private String interTimeStr;
    //货运距离
    private String interDistanceStr;
    
    //签收时间起
    private Long ownerSigntime_0;
    //签收时间止
    private Long ownerSigntime_1;
	
	private Long endtime;
	
    private String id;
    //是否有提货地 1-有
  	private String t_position;
  	//是否有到货地
  	private String d_position;
    
    private Double q_deviation;
    
    private Double d_deviation;
    
    private Long ownerSigntime;

    private String type;

    private Long createtime;

    private String waybillno;

    private String drivername;

    private String drivertel;

    private String vehicleno;

    private String cargoname;

    private String routeid;

    private Long unloadtime;

    private String weight;

    private Double trueweight;

    private Double pickupweight;

    private Double signweight;

    private Byte status;

    private String plancode;

    private String shippermerchant;

    private String consigneemerchant;

    private String shippermerchantname;

    private String consigneemerchantname;

    private String remarkname;

    private String routename;

    private String orgid;

    private String palnid;
   
    private Long begintime;
    
    private Long interTime;
    
    private Double interDistance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getWaybillno() {
        return waybillno;
    }

    public void setWaybillno(String waybillno) {
        this.waybillno = waybillno == null ? null : waybillno.trim();
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername == null ? null : drivername.trim();
    }

    public String getDrivertel() {
        return drivertel;
    }

    public void setDrivertel(String drivertel) {
        this.drivertel = drivertel == null ? null : drivertel.trim();
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
    }

    public String getCargoname() {
        return cargoname;
    }

    public void setCargoname(String cargoname) {
        this.cargoname = cargoname == null ? null : cargoname.trim();
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid == null ? null : routeid.trim();
    }

    public Long getUnloadtime() {
        return unloadtime;
    }

    public void setUnloadtime(Long unloadtime) {
        this.unloadtime = unloadtime;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public Double getTrueweight() {
        return trueweight;
    }

    public void setTrueweight(Double trueweight) {
        this.trueweight = trueweight;
    }

    public Double getPickupweight() {
        return pickupweight;
    }

    public void setPickupweight(Double pickupweight) {
        this.pickupweight = pickupweight;
    }

    public Double getSignweight() {
        return signweight;
    }

    public void setSignweight(Double signweight) {
        this.signweight = signweight;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getPlancode() {
        return plancode;
    }

    public void setPlancode(String plancode) {
        this.plancode = plancode == null ? null : plancode.trim();
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

    public String getShippermerchantname() {
        return shippermerchantname;
    }

    public void setShippermerchantname(String shippermerchantname) {
        this.shippermerchantname = shippermerchantname == null ? null : shippermerchantname.trim();
    }

    public String getConsigneemerchantname() {
        return consigneemerchantname;
    }

    public void setConsigneemerchantname(String consigneemerchantname) {
        this.consigneemerchantname = consigneemerchantname == null ? null : consigneemerchantname.trim();
    }

    public String getRemarkname() {
        return remarkname;
    }

    public void setRemarkname(String remarkname) {
        this.remarkname = remarkname == null ? null : remarkname.trim();
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename == null ? null : routename.trim();
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    public String getPalnid() {
        return palnid;
    }

    public void setPalnid(String palnid) {
        this.palnid = palnid == null ? null : palnid.trim();
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

	public Double getQ_deviation() {
		return q_deviation;
	}

	public void setQ_deviation(Double q_deviation) {
		this.q_deviation = q_deviation;
	}

	public Double getD_deviation() {
		return d_deviation;
	}

	public void setD_deviation(Double d_deviation) {
		this.d_deviation = d_deviation;
	}

	public Long getBegintime() {
		return begintime;
	}

	public void setBegintime(Long begintime) {
		this.begintime = begintime;
	}

	public Long getInterTime() {
		return interTime;
	}

	public void setInterTime(Long interTime) {
		this.interTime = interTime;
	}

	public Double getInterDistance() {
		return interDistance;
	}

	public void setInterDistance(Double interDistance) {
		this.interDistance = interDistance;
	}

	public Long getOwnerSigntime() {
		return ownerSigntime;
	}

	public void setOwnerSigntime(Long ownerSigntime) {
		this.ownerSigntime = ownerSigntime;
	}

	public String getInterTimeStr() {
		return interTimeStr;
	}

	public void setInterTimeStr(String interTimeStr) {
		this.interTimeStr = interTimeStr;
	}

	public String getInterDistanceStr() {
		return interDistanceStr;
	}

	public void setInterDistanceStr(String interDistanceStr) {
		this.interDistanceStr = interDistanceStr;
	}

	public String getT_position() {
		return t_position;
	}

	public void setT_position(String t_position) {
		this.t_position = t_position;
	}

	public String getD_position() {
		return d_position;
	}

	public void setD_position(String d_position) {
		this.d_position = d_position;
	}

	public Long getOwnerSigntime_0() {
		return ownerSigntime_0;
	}

	public void setOwnerSigntime_0(Long ownerSigntime_0) {
		this.ownerSigntime_0 = ownerSigntime_0;
	}

	public Long getOwnerSigntime_1() {
		return ownerSigntime_1;
	}

	public void setOwnerSigntime_1(Long ownerSigntime_1) {
		this.ownerSigntime_1 = ownerSigntime_1;
	}

}