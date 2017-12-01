package com.tianrui.api.message.intf;

import java.lang.reflect.InvocationTargetException;

import com.tianrui.api.req.money.MessagePushReq;
import com.tianrui.common.vo.Result;

public interface IMessagePushService {

	/**
	 * 保存待推送消息
	 * @param req
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	Result save(MessagePushReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
