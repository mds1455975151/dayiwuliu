package com.tianrui.web.app.action.money;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.admin.intf.IFileOrgCargoService;
import com.tianrui.api.intf.planGoods.IPlanGoodsService;
import com.tianrui.api.req.admin.FileOrgCargoReq;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.resp.admin.FileOrgCargoResp;
import com.tianrui.api.resp.goods.SelectAppBillResp;
import com.tianrui.api.resp.goods.SelectAppPlanGoodsResp;
import com.tianrui.api.resp.goods.SelectAppPlanResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/goods")
public class AppGoodsAction {

	@Autowired
	IPlanGoodsService planGoodsService;
	@Autowired
	IFileOrgCargoService fileOrgCargoService;
	
	/**
	 * 物料
	 * */
	@RequestMapping(value="/cargoSelect",method=RequestMethod.POST)
	@ApiParamRawType(FileOrgCargoReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult cargoSelect(AppParam<FileOrgCargoReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		FileOrgCargoReq req = appParam.getBody();
		PaginationVO<FileOrgCargoResp> page = fileOrgCargoService.queryMyCargoInfoByCondition(req);
		appResult.setReturnData(page.getList());
		appResult.setTotal(page.getTotalInt());
		return appResult;
	}
	
	
	/**
	 * 查询货源列表
	 * */
	@RequestMapping(value="/planGoodsSelect",method=RequestMethod.POST)
	@ApiParamRawType(PlanGoodsReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult planGoodsSelect(AppParam<PlanGoodsReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		PlanGoodsReq req = appParam.getBody();
		req.setIsfamily((byte)1);
		PaginationVO<SelectAppPlanGoodsResp> page = planGoodsService.appSelect(req);
		appResult.setReturnData(page.getList());
		appResult.setTotal(page.getTotalInt());
		return appResult;
	}
	
	/**
	 * 查询计划列表
	 * */
	@RequestMapping(value="/planSelect",method=RequestMethod.POST)
	@ApiParamRawType(PlanGoodsReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult planSelect(AppParam<PlanGoodsReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		PlanGoodsReq req = appParam.getBody();
		PaginationVO<SelectAppPlanResp> page = planGoodsService.appPlanSelect(req);
		appResult.setReturnData(page.getList());
		appResult.setTotal(page.getTotalInt());
		return appResult;
	}
	
	/**
	 * 查询运单列表
	 * */
	@RequestMapping(value="/billSelect",method=RequestMethod.POST)
	@ApiParamRawType(PlanGoodsReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult billSelect(AppParam<PlanGoodsReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		PlanGoodsReq req = appParam.getBody();
		PaginationVO<SelectAppBillResp> page = planGoodsService.appBillSelect(req);
		appResult.setReturnData(page.getList());
		appResult.setTotal(page.getTotalInt());
		return appResult;
	}
}
