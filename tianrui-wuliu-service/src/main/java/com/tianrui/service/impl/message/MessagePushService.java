package com.tianrui.service.impl.message;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.req.money.MessagePushReq;
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

}
