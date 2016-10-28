package com.tianrui.web.app.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.ICargoPlanTemplateService;
import com.tianrui.api.req.front.cargoplan.PlanTemplateReq;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 计划模板APP接口
 * @author zhanggaohao
 * @createtime 2016年10月27日 下午4:01:59
 * @classname AppPlanTemplateAction.java
 */
@Controller
@RequestMapping("/app/plantemplate")
public class AppPlanTemplateAction {
	
	public Logger logger = LoggerFactory.getLogger(AppPlanTemplateAction.class);
	
	@Autowired
	protected ICargoPlanTemplateService cargoPlanTemplateService;
	
	//获取计划模板列表
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ApiParamRawType(PlanTemplateReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult page(AppParam<PlanTemplateReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
	
		//拼装查询条件
		PlanTemplateReq query =appParam.getBody();
		query.setCurruId(uId);
		List<PlanResp> list = cargoPlanTemplateService.findPlanTemplat(query);
		
		appResult.setCode("000000");
		appResult.setReturnData(list);
		return appResult;
	}
	
	//计划模板详情
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ApiParamRawType(PlanTemplateReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult detail(AppParam<PlanTemplateReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		PlanTemplateReq query =appParam.getBody();
		query.setCurruId(uId);
		PlanResp resp = cargoPlanTemplateService.findOne(query);
		
		appResult.setCode("000000");
		appResult.setReturnData(resp);
		return appResult;
	}
	
	//删除计划模板
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ApiParamRawType(PlanTemplateReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult delete(AppParam<PlanTemplateReq> appParam) throws Exception{
		//拼装查询条件
		PlanTemplateReq query =appParam.getBody();
		Result rs = cargoPlanTemplateService.delTemplat(query);
		return AppResult.valueOf(rs);
	}
	
}