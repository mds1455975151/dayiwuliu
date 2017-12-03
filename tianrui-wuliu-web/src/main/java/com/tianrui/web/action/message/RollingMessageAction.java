package com.tianrui.web.action.message;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.planGoods.IPlanGoodsService;
import com.tianrui.api.message.intf.IMessageRollingService;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.resp.goods.SelectAppBillResp;
import com.tianrui.api.resp.goods.SelectAppPlanGoodsResp;
import com.tianrui.api.resp.goods.SelectAppPlanResp;
import com.tianrui.api.resp.money.MessageRollingResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("/publicMember/rollingMessage")
public class RollingMessageAction {
	@Autowired
	protected IMessageRollingService messageService;
	@Autowired
	protected IPlanGoodsService planGoodsService;
	
	/**
	 * 获取在途运单列表
	 * @param request
	 * @param number
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/onWaybill")
	@ResponseBody
	public Result onWaybill(PlanGoodsReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<SelectAppBillResp> pv = planGoodsService.appBillSelect(req);
		rs.setData(pv);
		return rs;
	}
	
	/**
	 * 获取货运计划列表
	 * @param request
	 * @param number
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/freightPlan")
	@ResponseBody
	public Result freightPlan(PlanGoodsReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<SelectAppPlanResp> pv = planGoodsService.appPlanSelect(req);
		rs.setData(pv);
		return rs;
	}
	/**
	 * 获取货源需求列表
	 * @param request
	 * @param number
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/freightDemand")
	@ResponseBody
	public Result freightDemand(PlanGoodsReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<SelectAppPlanGoodsResp> pv = planGoodsService.appSelect(req);
		rs.setData(pv);
		return rs;
	}
	/**
	 * 获取首页滚动消息
	 * @param request
	 * @param number
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findRollingMessage")
	@ResponseBody
	public Result findMessage(HttpServletRequest request,Integer number) throws Exception{
		Result rs = Result.getSuccessResult();
		if( number ==null || number ==0 ){
			number = 10;
		}
		List<MessageRollingResp> page = messageService.findRollingMessage(number);
		rs.setData(page);
		return rs;
	}
	/**
	 * 消息点击次数+1
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateConsultNumber")
	@ResponseBody
	public Result updateMessage(HttpServletRequest request,Long id) throws Exception{
		Result rs =messageService.updateConsultNumber(id);
		return rs;
	}
}
