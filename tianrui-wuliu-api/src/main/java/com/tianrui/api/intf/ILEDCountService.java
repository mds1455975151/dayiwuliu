package com.tianrui.api.intf;

import com.tianrui.api.req.LED.LEDCountReq;
import com.tianrui.api.resp.LED.LEDCountResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface ILEDCountService {

	PaginationVO<LEDCountResp> findCount(LEDCountReq req) throws Exception;
	/**当天数据统计*/
	Result allCountToday(Long data);
	/** 头部数据统计*/
	Result allCountData();
	/** 运单运量统计*/
	Result billCouAl(Long begin,Long end,String timeStr);
	/** 货物统计数据*/
	Result billCargo();
	/** 车型统计*/
	Result vehicleType();
	/** 车辆归属地查询*/
	Result vehicleAddress();
	/** 车辆使用频率*/
	Result vehicleRate();
	/** 运费统计*/
	Result payAmountCount(Long begin,Long end,String timeStr);
	/** 车主统计*/
	Result venderCount();
	/** 货主统计*/
	Result ownerCount();
}
