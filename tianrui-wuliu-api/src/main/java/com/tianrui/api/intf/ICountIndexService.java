package com.tianrui.api.intf;

import com.tianrui.api.req.count.CountSelectReq;
import com.tianrui.common.vo.Result;

public interface ICountIndexService {
	
	//首页数据每天统计
	void everyDay(String dataStr)throws Exception;
}
