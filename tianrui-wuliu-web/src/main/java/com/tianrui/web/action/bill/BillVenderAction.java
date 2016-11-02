package com.tianrui.web.action.bill;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.intf.IMemberCapaService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.front.bill.WaybillConfirmReq;
import com.tianrui.api.req.front.bill.WaybillEditReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.req.front.bill.WaybillSaveReq;
import com.tianrui.api.req.front.capa.CapaReq;
import com.tianrui.api.resp.front.bill.BillPlanResp;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.api.resp.front.capa.MemberCapaListResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.BillService;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;


/**
  * @ClassName: BillVenderAction 
  * @Description: 车主订单控制器
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月1日 上午9:42:33 
  */
@Controller
@RequestMapping("/trwuliu/billvender")
public class BillVenderAction {
	
	public static Logger loger =LoggerFactory.getLogger(BillVenderAction.class);
	
	
	@Autowired
	BillService billService;
	@Autowired
	IVehicleDriverService vehicleDriverService;
	@Autowired
	ICargoPlanService cargoPlanService;
	@Autowired
	IMemberCapaService memberCapaService;
	
	@RequestMapping("/main")
	@AuthValidation(autyType=Constant.AUTHCHECK_VEHICLE_OWNER)
	public ModelAndView main(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/vender/bill_page");
		return view;
	}
	
	@RequestMapping("/addView")
	@AuthValidation(autyType=Constant.AUTHCHECK_USER)
	public ModelAndView addView(@RequestParam(required=true)String planId,HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/vender/bill_create");
		//计划信息
		BillPlanResp plan =billService.queryWithPlanId(planId);
		view.addObject("plan", plan);
		MemberVo vo = SessionManager.getSessionMember(request);
		view.addAllObjects(cargoPlanService.planComplete(planId, vo.getId(), 0));
		return view;
	}
	@RequestMapping("/searchCapa")
	@ResponseBody
	public Result searchCapa(CapaReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setMemberid(vo.getId());
		List<MemberCapaListResp> list = memberCapaService.createBill(req);
		rs.setData(list);
		return rs;
	}
	
	@RequestMapping("/updateView")
	public ModelAndView updateView(WaybillQueryReq req,HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/vender/bill_update");
		//计划信息
		WaybillResp bill =billService.queryWayBill(req);
		view.addObject("bill", bill);
		MemberVo vo = SessionManager.getSessionMember(request);
		view.addAllObjects(cargoPlanService.planComplete(bill.getPlanid(), vo.getId(), 0));
		return view;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(WaybillSaveReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs = billService.saveWayBill(req);
		}
		return rs;
	}
	
	
	//运单详情
	@RequestMapping("/detail")
	public ModelAndView detail(WaybillQueryReq req,HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("/bill/vender/bill_detail");
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurrId(currUser.getId());
			view.addObject("bill",billService.queryWayBill(req));
		}
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(WaybillQueryReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurrId(currUser.getId());
			req.setQueryType(2);
			rs.setData(billService.page(req));
		}
		return rs;
	}
	
	
	//修改运单
	@RequestMapping("/update")
	@ResponseBody
	public Result update(WaybillEditReq req, HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.editWayBill(req);
		}
		return rs;
	}
	
	//取消运单
	@RequestMapping("/cancle")
	@ResponseBody
	public Result cancle(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.cancleConfirm(req);
		}
		return rs;
	}
	
	//删除运单
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.verderdelete(req);
		}
		return rs;
	}
	

}
