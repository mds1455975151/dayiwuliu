package com.tianrui.api.intf;

import com.tianrui.api.req.admin.MaterialReq;
import com.tianrui.api.req.admin.MaterielRouteReq;
import com.tianrui.api.req.front.cargoplan.RouteReq;
import com.tianrui.common.vo.Result;

public interface IMaterialSetService {

	/**
	 * 搜索待选全部物料
	 * @author xcy
	 * @param req
	 * @return
	 */
	Result queryMaterial(MaterialReq req);

	/**
	 * 设置到已选物料
	 * @author xcy
	 * @param req
	 * @return
	 */
	Result setSelectedMaterial(MaterialReq req);

	/**
	 * 搜索已选物料
	 * @author xcy
	 * @param req
	 * @return
	 */
	Result querySelecedMaterial(MaterialReq req);

	/**
	 * 查询全部路线
	 * @author xcy
	 * @param req
	 * @return
	 */
	Result queryRoute(RouteReq req);

	/**
	 * 设置路线和物料为已选
	 * @author xcy
	 * @return
	 */
	Result setRoute(MaterielRouteReq req);

	/**
	 * 查询已选路线
	 * @author xcy
	 * @param req
	 * @return
	 */
	Result querySelecedRoute(MaterielRouteReq req);

	/**
	 * 查询待选物料
	 * @author xcy
	 * @param req
	 * @return
	 */
	Result queryWaitMate(MaterialReq req);

	/**
	 * 查询待选路线
	 * @author xcy
	 * @param req
	 * @return
	 */
	Result queryWaitRoute(MaterielRouteReq req);
	
	/** 校验运价策略是否在白名单*/
	Result selectRouteAndCargo(String id);

	/**
	 * 档案数据
	 * @author xcy
	 * @param req
	 * @return
	 */
	Result selectMaterial(RouteReq req);

}
