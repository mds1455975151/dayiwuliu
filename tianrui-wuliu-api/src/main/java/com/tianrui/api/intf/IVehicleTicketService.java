package com.tianrui.api.intf;

import com.tianrui.api.req.front.vehicle.VehicleTicketReq;
import com.tianrui.common.vo.Result;

/**
 * 前台开票认证
 * @author jh
 *
 */
public interface IVehicleTicketService {

	/** 申请开票认证*/
	public Result insert(VehicleTicketReq req)throws Exception;
}
