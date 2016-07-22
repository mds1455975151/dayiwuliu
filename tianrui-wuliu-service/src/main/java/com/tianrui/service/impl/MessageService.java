package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberOwnerService;
import com.tianrui.api.intf.IMemberPushService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.intf.IOwnerDriverService;
import com.tianrui.api.req.front.message.MessageQueryReq;
import com.tianrui.api.req.front.message.MessageReplayReq;
import com.tianrui.api.req.front.message.MessageReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.api.req.front.vehicle.OwnerDriverReq;
import com.tianrui.api.resp.front.message.MessageResp;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.utils.MessageUtils;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.Message;
import com.tianrui.service.mapper.MessageMapper;
@Service
public class MessageService implements IMessageService {
	@Autowired
	private MessageMapper messagemapper;
	
	@Autowired
	private IOwnerDriverService ownerDriverService;
	@Autowired
	private IMemberOwnerService memberOwnerService;
	@Autowired
	IMemberPushService pushService;
	
	
	
	@Override
	public Result sendMessageInside(SendMsgReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		Message message = new Message();
		message.setId(UUIDUtil.getId());
		message.setStatus("1");
		message.setIsreply("0");
		message.setSendtime(System.currentTimeMillis());
		message.setCode(String.valueOf(req.getCodeEnum().getCode()));
		message.setKeyid(req.getKeyid());
		message.setSendid(req.getSendid());
		message.setSendname(req.getSendname());
		message.setRecid(req.getRecid());
		message.setRecname(req.getRecname());
		message.setUri(req.getURI());
		message.setType(req.getType());
		String content =MessageUtils.getMsgText(req.getParams(), req.getCodeEnum().getDesc());
		if( StringUtils.isNotBlank(content) ){
			message.setContent(content);
			//发送站内信
			messagemapper.insert(message);
			//发送推送消息
			pushService.sendPsuhMesage(req.getRecid(), content, message.getCode());
		}else{
			rs.setCode("1");
			rs.setError("参数列表不匹配");
		}
		return rs;
	}
	
	public List<MessageResp> copyProperties(List<Message> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<MessageResp> la = new ArrayList<MessageResp>();
		for(Message m : list){
			MessageResp re = new MessageResp();
			PropertyUtils.copyProperties(re, m);
			la.add(re);
		}
		return la;
	}

	@Override
	public Result updateMessage(MessageReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		Message message = new Message();
		PropertyUtils.copyProperties(message, req);
		int a = messagemapper.updateByPrimaryKeySelective(message);
		if(a!=1){
			rs.setCode("1");
			rs.setError("数据更新失败");
		}
		return rs;
	}

	@Override
	public List<MessageResp> findByEntity(MessageQueryReq req) throws Exception {
		Message query = new Message();
		query.setRecid(req.getCurruId());
		List<Message> list = messagemapper.findByEntity(query);
		return copyProperties(list);
	}

	@Override
	public PaginationVO<MessageResp> page(MessageQueryReq req) throws Exception {
		PaginationVO<MessageResp> page = null;
		if( req !=null && StringUtils.isNotBlank(req.getCurruId()) ){
			page =new PaginationVO<MessageResp>();
			Message query = new Message();
			query.setRecid(req.getCurruId());
			long total = messagemapper.countByEntity(query);
			if(total>0){
				query.setStart((req.getPageNo()-1)*req.getPageSize());
				query.setLimit(req.getPageSize());
				page.setList(copyProperties(messagemapper.findByEntity(query)));
			}
			page.setTotal(total);
			page.setPageNo(req.getPageNo());
		}
		
		return page;
	}

	@Override
	public Result replayMessage(MessageReplayReq req) throws Exception {
		
		Result rs =Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			Message db =messagemapper.selectByPrimaryKey(req.getId());
			if( db !=null  ){
				Message update = new Message();
				update.setId(db.getId());
				update.setStatus("2");
				//默认情况下   同意拒绝不修改
				if( StringUtils.isNotBlank(req.getIsreply()) ){
					update.setIsreply(req.getIsreply());
				}
				//判断是否修改相关的数据
				messagemapper.updateByPrimaryKeySelective(update);
				//绑定司机
				if( db.getCode().equals("161")  && StringUtils.isNotBlank(req.getIsreply())){
					bindDriver(db,req.getIsreply());
				//绑定车主	
				}else if( db.getCode().equals("162") && StringUtils.isNotBlank(req.getIsreply())){
					bindVender(db,req.getIsreply());
				}
			}
		}
		return rs;
	}

	@Override
	public long queryUnreadTotal(MessageQueryReq req) throws Exception {
		long total =0L;
		if( req !=null && StringUtils.isNotBlank(req.getCurruId()) ){
			Message query = new Message();
			query.setRecid(req.getCurruId());
			query.setStatus("1");
			total =messagemapper.countByEntity(query);
		}
		
		return total;
	}
	
	private void bindDriver(Message dbMessage,String isReplay) throws Exception{
		OwnerDriverReq driverReq = new OwnerDriverReq();
		driverReq.setId(dbMessage.getKeyid());
		driverReq.setStatus(isReplay);
		ownerDriverService.updateByPrimaryKeySelective(driverReq);
		//发送消息
		SendMsgReq req =new SendMsgReq();
		req.setKeyid(dbMessage.getKeyid());
		//发送人
		req.setSendid(dbMessage.getRecid());
		req.setSendname(dbMessage.getRecname());
		//接受人
		req.setRecid(dbMessage.getSendid());
		req.setRecname(dbMessage.getSendname());
		req.setParams(Arrays.asList(new String[]{dbMessage.getRecname()}));
		if( "1".equals(isReplay) ){
			req.setCodeEnum(MessageCodeEnum.MSG_2VENDER_APPLY);
		}else{
			req.setCodeEnum(MessageCodeEnum.MSG_2VENDER_REFUSE);
		}
		//消息类别  系统 还是会员
		req.setType("2");
		sendMessageInside(req);
	}
	
	private void bindVender(Message dbMessage,String isReplay) throws Exception{
		MemberOwnerReq ownerReq = new MemberOwnerReq();
		ownerReq.setId(dbMessage.getKeyid());
		ownerReq.setStatus(isReplay);
		memberOwnerService.updateByPrimaryKeySelective(ownerReq);
		//发送消息3a08357b477e4ef296adf20e348f7ab9
		SendMsgReq req =new SendMsgReq();
		req.setKeyid(dbMessage.getKeyid());
		//发送人
		req.setSendid(dbMessage.getRecid());
		req.setSendname(dbMessage.getRecname());
		//接受人
		req.setRecid(dbMessage.getSendid());
		req.setRecname(dbMessage.getSendname());
		req.setParams(Arrays.asList(new String[]{dbMessage.getRecname()}));
		if( "1".equals(isReplay) ){
			req.setCodeEnum(MessageCodeEnum.MSG_2OWNER_APPLY);
		}else{
			req.setCodeEnum(MessageCodeEnum.MSG_2OWNWE_REFUSE);
		}
		//消息类别  系统 还是会员
		req.setType("2");
		sendMessageInside(req);
	}
	
}
