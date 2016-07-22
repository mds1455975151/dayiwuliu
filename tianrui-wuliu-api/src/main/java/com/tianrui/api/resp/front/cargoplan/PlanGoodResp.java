package com.tianrui.api.resp.front.cargoplan;

import java.util.List;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.resp.admin.FileOrgCargoResp;
/**
 * 
* @ClassName: PlanGoodResp
* @Description: 货物计划返回list封装类
* @author wuchl
* @date 2016年6月2日
*
 */
public class PlanGoodResp extends BaseResp{
	/**
	 * 货物list
	 */
	private List<FileOrgCargoResp> cargoList;
	/**
	 * 路线list
	 */
	private List<FreightResp> freightList;
	/**
	 * 价格策略list
	 */
	private List<RouteResp> routeList;

	public List<FileOrgCargoResp> getCargoList() {
		return cargoList;
	}

	public void setCargoList(List<FileOrgCargoResp> cargoList) {
		this.cargoList = cargoList;
	}

	public List<FreightResp> getFreightList() {
		return freightList;
	}

	public void setFreightList(List<FreightResp> freightList) {
		this.freightList = freightList;
	}

	public List<RouteResp> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<RouteResp> routeList) {
		this.routeList = routeList;
	}
	
	
	
}
