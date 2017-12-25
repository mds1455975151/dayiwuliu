package com.tianrui.api.tracking.intf;

import com.tianrui.api.req.money.TrackReq;
import com.tianrui.api.req.money.TrackSelectReq;
import com.tianrui.api.resp.money.CustomRcordResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface ITrackingService {

	/**
	 * 记录在途车辆轨迹
	 * @param req
	 * @return
	 */
	Result save(TrackReq req) ;
	/** 在途轨迹查询*/
	public PaginationVO<CustomRcordResp> select(TrackSelectReq req)throws Exception;
}
