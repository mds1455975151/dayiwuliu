package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.common.SmsDetails;

/**
 * 
 * @类描述：发送短信业务接口
 * @创建人：tank
 * @创建时间：2016年1月18日下午4:48:03
 *
 * @修改人：tank
 * @修改时间：2016年1月18日下午4:48:03
 * @修改备注：
 *
 */
public interface ISendMobileMessage{
	/**
	 * 
	 * @描述:单一发送短信
	 * @param content
	 * @param mobile
	 * @return
	 * @throws Exception
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年1月18日下午4:48:26
	 */
	public String sendMobileMessage(SmsDetails sms) throws Exception;
}
