package com.tianrui.service.bean.vehiclereg;

public class VehicleDriverNew {
    //  主键uuid
    private String id;

    //  车牌id
    private String vehicleid;

    //  姓名
    private String drivername;

    //  性别   1:男� 2女
    private String driversex;

    //  身份证号
    private String driveridcard;

    //  出生日期
    private String driverbirthdate;

    //  联系电话
    private String driverlinktel;

    //  身份证地址
    private String driveridcardaddr;

    //  初次领证日期
    private String drivercardfirstlicens;

    //  发证机关
    private String drivercardlicenceorg;

    //  驾驶证注册时间
    private String drivercardregdate;

    //  有效年限
    private String drivercardusefullife;

    //  准架车型
    private String drivercardtype;

    //  驾驶证图片
    private String drivercardimg;

    //  0未选择1已经选中
    private Byte checkstatus;

    //  0 未审批1审核成功 2审核中3审核失败
    private Byte authstats;

    //  审核备注
    private String authremark;

    //  审核人
    private String authuser;

    //  审核时间
    private String authtime;

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

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername == null ? null : drivername.trim();
    }

    public String getDriversex() {
        return driversex;
    }

    public void setDriversex(String driversex) {
        this.driversex = driversex == null ? null : driversex.trim();
    }

    public String getDriveridcard() {
        return driveridcard;
    }

    public void setDriveridcard(String driveridcard) {
        this.driveridcard = driveridcard == null ? null : driveridcard.trim();
    }

    public String getDriverbirthdate() {
        return driverbirthdate;
    }

    public void setDriverbirthdate(String driverbirthdate) {
        this.driverbirthdate = driverbirthdate == null ? null : driverbirthdate.trim();
    }

    public String getDriverlinktel() {
        return driverlinktel;
    }

    public void setDriverlinktel(String driverlinktel) {
        this.driverlinktel = driverlinktel == null ? null : driverlinktel.trim();
    }

    public String getDriveridcardaddr() {
        return driveridcardaddr;
    }

    public void setDriveridcardaddr(String driveridcardaddr) {
        this.driveridcardaddr = driveridcardaddr == null ? null : driveridcardaddr.trim();
    }

    public String getDrivercardfirstlicens() {
        return drivercardfirstlicens;
    }

    public void setDrivercardfirstlicens(String drivercardfirstlicens) {
        this.drivercardfirstlicens = drivercardfirstlicens == null ? null : drivercardfirstlicens.trim();
    }

    public String getDrivercardlicenceorg() {
        return drivercardlicenceorg;
    }

    public void setDrivercardlicenceorg(String drivercardlicenceorg) {
        this.drivercardlicenceorg = drivercardlicenceorg == null ? null : drivercardlicenceorg.trim();
    }

    public String getDrivercardregdate() {
        return drivercardregdate;
    }

    public void setDrivercardregdate(String drivercardregdate) {
        this.drivercardregdate = drivercardregdate == null ? null : drivercardregdate.trim();
    }

    public String getDrivercardusefullife() {
        return drivercardusefullife;
    }

    public void setDrivercardusefullife(String drivercardusefullife) {
        this.drivercardusefullife = drivercardusefullife == null ? null : drivercardusefullife.trim();
    }

    public String getDrivercardtype() {
        return drivercardtype;
    }

    public void setDrivercardtype(String drivercardtype) {
        this.drivercardtype = drivercardtype == null ? null : drivercardtype.trim();
    }

    public String getDrivercardimg() {
        return drivercardimg;
    }

    public void setDrivercardimg(String drivercardimg) {
        this.drivercardimg = drivercardimg == null ? null : drivercardimg.trim();
    }

    public Byte getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Byte checkstatus) {
        this.checkstatus = checkstatus;
    }

    public Byte getAuthstats() {
        return authstats;
    }

    public void setAuthstats(Byte authstats) {
        this.authstats = authstats;
    }

    public String getAuthremark() {
        return authremark;
    }

    public void setAuthremark(String authremark) {
        this.authremark = authremark == null ? null : authremark.trim();
    }

    public String getAuthuser() {
        return authuser;
    }

    public void setAuthuser(String authuser) {
        this.authuser = authuser == null ? null : authuser.trim();
    }

    public String getAuthtime() {
        return authtime;
    }

    public void setAuthtime(String authtime) {
        this.authtime = authtime == null ? null : authtime.trim();
    }

    public Long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
}