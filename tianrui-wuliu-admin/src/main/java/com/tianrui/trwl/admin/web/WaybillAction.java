package com.tianrui.trwl.admin.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.req.admin.AdminPlanReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.req.front.cargoplan.PlanReq;
import com.tianrui.api.resp.admin.AdminPlanResp;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("/admin/waybill")
public class WaybillAction {
	
	@Autowired
	protected ICargoPlanService cargoPlanService;
	
	@Autowired
	protected IBillService billService;
	
	
	/**
	 * 
	 * @描述:平台运单管理
	 * @return
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年6月12日上午9:44:52
	 */
	@RequestMapping("/flieWaybill")
	public ModelAndView flieWaybill(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/waybill/file_bill");
		return view;
	}
	/**
	 * 
	 * @描述:平台计划管理
	 * @return
	 * @返回类型 ModelAndView
	 * @创建人 lsj
	 * @创建时间 2016年6月12日上午9:45:23
	 */
	@RequestMapping("/flieProject")
	public ModelAndView flieProject(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/waybill/file_project");
		return view;
	}
	/**
	 * 
	 * @描述:查询货运计划
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月12日上午9:47:05
	 */
	@RequestMapping("/findPlan")
	@ResponseBody
	public Result findPlan(AdminPlanReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<PlanResp> resp = cargoPlanService.pageForAdmin(req);
		rs.setData(resp);
		return rs;
	}
	/**
	 * 
	 * @描述:后台运单管理查询
	 * @param req
	 * @param requset
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月18日下午2:01:36
	 */
	@RequestMapping("/findWaybill")
	@ResponseBody
	public Result findWaybill(WaybillQueryReq req,HttpServletRequest requset) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<WaybillResp> page = billService.pageForBack(req);
		rs.setData(page);
		return rs;
	}
	
}
