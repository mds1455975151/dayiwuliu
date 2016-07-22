package com.tianrui.api.resp.admin;

import com.tianrui.api.resp.BaseResp;

/**
 * 我的货物对应响应类
 * <p>
 * @author guyuke
 * @time 2016年5月22日 下午5:28:42
 */
public class FileOrgCargoResp extends BaseResp{

	private static final long serialVersionUID = -7190183760043617974L;

	/** 主键 */
	private String id;
	/** 运价策略调用次数*/
	private String count;
	/** 组织主键 */
	private String orgId;
	/** 组织名称 */
	private String orgName;
	/** 物料主键 */
	private String materId;
	/** 物料编码 */
	private String materCode;
	/** 物料名称 */
	private String materName;
	/** 物料状态 */
	private String materState;
	/** 主计量单位 */
	private String measUnit;
	/** 状态 */
	private String state;
	/** 创建人 */
	private String creator;
	/** 创建时间 */
	private String createTime;
	/** 修改人 */
	private String modifier;
	/** 修改时间*/
	private String modifyTime;
	/** 自定义项1 */
	private String desc1;
	/** 自定义项2 */
	private String desc2;
	/** 自定义项3 */
	private String desc3;
	/** 自定义项4 */
	private String desc4;
	
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
	
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * 获取组织ID
	 * @return orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * 设置组织ID
	 * @param newOrgId
	 */
	public void setOrgId(String newOrgId) {
		this.orgId = newOrgId;
	}
	
	/**
	 * 获取组织名称
	 * @return orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * 设置组织名称
	 * @param newOrgName
	 */
	public void setOrgName(String newOrgName) {
		this.orgName = newOrgName;
	}
	
	/**
	 * 获取物料ID
	 * @return materId
	 */
	public String getMaterId() {
		return materId;
	}
	
	/**
	 * 设置物料ID
	 * @param newMaterId
	 */
	public void setMaterId(String newMaterId) {
		this.materId = newMaterId;
	}
	
	/**
	 * 获取物料编码
	 * @return materCode
	 */
	public String getMaterCode() {
		return materCode;
	}
	
	/**
	 * 设置物料编码
	 * @param newMaterCode
	 */
	public void setMaterCode(String newMaterCode) {
		this.materCode = newMaterCode;
	}
	
	/**
	 * 获取物料名称
	 * @return materName
	 */
	public String getMaterName() {
		return materName;
	}
	
	/**
	 * 设置物料名称
	 * @param newMaterName
	 */
	public void setMaterName(String newMaterName) {
		this.materName = newMaterName;
	}
	
	/**
	 * 获取物料状态
	 * @return materState
	 */
	public String getMaterState() {
		return materState;
	}

	/**
	 * 设置物料状态
	 * @param newMaterState
	 */
	public void setMaterState(String newMaterState) {
		this.materState = newMaterState;
	}
	
	/**
	 * 获取状态
	 * @return state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 设置状态
	 * @param newState
	 */
	public void setState(String newState) {
		this.state = newState;
	}
	
	/**
	 * 获取主计量单位
	 * @return measUnit
	 */
	public String getMeasUnit() {
		return measUnit;
	}
	
	/**
	 * 设置主计量单位
	 * @param newMeasUnit
	 */
	public void setMeasUnit(String newMeasUnit) {
		this.measUnit = newMeasUnit;
	}

	/**
	 * 获取创建人
	 * @return creator
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * 设置创建人
	 * @param newCreator
	 */
	public void setCreator(String newCreator) {
		this.creator = newCreator;
	}

	/**
	 * 获取创建时间
	 * @return createTime
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
	 * 获取修改人
	 * @return modifier
	 */
	public String getModifier() {
		return modifier;
	}
	
	/**
	 * 设置修改人
	 * @param newModifier
	 */
	public void setModifier(String newModifier) {
		this.modifier = newModifier;
	}

	/**
	 * 获取修改时间
	 * @return modifyTime
	 */
	public String getModifyTime() {
		return modifyTime;
	}
	
	/**
	 * 设置修改时间
	 * @param newModifyTime
	 */
	public void setModifyTime(String newModifyTime) {
		this.modifyTime = newModifyTime;
	}

	/**
	 * 获取自定义项1
	 * @return desc1
	 */
	public String getDesc1() {
		return desc1;
	}
	
	/**
	 * 设置自定义项1
	 * @param newDesc1
	 */
	public void setDesc1(String newDesc1) {
		this.desc1 = newDesc1;
	}

	/**
	 * 获取自定义项2
	 * @return desc2
	 */
	public String getDesc2() {
		return desc2;
	}
	
	/**
	 * 设置自定义项2
	 * @param newDesc2
	 */
	public void setDesc2(String newDesc2) {
		this.desc2 = newDesc2;
	}

	/**
	 * 获取自定义项3
	 * @return desc3
	 */
	public String getDesc3() {
		return desc3;
	}
	
	/**
	 * 设置自定义项3
	 * @param newDesc3
	 */
	public void setDesc3(String newDesc3) {
		this.desc3 = newDesc3;
	}

	/**
	 * 获取自定义项4
	 * @return desc4
	 */
	public String getDesc4() {
		return desc4;
	}
	
	/**
	 * 设置自定义项4
	 * @param newDesc4
	 */
	public void setDesc4(String newDesc4) {
		this.desc4 = newDesc4;
	}
	
}
