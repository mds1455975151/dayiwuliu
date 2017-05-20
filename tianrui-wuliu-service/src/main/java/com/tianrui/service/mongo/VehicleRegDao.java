package com.tianrui.service.mongo;


import java.util.List;

import com.tianrui.api.req.admin.vehicle_new.VehicleMGoReq;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.bean.VehicleReg;
/**
 * 车辆注册优化底层服务.
 */
public interface VehicleRegDao extends BaseDao<VehicleReg,String> {

	/** 查询车辆列表*/
	public PaginationVO<VehicleReg> findVehicleReq(VehicleMGoReq req);
	/** 车辆详情*/
	public VehicleReg findVehicleById(String id);
}
