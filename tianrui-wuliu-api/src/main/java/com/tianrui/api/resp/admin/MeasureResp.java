package com.tianrui.api.resp.admin;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.util.DateTypeUtil;

public class MeasureResp extends BaseResp{

	private String id;
	/**
	 * 计量编码
	 */
	private String measureCode;
	/**
	 * 计量名称
	 */
	private String measureName;
	/**
	 * 所属量纲
	 */
	private String measureType;
	/**
	 * 是否基本单位  0是，，1否
	 */
	private String measureBase;
	/**
	 * 是否基本量纲 0是，，1否
	 */
	private String baseType;
	/**
	 * 量纲联合    主量纲
	 */
	private String measureMain;
	/**
	 * 换算单位(当前单位与基本单位的换算率)(若为量纲联合，则为当前量纲的基本单位与基本量纲的基本单位的换算率)
	 */
	private Double conversion;
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
	public String getBaseType() {
		return baseType;
	}
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}
	public String getMeasureMain() {
		return measureMain;
	}
	public void setMeasureMain(String measureMain) {
		this.measureMain = measureMain;
	}
	public Double getConversion() {
		return DateTypeUtil.doubleType(conversion);
	}
	public void setConversion(Double conversion) {
		this.conversion = conversion;
	}
	
}
