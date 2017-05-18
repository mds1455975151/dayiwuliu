package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.vehicle_new.VehicleDriverNewReq;
import com.tianrui.api.resp.admin.vehicle_new.VehicleDriverNewResp;
import com.tianrui.common.vo.PaginationVO;

public interface IVehicleDriverNewService {

	/** 查询司机*/
	PaginationVO<VehicleDriverNewResp> find(VehicleDriverNewReq req)throws Exception;
	/** 查询司机详情*/
	VehicleDriverNewResp selectBykey(String id)throws Exception;
}
