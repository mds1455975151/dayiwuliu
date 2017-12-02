package com.tianrui.api.message.intf;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.tianrui.api.req.money.AppMessageReq;
import com.tianrui.api.req.money.MessagePushReq;
import com.tianrui.api.resp.front.message.MessageAppResp;
import com.tianrui.api.resp.money.MessagePushResp;
import com.tianrui.common.vo.PaginationVO;
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
	/**
	 * 消息浏览次数+1
	 * @param id   浮动窗口点击  id  传 0
	 * @return
	 */
	Result updateConsultNumber(long id);
	/**
	 * APP拨打电话次数+1
	 * @param id   无消息  id  传 0
	 * @return
	 */
	Result updateCalledNumber(long id);
	/**
	 * 获取待推送消息列表
	 * @return
	 */
	List<MessagePushResp> findPendingMessage();
	/**
	 * 修改推送状态
	 * @param id
	 * @param sendCount
	 * @param beginTime
	 */
	void updatePushState(Long id, int sendCount, Long beginTime);
	/**
	 * 获取群推消息列表
	 * @param req
	 * @return
	 */
	PaginationVO<MessageAppResp> findAppMessage(AppMessageReq req);
}
