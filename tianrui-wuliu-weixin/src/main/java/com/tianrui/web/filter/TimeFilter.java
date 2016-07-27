package com.tianrui.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianrui.web.action.weixin.util.entity.Tokens;
import com.tianrui.web.action.weixin.util.util.CommonUtil;
import com.tianrui.web.action.weixin.util.util.Count;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class TimeFilter {
	private static Logger log = LoggerFactory.getLogger(TimeFilter.class);

	// 凭证获取（GET）
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	public static Tokens TOKEN;
	public void runTime(){
		log.debug("定时任务启动");
		String requestUrl = TOKEN_URL.replace("APPID", Count.APPID).replace("APPSECRET", Count.APPSECRET);
		// 发起GET请求获取凭证
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {
				TOKEN = new Tokens();
				TOKEN.setAccessToken(jsonObject.getString("access_token"));
				TOKEN.setExpiresIn(jsonObject.getInt("expires_in"));
				log.debug("获取token成功：{}",jsonObject.getString("access_token"));
			} catch (JSONException e) {
				TOKEN = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
	}
}
