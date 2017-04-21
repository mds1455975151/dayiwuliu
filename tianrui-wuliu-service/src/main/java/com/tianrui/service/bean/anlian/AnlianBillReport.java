package com.tianrui.service.bean.anlian;

public class AnlianBillReport {
	
	private Integer pageNo;
	
	private Integer pageSize;
	
    private String id;

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

}