package com.tianrui.api.resp.front.vehicle;

import com.tianrui.api.resp.BaseResp;

/**
 * 我的车主对应响应类
 * <p>
 * @author guyuke
 * @time 2016年6月6日 上午9:07:42
 */
public class MemberOwnerResp extends BaseResp{

	private static final long serialVersionUID = -7190183760043617974L;
	
	/** 主键 */
	private String id;
	/** 会员主键 */
	private String memberId;
	/** 车主主键 */
	private String ownerId;
	/** 车主姓名 */
	private String ownerName;
	/** 车主电话 */
	private String ownerTel;
	/** 是否开启 */
	private String isEnabled;
	/** 状态 */
	private String status;
	/** 创建时间 */
	private String createTime;
	/** 个人图像地址 */
	private String avatarsPath;
	
	/**
	 * 获取主键
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置主键
	 * @param newId
	 */
	public void setId(String newId) {
		this.id = newId;
	}
	
	/**
	 * 获取会员主键
	 * @return memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * 设置会员主键
	 * @param newMemberId
	 */
	public void setMemberId(String newMemberId) {
		this.memberId = newMemberId;
	}

	/**
	 * 获取车主主键
	 * @return ownerId
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * 设置车主主键
	 * @param newOwnerId
	 */
	public void setOwnerId(String newOwnerId) {
		this.ownerId = newOwnerId;
	}

	/**
	 * 获取车主姓名
	 * @return ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * 设置车主姓名
	 * @param newOwnerName
	 */
	public void setOwnerName(String newOwnerName) {
		this.ownerName = newOwnerName;
	}

	/**
	 * 获取车主电话
	 * @return ownerTel
	 */
	public String getOwnerTel() {
		return ownerTel;
	}

	/**
	 * 设置车主电话
	 * @param newOwnerTel
	 */
	public void setOwnerTel(String newOwnerTel) {
		this.ownerTel = newOwnerTel;
	}

	/**
	 * 获取是否开启
	 * @return isEnabled
	 */
    public String getIsEnabled() {
		return isEnabled;
	}

    /**
	 * 获取是否开启
	 * @return newIsEnabled
	 */
	public void setIsEnabled(String newIsEnabled) {
		this.isEnabled = newIsEnabled;
	}
	
	/**
	 * 获取状态
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置状态
	 * @param newStatus
	 */
	public void setStatus(String newStatus) {
		this.status = newStatus;
	}
	
	/**
	 * 获取创建时间
	 * @return imgPath
	 */
	public String getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置创建时间
	 * @param newCreateTime
	 */
	public void setCreateTime(String newCreateTime) {
		this.createTime = newCreateTime;
	}
	
    /**
	 * 获取个人图像地址
	 * @return avatarsPath
	 */
    public String getAvatarsPath() {
		return avatarsPath;
	}

    /**
	 * 设置个人图像地址
	 * @param newAvatarsPath
	 */
	public void setAvatarsPath(String newAvatarsPath) {
		this.avatarsPath = newAvatarsPath;
	}
}
