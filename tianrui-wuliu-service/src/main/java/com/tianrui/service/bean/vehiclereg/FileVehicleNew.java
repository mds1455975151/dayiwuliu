package com.tianrui.service.bean.vehiclereg;

public class FileVehicleNew {
    //  主键uuid
    private String id;

    //  车牌号码
    private String vehicleno;

    //  驾驶员id
    private String driverid;

    //  随车电话,账户
    private String vehiclemobile;

    //  车辆类型
    private String vehicletype;

    //  车长
    private String vehiclelen;

    //  载重
    private String vehicleload;

    //  所有人
    private String vehicleowner;

    //  所有人证件号
    private String vehicleowneridcard;

    //  营运证号
    private String taxilicenseno;

    //  道路运输证号
    private String roadtransportno;

    //  营运证图证
    private String taxilicenseimg;

    //  车辆图片
    private String vehicleimg;

    //  行驶证
    private String drivinglicenseno;

    //  行驶证图片
    private String drivinglicenseimg;

    //  车辆登记证
    private String vehiclegradeno;

    //  车辆登记证图片
    private String vehiclegradeimg;

    //  使用性质,1营运,2非营�?
    private String nature;

    //  总质量
    private String quality;

    //  证件号码
    private String idcardno;

    //  登记证书编号
    private String certificateno;

    //  检验有效期止
    private String expirydata;

    //  车辆识别码
    private String identification;

    //  发动机号
    private String motor;

    //  发动机型�?
    private String motorno;

    //  -1作废,3开票认证,2完全认证,1临时认证
    private Byte authtype;

    //  1历史数据导入,2用户添加
    private Byte vehiclesource;

    //  运单状态,0空闲,1运输中
    private Byte billstatus;

    //  创建时间
    private Long createtime;

    //  创建人
    private String createuser;

    //  修改时间
    private Long lastupdatetime;

    //  修改人
    private Long lastupdateuser;
    
    private Integer limit;
    
    private Integer start;
    

    //  审核时间
    private String authTime;
    //  车主联系方式
    private String vehicleownerTel;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getVehiclemobile() {
        return vehiclemobile;
    }

    public void setVehiclemobile(String vehiclemobile) {
        this.vehiclemobile = vehiclemobile == null ? null : vehiclemobile.trim();
    }

    public String getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype == null ? null : vehicletype.trim();
    }

    public String getVehiclelen() {
        return vehiclelen;
    }

    public void setVehiclelen(String vehiclelen) {
        this.vehiclelen = vehiclelen == null ? null : vehiclelen.trim();
    }

    public String getVehicleload() {
        return vehicleload;
    }

    public void setVehicleload(String vehicleload) {
        this.vehicleload = vehicleload == null ? null : vehicleload.trim();
    }

    public String getVehicleowner() {
        return vehicleowner;
    }

    public void setVehicleowner(String vehicleowner) {
        this.vehicleowner = vehicleowner == null ? null : vehicleowner.trim();
    }

    public String getVehicleowneridcard() {
        return vehicleowneridcard;
    }

    public void setVehicleowneridcard(String vehicleowneridcard) {
        this.vehicleowneridcard = vehicleowneridcard == null ? null : vehicleowneridcard.trim();
    }

    public String getTaxilicenseno() {
        return taxilicenseno;
    }

    public void setTaxilicenseno(String taxilicenseno) {
        this.taxilicenseno = taxilicenseno == null ? null : taxilicenseno.trim();
    }

    public String getRoadtransportno() {
        return roadtransportno;
    }

    public void setRoadtransportno(String roadtransportno) {
        this.roadtransportno = roadtransportno == null ? null : roadtransportno.trim();
    }

    public String getTaxilicenseimg() {
        return taxilicenseimg;
    }

    public void setTaxilicenseimg(String taxilicenseimg) {
        this.taxilicenseimg = taxilicenseimg == null ? null : taxilicenseimg.trim();
    }

    public String getVehicleimg() {
        return vehicleimg;
    }

    public void setVehicleimg(String vehicleimg) {
        this.vehicleimg = vehicleimg == null ? null : vehicleimg.trim();
    }

    public String getDrivinglicenseno() {
        return drivinglicenseno;
    }

    public void setDrivinglicenseno(String drivinglicenseno) {
        this.drivinglicenseno = drivinglicenseno == null ? null : drivinglicenseno.trim();
    }

    public String getDrivinglicenseimg() {
        return drivinglicenseimg;
    }

    public void setDrivinglicenseimg(String drivinglicenseimg) {
        this.drivinglicenseimg = drivinglicenseimg == null ? null : drivinglicenseimg.trim();
    }

    public String getVehiclegradeno() {
        return vehiclegradeno;
    }

    public void setVehiclegradeno(String vehiclegradeno) {
        this.vehiclegradeno = vehiclegradeno == null ? null : vehiclegradeno.trim();
    }

    public String getVehiclegradeimg() {
        return vehiclegradeimg;
    }

    public void setVehiclegradeimg(String vehiclegradeimg) {
        this.vehiclegradeimg = vehiclegradeimg == null ? null : vehiclegradeimg.trim();
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature == null ? null : nature.trim();
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality == null ? null : quality.trim();
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno == null ? null : idcardno.trim();
    }

    public String getCertificateno() {
        return certificateno;
    }

    public void setCertificateno(String certificateno) {
        this.certificateno = certificateno == null ? null : certificateno.trim();
    }

    public String getExpirydata() {
        return expirydata;
    }

    public void setExpirydata(String expirydata) {
        this.expirydata = expirydata == null ? null : expirydata.trim();
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification == null ? null : identification.trim();
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor == null ? null : motor.trim();
    }

    public String getMotorno() {
        return motorno;
    }

    public void setMotorno(String motorno) {
        this.motorno = motorno == null ? null : motorno.trim();
    }

    public Byte getAuthtype() {
        return authtype;
    }

    public void setAuthtype(Byte authtype) {
        this.authtype = authtype;
    }

    public Byte getVehiclesource() {
        return vehiclesource;
    }

    public void setVehiclesource(Byte vehiclesource) {
        this.vehiclesource = vehiclesource;
    }

    public Byte getBillstatus() {
        return billstatus;
    }

    public void setBillstatus(Byte billstatus) {
        this.billstatus = billstatus;
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Long getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Long lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public Long getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(Long lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

	public String getAuthTime() {
		return authTime;
	}

	public void setAuthTime(String authTime) {
		this.authTime = authTime;
	}

	public String getVehicleownerTel() {
		return vehicleownerTel;
	}

	public void setVehicleownerTel(String vehicleownerTel) {
		this.vehicleownerTel = vehicleownerTel;
	}
    
    

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
}