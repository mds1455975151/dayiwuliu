package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.cargoplan.CargoReq;
import com.tianrui.api.resp.front.cargoplan.CargoResp;

/**
 * 
 * @类描述：货物档案
 * @创建人：lsj
 * @创建时间：2016年5月23日上午10:45:43
 *
 * @修改人：lsj
 * @修改时间：2016年5月23日上午10:45:43
 * @修改备注：
 *
 */
public interface ICargoService {
	
	List<CargoResp> findCargoEntity(CargoReq req) throws Exception;
	CargoResp findCargoById(String id) throws Exception;
}
