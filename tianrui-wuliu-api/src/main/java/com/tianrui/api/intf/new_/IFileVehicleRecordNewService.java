package com.tianrui.api.intf.new_;

import com.tianrui.api.req.new_.FileVehicleRecordNewReq;
import com.tianrui.api.resp.new_.FileVehicleRecordNewResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IFileVehicleRecordNewService {
	/** 查询我的运力*/
	PaginationVO<FileVehicleRecordNewResp> select(FileVehicleRecordNewReq req) throws Exception;
	/** 开票运力审核*/
	Result vehicleTrickCheck(FileVehicleRecordNewReq req)throws Exception;
}
