package com.tianrui.api.req.front.vehicle;

import com.tianrui.api.req.BaseReq;

/**
 * 车辆司机联合查询对应请求类
 * <p>
 * @author guyuke
 * @time 2016年6月06日 下午4:48:35
 */
public class VehicleAndDriverReq extends BaseReq{

	private static final long serialVersionUID = -7190183760043617974L;
	
	/** 用户主键 */
	private String memberId;
	/** app搜索字段*/
	private String search;
	/** 车辆主键*/
	private String vehiId;
	/** 车牌号前缀 */
	private String vehiPrefix;
	/** 车牌号 */
	private String vehiNo;
	/** 车辆类型(1:箱式,2:车板,3:冷藏,4:散装罐车,5:半挂车) */
	private String vehiType;
	/** 车辆类型名称 */
	private String vehiTypeName;
	/** 车辆长度(米) */
	private String vehiLength;
	/** 车辆重量(吨) */
	private String vehiWeight;
	/** 车头照片路径 */
	private String vehiHeadImgPath;
	/** 车辆状态(-1:认证失败,0:未认证,1:认证成功,2:认证中) */
	private String status;
	/** 认证失败原因 */
	private String memo;
	
	/** 车辆司机表主键 */
	private String vehiDriverId;
	/** 司机主键 */
	private String driverId;
	/** 司机姓名 */
	private String driverName;
	/** 司机电话 */
	private String driverTel;
	/** 驾驶证/身份证号 */
	private String identityCard;
	
	/**
	 * 获取用户主键
	 * @return memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * 设置用户主键
	 * @param newMemberId
	 */
	public void setMemberId(String newMemberId) {
		this.memberId = newMemberId;
	}
	
	/**
	 * 获取车辆主键
	 * @return vehiId
	 */
	public String getVehiId() {
		return vehiId;
	}

	/**
	 * 设置车辆主键
	 * @param newVehiId
	 */
	public void setVehiId(String newVehiId) {
		this.vehiId = newVehiId;
	}

	/**
	 * 获取车牌号前缀
	 * @return vehiprefix
	 */
	public String getVehiPrefix() {
		return vehiPrefix;
	}

	/**
	 * 设置车牌号前缀
	 * @param newVehiPrefix
	 */
	public void setVehiPrefix(String newVehiPrefix) {
		this.vehiPrefix = newVehiPrefix;
	}

	/**
	 * 获取车牌号
	 * @return vehiNo
	 */
	public String getVehiNo() {
		return vehiNo;
	}

	/**
	 * 设置车牌号
	 * @param newVehiNo
	 */
	public void setVehiNo(String newVehiNo) {
		this.vehiNo = newVehiNo;
	}

	/**
	 * 获取车辆类型(1:箱式,2:车板,3:冷藏,4:散装罐车,5:半挂车)
	 * @return vehiType
	 */
	public String getVehiType() {
		return vehiType;
	}

	/**
	 * 设置车辆类型(1:箱式,2:车板,3:冷藏,4:散装罐车,5:半挂车)
	 * @param newVehiType
	 */
	public void setVehiType(String newVehiType) {
		this.vehiType = newVehiType;
	}

	/**
	 * 获取车辆类型名称
	 * @return vehiTypeName
	 */
	public String getVehiTypeName() {
		return vehiTypeName;
	}

	/**
	 * 设置车辆类型名称
	 * @param newVehiTypeName
	 */
	public void setVehiTypeName(String newVehiTypeName) {
		this.vehiTypeName = newVehiTypeName;
	}

	/**
	 * 获取车辆长度(米)
	 * @return vehiLength
	 */
	public String getVehiLength() {
		return vehiLength;
	}

	/**
	 * 设置车辆长度(米)
	 * @param newVehiLength
	 */
	public void setVehiLength(String newVehiLength) {
		this.vehiLength = newVehiLength;
	}

	/**
	 * 获取车辆重量(吨)
	 * @return vehiWeight
	 */
	public String getVehiWeight() {
		return vehiWeight;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * 设置车辆重量(吨)
	 * @param newVehiWeight
	 */
	public void setVehiWeight(String newVehiWeight) {
		this.vehiWeight = newVehiWeight;
	}

	/**
	 * 获取车头照片路径
	 * @return vehiHeadImgPath
	 */
	public String getVehiHeadImgPath() {
		return vehiHeadImgPath;
	}

	/**
	 * 设置车头照片路径
	 * @param newVehiHeadImgPath
	 */
	public void setVehiHeadImgPath(String newVehiHeadImgPath) {
		this.vehiHeadImgPath = newVehiHeadImgPath;
	}

	/**
	 * 获取车辆状态(-1:认证失败,0:未认证,1:认证成功,2:认证中) 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置车辆状态(-1:认证失败,0:未认证,1:认证成功,2:认证中) 
	 * @param newStatus
	 */
	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	/**
	 * 获取认证失败原因
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置认证失败原因
	 * @param newMemo
	 */
	public void setMemo(String newMemo) {
		this.memo = newMemo;
	}
	
	/**
	 * 获取车辆司机表主键
	 * @return vehiDriverId
	 */
	public String getVehiDriverId() {
		return vehiDriverId;
	}

	/**
	 * 设置车辆司机表主键
	 * @param newVehiDriverId
	 */
	public void setVehiDriverId(String newVehiDriverId) {
		this.vehiDriverId = newVehiDriverId;
	}
	
	/**
	 * 获取司机主键
	 * @return driverId
	 */
	public String getDriverId() {
		return driverId;
	}

	/**
	 * 设置司机主键
	 * @param newDriverId
	 */
	public void setDriverId(String newDriverId) {
		this.driverId = newDriverId;
	}

	/**
	 * 获取司机姓名
	 * @return driverName
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * 设置司机姓名
	 * @param newDriverName
	 */
	public void setDriverName(String newDriverName) {
		this.driverName = newDriverName;
	}

	/**
	 * 获取司机电话
	 * @return driverTel
	 */
	public String getDriverTel() {
		return driverTel;
	}

	/**
	 * 设置司机电话
	 * @param newDriverTel
	 */
	public void setDriverTel(String newDriverTel) {
		this.driverTel = newDriverTel;
	}
	
	/**
	 * 获取驾驶证/身份证号
	 * @return identityCard
	 */
	public String getIdentityCard() {
		return identityCard;
	}

	/**
	 * 设置驾驶证/身份证号
	 * @param newIdentityCard
	 */
	public void setIdentityCard(String newIdentityCard) {
		this.identityCard = newIdentityCard;
	}
	
}
