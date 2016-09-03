package com.tianrui.web.app.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.req.front.cargoplan.PlanAppointReq;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.cargoplan.PlanQueryReq;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.MemberVoService;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
 * @author zhanggaohao
 * @createtime 2016年9月2日 下午4:48:29
 * @classname AppPlanAppointAction.java
 */
@Controller
@RequestMapping("/app/appPlanAppointAction")
public class AppPlanAppointAction {
	
	public Logger logger = LoggerFactory.getLogger(AppMyVenderAction.class);
	
	@Autowired
	protected ICargoPlanService cargoPlanService;
	
	@Autowired
	protected MemberVoService memberVoService;
	
	//获取委派计划列表
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
		query.setOwnerId(uId);
		query.setIsAppoint("1");
		PaginationVO<PlanResp> page = cargoPlanService.pageForApp(query);
		appResult.setCode("000000");
		appResult.setTotal(page.getTotalInt());
		appResult.setReturnData(page.getList());
		return appResult;
	}
	
	//收回委派计划
	@RequestMapping(value="/cancle",method=RequestMethod.POST)
	@ApiParamRawType(PlanConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult cancle(AppParam<PlanConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId = appParam.getHead().getId();
		//拼装查询条件
		PlanConfirmReq query =appParam.getBody();
		query.setCurruId(uId);
		Result rs= cargoPlanService.cancleConfirm(query);
		return AppResult.valueOf(rs);
	}
		
	//删除委派计划
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ApiParamRawType(PlanConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult plandel(AppParam<PlanConfirmReq> appParam) throws Exception {
		//获取当前用户
		String uId = appParam.getHead().getId();
		//拼装查询条件
		PlanConfirmReq query =appParam.getBody();
		query.setCurruId(uId);
		Result rs= cargoPlanService.venderDelConfirm(query);
		return AppResult.valueOf(rs);
	}
	
	//修改委派计划
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ApiParamRawType(PlanAppointReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult editAppointPlan(AppParam<PlanAppointReq> appParam) throws Exception{
		MemberVo vo = memberVoService.get(appParam.getHead().getId());
		PlanAppointReq par = appParam.getBody();
		par.setMemberVo(vo);
		return AppResult.valueOf(cargoPlanService.editAppointPlan(par));
	}
	
	//新增委派计划
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ApiParamRawType(PlanAppointReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult addAppointPlan(AppParam<PlanAppointReq> appParam) throws Exception{
		MemberVo vo = memberVoService.get(appParam.getHead().getId());
		PlanAppointReq par = appParam.getBody();
		par.setMemberVo(vo);
		return AppResult.valueOf(cargoPlanService.addAppointPlan(par));
	}
	
	//查看委派计划详情
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ApiParamRawType(PlanQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult detail(AppParam<PlanQueryReq> appParam) throws Exception {
		AppResult appResult = new AppResult();
		PlanResp resp = cargoPlanService.detail(appParam.getBody());
		appResult.setCode("000000");
		appResult.setReturnData(resp);
		return appResult;
	}
	
}
