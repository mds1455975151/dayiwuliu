
/**
 * @标题: Measure.java
 * @功能描述：TODO
 * @作者： lsj
 * @创建时间： 2016年5月5日 下午6:02:15
 */

package com.tianrui.service.bean;

/**
 * @类描述：计量单位换算
 * @创建人：lsj
 * @创建时间：2016年5月5日下午6:02:15
 *
 * @修改人：lsj
 * @修改时间：2016年5月5日下午6:02:15
 * @修改备注：
 *
 */

public class Measure implements IModel{
	
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
	/**
	 * 修改时间
	 */
	private String updateTime;
	/**
	 * @return id
	 */
	
	public String getId() {
		return id;
	}
	/**
	 * @param id id
	 */
	
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return measureCode
	 */
	
	public String getMeasureCode() {
		return measureCode;
	}
	/**
	 * @return baseType
	 */
	
	public String getBaseType() {
		return baseType;
	}
	/**
	 * @param baseType baseType
	 */
	
	public void setBaseType(String baseType) {
		this.baseType = baseType;
	}
	/**
	 * @param measureCode measureCode
	 */
	
	public void setMeasureCode(String measureCode) {
		this.measureCode = measureCode;
	}
	/**
	 * @return measureName
	 */
	
	public String getMeasureName() {
		return measureName;
	}
	/**
	 * @param measureName measureName
	 */
	
	public void setMeasureName(String measureName) {
		this.measureName = measureName;
	}
	/**
	 * @return measureType
	 */
	
	public String getMeasureType() {
		return measureType;
	}
	/**
	 * @param measureType measureType
	 */
	
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	/**
	 * @return measureBase
	 */
	
	public String getMeasureBase() {
		return measureBase;
	}
	/**
	 * @param measureBase measureBase
	 */
	
	public void setMeasureBase(String measureBase) {
		this.measureBase = measureBase;
	}

	
	public Double getConversion() {
		return conversion;
	}
	public void setConversion(Double conversion) {
		this.conversion = conversion;
	}
	/**
	 * @return createId
	 */
	
	public Integer getCreateId() {
		return createId;
	}
	/**
	 * @param createId createId
	 */
	
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	/**
	 * @return createName
	 */
	
	public String getCreateName() {
		return createName;
	}
	/**
	 * @param createName createName
	 */
	
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	/**
	 * @return createTime
	 */
	
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime createTime
	 */
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return updateId
	 */
	
	public Integer getUpdateId() {
		return updateId;
	}
	/**
	 * @param updateId updateId
	 */
	
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	/**
	 * @return updateName
	 */
	
	public String getUpdateName() {
		return updateName;
	}
	/**
	 * @param updateName updateName
	 */
	
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	/**
	 * @return updateTime
	 */
	
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime updateTime
	 */
	
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return measureMain
	 */
	
	public String getMeasureMain() {
		return measureMain;
	}
	/**
	 * @param measureMain measureMain
	 */
	
	public void setMeasureMain(String measureMain) {
		this.measureMain = measureMain;
	}
}
