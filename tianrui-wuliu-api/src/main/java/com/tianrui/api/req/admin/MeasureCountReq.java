package com.tianrui.api.req.admin;

import com.tianrui.api.req.BaseReq;

/**
 * 
 * @类描述：计算 数据请求类
 * @创建人：lsj
 * @创建时间：2016年5月17日上午9:55:41
 *
 * @修改人：lsj
 * @修改时间：2016年5月17日上午9:55:41
 * @修改备注：
 *
 */
public class MeasureCountReq extends BaseReq {
	/**
	 * 传参
	 */
	private String value;
	/**
	 * 单位
	 */
	private String measureCode;
	/**
	 * 要换算的量纲
	 */
	private String measureMain;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getMeasureCode() {
		return measureCode;
	}
	public void setMeasureCode(String measureCode) {
		this.measureCode = measureCode;
	}
	public String getMeasureMain() {
		return measureMain;
	}
	public void setMeasureMain(String measureMain) {
		this.measureMain = measureMain;
	}
	
}
