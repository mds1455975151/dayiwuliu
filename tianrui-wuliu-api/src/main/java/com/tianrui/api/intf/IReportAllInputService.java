package com.tianrui.api.intf;

import com.tianrui.common.vo.Result;

/**
 * 运单,计划,账单报表统计数据导入
 * @author lixp 2017年9月23日 09:16:31
 *
 */
public interface IReportAllInputService {

	/** 添加计划*/
	public Result planUpdate()throws Exception;
	
	/** 添加运单*/
	public Result billAlianUpdate()throws Exception;
	
	public Result billCommonUpdate()throws Exception;
	
	/** 添加账单*/
	public Result payAlianUpdate()throws Exception;
	
	/**
	 * 缓存相关更新
	 * @return
	 * @throws Exception
	 */
	public Result cacheUpdate()throws Exception;
	
}
