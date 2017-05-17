package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.vehicle_new.FileVehicleNewReq;
import com.tianrui.api.resp.admin.vehicle_new.FileVehicleNewResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IFileVehicleNewService {
	/** 查询车辆信息*/
	PaginationVO<FileVehicleNewResp> find(FileVehicleNewReq req)throws Exception;
	/** 车辆认证审核通过，并添加司机账户*/
	Result saveVehicleAndDriver(String id)throws Exception;
}
