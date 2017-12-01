package com.tianrui.trwl.admin.web.goods;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.planGoods.IPlanGoodsService;
import com.tianrui.api.req.goods.GoodsTOPlanReq;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.resp.goods.PlanGoodsResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("adminGoods")
public class AdminGoodsAction {
	@Autowired
	IPlanGoodsService planGoodsService;
	
	@RequestMapping("/page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/goods/page");
		return view;
	}
	
	@RequestMapping("tration")
	public ModelAndView tration(String id) throws Exception{
		ModelAndView view = new ModelAndView();
		Result rs = planGoodsService.findPlanGoodsId(id);
		view.setViewName("/goods/upt_page");
		view.addObject("data",rs.getData());
		return view;
	}
	
	@RequestMapping("select")
	@ResponseBody
	public Result select(PlanGoodsReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<PlanGoodsResp> page = planGoodsService.select(req);
		rs.setData(page);
		return rs;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Result save(GoodsTOPlanReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setUserId(user.getAccount());
		rs = planGoodsService.goodsToPlan(req);
		return rs;
	}
}
