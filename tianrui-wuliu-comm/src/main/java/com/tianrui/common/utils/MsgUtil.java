package com.tianrui.common.utils;


/**
 * 
 * @类描述：消息内容工具类
 * @创建人：tank
 * @创建时间：2016年4月24日下午12:26:49
 *
 * @修改人：tank
 * @修改时间：2016年4月24日下午12:26:49
 * @修改备注：
 *
 */
public class MsgUtil {
	
	
	public static String getUserLogin(int userCode){
		String msg = "尊敬的用户，欢迎您大易物流平台后台管理系统，您的验证码是：" + userCode + "，请不要把验证码泄露给其他人，请在页面中输入以完成验证，如非本人操作，可不用理会！";
		return msg;
	}
	public static String getSendmsg(int userCode, String type) {
		
		StringBuffer msg = new StringBuffer(256);
		if("1".equals(type)){
			msg.append("尊敬的大易物流平台用户，您正在重置你的平台密码，您的验证码是：" + userCode + "，请不要把验证码泄露给其他人，请在页面中输入以完成验证，如非本人操作，可不用理会！");
		}else if ("0".equals(type)) {
			msg.append("尊敬的用户，欢迎您注册大易物流平台，您的认证码是：" + userCode + "，请不要把认证码泄露给其他人，请在页面中输入以完成验证，如非本人操作，可不用理会！");
		}else if ("2".equals(type)) {
			msg.append("尊敬的用户，欢迎您登陆大易物流平台，您的登陆验证码是：" + userCode + "，请不要把认证码泄露给其他人，请在页面中输入以完成验证，如非本人操作，可不用理会！");
		}
		return msg.toString();
	}
}
