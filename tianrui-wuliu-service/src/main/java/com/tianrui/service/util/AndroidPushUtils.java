package com.tianrui.service.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;


public class AndroidPushUtils {
	static String apiKey = "B0pttqMTh7f1zOR5YpfR2aXl";
	static String secretKey = "xuhQKjLZg8wuBxzStYGFeGc08yeTG6VE";
	
	static Logger logger=LoggerFactory.getLogger(AndroidPushUtils.class);
	
	public static void  push(String pushId,String msg,String code){
		
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				logger.info(event.getMessage());
			}
		});

		try {
			JSONObject notification = new JSONObject();
			notification.put("title", "TEST");
			notification.put("code2", "code2");
//			notification.put("description","Hello Baidu Push");
//			notification.put("notification_builder_id", 0);
//			notification.put("notification_basic_style", 4);
//			notification.put("open_type", 1);
//			notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("code1", "code1"); //自定义内容，key-value
			notification.put("custom_content", jsonCustormCont);

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId(pushId)
					.addMsgExpires(new Integer(3600)) 	// 设置message的有效时间
					.addMessageType(1)					// 1：通知,0:透传消息.默认为0 注：IOS只有通知.
					.addMessage(msg)					//消息内容
					.addDeployStatus(1) 				// 1:测试环境  2生产
					.addDeviceType(3);					// 3:android, 4:ios
			// 5. http request
			PushMsgToSingleDeviceResponse response = pushClient
					.pushMsgToSingleDevice(request);
			// Http请求结果解析打印
			logger.info("msgId: " + response.getMsgId() + ",sendTime: "+ response.getSendTime());
		} catch (PushClientException e) {
			logger.error(e.getMessage(),e);
		} catch (PushServerException e) {
			logger.error(e.getMessage(),e);
		}
	}
}
