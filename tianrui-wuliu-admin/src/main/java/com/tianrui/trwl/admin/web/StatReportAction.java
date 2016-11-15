package com.tianrui.trwl.admin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IOrganizationService;
import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.req.front.adminReport.StatReportReq;
import com.tianrui.api.resp.front.adminReport.StatReportOfBillResp;
import com.tianrui.api.resp.front.adminReport.StatReportOfPlanResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("/statReport")
public class StatReportAction {
	public static Logger logger = LoggerFactory.getLogger(StatReportAction.class);
	
	@Autowired
	private ICargoPlanService planService;
	@Autowired
	private IBillService billService;
	@Autowired
	private IOrganizationService orgService;
	
	@RequestMapping("/planStat")
	public ModelAndView planStat(HttpServletRequest request){
		ModelAndView view = new ModelAndView("/file/statReport/planStat");
		try {
			view.addObject("orgCode", orgService.findOne(SessionManager.getSessionMember(request).getOrgid()).getOrganizationno());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("getPlanStatReport")
	@ResponseBody
	public Result getPlanStatReport(StatReportReq req, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		PaginationVO<StatReportOfPlanResp> page = null;
		try {
			String orgid = SessionManager.getSessionMember(request).getOrgid();
			if(StringUtils.isNotBlank(orgid)){
				String orgCode = orgService.findOne(orgid).getOrganizationno();
				if(!StringUtils.equals(orgCode, "0000")){
					req.setOrgid(orgid);
				}
			}
			page = planService.queryAdminStatReport(req);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setCode("-1");
			result.setData(e.getMessage());
			return result;
		}
		return result;
	}
	
	@RequestMapping("/planStatExport")
	public void planStatExport(HttpServletRequest request, HttpServletResponse response){
		
	}
	
	@RequestMapping("/billStat")
	public ModelAndView billStat(HttpServletRequest request){
		ModelAndView view = new ModelAndView("/file/statReport/billStat");
		try {
			view.addObject("orgCode", orgService.findOne(SessionManager.getSessionMember(request).getOrgid()).getOrganizationno());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("getBillStatReport")
	@ResponseBody
	public Result getBillStatReport(StatReportReq req, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		PaginationVO<StatReportOfBillResp> page = null;
		try {
			String orgid = SessionManager.getSessionMember(request).getOrgid();
			if(StringUtils.isNotBlank(orgid)){
				String orgCode = orgService.findOne(orgid).getOrganizationno();
				if(!StringUtils.equals(orgCode, "0000")){
					req.setOrgid(orgid);
				}
			}
			page = billService.queryAdminStatReport(req);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setCode("-1");
			result.setData(e.getMessage());
			return result;
		}
		return result;
	}
	
}
