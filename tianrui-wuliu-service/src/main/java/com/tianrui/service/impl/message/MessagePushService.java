package com.tianrui.service.impl.message;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.message.intf.IMessageRollingService;
import com.tianrui.api.req.money.AppMessageReq;
import com.tianrui.api.req.money.MessagePushReq;
import com.tianrui.api.req.money.MessageRollingReq;
import com.tianrui.api.resp.front.message.MessageAppResp;
import com.tianrui.api.resp.money.MessagePushResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FilePositoin;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.mapper.FilePositoinMapper;
import com.tianrui.service.admin.mapper.FileRouteMapper;
import com.tianrui.service.bean.MessagePush;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.bean.PlanGoods;
import com.tianrui.service.mapper.MessagePushMapper;
@Service
public class MessagePushService implements IMessagePushService {

	Logger logger=LoggerFactory.getLogger(MessagePushService.class);
	@Autowired 
	private MessagePushMapper messagePushMapper;
	@Autowired 
	private IMessageRollingService  messageRollingService;
	@Autowired
	FileRouteMapper fileRouteMapper;
	@Autowired
	FilePositoinMapper filePositoinMapper;
	@Override
	public Result save(MessagePushReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		MessagePush mp = new MessagePush();
		String messageContent = "";
		if(req.getMessageType() == 1){
			messageContent = demandMessage(req, mp, messageContent);
		}else if (req.getMessageType() == 2) {
			messageContent = planMeaage(req, rs, messageContent);
		}
		req.setChannel((byte)1);
		req.setMessageContent(messageContent);
		PropertyUtils.copyProperties(mp,req);
		messagePushMapper.insertSelective(mp);
		return rs;
	}
	/**
	 * 货运计划消息处理
	 * @param req
	 * @param rs
	 * @param messageContent
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private String planMeaage(MessagePushReq req, Result rs, String messageContent)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String cyr;
		FileRoute route;
		String tyr;
		FilePositoin o;
		FilePositoin d;
		String qyd;
		String mdd;
		String hwmc;
		String shuliang;
		Plan goods = (Plan) req.getGoods();
		cyr=goods.getVehicleownername();
		 route = fileRouteMapper.selectByPrimaryKey(goods.getRouteid());
		tyr = route.getOrganizationname();
		 o = filePositoinMapper.selectByPrimaryKey(route.getOpositionid());
		 d =filePositoinMapper.selectByPrimaryKey(route.getDpositionid());
		 qyd=o.getOp() + o.getOc();
		 mdd=d.getOp() + d.getOc();
		 hwmc=goods.getCargoname();
		 shuliang=goods.getTotalplanned()+""+goods.getMeasure();
		if(req.getChannel() == 1 ){
			messageContent = MessageHelper.getPlanPushMesage(cyr, tyr, qyd, mdd, hwmc, shuliang);
		}else {
			rs.setCode("11");
			rs.setError("不支持的推送方式");
		}
		qyd = qyd + o.getAddr();
		mdd = mdd + d.getAddr();
		String time = new SimpleDateFormat("yyyy年M月d日").format(new Date(goods.getStarttime())) + "--"+new SimpleDateFormat("yyyy年M月d日").format(new Date(goods.getEndtime()));
		savePlanRolling(req, cyr, qyd, mdd, hwmc, shuliang,time);
		return messageContent;
	}
	/**
	 * 保存货运计划滚动消息
	 * @param req
	 * @param cyr
	 * @param qyd
	 * @param mdd
	 * @param hwmc
	 * @param shuliang
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private void savePlanRolling(MessagePushReq req, String cyr, String qyd, String mdd, String hwmc, String shuliang,String time)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		MessageRollingReq roll =new MessageRollingReq();
		roll.setMessageType(req.getMessageType());
		roll.setCreateTime(new Date().getTime());
		roll.setMessageContent(MessageHelper.getPlanRollingMesage(cyr, qyd, mdd, hwmc, shuliang));
		roll.setDesc1(qyd);
		roll.setDesc2(mdd);
		roll.setDesc3(time);
		messageRollingService.save(roll);
	}
	/**
	 * 货运需求消息处理
	 * @param req
	 * @param mp
	 * @param messageContent
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private String demandMessage(MessagePushReq req, MessagePush mp, String messageContent)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		FileRoute route;
		String tyr;
		FilePositoin o;
		FilePositoin d;
		String qyd;
		String mdd;
		String hwmc;
		String shuliang;
		PlanGoods goods = (PlanGoods) req.getGoods();
		 route = fileRouteMapper.selectByPrimaryKey(goods.getRouteid());
		tyr = route.getOrganizationname();
		 o = filePositoinMapper.selectByPrimaryKey(route.getOpositionid());
		 d =filePositoinMapper.selectByPrimaryKey(route.getDpositionid());
		 qyd=o.getOp() + o.getOc();
		 mdd=d.getOp() + d.getOc();
		 hwmc=goods.getCargoname();
		 shuliang=goods.getTotalplanned()+""+goods.getMeasure();
		if(req.getChannel() == 1 ){
			messageContent = MessageHelper.getDemandPushMesage(tyr, qyd, mdd, hwmc, shuliang);
		}else if (req.getChannel() == 2) {
			messageContent = MessageHelper.getDemandSMSMesage(tyr, qyd, mdd, hwmc, shuliang);
		}else if (req.getChannel() == 3) {
			messageContent = MessageHelper.getDemandPushMesage(tyr, qyd, mdd, hwmc, shuliang);
			saveSMSMessage(req, mp, tyr, qyd, mdd, hwmc, shuliang);
		}
		qyd = qyd + o.getAddr();
		mdd = mdd + d.getAddr();
		String time = new SimpleDateFormat("yyyy年M月d日").format(new Date(goods.getStarttime())) + "--"+new SimpleDateFormat("yyyy年M月d日").format(new Date(goods.getEndtime()));
		saveRollingMessage(req, tyr, qyd, mdd, hwmc, shuliang,time);
		return messageContent;
	}
	/**
	 * 保存货运需求滚动消息
	 * @param req
	 * @param tyr
	 * @param qyd
	 * @param mdd
	 * @param hwmc
	 * @param shuliang
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private void saveRollingMessage(MessagePushReq req, String tyr, String qyd, String mdd, String hwmc,
			String shuliang,String time) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		MessageRollingReq roll =new MessageRollingReq();
		roll.setMessageType(req.getMessageType());
		roll.setCreateTime(new Date().getTime());
		roll.setMessageContent(MessageHelper.getDemandRollingMesage(tyr, qyd, mdd, hwmc, shuliang));
		roll.setDesc1(qyd);
		roll.setDesc2(mdd);
		roll.setDesc3(time);
		messageRollingService.save(roll);
	}
	/**
	 * 保存短信发送消息
	 * @param req
	 * @param mp
	 * @param tyr
	 * @param qyd
	 * @param mdd
	 * @param hwmc
	 * @param shuliang
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private void saveSMSMessage(MessagePushReq req, MessagePush mp, String tyr, String qyd, String mdd, String hwmc,
			String shuliang) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String messageContentSMS = MessageHelper.getDemandSMSMesage(tyr, qyd, mdd, hwmc, shuliang);
		req.setMessageContent(messageContentSMS);
		req.setChannel((byte)2);
		PropertyUtils.copyProperties(mp,req);
		messagePushMapper.insertSelective(mp);
	}

	@Override
	public Result updateConsultNumber(long id) {
		Result rs = Result.getSuccessResult();
		MessagePush mp = null;
		if(id > 0){
			mp = messagePushMapper.selectByPrimaryKey(id);
		}else {
			mp = messagePushMapper.selectLastMessagePush();
		}
		if(null != mp ){
			mp.setConsultNumber(mp.getConsultNumber() + 1 );
			messagePushMapper.updateByPrimaryKeySelective(mp);
		}
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
		if(null != mp ){
			mp.setCalledNumber(mp.getCalledNumber()+ 1);
			messagePushMapper.updateByPrimaryKeySelective(mp);
		}
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
		total = total > 200 ?200:total;
		if(null !=req.getPageNo()){
			req.setPageNo(req.getPageNo() - 1);
		}
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
		if(null !=req.getPageNo()){
			vo.setPageNo(req.getPageNo() + 1);
		}
		if(null != req.getPageSize()){
			vo.setPageSize(req.getPageSize());
		}
		vo.setTotal(total);
		return vo;
	}

}
