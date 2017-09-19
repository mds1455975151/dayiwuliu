package com.tianrui.service.admin.bean;


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
public class MyVehicle{

	private String id;
	/**
	 * 车主id
	 */
	private String memberid;
	//车主账号
	private String cellphone;
	
	private String drivingTime;
	
	private String registcode;
	private String registimage;
	private String opercode;
	private String operimage;
	
	private String identitycode;
	private String identieyimage;
	private String agreeimage;
	private String roadtransportcode;
	private String roadtransportimage;
	
	private String ownername;
	private String ownerphone;
	private String companyname;
	private String companytel;
	
	private String desc1;
	private String desc2;
	private String desc3;
	//查询开始时间
  	private Long beginTime;
  	//查询结束时间
  	private Long endTime;
	private Double vehiwidth;
	 
	private Double vehiheight;
	private Long audittime;
	private String auditname;//审核人
	/**
	 * 车牌号前缀
	 */
	private String vehicleprefix;
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
	
	private String vehicletypename;
	/**
	 * 可用长度
	 */
	private Double vehiweight;
	/**
	 * 可用载重（吨）
	 */
	private Double vehilength;
	/**
	 * -1:认证失败 0:未认证 1:认证成功2:认证中  
	 */
	private Byte status;
	/**
	 * 认证时间
	 */
	private Long createtime;
	/**
	 * 行驶证照片路径
	 */
	private String vehilicenseimgpath;
	/**
	 * 车头照片路径
	 */
	private String vehiheadimgpath;
	/**
	 * 分页开始数据
	 */
	private Integer pageNo;
	/**
	 * 分页查询数据
	 */
	private Integer pageSize;
	private Integer limit;
	public String getId() {
		return id;
	}
	
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getMemberid() {
		return memberid;
	}

	public String getVehicletypename() {
		return vehicletypename;
	}

	public void setVehicletypename(String vehicletypename) {
		this.vehicletypename = vehicletypename;
	}

	public String getRegistcode() {
		return registcode;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public void setRegistcode(String registcode) {
		this.registcode = registcode;
	}

	public String getRegistimage() {
		return registimage;
	}

	public void setRegistimage(String registimage) {
		this.registimage = registimage;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getOpercode() {
		return opercode;
	}

	public String getIdentitycode() {
		return identitycode;
	}

	public void setIdentitycode(String identitycode) {
		this.identitycode = identitycode;
	}

	public String getIdentieyimage() {
		return identieyimage;
	}

	public void setIdentieyimage(String identieyimage) {
		this.identieyimage = identieyimage;
	}

	public String getAgreeimage() {
		return agreeimage;
	}

	public void setAgreeimage(String agreeimage) {
		this.agreeimage = agreeimage;
	}

	public String getRoadtransportcode() {
		return roadtransportcode;
	}

	public void setRoadtransportcode(String roadtransportcode) {
		this.roadtransportcode = roadtransportcode;
	}

	public String getRoadtransportimage() {
		return roadtransportimage;
	}

	public void setRoadtransportimage(String roadtransportimage) {
		this.roadtransportimage = roadtransportimage;
	}

	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}

	public String getOperimage() {
		return operimage;
	}

	public void setOperimage(String operimage) {
		this.operimage = operimage;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
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

	public void setId(String id) {
		this.id = id;
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
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public String getVehicleprefix() {
		return vehicleprefix;
	}
	public void setVehicleprefix(String vehicleprefix) {
		this.vehicleprefix = vehicleprefix;
	}
	public Double getVehiweight() {
		return vehiweight;
	}
	public void setVehiweight(Double vehiweight) {
		this.vehiweight = vehiweight;
	}
	public Double getVehilength() {
		return vehilength;
	}
	public void setVehilength(Double vehilength) {
		this.vehilength = vehilength;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getVehilicenseimgpath() {
		return vehilicenseimgpath;
	}
	public void setVehilicenseimgpath(String vehilicenseimgpath) {
		this.vehilicenseimgpath = vehilicenseimgpath;
	}
	public String getVehiheadimgpath() {
		return vehiheadimgpath;
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

	public void setVehiheadimgpath(String vehiheadimgpath) {
		this.vehiheadimgpath = vehiheadimgpath;
	}

	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	public Double getVehiwidth() {
		return vehiwidth;
	}

	public void setVehiwidth(Double vehiwidth) {
		this.vehiwidth = vehiwidth;
	}

	public Double getVehiheight() {
		return vehiheight;
	}

	public void setVehiheight(Double vehiheight) {
		this.vehiheight = vehiheight;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Long getAudittime() {
		return audittime;
	}

	public void setAudittime(Long audittime) {
		this.audittime = audittime;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getDrivingTime() {
		return drivingTime;
	}

	public void setDrivingTime(String drivingTime) {
		this.drivingTime = drivingTime;
	}

	public String getAuditname() {
		return auditname;
	}

	public void setAuditname(String auditname) {
		this.auditname = auditname;
	}
	
}
