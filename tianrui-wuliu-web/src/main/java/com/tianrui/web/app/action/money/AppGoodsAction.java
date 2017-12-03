package com.tianrui.web.app.action.money;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.planGoods.IPlanGoodsService;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.resp.goods.SelectAppPlanGoodsResp;
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
}
