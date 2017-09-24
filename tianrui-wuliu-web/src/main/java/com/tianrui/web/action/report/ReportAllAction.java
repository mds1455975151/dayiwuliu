package com.tianrui.web.action.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IReportAllService;
import com.tianrui.api.req.report.ReportBillAllReq;
import com.tianrui.api.req.report.ReportPayAllReq;
import com.tianrui.api.req.report.ReportPlanAllReq;
import com.tianrui.api.resp.report.ReportBillAllResp;
import com.tianrui.api.resp.report.ReportPayAllResp;
import com.tianrui.api.resp.report.ReportPlanAllResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 前台报表
 * 2017-9-23
 * @author jh
 *
 */
@Controller
@RequestMapping("/trwuliu/ReportAll")
public class ReportAllAction {

	@Autowired
	IReportAllService reportAllService;
	
	@RequestMapping("payPage")
	public ModelAndView payPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/report/all/pay/driverPay");
		return view;
	}
	@RequestMapping("pay")
	@ResponseBody
	public Result pay(ReportPayAllReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<ReportPayAllResp> page = reportAllService.selectPay(req);
		rs.setData(page);
		return rs;
	}
	
	@RequestMapping("planPage")
	public ModelAndView planPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/report/all/plan/ownerPlan");
		return view;
	}
	@RequestMapping("plan")
	@ResponseBody
	public Result plan(ReportPlanAllReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<ReportPlanAllResp> page = reportAllService.selectPlan(req);
		rs.setData(page);
		return rs;
	}
	
	@RequestMapping("billPage")
	public ModelAndView billPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/report/all/bill/driverBill");
		return view;
	}
	@RequestMapping("bill")
	@ResponseBody
	public Result bill(ReportBillAllReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<ReportBillAllResp> page = reportAllService.selectBill(req);
		rs.setData(page);
		return rs;
	}
}
