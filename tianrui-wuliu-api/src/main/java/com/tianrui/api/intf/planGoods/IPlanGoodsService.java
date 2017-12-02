package com.tianrui.api.intf.planGoods;

import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.req.goods.GoodsAuditReq;
import com.tianrui.api.req.goods.GoodsTOPlanReq;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.resp.goods.PlanGoodsResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IPlanGoodsService {

	/** 新增货源计划*/
	Result savePlanGoods(PlanSaveReq req);
	/** 查询货源*/
	public PaginationVO<PlanGoodsResp> select(PlanGoodsReq req)throws Exception;
	/** id查询详情
	 * @throws Exception */
	Result findPlanGoodsId(String id) throws Exception;
	/** 后台货源发布计划*/
	Result goodsToPlan(GoodsTOPlanReq req);
	/** 货源审核*/
	Result auditGoods(GoodsAuditReq req);
}
