package com.tianrui.web.action.weixin.util.util;

import java.util.Date;

public class FormatXmlProcess {

	/**
	 * 消息推送
	 * @param to
	 * @param from
	 * @param content
	 * @return
	 */
	public String formatXmlAnswer(String to, String from, String url) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(to);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(from);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(date.getTime());
		sb.append("</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[VIEW]]></Event><EventKey><![CDATA[");
		//sb.append("www.baidu.com");
		sb.append(url);
		sb.append("]]></EventKey></xml>");
		
		return sb.toString();
	}
	
	/**
	 * 封装文字类的返回消息
	 * @param to
	 * @param from
	 * @param content
	 * @return
	 */
	public String formatXmlContent(String to, String from, String content,String url,String urltext,String content2) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(to);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(from);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(date.getTime());
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(content);
		sb.append("" +
				"<a href='");
		sb.append(url);
		sb.append("'>");
		sb.append(urltext);
		sb.append("</a>");
		sb.append(content2);
		
		sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");
		return sb.toString();
	}

	/**
	 * 全局返回
	 * @param to
	 * @param from
	 * @param content
	 * @param wxname
	 * @param text
	 * @param upt
	 * @return
	 */
	public String formatXmlAllContent(String to, String from,
			String content) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(to);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(from);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(date.getTime());
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(content);
		sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");
		return sb.toString();
	}
	
	/**
	 * 转多客服
	 * @param to
	 * @param from
	 * @param content
	 * @return
	 */
	public String formatXmlKeFu(String to, String from) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(to);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(from);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(date.getTime());
		sb.append("</CreateTime><MsgType><![CDATA[transfer_customer_service]]></MsgType></xml>");
		return sb.toString();
	}
	
	/**
	 * 微信红包
	 * @Description :
	 * @param 
	 * @return 
	 * ---------------
	 * @Author  : My
	 * @CreateData : 2016-1-25
	 */
	public String formatXmlHongbao(String appid, String wxName){
		StringBuffer str = new StringBuffer();
		str.append("<xml>");
 
		str.append("<sign><![CDATA[E1EE61A91C8E90F299DE6AE075D60A2D]]></sign>"); 
		
		str.append("<mch_billno><![CDATA[0010010404201411170000046545]]></mch_billno>"); 
		
		str.append("<mch_id><![CDATA[888]]></mch_id>"); 
		
		str.append("<wxappid><![CDATA["); 
		
		str.append(appid);
		
		str.append("]]></wxappid>");
		
		str.append("<send_name><![CDATA[send_name]]></send_name>"); 
		
		str.append("<re_openid><![CDATA["); 
		
		str.append(wxName);
		
		str.append("]]></re_openid>");
		
		str.append("<total_amount><![CDATA[200]]></total_amount>");
		
		str.append("<total_num><![CDATA[1]]></total_num>"); 
		
		str.append("<wishing><![CDATA[恭喜发财]]></wishing>"); 
		
		str.append("<client_ip><![CDATA[127.0.0.1]]></client_ip>"); 
		
		str.append("<act_name><![CDATA[新年红包]]></act_name>"); 
		
		str.append("<remark><![CDATA[新年红包]]></remark>"); 
		
		str.append("<nonce_str><![CDATA[50780e0cca98c8c8e814883e5caa672e]]></nonce_str>"); 
		
		str.append("</xml>");
		return str.toString();
	}
	
	
}
