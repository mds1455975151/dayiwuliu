package com.tianrui.api.resp.front.cargoplan;

import com.tianrui.api.resp.BaseResp;

/**
 * 计划 路径关联关系
 * @author lixp  
 *
 */
public class PlanRouteResp extends BaseResp  {

	private String planId;
	private String routeId;
	private String routeName;
	
	
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	
	
	
    
}
