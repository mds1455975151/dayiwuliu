package com.tianrui.api.message.intf;

import com.tianrui.api.req.groupMsg.PushGroupMessageReq;
import com.tianrui.common.vo.Result;

public interface IMessageGroupService {
	/** 推送消息*/
	public Result pushGroupMsg(PushGroupMessageReq req);
	
}
