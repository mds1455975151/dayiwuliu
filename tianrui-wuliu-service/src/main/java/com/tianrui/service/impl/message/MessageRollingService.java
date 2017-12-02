package com.tianrui.service.impl.message;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.message.intf.IMessageRollingService;
import com.tianrui.api.req.money.AppMessageReq;
import com.tianrui.api.req.money.MessageRollingReq;
import com.tianrui.api.resp.money.MessageRollingResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MessageRolling;
import com.tianrui.service.mapper.MessageRollingMapper;
@Service
public class MessageRollingService implements IMessageRollingService {

	Logger logger=LoggerFactory.getLogger(MessageRollingService.class);
	@Autowired 
	private MessageRollingMapper messageRollingMapper;
	@Override
	public Result save(MessageRollingReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		MessageRolling mr = new MessageRolling();
		PropertyUtils.copyProperties(mr,req);
		messageRollingMapper.insertSelective(mr);
		return rs;
	}
	@Override
	public Result updateConsultNumber(long id) {
		Result rs = Result.getSuccessResult();
		MessageRolling record = messageRollingMapper.selectByPrimaryKey(id);
		if(null != record){
			record.setConsultNumber(record.getConsultNumber() +1 );
			messageRollingMapper.updateByPrimaryKeySelective(record);
		}
		return rs;
	}
	@Override
	public List<MessageRollingResp> findRollingMessage(int number) {
		AppMessageReq req = new AppMessageReq();
		req.setPageNo(0);;
		req.setPageSize(number);
		return messageRollingMapper.selectByCondition(req);
	}

}
