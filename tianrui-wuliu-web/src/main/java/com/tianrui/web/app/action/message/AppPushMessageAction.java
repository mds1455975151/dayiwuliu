package com.tianrui.web.app.action.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.ILEDCountService;
import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.message.intf.IMessageRollingService;
import com.tianrui.api.req.LED.LEDCountReq;
import com.tianrui.api.req.app.message.PullMessageReq;
import com.tianrui.api.req.money.AppMessageReq;
import com.tianrui.api.resp.LED.LEDCountResp;
import com.tianrui.api.resp.front.message.MessageAppResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.LEDCountData;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/pushMessage")
public class AppPushMessageAction {

	
	@Autowired
	IMessagePushService messagePushService;
	@Autowired
	IMessageRollingService messageRollingService;
	@Autowired
	ILEDCountService lEDCountService;

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
	 * 浏览次数+1
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
	
	/**
	 * 平台动态数据统计
	 * */
	@RequestMapping(value="/platformMessage",method=RequestMethod.POST)
	@ApiParamRawType(PullMessageReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult platformMessage(AppParam<PullMessageReq> appParam) throws Exception{
		Result rs = messageRollingService.getAppPlatformMessage();
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 运量统计
	 * */
	@RequestMapping(value="/billCountMessage",method=RequestMethod.POST)
	@ApiParamRawType(LEDCountReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult billCountMessage(AppParam<LEDCountReq> appParam) throws Exception{
		AppResult apprs = new AppResult();
		apprs.setCode("000000");
		Result rs = lEDCountService.selectConfig();
		if(rs.getCode().equals("000000")){
			rs = lEDCountService.selectByKey("0000000_data_upt");
			LEDCountData data = (LEDCountData) rs.getData();
			LEDCountReq req = appParam.getBody();
			req.setDataType(data.getStimestr());
			req.setLedType("1");//运量
			req.setPageNO(0);
			req.setPageSize(2);
			PaginationVO<LEDCountResp> page = lEDCountService.findCount(req);
			apprs.setReturnData(page.getList());
		}else{
			apprs.setCode(rs.getCode());
			apprs.setMessage(rs.getError());
		}
		return apprs;
	}
	
	/**
	 * 运费统计
	 * */
	@RequestMapping(value="/payCountMessage",method=RequestMethod.POST)
	@ApiParamRawType(LEDCountReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult payCountMessage(AppParam<LEDCountReq> appParam) throws Exception{
		AppResult apprs = new AppResult();
		apprs.setCode("000000");
		Result rs = Result.getSuccessResult();
		rs = lEDCountService.selectConfig();
		if(rs.getCode().equals("000000")){
			rs = lEDCountService.selectByKey("0000000_data_upt");
			LEDCountData data = (LEDCountData) rs.getData();
			LEDCountReq req = appParam.getBody();
			req.setDataType(data.getStimestr());
			req.setLedType("6");//运费
			req.setPageNO(0);
			req.setPageSize(2);
			PaginationVO<LEDCountResp> page = lEDCountService.findCount(req);
			apprs.setReturnData(page.getList());
		}else{
			apprs.setCode(rs.getCode());
			apprs.setMessage(rs.getError());
		}
		return apprs;
	}
}
