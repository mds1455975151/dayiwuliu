package com.tianrui.web.action.plan;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.cargoplan.PlanQueryReq;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

/**
 * 
  * @ClassName: PlanOwnerAction 
  * @Description: 货主操作运单
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月23日 下午4:56:25 
  *
 */
@Controller
@RequestMapping("/trwuliu/planvender")
public class PlanVenderAction {

	private static Logger logger =org.slf4j.LoggerFactory.getLogger(PlanVenderAction.class);
	@Autowired
	protected ICargoPlanService cargoPlanService;
	
	//计划拒绝操作
	@RequestMapping("refuse")
	@ResponseBody
	public Result refuse( PlanConfirmReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs= cargoPlanService.refuseConfirm(req);
		return rs;
	}
	//计划接受操作
	@RequestMapping("accept")
	@ResponseBody
	public Result accept( PlanConfirmReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs= cargoPlanService.acceptConfirm(req);
		return rs;
	}
	//计划接受操作
	@RequestMapping("delete")
	@ResponseBody
	public Result delete( PlanConfirmReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs= cargoPlanService.venderDelConfirm(req);
		return rs;
	}
	//计划详情页面
	@RequestMapping("detail")
	public ModelAndView detail(HttpServletRequest request,PlanQueryReq req) throws Exception {
		ModelAndView model=new ModelAndView("plan/vender/detail");
		MemberVo currUser =SessionManager.getSessionMember(request);
		model.addObject("currUser",currUser);
		model.addObject("plan",cargoPlanService.detail(req));
		return model;
	}
	//我承运的计划页面
	@RequestMapping("main")
	@AuthValidation(autyType=Constant.AUTHCHECK_VEHICLE_OWNER)
	public ModelAndView main(HttpServletRequest request) throws Exception {
		ModelAndView model=new ModelAndView("plan/vender/main");
		MemberVo currUser =SessionManager.getSessionMember(request);
		model.addObject("currUser",currUser);
		return model;
	}
	//发布计划页面
	@RequestMapping("page")
	@ResponseBody
	public Result main(PlanQueryReq req ,HttpServletRequest request) throws Exception {
		Result rs =Result.getSuccessResult();
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setVenderId(currUser.getId());
		rs.setData(cargoPlanService.pageForApp(req));
		return rs;
	}
}
