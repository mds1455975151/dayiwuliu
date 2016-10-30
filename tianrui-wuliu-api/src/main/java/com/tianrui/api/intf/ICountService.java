package com.tianrui.api.intf;

import com.tianrui.api.req.count.CountSelectReq;
import com.tianrui.common.vo.Result;

/**
 * 
 * @类描述：统计
 * @创建人：lsj
 * @创建时间：2016年10月26日下午2:34:15
 *
 * @修改人：lsj
 * @修改时间：2016年10月26日下午2:34:15
 * @修改备注：
 *
 */
public interface ICountService {
	/** 每天定时任务*/
	void everyDay(CountSelectReq count)throws Exception;
	/** 每小时定时任务*/
	void everyHour(CountSelectReq count)throws Exception;
	/** 最热路线*/
	void routeHot(CountSelectReq count)throws Exception;
	/** 每日运单量*/
	void billEveryDay(CountSelectReq count)throws Exception;
}
