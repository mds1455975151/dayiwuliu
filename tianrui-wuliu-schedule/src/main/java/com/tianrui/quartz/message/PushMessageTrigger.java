package com.tianrui.quartz.message;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.api.resp.money.MessagePushResp;
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
}
