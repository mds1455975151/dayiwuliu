package com.tianrui.api.intf;

import com.tianrui.api.req.accessLog.AccessLogReq;
import com.tianrui.api.req.front.api.VehicleGpsReq;
import com.tianrui.api.resp.accessLog.AccessLogResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IAccessLogService {

	public PaginationVO<AccessLogResp> select(AccessLogReq req)throws Exception;
	
	public Result save(AccessLogReq req) throws Exception;
	
	public Result saveLog(Result rs,VehicleGpsReq req,String url)throws Exception;
}
