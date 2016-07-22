package com.tianrui.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ISendMobileMessage;
import com.tianrui.api.req.common.SmsDetails;
import com.tianrui.service.util.ConcatUrl;
import com.tianrui.service.util.GetApplicationPropertery;

/**
 * 
 * @类描述：美联软通 短信平台 发送短信业务实现
 * @创建人：tank
 * @创建时间：2016年1月18日下午5:09:13
 *
 * @修改人：tank
 * @修改时间：2016年1月18日下午5:09:13
 * @修改备注：
 *
 */
@Service
public class SendMobileMessage implements ISendMobileMessage{

	public String sendMobileMessage(SmsDetails sms) throws Exception{
		Map<String,String> map=new HashMap<String,String>();
		String sendPermit=GetApplicationPropertery.getValueByKey("sendPermit");
		if(sendPermit.equals("1")){
			/**
			 * 返回发送结果
			 */
			String response="";
			try {
				String smsServer = GetApplicationPropertery.getValueByKey("sms.serial.smsServer");
				String smsUser = GetApplicationPropertery.getValueByKey("sms.serial.smsUser");
				String smsPassword  = GetApplicationPropertery.getValueByKey("sms.serial.smsPassword");
				String smsApikey = GetApplicationPropertery.getValueByKey("sms.serial.smsApikey");
				map.put("apikey", smsApikey);
				map.put("username", smsUser);
				map.put("password", smsPassword);
				map.put("mobile", sms.getTelephoneReceiver());
				map.put("content", URLEncoder.encode(sms.getSmsContent(),"GBK"));
				
				String concatUrl=ConcatUrl.concatUrl(smsServer, map);
				URL url = new URL(concatUrl);
				// 打开url连接
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				// 设置url请求方式 ‘get’ 或者 ‘post’
				connection.setRequestMethod("POST");
				// 发送
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				response = in.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//success:14528300905918
			return response;
		}else{
			return "test";
		}
	}
	
}
