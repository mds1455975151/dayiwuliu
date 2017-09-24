package com.tianrui.web.action.report;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
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
