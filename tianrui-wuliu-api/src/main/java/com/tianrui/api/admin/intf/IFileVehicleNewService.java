package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.vehicle_new.FileVehicleNewReq;
import com.tianrui.api.resp.admin.vehicle_new.FileVehicleNewResp;
import com.tianrui.common.vo.PaginationVO;

public interface IFileVehicleNewService {

	PaginationVO<FileVehicleNewResp> find(FileVehicleNewReq req)throws Exception;
}
