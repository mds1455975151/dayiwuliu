package com.tianrui.api.tracking.intf;

import com.tianrui.api.req.money.TrackReq;
import com.tianrui.common.vo.Result;

public interface ITrackingService {

	/**
	 * 记录在途车辆轨迹
	 * @param req
	 * @return
	 */
	Result save(TrackReq req) ;
}
