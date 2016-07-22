package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.cargoplan.RouteReq;
import com.tianrui.api.resp.front.cargoplan.RouteResp;

/**
 * 
 * @类描述：路径管理
 * @创建人：lsj
 * @创建时间：2016年5月23日上午10:45:22
 *
 * @修改人：lsj
 * @修改时间：2016年5月23日上午10:45:22
 * @修改备注：
 *
 */
public interface IRouteService {

	List<RouteResp> findRouteCgId(String id) throws Exception;
	
	List<RouteResp> findRouteByEntity(RouteReq entity) throws Exception;
	/**
	 * 根据id查询
	 */
	RouteResp findRouteById(String id) throws Exception;
}
