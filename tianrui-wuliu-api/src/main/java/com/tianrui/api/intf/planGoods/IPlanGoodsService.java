package com.tianrui.api.intf.planGoods;

import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.req.goods.GoodsAuditReq;
import com.tianrui.api.req.goods.GoodsTOPlanReq;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.resp.goods.SelectAppBillResp;
import com.tianrui.api.resp.goods.SelectAppPlanGoodsResp;
import com.tianrui.api.resp.goods.SelectAppPlanResp;
import com.tianrui.api.resp.goods.SelectPlanGoodsResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IPlanGoodsService {

	/** 新增货源计划*/
	Result savePlanGoods(PlanSaveReq req);
	/** 查询货源*/
	public PaginationVO<SelectPlanGoodsResp> select(PlanGoodsReq req)throws Exception;
	/** id查询详情
	 * @throws Exception */
	Result findPlanGoodsId(String id) throws Exception;
	/** 后台货源发布计划*/
	Result goodsToPlan(GoodsTOPlanReq req);
	/** 货源审核*/
	Result auditGoods(GoodsAuditReq req);
	
	/** 货源中心-查询货源*/
	public PaginationVO<SelectAppPlanGoodsResp> appSelect(PlanGoodsReq req)throws Exception;
	/** 货源中心-查询计划*/
	public PaginationVO<SelectAppPlanResp> appPlanSelect(PlanGoodsReq req)throws Exception;
	/** 货源中心-查询运单*/
	public PaginationVO<SelectAppBillResp> appBillSelect(PlanGoodsReq req)throws Exception;
	
	/** 统计计划*/
	public Double countPlanTotal(Long timeBegin,Long timeEnd);
	/** 统计货源*/
	public Double countGoodsTotal(Long timeBegin,Long timeEnd);
}
