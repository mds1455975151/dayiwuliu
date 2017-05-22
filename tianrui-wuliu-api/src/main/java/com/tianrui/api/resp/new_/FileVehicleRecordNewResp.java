package com.tianrui.api.resp.new_;

public class FileVehicleRecordNewResp {
    //  主键uuid
    private String id;

    //  车辆id
    private String vehicleid;

    //  驾驶员id
    private String driverid;
    
    //  所有人
    private String vehicleowner;

    //  所有人证件号
    private String vehicleowneridcard;

    //  车牌号码
    private String vehicleno;

    //  使用性质,1营运,2非营运
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

    //  发动机型号
    private String motorno;

    //  3开票认证,2完全认证,1临时认证,-1作废
    private Byte authtype;

    //  1认证通过,2认证中,3认证失败 
    private Byte authstatus;

    //  审核备注
    private String authremark;

    //  审核时间
    private String authtime;

    //  审核人
    private String authuser;

    //  创建时间
    private Long createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid == null ? null : vehicleid.trim();
    }

    public String getDriverid() {
        return driverid;
    }

    public void setDriverid(String driverid) {
        this.driverid = driverid == null ? null : driverid.trim();
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
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

    public Byte getAuthstatus() {
        return authstatus;
    }

    public void setAuthstatus(Byte authstatus) {
        this.authstatus = authstatus;
    }

    public String getAuthremark() {
        return authremark;
    }

    public void setAuthremark(String authremark) {
        this.authremark = authremark == null ? null : authremark.trim();
    }

    public String getAuthtime() {
        return authtime;
    }

    public void setAuthtime(String authtime) {
        this.authtime = authtime == null ? null : authtime.trim();
    }

    public String getAuthuser() {
        return authuser;
    }

    public void setAuthuser(String authuser) {
        this.authuser = authuser == null ? null : authuser.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

	public String getVehicleowner() {
		return vehicleowner;
	}

	public void setVehicleowner(String vehicleowner) {
		this.vehicleowner = vehicleowner;
	}

	public String getVehicleowneridcard() {
		return vehicleowneridcard;
	}

	public void setVehicleowneridcard(String vehicleowneridcard) {
		this.vehicleowneridcard = vehicleowneridcard;
	}
}