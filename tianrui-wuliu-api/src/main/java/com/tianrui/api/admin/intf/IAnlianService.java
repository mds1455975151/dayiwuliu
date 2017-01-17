package com.tianrui.api.admin.intf;

import java.lang.reflect.InvocationTargetException;

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
	/** 认证运单
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException */
	public Result shipment(AnlianShipmentReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	/** 查询运单状态*/
	public Result detail(String shipmentno);
	
	
}
