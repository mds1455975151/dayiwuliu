package com.tianrui.api.message.intf;

import com.tianrui.api.req.groupMsg.CustomRcordReq;
import com.tianrui.api.req.groupMsg.PushGroupMessageReq;
import com.tianrui.common.vo.Result;

public interface IMessageGroupService {
	/** 推送消息
	 * @throws Exception */
	public Result pushGroupMsg(PushGroupMessageReq req) throws Exception;
	/** 修改消息
	 * @throws Exception */
	public Result uptErrMsg(CustomRcordReq req)throws Exception;
}
