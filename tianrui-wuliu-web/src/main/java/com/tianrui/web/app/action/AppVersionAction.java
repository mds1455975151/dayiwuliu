package com.tianrui.web.app.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IAppVersionService;
import com.tianrui.api.req.front.version.AppVersionReq;
import com.tianrui.api.resp.front.version.AppVersionResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/version")
public class AppVersionAction {

	@Autowired
	IAppVersionService appVersionService;
	/**
	 * 
	 * @描述:获取最新版本信息
	 * @param appParam
	 * @return
	 * @throws Exception
	 * @返回类型 AppResult
	 * @创建人 lsj
	 * @创建时间 2016年8月22日上午11:12:44
	 */
	@RequestMapping(value="/getVersion",method=RequestMethod.POST)
	@ApiParamRawType(AppVersionReq.class)
	@ResponseBody
	public AppResult save(AppParam<AppVersionReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		AppVersionReq req = appParam.getBody();
		AppVersionResp resp = appVersionService.selectByid(req.getAppType());
		rs.setData(resp);
		return AppResult.valueOf(rs);
	}
}
