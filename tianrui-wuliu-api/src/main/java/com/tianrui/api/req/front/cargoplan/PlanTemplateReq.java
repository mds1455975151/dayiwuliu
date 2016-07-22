package com.tianrui.api.req.front.cargoplan;

import com.tianrui.api.req.BaseReq;

/**
  * @ClassName: PlanTemplateReq 
  * @Description: 计划模版
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月24日 下午5:05:29 
  *
 */
public class PlanTemplateReq extends BaseReq{
	private static final long serialVersionUID = -3942295359427263186L;
	
    private String id;
    private String curruId;
    
    private String queryKey;
    
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
	public String getQueryKey() {
		return queryKey;
	}
	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}
	public PlanTemplateReq(String id, String curruId) {
		super();
		this.id = id;
		this.curruId = curruId;
	}
	public PlanTemplateReq() {
		super();
	}
	public PlanTemplateReq(String id, String curruId, String queryKey) {
		super();
		this.id = id;
		this.curruId = curruId;
		this.queryKey = queryKey;
	}
	
   
}