package com.tianrui.web.app.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.cargoplan.PlanQueryReq;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.api.resp.front.cargoplan.PlanStatResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
  * @ClassName: AppPlanAction 
  * @Description: 我承运的计划 app接口
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月22日 下午3:06:49 
  *
 */
@Controller
@RequestMapping("/app/planvender")
public class AppPlanVenderAction {
	
	public Logger logger = LoggerFactory.getLogger(AppPlanVenderAction.class);
	
	
	@Autowired
	protected ICargoPlanService planService;

	
	//获取承运计划列表
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ApiParamRawType(PlanQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult page(AppParam<PlanQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
	
		//拼装查询条件
		PlanQueryReq query =appParam.getBody();
		query.setVenderId(uId);
		PaginationVO<PlanResp> page =planService.pageForApp(query);
		
		appResult.setCode("000000");
		appResult.setTotal(page.getTotalInt());
		appResult.setReturnData(page.getList());
		return appResult;
	}
	
	
	//承运计划详情
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ApiParamRawType(PlanQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult detail(AppParam<PlanQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		PlanQueryReq query =appParam.getBody();
		query.setVenderId(uId);
		PlanResp plan =planService.detail(query);
		
		appResult.setCode("000000");
		appResult.setReturnData(plan);
		return appResult;
	}
	
	
	//计划完成数量 
	@RequestMapping(value="/planStat",method=RequestMethod.POST)
	@ApiParamRawType(PlanQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult planStat(AppParam<PlanQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		
		appResult.setCode("000000");
		appResult.setReturnData(planService.planstat(appParam.getBody()));
		return appResult;
	}
	
	//车主确认计划
	@RequestMapping(value="/accept",method=RequestMethod.POST)
	@ApiParamRawType(PlanConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult acceptConfirm(AppParam<PlanConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		//拼装查询条件
		PlanConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs =planService.acceptConfirm(req);
		return AppResult.valueOf(rs);
	}
	
	//车主拒绝计划
	@RequestMapping(value="/refuse",method=RequestMethod.POST)
	@ApiParamRawType(PlanConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult refuseConfirm(AppParam<PlanConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		//拼装查询条件
		PlanConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs =planService.refuseConfirm(req);
		return AppResult.valueOf(rs);
	}
	//车主删除计划
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ApiParamRawType(PlanConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult deleteConfirm(AppParam<PlanConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		//拼装查询条件
		PlanConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs =planService.venderDelConfirm(req);
		return AppResult.valueOf(rs);
	}
	
}
