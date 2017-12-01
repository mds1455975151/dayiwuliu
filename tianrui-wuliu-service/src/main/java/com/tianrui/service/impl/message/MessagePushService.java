package com.tianrui.service.impl.message;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.req.money.MessagePushReq;
import com.tianrui.api.resp.money.MessagePushResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MessagePush;
import com.tianrui.service.mapper.MessagePushMapper;
@Service
public class MessagePushService implements IMessagePushService {

	Logger logger=LoggerFactory.getLogger(MessagePushService.class);
	@Autowired 
	private MessagePushMapper messagePushMapper;
	
	@Override
	public Result save(MessagePushReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		MessagePush mp = new MessagePush();
		PropertyUtils.copyProperties(mp,req);
		messagePushMapper.insert(mp);
		return rs;
	}

	@Override
	public Result updateConsultNumber(Long id) {
		Result rs = Result.getSuccessResult();
		MessagePush mp = messagePushMapper.selectByPrimaryKey(id);
		mp.setConsultNumber(mp.getCalledNumber() + 1 );
		messagePushMapper.updateByPrimaryKeySelective(mp);
		return rs;
	}

	@Override
	public Result updateCalledNumber(Long id) {
		Result rs = Result.getSuccessResult();
		MessagePush mp = messagePushMapper.selectByPrimaryKey(id);
		mp.setCalledNumber(mp.getCalledNumber()+ 1);
		messagePushMapper.updateByPrimaryKeySelective(mp);
		return rs;
	}

	@Override
	public List<MessagePushResp> findPendingMessage() {
		List<MessagePush> ls = messagePushMapper.findPendingMessage();
		if(null != ls && ls.size() > 0){
			List<MessagePushResp> respList = new ArrayList<MessagePushResp>();
			for(MessagePush mp : ls){
				MessagePushResp mpr = new MessagePushResp();
				mpr.setChannel(mp.getChannel());
				mpr.setId(mp.getId());
				mpr.setMessageContent(mp.getMessageContent());
				respList.add(mpr);
			}
			return respList;
		}
		return null;
	}

}
