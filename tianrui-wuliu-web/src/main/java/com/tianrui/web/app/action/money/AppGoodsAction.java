package com.tianrui.web.app.action.money;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.admin.intf.IFileCargoService;
import com.tianrui.api.intf.planGoods.IPlanGoodsService;
import com.tianrui.api.req.admin.FileCargoReq;
import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.resp.admin.FileCargoResp;
import com.tianrui.api.resp.goods.SelectAppBillResp;
import com.tianrui.api.resp.goods.SelectAppPlanGoodsResp;
import com.tianrui.api.resp.goods.SelectAppPlanResp;
import com.tianrui.api.resp.goods.SelectPlanGoodsResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/goods")
public class AppGoodsAction {

	@Autowired
	IPlanGoodsService planGoodsService;
	@Autowired
	IFileCargoService fileCargoService;
	
	/**货源新增操作*/
	@RequestMapping("save")
	@ApiParamRawType(PlanSaveReq.class)
	@ApiTokenValidation
	@ResponseBody
	public Result plansave(AppParam<PlanSaveReq> appParam) throws Exception {
		Head head = appParam.getHead();
		PlanSaveReq req = appParam.getBody();
		req.setCurruId(head.getId());
		Result rs= planGoodsService.savePlanGoods(req);
		return rs;
	}
	/** 删除货源*/
	@RequestMapping("goodsDelete")
	@ApiParamRawType(FileCargoReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult goodsDelete(AppParam<FileCargoReq> appParam){
		FileCargoReq req = appParam.getBody();
		Result rs =planGoodsService.deleteGoods(req.getId());
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 查询发布的货源
	 */
	@RequestMapping("select")
	@ApiParamRawType(PlanGoodsReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult select(AppParam<PlanGoodsReq> appParam) throws Exception{
		AppResult rs = new AppResult();
		Head head = appParam.getHead();
		PlanGoodsReq req = appParam.getBody();
		req.setCreator(head.getId());
		PaginationVO<SelectPlanGoodsResp> page = planGoodsService.select(req);
		rs.setCode("000000");
		rs.setReturnData(page.getList());
		rs.setTotal(page.getTotalInt());
		return rs;
	}
	
	/***
	 * 查询货源详情
	 */
	@RequestMapping(value="/goodsDetail",method=RequestMethod.POST)
	@ApiParamRawType(FileCargoReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult goodsDetail(AppParam<FileCargoReq> appParam) throws Exception{
		FileCargoReq req = appParam.getBody();
		Result rs = planGoodsService.findPlanGoodsId(req.getId());
		return AppResult.valueOf(rs);
	}
	
	
	//------------------------------------------------------------------------------
	/**
	 * 货物查询
	 * */
	@RequestMapping(value="/cargoSelect",method=RequestMethod.POST)
	@ApiParamRawType(FileCargoReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult cargoSelect(AppParam<FileCargoReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		appResult.setCode("000000");
		FileCargoReq req = appParam.getBody();
		PaginationVO<FileCargoResp> page = fileCargoService.queryCargoInfoByCondition(req);
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
