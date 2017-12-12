package com.tianrui.quartz.message;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.IMemberPushService;
import com.tianrui.api.intf.ISendMobileMessage;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.req.common.SmsDetails;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.api.resp.money.MessagePushResp;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.utils.DateUtil;
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
	IMemberPushService pushService;
	@Autowired
	ISendMobileMessage sendMobileMessage;
	
//	@Scheduled(cron="0 0/5 *  * * ? ")
    public void getMessageAndPush() {  
    	Long st = new Date().getTime();
    	logger.info("定时器[PushMessageTrigger]启动.时间是 :" + DateUtil.getDateString());  
    	int count =0;
        try {
        	//获取待推送的消息
        	List<MessagePushResp> ls = messagePushService.findPendingMessage();
        	if(ls != null && ls.size() > 0){
        		//获取推送目标车主集合
        		List<MemberResp> memberList = systemMemberService.findAllVender();
        		for(MessagePushResp message :ls){//循环待推送消息
        			try {
        				Long beginTime = new Date().getTime();
        				int sendCount =0;
        				if(null != memberList && memberList.size() > 0){
        					for(MemberResp member : memberList){//循环车主列表
            					if(message.getChannel() == 1){//推送APP消息
            						pushService.sendPsuhMesage(member.getId(), message.getMessageContent(), MessageCodeEnum.MSG_ALL_OWNER.getCode()+"");
            						count++;
            						sendCount++;
            					}else if (message.getChannel() == 2) {//发送短信通知
            						SmsDetails sms = new SmsDetails();
            						sms.setTelephoneReceiver(member.getCellPhone());
            						sms.setSmsContent(message.getMessageContent());
            						sendMobileMessage.sendMobileMessage(sms);
            						count++;
            						sendCount++;
								}else if (message.getChannel() == 3) {//推送APP消息和发送短信通知
									SmsDetails sms = new SmsDetails();
            						sms.setTelephoneReceiver(member.getCellPhone());
            						sms.setSmsContent(message.getMessageContent());
            						sendMobileMessage.sendMobileMessage(sms);
            						pushService.sendPsuhMesage(member.getId(), message.getMessageContent(), MessageCodeEnum.MSG_ALL_OWNER.getCode()+"");
									count+=2;
            						sendCount+=2;
								}
            				}
        					messagePushService.updatePushState(message.getId(),sendCount,beginTime);
        				}
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
	
}
