package com.tianrui.web.app.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IBannerManageService;
import com.tianrui.api.req.admin.BannerManagerReq;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 手机端banner管理接口
 * @author lenovo
 *
 */
@Controller
@RequestMapping("/app/banner")
public class AppBannerAction {
	
	@Autowired
	private IBannerManageService bannerManageService;

	/**
	 * 展示banner图片
	 * @author xcy
	 * @return
	 */
	@RequestMapping("queryBanner")
	@ApiParamRawType(BannerManagerReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult queryAppBanner(AppParam<BannerManagerReq> appParam){
		BannerManagerReq req = appParam.getBody();
		Result rs = bannerManageService.queryAppBanner(req);
		AppResult appResult = AppResult.valueOf(rs);
		return appResult;
	}
}
