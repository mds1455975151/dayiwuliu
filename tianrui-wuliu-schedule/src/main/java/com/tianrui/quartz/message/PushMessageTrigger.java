package com.tianrui.quartz.message;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.money.MessagePushResp;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.service.impl.MessageService;
/**
 * 定时推送消息
 * @author Administrator
 *
 */
@Component 
public class PushMessageTrigger {
	
	private Logger logger = LoggerFactory.getLogger(PushMessageTrigger.class);
	/**
	 * 定时任务参数
	 */
	@Autowired
	protected IMessagePushService messagePushService;
	@Autowired
	private ISystemMemberService systemMemberService;
	@Autowired
	MessageService messageService;
	
	@Scheduled(cron="0 0/5 *  * * ? ")
    public void getPlanAndCompleted() {  
    	Long st = new Date().getTime();
    	logger.info("定时器[PushMessageTrigger]启动.时间是 :" + DateUtil.getDateString());  
    	int count =0;
        try {
        	//获取待推送的消息
        	List<MessagePushResp> ls = messagePushService.findPendingMessage();
        	if(ls != null && ls.size() > 0){
        		//获取推送目标车主集合
        		
        		for(MessagePushResp message :ls){
        			try {
        				
						count++;
					} catch (Exception e) {
						logger.error(e.getMessage(),e);
					}
            	}
        	}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
        logger.info("定时任务[PushMessageTrigger]完成.推送消息{}条,耗时：{}",new Object[]{count,(new Date().getTime()-st)});
    }
	
	//发送站内信
		private void sendMsgAllVender(List<String> params,String keyId,MemberVo sender,MessageCodeEnum codeEnum) throws Exception{
			SendMsgReq req = new SendMsgReq();
			List<MemberResp> list = systemMemberService.findAllVender();
			if(sender != null && codeEnum != null){
				for(MemberResp sp : list){
					req.setParams(params);
					req.setKeyid(keyId);
					//发送人
					req.setSendid(sender.getId());
					req.setSendname(sender.getRealName());
					//接受人
					req.setRecid(sp.getId());
					req.setRecname(sp.getRemarkname());
					req.setCodeEnum(codeEnum);
					req.setRecType(codeEnum.getType());
					//消息类别  系统 还是会员
					req.setType("2");
					//详情URI
					String uri ="/trwuliu/planvender/detail?id="+keyId;
					req.setURI(uri);
					try {
						messageService.sendMessageInside(req);
					} catch (Exception e) {
						logger.warn("站内信发送失败,发送信息:{}",JSON.toJSON(req),e);
					}
				}
			}
		}
}
