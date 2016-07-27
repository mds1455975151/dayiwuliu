package com.tianrui.api.req.weixin;

import com.tianrui.api.req.BaseReq;

/**
 * 
 * @类描述：微信取消绑定
 * @创建人：lsj
 * @创建时间：2016年7月26日下午2:38:38
 *
 * @修改人：lsj
 * @修改时间：2016年7月26日下午2:38:38
 * @修改备注：
 *
 */
public class CancelMember extends BaseReq  {
	/** 用户id*/
	private String id;
	/** 微信唯一标识*/
	private String openid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
