package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

/**
 * 
 * @类描述：我的车辆
 * @创建人：lsj
 * @创建时间：2016年6月4日上午9:56:17
 *
 * @修改人：lsj
 * @修改时间：2016年6月4日上午9:56:17
 * @修改备注：
 *
 */
public class MyVehicleReq extends BaseReq{

	private String id;
	/**
	 * 车牌号前缀
	 */
	private String vehicleprefix;

	private String ownername;
	private String ownerphone;
	private String companyname;
	private String companytel;
	/** 道路运输证*/
	private String roadtransportcode;
	
	private String desc2;
	/**
	 * 车牌号
	 */
	private String vehicleno;
	/**
	 * 车主姓名
	 */
	private String userName;
	/**
	 * 车主电话
	 */
	private String telphone;
	/**
	 * 车辆类型(1:箱式,2:车板,3:冷藏,4:散装罐车,5:半挂车)
	 */
	private String vehicletype;
	/**
	 * 可用长度
	 */
	private Double weight;
	/**
	 * -1:认证失败 0:未认证 1:认证成功2:认证中  
	 */
	private Byte status;
	/**
	 * 可用载重（吨）
	 */
	private Double length;
	/**
	 * 1:已认证  0:未认证 
	 */
	private Byte verified;
	/**
	 * 认证时间
	 */
	private Long createtime;
	private Long audittime;//审核时间
	private String auditname;//审核人
	
	private Integer limit;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getLimit() {
		limit = (pageNo-1)*pageSize;
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getVehicleprefix() {
		return vehicleprefix;
	}
	public void setVehicleprefix(String vehicleprefix) {
		this.vehicleprefix = vehicleprefix;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTelphone() {
		return telphone;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getLength() {
		return length;
	}
	public String getRoadtransportcode() {
		return roadtransportcode;
	}
	public void setRoadtransportcode(String roadtransportcode) {
		this.roadtransportcode = roadtransportcode;
	}
	public void setLength(Double length) {
		this.length = length;
	}
	public Byte getVerified() {
		return verified;
	}
	public void setVerified(Byte verified) {
		this.verified = verified;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getOwnerphone() {
		return ownerphone;
	}
	public void setOwnerphone(String ownerphone) {
		this.ownerphone = ownerphone;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCompanytel() {
		return companytel;
	}
	public void setCompanytel(String companytel) {
		this.companytel = companytel;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getAuditname() {
		return auditname;
	}
	public void setAuditname(String auditname) {
		this.auditname = auditname;
	}
	public Long getAudittime() {
		return audittime;
	}
	public void setAudittime(Long audittime) {
		this.audittime = audittime;
	}
	
	
}
