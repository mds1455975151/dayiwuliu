package com.tianrui.api.message.intf;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.tianrui.api.req.money.MessageRollingReq;
import com.tianrui.api.resp.money.MessageRollingResp;
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
	/**
	 * 消息浏览次数+1
	 * @param id
	 * @return
	 */
	Result updateConsultNumber(long id);
	/**
	 *  获取滚动消息列表
	 * @param number
	 * @return
	 */
	List<MessageRollingResp> findRollingMessage(int number );
	/**
	 * 获取APP货源基地统计信息
	 * @return
	 */
	Result getAppPlatformMessage();
}
