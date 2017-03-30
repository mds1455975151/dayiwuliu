package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.weixin.WeixinUserReq;
import com.tianrui.common.vo.Result;

public interface IWXUserService {

	/** 正常登录*/
	public Result login(WeixinUserReq req);
	/** 微信登录*/
	public Result wxLogin(WeixinUserReq req);
}
