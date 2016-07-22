package com.tianrui.api.req.common;

import com.tianrui.api.req.BaseReq;

public class SmsDetails  extends BaseReq{

	
	/**
	 * @字段：serialVersionUID
	 * @功能描述：
	 * @创建人：tank
	 * @创建时间：2016年5月10日下午3:02:27
	 */
	
	private static final long serialVersionUID = -7099176114694519872L;
	
	/**
	 * 接收人电话号码，not null
	 */
	private String  telephoneReceiver;
	/**
	 * 短信内容  , not null
	 */
	private String  smsContent;
	public String getTelephoneReceiver() {
		return telephoneReceiver;
	}
	public void setTelephoneReceiver(String telephoneReceiver) {
		this.telephoneReceiver = telephoneReceiver;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	
}
