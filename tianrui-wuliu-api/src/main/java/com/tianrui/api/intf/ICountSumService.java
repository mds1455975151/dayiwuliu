package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.count.CountSumReq;
import com.tianrui.api.resp.count.CountSumResp;

/**
 * 
 * @类描述：统计查询
 * @创建人：lsj
 * @创建时间：2016年10月30日下午3:00:55
 *
 * @修改人：lsj
 * @修改时间：2016年10月30日下午3:00:55
 * @修改备注：
 *
 */
public interface ICountSumService {
	/** 查询数据总和
	 * @throws Exception */
	List<CountSumResp> selectByCondition(CountSumReq req) throws Exception;
}
