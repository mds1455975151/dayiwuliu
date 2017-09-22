package com.tianrui.trwl.admin.web.report;

import java.util.HashMap;
import java.util.Map;

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
import com.tianrui.trwl.admin.report.BillAllReportExcilUtil;
import com.tianrui.trwl.admin.report.PaylAllReportExcilUtil;
import com.tianrui.trwl.admin.report.PlanAllReportExcilUtil;

@Controller
@RequestMapping("reportAll")
public class ReportAllAction {

	@Autowired
	IReportAllService reportAllService;
	
	/** 计划统计报表页面*/
	@RequestMapping("planPage")
	public ModelAndView planPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/statReport/planAll");
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
	@RequestMapping("planReport")
	public ModelAndView planReport(ReportPlanAllReq req) throws Exception{
		req.setPageNo(null);
		PaginationVO<ReportPlanAllResp> page = reportAllService.selectPlan(req);
		Map map = new HashMap();
    	map.put("list", page.getList());
    	PlanAllReportExcilUtil excilUtil = new PlanAllReportExcilUtil(); 
	    return new ModelAndView(excilUtil, map); 
	}
	
	/** 运单统计报表页面*/
	@RequestMapping("billPage")
	public ModelAndView billPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/statReport/billAll");
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
	@RequestMapping("billReport")
	public ModelAndView billReport(ReportBillAllReq req) throws Exception{
		req.setPageNo(null);
		PaginationVO<ReportBillAllResp> page = reportAllService.selectBill(req);
		Map map = new HashMap();
    	map.put("list", page.getList());
    	BillAllReportExcilUtil excilUtil = new BillAllReportExcilUtil(); 
	    return new ModelAndView(excilUtil, map); 
	}
	/** 账单统计报表页面*/
	@RequestMapping("payPage")
	public ModelAndView payPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/statReport/payAll");
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
	
	@RequestMapping("payReport")
	public ModelAndView payReport(ReportPayAllReq req) throws Exception{
		req.setPageNo(null);
		PaginationVO<ReportPayAllResp> page = reportAllService.selectPay(req);
		Map map = new HashMap();
    	map.put("list", page.getList());
    	PaylAllReportExcilUtil excilUtil = new PaylAllReportExcilUtil(); 
	    return new ModelAndView(excilUtil, map); 
	}
}
