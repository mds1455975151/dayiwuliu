package com.tianrui.service.bean;

public class Vehickle implements IModel{
	/**
	 * 主键
	 */
    private String id;

	/**
	 * 车牌号前缀
	 */
    private String prefix;

	/**
	 * 车牌号
	 */
    private String vehicleno;

	/**
	 * 车辆类型(1:箱式,2:车板,3:冷藏,4:散装罐车,5:半挂车)
	 */
    private Byte vehicletype;

	/**
	 * 类型名称
	 */
    private String vehicletypename;

	/**
	 * 可用长度
	 */
    private Double length;

	/**
	 * 可用载重（吨）
	 */
    private Double weight;

	/**
	 * -1:认证失败 0:未认证 1:认证成功2:认证中  
	 */
    private Byte verified;

	/**
	 * 行驶证路径
	 */
    private String registrationimgpath;

	/**
	 * 车头照片路径
	 */
    private String frontphotoimgpath;

	/**
	 * 车左侧照片路径
	 */
    private String leftphotoimgpath;

	/**
	 * 车右侧照片路径
	 */
    private String rightphotoimgpath;

	/**
	 * 人车合照路径
	 */
    private String ownerimgpath;
    /**
     * 认证失败愿意
     */
    private String desc1;

    private String desc2;

    private String desc3;

    private String desc4;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? null : prefix.trim();
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
    }

    public Byte getVehicletype() {
        return vehicletype;
    }

    public void setVehicletype(Byte vehicletype) {
        this.vehicletype = vehicletype;
    }

    public String getVehicletypename() {
        return vehicletypename;
    }

    public void setVehicletypename(String vehicletypename) {
        this.vehicletypename = vehicletypename == null ? null : vehicletypename.trim();
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Byte getVerified() {
        return verified;
    }

    public void setVerified(Byte verified) {
        this.verified = verified;
    }

    public String getRegistrationimgpath() {
        return registrationimgpath;
    }

    public void setRegistrationimgpath(String registrationimgpath) {
        this.registrationimgpath = registrationimgpath == null ? null : registrationimgpath.trim();
    }

    public String getFrontphotoimgpath() {
        return frontphotoimgpath;
    }

    public void setFrontphotoimgpath(String frontphotoimgpath) {
        this.frontphotoimgpath = frontphotoimgpath == null ? null : frontphotoimgpath.trim();
    }

    public String getLeftphotoimgpath() {
        return leftphotoimgpath;
    }

    public void setLeftphotoimgpath(String leftphotoimgpath) {
        this.leftphotoimgpath = leftphotoimgpath == null ? null : leftphotoimgpath.trim();
    }

    public String getRightphotoimgpath() {
        return rightphotoimgpath;
    }

    public void setRightphotoimgpath(String rightphotoimgpath) {
        this.rightphotoimgpath = rightphotoimgpath == null ? null : rightphotoimgpath.trim();
    }

    public String getOwnerimgpath() {
        return ownerimgpath;
    }

    public void setOwnerimgpath(String ownerimgpath) {
        this.ownerimgpath = ownerimgpath == null ? null : ownerimgpath.trim();
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
}