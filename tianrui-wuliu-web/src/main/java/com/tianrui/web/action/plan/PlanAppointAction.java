package com.tianrui.web.action.plan;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.req.front.cargoplan.PlanAppointReq;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.cargoplan.PlanQueryReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.CargoPlanService;
import com.tianrui.service.impl.MemberOwnerService;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

/**
 * 
 * @author zhanggaohao
 * @createtime 2016年9月1日 下午5:37:15
 * @classname PlanAppointAction.java
 */
@Controller
@RequestMapping("/trwuliu/planAppoint")
public class PlanAppointAction {
	
	private static Logger logger = LoggerFactory.getLogger(PlanAppointAction.class);
	
	@Autowired
	private CargoPlanService cargoPlanService;
	
	@Autowired
	private MemberOwnerService memberOwnerService;
	
	@RequestMapping("main")
	@AuthValidation(autyType=Constant.AUTHCHECK_VEHICLE_OWNER)
	public ModelAndView main(){
		ModelAndView model = new ModelAndView("plan/appoint/main");
		return model;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(PlanQueryReq req, HttpServletRequest request) throws Exception{
		Result result = Result.getSuccessResult();
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setOwnerId(currUser.getId());
		req.setIsAppoint("1");
		result.setData(cargoPlanService.pageForApp(req));
		return result;
	}
	
	@RequestMapping("cancle")
	@ResponseBody
	public Result cancle(PlanConfirmReq req, HttpServletRequest request) throws Exception{
		MemberVo currUser = SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs= cargoPlanService.cancleConfirm(req);
		return rs;
	}
	
	//删除委派计划
	@RequestMapping("plandel")
	@ResponseBody
	public Result plandel(PlanConfirmReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs= cargoPlanService.venderDelConfirm(req);
		return rs;
	}
	
	//委派计划发布页面
	@RequestMapping("initAppointPage")
	public ModelAndView initAppointPage(PlanQueryReq req, HttpServletRequest request) throws Exception {
		ModelAndView model = new ModelAndView("plan/appoint/appoint");
		model.addObject("plan", cargoPlanService.detail(req));
		MemberOwnerReq ownerReq = new MemberOwnerReq();
		ownerReq.setMemberId(SessionManager.getSessionMember(request).getId());
		ownerReq.setStatus("1");
		model.addObject("venderList", memberOwnerService.queryMyVehiOwnerByCondition(ownerReq));
		model.addObject("now", new Date());
		model.addObject("operate", "add");
		return model;
	}
		
	@RequestMapping("addAppointPlan")
	@ResponseBody
	public Result addAppointPlan(PlanAppointReq req, HttpServletRequest request) throws Exception{
		req.setMemberVo(SessionManager.getSessionMember(request));
		return cargoPlanService.addAppointPlan(req);
	}
	
	//委派计划修改页面
	@RequestMapping("editView")
	public ModelAndView planUpdateView(PlanQueryReq req, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("plan/appoint/appoint");
		try {
			model.addObject("plan", cargoPlanService.detail(req));
			MemberOwnerReq ownerReq = new MemberOwnerReq();
			ownerReq.setMemberId(SessionManager.getSessionMember(request).getId());
			ownerReq.setStatus("1");
			model.addObject("venderList", memberOwnerService.queryMyVehiOwnerByCondition(ownerReq));
			model.addObject("now", new Date());
			model.addObject("operate", "edit");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return model;
	}

	@RequestMapping("editAppointPlan")
	@ResponseBody
	public Result editAppointPlan(PlanAppointReq req, HttpServletRequest request) throws Exception{
		req.setMemberVo(SessionManager.getSessionMember(request));
		return cargoPlanService.editAppointPlan(req);
	}
	
	//委派计划详情
	@RequestMapping("detail")
	public ModelAndView detail(HttpServletRequest request,PlanQueryReq req) throws Exception {
		ModelAndView model=new ModelAndView("plan/appoint/detail");
		MemberVo currUser = SessionManager.getSessionMember(request);
		model.addObject("currUser",currUser);
		model.addObject("plan",cargoPlanService.detail(req));
		return model;
	}
	
}