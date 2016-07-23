package com.tianrui.web.action.weixin.util.entity;


/**
 * 
 * @类描述：
 * @创建人：lsj
 * @创建时间：2016年7月19日上午8:46:56
 *
 * @修改人：lsj
 * @修改时间：2016年7月19日上午8:46:56
 * @修改备注：
 *
 */
public class Tokens {
	// 接口访问凭证
	private static String accessToken;
	//微信支付凭证
	private static String jsapi_ticket;
	
	// 凭证有效期，单位：秒
	private static int expiresIn;


	
	public static String getJsapi_ticket() {
		return jsapi_ticket;
	}

	public static void setJsapi_ticket(String jsapi_ticket) {
		Tokens.jsapi_ticket = jsapi_ticket;
	}

	public static int getExpiresIn() {
		return expiresIn;
	}

	public static void setExpiresIn(int expiresIn) {
		Tokens.expiresIn = expiresIn;
	}

	public static String getAccessToken() {
		return accessToken;
	}

	public static void setAccessToken(String accessToken) {
		Tokens.accessToken = accessToken;
	}

}