package com.tianrui.trwl.admin.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.admin.intf.IFreightInfoService;
import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.intf.IAssessService;
import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.intf.IPayInvoiceDetailService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.admin.AdminPlanReq;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.req.front.bill.BillAssessReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.req.front.pay.PayInvoiceDetailSaveReq;
import com.tianrui.api.resp.front.bill.AnlianBillResp;
import com.tianrui.api.resp.front.bill.BillAssessResp;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.UUIDUtil;
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
	@Autowired
	IFreightInfoService iFreightInfoService;
	@Autowired
	private IAssessService iAssessService;
	@Autowired
	private IAnlianBillService anlianBillService;
	@Autowired
	private IAnlianService anlianService;
	@Autowired
	private ISystemMemberService systemMemberService;
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
	 * 查看安联运单
	 * @return
	 */
	@RequestMapping("/flieanlianbill")
	public ModelAndView flieanlianbill(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/waybill/file_anlian_bill");
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
	
	/** 后台查看开票
	 * @throws Exception */
	@RequestMapping("/findAnlianBill")
	@ResponseBody
	public Result findAnlianBill(AnlianBillFindReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<AnlianBillResp> resp = anlianBillService.find(req);
		rs.setData(resp);
		return rs;
	}
	@RequestMapping("/findAnlianBillId")
	@ResponseBody
	public Result findAnlianBillId(AnlianBillFindReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = anlianBillService.findByid(req);
		AnlianBillResp bill = (AnlianBillResp) rs.getData();
		rs = anlianService.detail(bill.getBillno());
		MemberResp resp = systemMemberService.findById(bill.getDriverid());
		bill.setDrivertel(resp.getCellPhone());
		if(!rs.getCode().equals("000000")){
			bill.setStatus(rs.getError());
		}else{
			bill.setStatus("运输中");
		}
		Result rs2 = Result.getSuccessResult();
		rs2.setData(bill);
		return rs2;
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
	/** 查看当前运单价格
	 * @throws Exception */
	@RequestMapping("/billPrice")
	@ResponseBody
	public Result billPrice(String billId) throws Exception{
		Result rs = Result.getSuccessResult();
		WaybillQueryReq br = new WaybillQueryReq();
		br.setId(billId);
		WaybillResp resp = billService.queryWayBill(br);
		rs.setData(resp);
		return rs;
	}
	
	@RequestMapping("/billAssess")
	@ResponseBody
	public Result billAssess(BillAssessReq req, HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			Users  currUser =SessionManager.getSessionMember(request);
			req.setId(UUIDUtil.getId());
			req.setCreator(currUser.getId()+"");
			req.setCreatetime(System.currentTimeMillis());
			int count = iAssessService.saveAssess(req);
			if(count == 1){
				rs.setCode("000000");
				rs.setData("保存成功");
			}else{
				rs.setCode("-1");
				rs.setError("保存失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	@RequestMapping("/queryAssess")
	@ResponseBody
	public Result queryAssess(BillAssessReq req, HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			BillAssessResp resp = iAssessService.queryAssess(req);
			if(resp != null){
				rs.setCode("000000");
				rs.setData(resp);
			}else{
				rs.setCode("-1");
				rs.setError("查询失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
}
