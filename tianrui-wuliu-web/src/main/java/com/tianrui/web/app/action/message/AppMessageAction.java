package com.tianrui.web.app.action.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.req.app.message.PullMessageReq;
import com.tianrui.api.req.money.AppMessageReq;
import com.tianrui.api.resp.front.message.MessageAppResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/pushMessage")
public class AppMessageAction {

	
	@Autowired
	IMessagePushService messagePushService;
	/**
	 * 拨打客服电话次数+1
	 * */
	@RequestMapping(value="/updateCalledNumber",method=RequestMethod.POST)
	@ApiParamRawType(PullMessageReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult updateCalledNumber(AppParam<PullMessageReq> appParam) throws Exception{
		PullMessageReq req = appParam.getBody();
		Result rs = messagePushService.updateCalledNumber(Long.parseLong(req.getId()));
		return AppResult.valueOf(rs);
	}
	/**
	 * 拨打客服电话次数+1
	 * */
	@RequestMapping(value="/updateConsultNumber",method=RequestMethod.POST)
	@ApiParamRawType(PullMessageReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult updateConsultNumber(AppParam<PullMessageReq> appParam) throws Exception{
		PullMessageReq req = appParam.getBody();
		Result rs = messagePushService.updateConsultNumber(Long.parseLong(req.getId()));
		return AppResult.valueOf(rs);
	}
	/**
	 * 获取货运速递消息列表
	 * */
	@RequestMapping(value="/findAppMessage",method=RequestMethod.POST)
	@ApiParamRawType(AppMessageReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult findAppMessage(AppParam<AppMessageReq> appParam) throws Exception{
		AppMessageReq req = appParam.getBody();
		PaginationVO<MessageAppResp> pv = messagePushService.findAppMessage(req);
		Result rs = Result.getSuccessResult();
		rs.setData(pv);
		return AppResult.valueOf(rs);
	}
}
