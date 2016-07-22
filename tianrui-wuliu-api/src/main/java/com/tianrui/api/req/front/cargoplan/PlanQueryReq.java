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
public class PlanQueryReq extends BaseReq{
	private static final long serialVersionUID = -3942295359427263186L;
	
    private String id;
    //状态   
    private String status;
    //货主
    private String ownerId;
    //车主
    private String venderId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getVenderId() {
		return venderId;
	}
	public void setVenderId(String venderId) {
		this.venderId = venderId;
	}
    
    
   
}