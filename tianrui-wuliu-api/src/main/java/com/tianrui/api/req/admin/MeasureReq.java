package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

public class MeasureReq extends BaseReq {
	/**
	 * id
	 */
	private String id;
	/**
	 * 单位编码
	 */
	private String measureCode;
	/**
	 * 单位名称
	 */
	private String measureName;
	/**
	 * 所属量纲
	 */
	private String measureType;
	/**
	 * 量纲联合，主量纲
	 */
	private String measureMain;
	/**
	 * 是否基本单位 0是    1否
 	 */
	private String measureBase;
	/**
	 * 换算率
	 */
	private Double conversion;
	/**
	 * 创建人id
	 */
	private Integer createId;
	/**
	 * 创建人
	 */
	private String createName;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 修改人Id
	 */
	private Integer updateId;
	/**
	 * 修改人
	 */
	private String updateName;
	
	public String getMeasureMain() {
		return measureMain;
	}
	public void setMeasureMain(String measureMain) {
		this.measureMain = measureMain;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMeasureCode() {
		return measureCode;
	}
	public void setMeasureCode(String measureCode) {
		this.measureCode = measureCode;
	}
	public String getMeasureName() {
		return measureName;
	}
	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}
	public String getMeasureType() {
		return measureType;
	}
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	public String getMeasureBase() {
		return measureBase;
	}
	public void setMeasureBase(String measureBase) {
		this.measureBase = measureBase;
	}
	public Double getConversion() {
		return conversion;
	}
	public void setConversion(Double conversion) {
		this.conversion = conversion;
	}
	public Integer getCreateId() {
		return createId;
	}
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	public String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	
}
