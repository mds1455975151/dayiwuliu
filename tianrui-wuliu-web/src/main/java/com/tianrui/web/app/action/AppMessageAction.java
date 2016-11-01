package com.tianrui.web.app.action;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.req.front.message.MessageQueryReq;
import com.tianrui.api.req.front.message.MessageReplayReq;
import com.tianrui.api.resp.front.message.MessageResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
  * @ClassName: AppMessageAction 
  * @Description:站内信
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年7月4日 10:04:40
  *
 */
@Controller
@RequestMapping("/app/msg")
public class AppMessageAction {
	
	public Logger logger = LoggerFactory.getLogger(AppMessageAction.class);
	@Autowired
	IMessageService messageService;

	//消息列表
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ApiParamRawType(MessageQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult page(AppParam<MessageQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
	
		//拼装查询条件
		MessageQueryReq query =appParam.getBody();	
		query.setCurruId(uId);
		if(StringUtils.equals("2", appParam.getHead().getAppIdCard())){
			query.setRectype(2);
		}else{
			query.setRectype(1);
		}
		PaginationVO<MessageResp> page =messageService.page(query);
		
		appResult.setCode("000000");
		appResult.setTotal(page.getTotalInt());
		appResult.setReturnData(page.getList());
		return appResult;
	}
	
	//已读列表
	@RequestMapping(value="/read",method=RequestMethod.POST)
	@ApiParamRawType(MessageReplayReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult read(AppParam<MessageReplayReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		MessageReplayReq query =appParam.getBody();
		query.setCurruId(uId);
		query.setIsreply(appParam.getBody().getIsreply());
		Result rs=messageService.replayMessage(query);
		
		AppResult appResult =AppResult.valueOf(rs);
		return appResult;
	}
	
	
	//未读条数
	@RequestMapping(value="/unReadCount",method=RequestMethod.POST)
	@ApiParamRawType(MessageQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult unReadCount(AppParam<MessageQueryReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		MessageQueryReq query =appParam.getBody();
		query.setCurruId(uId);
		long count  =messageService.queryUnreadTotal(query);
		Result rs =Result.getSuccessResult();
		rs.setData(count);
		AppResult appResult =AppResult.valueOf(rs);
		return appResult;
	}
	
}
