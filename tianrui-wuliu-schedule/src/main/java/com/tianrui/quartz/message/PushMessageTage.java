package com.tianrui.quartz.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberPush;
import com.tianrui.service.util.BaiduPushUtils;
/**
 * 定时推送消息
 * @author Administrator
 *
 */
@Component 
public class PushMessageTage {
	
	private Logger logger = LoggerFactory.getLogger(PushMessageTage.class);
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
	
	static String push_tage = "vender_push_tage";
	
	/**
	 * 创建推送分组 并塞入用户
	 * @throws Exception 
	 */
	public void savePushTage() throws Exception{
		//删除原有分组
		BaiduPushUtils.deleteTage(push_tage, 3);
		BaiduPushUtils.deleteTage(push_tage, 4);
		//创建分组
		BaiduPushUtils.createTage(push_tage, 3);
		BaiduPushUtils.createTage(push_tage, 4);
		
		List<String> android = new ArrayList<String>();
		List<String> ios = new ArrayList<String>();

		//获取推送目标车主集合
		List<MemberResp> memberList = systemMemberService.findAllVender();
		for(MemberResp member : memberList){
			Result rs = pushService.selectChannelId(member.getId());
			if(rs.getCode().equals("000000")){
				MemberPush push = (MemberPush) rs.getData();
				if(push.getApptype().equals("1")){//adnroid
					android.add(push.getPushid());
					if(android.size()>=9){
						//最多保存9 推送并清空
						String[] ids = android.toArray(new String[android.size()]);
						BaiduPushUtils.addUserToTage(push_tage, 3, ids);
						android.clear();
					}
				}else if(push.getApptype().equals("2")){//ios
					ios.add(push.getPushid());
					if(ios.size()>=9){
						//最多保存9 推送并清空
						String[] ids = ios.toArray(new String[ios.size()]);
						BaiduPushUtils.addUserToTage(push_tage, 4, ids);
						ios.clear();
					}
				}
			}
		}
	}
	
//	@Scheduled(cron="0 0/5 *  * * ? ")
    public void getMessageAndPush() {  
    	Long st = new Date().getTime();
    	logger.info("定时器[PushMessageTrigger]启动.时间是 :" + DateUtil.getDateString());  
    	int count =0;
        try {
        	//获取待推送的消息
        	List<MessagePushResp> ls = messagePushService.findPendingMessage();
        	if(ls != null && ls.size() > 0){
        		for(MessagePushResp message :ls){//循环待推送消息
        			try {
        				Long beginTime = new Date().getTime();
        				int sendCount =0;
        				BaiduPushUtils.pushMsgToTage(push_tage, 3, message.getMessageContent(), MessageCodeEnum.MSG_ALL_OWNER.getCode()+"");
        				BaiduPushUtils.pushMsgToTage(push_tage, 4, message.getMessageContent(), MessageCodeEnum.MSG_ALL_OWNER.getCode()+"");
        				messagePushService.updatePushState(message.getId(),sendCount,beginTime);
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
