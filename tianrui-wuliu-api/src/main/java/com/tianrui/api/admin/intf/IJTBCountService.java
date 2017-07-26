package com.tianrui.api.admin.intf;

import com.tianrui.api.req.count.JtbPushCountReq;
import com.tianrui.common.vo.Result;

public interface IJTBCountService {

	/** 大易推送
	 * @throws Exception */
	public Result dyPush(JtbPushCountReq req) throws Exception;
	/** 安联推送
	 * @throws Exception */
	public Result alPush(JtbPushCountReq req) throws Exception;
}
