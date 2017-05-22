package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.vehicle_new.DriverNewAuthReq;
import com.tianrui.api.req.admin.vehicle_new.VehicleDriverNewReq;
import com.tianrui.api.resp.admin.vehicle_new.VehicleDriverNewResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IVehicleDriverNewService {

	/** 查询司机*/
	PaginationVO<VehicleDriverNewResp> find(VehicleDriverNewReq req)throws Exception;
	/** 查询司机详情*/
	VehicleDriverNewResp selectBykey(String id)throws Exception;
	/** 司机审核*/
	Result authCheckType(DriverNewAuthReq req)throws Exception;
	
}
