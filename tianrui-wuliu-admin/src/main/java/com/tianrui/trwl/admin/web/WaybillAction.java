package com.tianrui.trwl.admin.web;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.intf.IPayInvoiceDetailService;
import com.tianrui.api.req.admin.AdminPlanReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.req.front.cargoplan.PlanReq;
import com.tianrui.api.req.front.pay.PayInvoiceDetailSaveReq;
import com.tianrui.api.resp.admin.AdminPlanResp;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("/admin/waybill")
public class WaybillAction {
	
	@Autowired
	protected ICargoPlanService cargoPlanService;
	
	@Autowired
	protected IBillService billService;
	@Autowired
	protected IPayInvoiceDetailService payInvoiceDetailService;
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
	public Result findPlan(AdminPlanReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users  currUser =SessionManager.getSessionMember(request);
		PaginationVO<PlanResp> resp =null;
		if( currUser !=null && StringUtils.isNotBlank(currUser.getOrgid()) ){
			req.setOrgId(currUser.getOrgid());
			resp = cargoPlanService.pageForAdmin(req);
		}
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
		Users  currUser =SessionManager.getSessionMember(requset);
		PaginationVO<WaybillResp> page=null;
		if( currUser !=null && StringUtils.isNotBlank(currUser.getOrgid()) ){
			req.setCurrOrgId(currUser.getOrgid());
			page = billService.pageForBack(req);
		}
		rs.setData(page);
		return rs;
	}
	/**
	 * 
	 * @描述:后台运单确认运价
	 * @param req
	 * @param requset
	 * @return
	 * @throws Exception 
	 * @返回类型 Result
	 * @创建人 lsj
	 * @创建时间 2016年6月18日下午2:01:36
	 */
	@RequestMapping("/priceConfrim")
	@ResponseBody
	public Result priceConfrim(PayInvoiceDetailSaveReq req,HttpServletRequest requset) throws Exception{
		Result rs = Result.getErrorResult();
		Users  currUser =SessionManager.getSessionMember(requset);
		if( currUser !=null && StringUtils.isNotBlank(currUser.getOrgid()) ){
			req.setCurruId(currUser.getAccount());
			rs =payInvoiceDetailService.saveByBillPriceConfirm(req,currUser.getOrgid());
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	
}
