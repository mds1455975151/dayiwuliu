package com.tianrui.web.app.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.admin.intf.IMerchantService;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.req.admin.merchant.MerchantReq;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.cargoplan.PlanEditReq;
import com.tianrui.api.req.front.cargoplan.PlanQueryReq;
import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.resp.admin.merchant.MerchantResp;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 我承运的计划APP接口
 * @author zhanggaohao
 * @createtime 2016年10月26日 上午8:54:01
 * @classname AppPlanOwnerAction.java
 */
@Controller
@RequestMapping("/app/planowner")
public class AppPlanOwnerAction {
	
	public Logger logger = LoggerFactory.getLogger(AppPlanOwnerAction.class);
	
	@Autowired
	protected ICargoPlanService planService;
	
	@Autowired
	private IMerchantService merchantService;
	
	//获取我发布的计划列表
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
		PaginationVO<PlanResp> page = planService.pageForApp(query);
		
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
		//拼装查询条件
		PlanQueryReq query =appParam.getBody();
		PlanResp plan =planService.detail(query);
		appResult.setCode("000000");
		appResult.setReturnData(plan);
		return appResult;
	}
	
	
	//删除计划
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
		Result rs =planService.ownerDeleteConfirm(req);
		return AppResult.valueOf(rs);
	}
	
	//新增计划
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ApiParamRawType(PlanSaveReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult add(AppParam<PlanSaveReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		//拼装查询条件
		PlanSaveReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs= planService.savePlan(req);
		return AppResult.valueOf(rs);
	}
	
	//修改计划
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ApiParamRawType(PlanEditReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult edit(AppParam<PlanEditReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		//拼装查询条件
		PlanEditReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs= planService.editPlan(req);
		return AppResult.valueOf(rs);
	}

	//收回计划
	@RequestMapping(value="/cancle",method=RequestMethod.POST)
	@ApiParamRawType(PlanConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult cancle(AppParam<PlanConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		//拼装查询条件
		PlanConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs= planService.cancleConfirm(req);
		return AppResult.valueOf(rs);
	}
	
	//完成计划
	@RequestMapping(value="/complete",method=RequestMethod.POST)
	@ApiParamRawType(PlanConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult complete(AppParam<PlanConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		//拼装查询条件
		PlanConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs= planService.completePlan(req);
		return AppResult.valueOf(rs);
	}
	
	/** 获取客商信息*/
	@RequestMapping(value="/merchant",method=RequestMethod.POST)
	@ApiParamRawType(MerchantReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult merchant(AppParam<MerchantReq> appParam) throws Exception{
		AppResult rs = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		//拼装查询条件
		MerchantReq merreq = appParam.getBody();
		PaginationVO<MerchantResp> merchant = merchantService.find(merreq);
		rs.setCode("000000");
		rs.setTotal(merchant.getTotalInt());
		if(merchant.getTotalInt()!=0){
			rs.setReturnData(merchant.getList());
		}
		return rs;
	}
	
}