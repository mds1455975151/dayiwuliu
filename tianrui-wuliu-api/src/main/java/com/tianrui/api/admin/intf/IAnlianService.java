package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.anlian.AnlianDriverReq;
import com.tianrui.api.req.admin.anlian.AnlianShipmentReq;
import com.tianrui.api.req.admin.anlian.AnlianTruckReq;
import com.tianrui.common.vo.Result;
/**
 * 安联相关接口
 * @author jh
 *
 */
public interface IAnlianService {
	/** 认证司机*/
	public Result driver(AnlianDriverReq req);
	/** 认证车辆*/
	public Result truck(AnlianTruckReq req);
	/** 认证运单*/
	public Result shipment(AnlianShipmentReq req);
}
