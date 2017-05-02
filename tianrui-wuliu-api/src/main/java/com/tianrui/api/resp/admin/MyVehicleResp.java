package com.tianrui.api.resp.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.util.DateTypeUtil;

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
public class MyVehicleResp extends BaseResp{
	
	private String id;
	/**
	 * 车主id
	 */
	private String memberid;
	private String ownername;
	private String ownerphone;
	private String companyname;
	private String companytel;
	
	
	private String registcode;
	private String registimage;
	private String opercode;
	private String operimage;
	
	private String identitycode;
	private String identieyimage;
	private String agreeimage;
	private String roadtransportcode;
	private String roadtransportimage;
	
	private String desc1;
	private String desc2;
	private String desc3;
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
	
	private Double vehiwidth;
	 
	private Double vehiheight;
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
	
	private String createtimeStr;
	/**
	 * 行驶证照片路径
	 */
	private String vehilicenseimgpath;
	/**
	 * 车头照片路径
	 */
	private String vehiheadimgpath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdentitycode() {
		identitycode = StringUtils.isBlank(identitycode)?"":identitycode.toUpperCase();
		return identitycode;
	}
	public void setIdentitycode(String identitycode) {
		this.identitycode = identitycode;
	}
	public String getIdentieyimage() {
		identieyimage = StringUtils.isBlank(identieyimage)?"":identieyimage;
		return identieyimage;
	}
	public void setIdentieyimage(String identieyimage) {
		this.identieyimage = identieyimage;
	}
	public String getAgreeimage() {
		agreeimage = StringUtils.isBlank(agreeimage)?"":agreeimage;
		return agreeimage;
	}
	public void setAgreeimage(String agreeimage) {
		this.agreeimage = agreeimage;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getRoadtransportcode() {
		roadtransportcode = StringUtils.isBlank(roadtransportcode)?"":roadtransportcode.toUpperCase();
		return roadtransportcode;
	}
	public void setRoadtransportcode(String roadtransportcode) {
		this.roadtransportcode = roadtransportcode;
	}
	public String getRoadtransportimage() {
		roadtransportimage = StringUtils.isBlank(roadtransportimage)?"":roadtransportimage;
		return roadtransportimage;
	}
	public void setRoadtransportimage(String roadtransportimage) {
		this.roadtransportimage = roadtransportimage;
	}
	public String getRegistcode() {
		registcode = StringUtils.isBlank(registcode)?"":registcode.toUpperCase();
		return registcode;
	}
	public void setRegistcode(String registcode) {
		this.registcode = registcode;
	}
	public String getRegistimage() {
		registimage = StringUtils.isBlank(registimage)?"":registimage;
		return registimage;
	}
	public void setRegistimage(String registimage) {
		this.registimage = registimage;
	}
	public String getOpercode() {
		opercode = StringUtils.isBlank(opercode)?"":opercode.toUpperCase();
		return opercode;
	}
	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	public String getOperimage() {
		operimage = StringUtils.isBlank(operimage)?"":operimage;
		return operimage;
	}
	public void setOperimage(String operimage) {
		this.operimage = operimage;
	}
	public String getCreatetimeStr() {
		if(createtime!=null){
			createtimeStr =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(createtime));
		}
		return createtimeStr;
	}
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}
	public String getOwnername() {
		return ownername;
	}
	public String getVehicletypename() {
		return vehicletypename;
	}
	public void setVehicletypename(String vehicletypename) {
		this.vehicletypename = vehicletypename;
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
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getVehicleno() {
		vehicleno = StringUtils.isBlank(vehicleno)?"":vehicleno.toUpperCase();
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
	public String getVehicleprefix() {
		vehicleprefix = StringUtils.isBlank(vehicleprefix)?"":vehicleprefix.toUpperCase();
		return vehicleprefix;
	}
	public void setVehicleprefix(String vehicleprefix) {
		this.vehicleprefix = vehicleprefix;
	}
	public Double getVehiweight() {
		return DateTypeUtil.doubleType(vehiweight);
	}
	public void setVehiweight(Double vehiweight) {
		this.vehiweight = vehiweight;
	}
	public Double getVehilength() {
		return DateTypeUtil.doubleType(vehilength);
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
	public void setVehiheadimgpath(String vehiheadimgpath) {
		this.vehiheadimgpath = vehiheadimgpath;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public String getDesc2() {
		desc2 = StringUtils.isBlank(desc2)?"2":desc2;
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
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
}
