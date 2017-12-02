package com.tianrui.service.impl.message;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.req.money.AppMessageReq;
import com.tianrui.api.req.money.MessagePushReq;
import com.tianrui.api.resp.front.message.MessageAppResp;
import com.tianrui.api.resp.money.MessagePushResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MessagePush;
import com.tianrui.service.bean.PlanGoods;
import com.tianrui.service.mapper.MessagePushMapper;
@Service
public class MessagePushService implements IMessagePushService {

	Logger logger=LoggerFactory.getLogger(MessagePushService.class);
	@Autowired 
	private MessagePushMapper messagePushMapper;
	
	@Override
	public Result save(MessagePushReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		PlanGoods goods = (PlanGoods) req.getGoods();
		MessagePush mp = new MessagePush();
		PropertyUtils.copyProperties(mp,req);
		messagePushMapper.insert(mp);
		return rs;
	}

	@Override
	public Result updateConsultNumber(Long id) {
		Result rs = Result.getSuccessResult();
		MessagePush mp = null;
		if(id > 0){
			mp = messagePushMapper.selectByPrimaryKey(id);
		}else {
			mp = messagePushMapper.selectLastMessagePush();
		}
		mp.setConsultNumber(mp.getCalledNumber() + 1 );
		messagePushMapper.updateByPrimaryKeySelective(mp);
		return rs;
	}

	@Override
	public Result updateCalledNumber(long id) {
		Result rs = Result.getSuccessResult();
		MessagePush mp = null;
		if(id > 0){
			mp = messagePushMapper.selectByPrimaryKey(id);
		}else {
			mp = messagePushMapper.selectLastMessagePush();
		}
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

	@Override
	public void updatePushState(Long id, int sendCount,Long beginTime) {
		MessagePush mp = messagePushMapper.selectByPrimaryKey(id);
		mp.setPushState((byte)2);
		mp.setConsultNumber(sendCount);
		mp.setBeginTime(beginTime);
		mp.setEndTime(new Date().getTime());
		messagePushMapper.updateByPrimaryKeySelective(mp);
	}

	@Override
	public PaginationVO<MessageAppResp> findAppMessage(AppMessageReq req) {
		PaginationVO<MessageAppResp> vo = new PaginationVO<MessageAppResp>();
		long total = messagePushMapper.selectCount(req) ;
		total = total > 200 ?total:200;
		List<MessagePush> ls = messagePushMapper.selectByCondition(req);
		List<MessageAppResp> list = new ArrayList<MessageAppResp>();
		for(MessagePush mp : ls){
			MessageAppResp mr = new MessageAppResp();
			mr.setId(mp.getId());
			mr.setMessageContent(mp.getMessageContent());
			if(mp.getMessageContent().length() > 18){
				mr.setMessageHeader(mp.getMessageContent().substring(0, 17)+"......");
			}else {
				mr.setMessageHeader(mp.getMessageContent());
			}
			mr.setMessageType(mp.getMessageType());
			list.add(mr);
		}
		vo.setList(list);
		vo.setPageNo(req.getPageNo());
		vo.setPageSize(req.getPageSize());
		vo.setTotal(total);
		return vo;
	}

}
