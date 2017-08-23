package com.tianrui.service.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tianrui.common.utils.DateUtil;

@Document(collection = "vehicle_gps_zjxl")
public class VehicleGpsZjxl implements Serializable{

	private static final long serialVersionUID = 5579767148660524131L;

	@Id
	private String id;
	
	//图片 上传时间
	private long timeStamp;
	//时间戳
	private String createTime =DateUtil.getDateString();
	//utc时间
	private String utcTime ;
	
	private Double lat;
	private Double lon;
	private Long utc;
	private String vehicleNo;
	private String addr;
	private String spd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getUtc() {
		return utc;
	}
	public void setUtc(Long utc) {
		this.utc = utc;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getSpd() {
		return spd;
	}
	public void setSpd(String spd) {
		this.spd = spd;
	}
	public String getUtcTime() {
		return utcTime;
	}
	public void setUtcTime(String utcTime) {
		this.utcTime = utcTime;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	
}
