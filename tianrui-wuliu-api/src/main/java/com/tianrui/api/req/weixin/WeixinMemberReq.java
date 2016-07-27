package com.tianrui.api.req.weixin;

import com.tianrui.api.req.BaseReq;

/**
 * 
 * @类描述：微信登陆
 * @创建人：lsj
 * @创建时间：2016年7月25日下午1:57:24
 *
 * @修改人：lsj
 * @修改时间：2016年7月25日下午1:57:24
 * @修改备注：
 *
 */
public class WeixinMemberReq extends BaseReq {

	/** 登陆账号*/
	private String cellPhone;
	/** 登陆密码*/
	private String passWord;
	/** 微信唯一标识*/
	private String openid;

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
}
