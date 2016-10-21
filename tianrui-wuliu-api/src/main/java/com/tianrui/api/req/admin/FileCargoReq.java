package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

/**
 * 货物档案对应请求类
 * <p>
 * @author guyuke
 * @time 2016年5月22日 下午4:48:35
 */
public class FileCargoReq extends BaseReq{

	private static final long serialVersionUID = -7190183760043617974L;
	
	/** 主键 */
	private String id;
	/** 组织名称 */
	private String orgName;
	/** 组织类型 */
	private String orgType;
	/** 组织编码 */
	private String orgCode;
	/** 物料编码 */
	private String materCode;
	/** 物料名称 */
	private String materName;
	/** 物料类别 */
	private String materClass;
	/** 状态 */
	private String state;
	/** 支付类型（0：在线支付，1：发票单支付） */
	private String payType;
	/** 货物规格 */
	private String spec;
	/** 货物型号 */
	private String model;
	/** 物料助记码 */
	private String materMNCode;
	/** 主计量单位 */
	private String measUnit;
	/** 图片地址 */
	private String imgPath;
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
	 * 获取组织类型
	 * @return orgType
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * 设置组织类型
	 * @param newOrgType
	 */
	public void setOrgType(String newOrgType) {
		this.orgType = newOrgType;
	}

	/**
	 * 获取组织编码
	 * @return orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * 设置组织编码
	 * @param newOrgCode
	 */
	public void setOrgCode(String newOrgCode) {
		this.orgCode = newOrgCode;
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
	 * 获取物料类别
	 * @return materClass
	 */
	public String getMaterClass() {
		return materClass;
	}
	
	/**
	 * 设置物料类别
	 * @param newMaterClass
	 */
	public void setMaterClass(String newMaterClass) {
		this.materClass = newMaterClass;
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
	 * 获取支付类型
	 * @return payType
	 */
	public String getPayType() {
		return payType;
	}
	
	/**
	 * 设置支付类型
	 * @param payType
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	/**
	 * 获取货物规格
	 * @return spec
	 */
	public String getSpec() {
		return spec;
	}
	
	/**
	 * 设置货物规格
	 * @param newSpec
	 */
	public void setSpec(String newSpec) {
		this.spec = newSpec;
	}
	
	/**
	 * 获取货物型号
	 * @return model
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * 设置货物型号
	 * @param newModel
	 */
	public void setModel(String newModel) {
		this.model = newModel;
	}
	
	/**
	 * 获取物料助记码
	 * @return materMNCode
	 */
	public String getMaterMNCode() {
		return materMNCode;
	}
	
	/**
	 * 设置物料助记码
	 * @param newMaterMNCode
	 */
	public void setMaterMNCode(String newMaterMNCode) {
		this.materMNCode = newMaterMNCode;
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
	 * 获取图片地址
	 * @return imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * 设置图片地址
	 * @param newImgPath
	 */
	public void setImgPath(String newImgPath) {
		this.imgPath = newImgPath;
	}

	/**
	 * 获取创建人
	 * @return imgPath
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
	 * 获取修改人
	 * @return imgPath
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
	 * @return imgPath
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
	 * @return imgPath
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
	 * @return imgPath
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
	 * @return imgPath
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
	 * @return imgPath
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
