package com.tianrui.api.message.intf;

import java.lang.reflect.InvocationTargetException;

import com.tianrui.api.req.money.MessageRollingReq;
import com.tianrui.common.vo.Result;

public interface IMessageRollingService {

	/**
	 * 保存首页滚动消息
	 * @param req
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	Result save(MessageRollingReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
