package com.tianrui.web.action.report;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.IReportAllService;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.req.report.ReportBillAllReq;
import com.tianrui.api.req.report.ReportPayAllReq;
import com.tianrui.api.req.report.ReportPlanAllReq;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.api.resp.report.ReportBillAllResp;
import com.tianrui.api.resp.report.ReportPayAllResp;
import com.tianrui.api.resp.report.ReportPlanAllResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.report.BillAllReportExcilUtil;
import com.tianrui.web.report.PaylAllReportExcilUtil;
import com.tianrui.web.report.PlanAllReportExcilUtil;
import com.tianrui.web.util.SessionManager;

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
	@Autowired
	IBillService billService;
	
	@RequestMapping("getPayBillId")
	public Result getPayBillId(String id){
		Result rs = Result.getSuccessResult();
		return rs;
	}
	@RequestMapping("getPayPlanCode")
	@ResponseBody
	public Result getPayPlanId(String planCode) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = reportAllService.findPlanCode(planCode);
		return rs;
	}
	
	@RequestMapping("billDatailPage")
	public ModelAndView billDatailPage(String id) throws Exception{
		ModelAndView view = new ModelAndView();
		WaybillQueryReq req = new WaybillQueryReq();
		req.setId(id);
		view.addObject("bill",billService.queryWayBill(req));
		view.setViewName("/bill/bill_detail");
		return view;
	}
	
	@RequestMapping("planId")
	@ResponseBody
	public Result planId(String id,String type) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = billService.findPlanId(id, type);
		return rs;
	}
	
	@RequestMapping("payPage")
	public ModelAndView payPage(String payType){
		ModelAndView view = new ModelAndView();
		if(StringUtils.equals("1", payType)){
			// 1-司机，2-车主，3-货主
			view.setViewName("/report/all/pay/driverPay");
		}else if(StringUtils.equals("2", payType)){
			view.setViewName("/report/all/pay/venderPay");
		}else if(StringUtils.equals("3", payType)){
			view.setViewName("/report/all/pay/ownerPay");
		}
		return view;
	}
	@RequestMapping("pay")
	@ResponseBody
	public Result pay(ReportPayAllReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals("1", req.getReportType())){
			// 1-司机，2-车主，3-货主
			req.setPayDriverId(vo.getId());
		}else if(StringUtils.equals("2", req.getReportType())){
			req.setPayVenderId(vo.getId());
		}else if(StringUtils.equals("3", req.getReportType())){
			req.setPayOwnerId(vo.getId());
		}
		PaginationVO<ReportPayAllResp> page = reportAllService.selectPay(req);
		rs.setData(page);
		return rs;
	}
	
	@RequestMapping("payCount")
	@ResponseBody
	public Result payCount(ReportPayAllReq req,HttpServletRequest request) throws Exception{
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals("1", req.getReportType())){
			// 1-司机，2-车主，3-货主
			req.setPayDriverId(vo.getId());
		}else if(StringUtils.equals("2", req.getReportType())){
			req.setPayVenderId(vo.getId());
		}else if(StringUtils.equals("3", req.getReportType())){
			req.setPayOwnerId(vo.getId());
		}
		Result rs = reportAllService.payCount(req);
		return rs;
	}
	
	@RequestMapping("payReport")
	public ModelAndView payReport(ReportPayAllReq req,HttpServletRequest request) throws Exception{
		req.setPageNo(null);
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals("1", req.getReportType())){
			// 1-司机，2-车主，3-货主
			req.setPayDriverId(vo.getId());
		}else if(StringUtils.equals("2", req.getReportType())){
			req.setPayVenderId(vo.getId());
		}else if(StringUtils.equals("3", req.getReportType())){
			req.setPayOwnerId(vo.getId());
		}
		PaginationVO<ReportPayAllResp> page = reportAllService.selectPay(req);
		Map map = new HashMap();
    	map.put("list", page.getList());
    	PaylAllReportExcilUtil excilUtil = new PaylAllReportExcilUtil(); 
	    return new ModelAndView(excilUtil, map); 
	}
	
	@RequestMapping("planPage")
	public ModelAndView planPage(String payType){
		ModelAndView view = new ModelAndView();
		if(StringUtils.equals("2", payType)){
			// 1-司机，2-车主，3-货主
			view.setViewName("/report/all/plan/venderPlan");
		}else if(StringUtils.equals("3", payType)){
			view.setViewName("/report/all/plan/ownerPlan");
		}
		return view;
	}
	@RequestMapping("plan")
	@ResponseBody
	public Result plan(ReportPlanAllReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals("2", req.getReportType())){
			// 2-车主，3-货主
			req.setPlanVenderId(vo.getId());
		}else if(StringUtils.equals("3", req.getReportType())){
			req.setPlanOwnerId(vo.getId());
		}
		PaginationVO<ReportPlanAllResp> page = reportAllService.selectPlan(req);
		rs.setData(page);
		return rs;
	}
	@RequestMapping("planCount")
	@ResponseBody
	public Result planCount(ReportPlanAllReq req,HttpServletRequest request) throws Exception{
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals("2", req.getReportType())){
			// 2-车主，3-货主
			req.setPlanVenderId(vo.getId());
		}else if(StringUtils.equals("3", req.getReportType())){
			req.setPlanOwnerId(vo.getId());
		}
		Result rs = reportAllService.planCount(req);
		return rs;
	}
	@RequestMapping("planReport")
	public ModelAndView planReport(ReportPlanAllReq req,HttpServletRequest request) throws Exception{
		req.setPageNo(null);
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals("2", req.getReportType())){
			// 2-车主，3-货主
			req.setPlanVenderId(vo.getId());
		}else if(StringUtils.equals("3", req.getReportType())){
			req.setPlanOwnerId(vo.getId());
		}
		PaginationVO<ReportPlanAllResp> page = reportAllService.selectPlan(req);
		Map map = new HashMap();
    	map.put("list", page.getList());
    	PlanAllReportExcilUtil excilUtil = new PlanAllReportExcilUtil(); 
	    return new ModelAndView(excilUtil, map); 
	}
	
	@RequestMapping("billPage")
	public ModelAndView billPage(String payType ){
		ModelAndView view = new ModelAndView();
		if(StringUtils.equals("1", payType)){
			// 1-司机，2-车主，3-货主
			view.setViewName("/report/all/bill/driverBill");
		}else if(StringUtils.equals("2", payType)){
			// 1-司机，2-车主，3-货主
			view.setViewName("/report/all/bill/venderBill");
		}else if(StringUtils.equals("3", payType)){
			view.setViewName("/report/all/bill/ownerBill");
		}
		return view;
	}
	@RequestMapping("bill")
	@ResponseBody
	public Result bill(ReportBillAllReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals("1", req.getReportType())){
			//1-司机 2-车主，3-货主
			req.setBillDriverId(vo.getId());
		}else if(StringUtils.equals("2", req.getReportType())){
			// 2-车主，3-货主
			req.setBillVenderId(vo.getId());
		}else if(StringUtils.equals("3", req.getReportType())){
			req.setBillOwnerId(vo.getId());
		}
		PaginationVO<ReportBillAllResp> page = reportAllService.selectBill(req);
		rs.setData(page);
		return rs;
	}
	@RequestMapping("billCount")
	@ResponseBody
	public Result billCount(ReportBillAllReq req,HttpServletRequest request) throws Exception{
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals("1", req.getReportType())){
			//1-司机 2-车主，3-货主
			req.setBillDriverId(vo.getId());
		}else if(StringUtils.equals("2", req.getReportType())){
			// 2-车主，3-货主
			req.setBillVenderId(vo.getId());
		}else if(StringUtils.equals("3", req.getReportType())){
			req.setBillOwnerId(vo.getId());
		}
		Result rs = reportAllService.billCount(req);
		return rs;
	}
	@RequestMapping("billReport")
	public ModelAndView billReport(ReportBillAllReq req,HttpServletRequest request) throws Exception{
		req.setPageNo(null);
		MemberVo vo = SessionManager.getSessionMember(request);
		if(StringUtils.equals("1", req.getReportType())){
			//1-司机 2-车主，3-货主
			req.setBillDriverId(vo.getId());
		}else if(StringUtils.equals("2", req.getReportType())){
			req.setBillVenderId(vo.getId());
		}else if(StringUtils.equals("3", req.getReportType())){
			req.setBillOwnerId(vo.getId());
		}
		PaginationVO<ReportBillAllResp> page = reportAllService.selectBill(req);
		Map map = new HashMap();
    	map.put("list", page.getList());
    	BillAllReportExcilUtil excilUtil = new BillAllReportExcilUtil(); 
	    return new ModelAndView(excilUtil, map); 
	}
}
