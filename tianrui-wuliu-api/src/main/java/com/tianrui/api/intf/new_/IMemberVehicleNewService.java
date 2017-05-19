package com.tianrui.api.intf.new_;

import com.tianrui.api.req.new_.MemberVehicleNewReq;
import com.tianrui.api.req.new_.V_vehicle_driverReq;
import com.tianrui.api.resp.new_.V_vehicle_driverResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IMemberVehicleNewService {
	
	/** 查询我的运力*/
	PaginationVO<V_vehicle_driverResp> select(V_vehicle_driverReq req) throws Exception;
	/** 查询运力详情*/
	public Result selectVehicle(String vehicleno)throws Exception;
	/** 添加车辆为我的运力*/
	public Result saveMyVehicle(MemberVehicleNewReq req)throws Exception;
	/** 删除运力*/
	public Result delect(String id)throws Exception;
}
