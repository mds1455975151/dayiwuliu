package com.tianrui.api.req.front.cargoplan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tianrui.api.req.BaseReq;

/**
 * 
 * @类描述：货运计划
 * @创建人：tank
 * @创建时间：2016年4月22日下午4:44:20
 *
 * @修改人：tank
 * @修改时间：2016年4月22日下午4:44:20
 * @修改备注：
 *
 */
public class PlanConfirmReq extends BaseReq{
	private static final long serialVersionUID = -3942295359427263186L;
	
    private String id;
   
    private String curruId;
    
    //理由
    private String reson;
    //理由类型
    private String resonType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurruId() {
		return curruId;
	}
	public void setCurruId(String curruId) {
		this.curruId = curruId;
	}
	public String getReson() {
		return reson;
	}
	public void setReson(String reson) {
		this.reson = reson;
	}
	public String getResonType() {
		return resonType;
	}
	public void setResonType(String resonType) {
		this.resonType = resonType;
	}
    
    
    
   
}