package com.tianrui.api.resp.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.resp.BaseResp;

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
	 * 车辆类型 1：箱式	2：车板3：冷藏
	 */
	private String vehicletype;
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
	public String getCreatetimeStr() {
		if(createtime!=null){
			createtimeStr =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(createtime));
		}
		return createtimeStr;
	}
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
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
	public void setVehiheadimgpath(String vehiheadimgpath) {
		this.vehiheadimgpath = vehiheadimgpath;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
}
