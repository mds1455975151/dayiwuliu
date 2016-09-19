package com.tianrui.web.action.plan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ICargoPlanTemplateService;
import com.tianrui.api.intf.IMemberOwnerService;
import com.tianrui.api.req.front.cargoplan.PlanTemplateReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.api.resp.front.vehicle.MemberOwnerResp;
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
@RequestMapping("/trwuliu/plantemplate")
public class PlanTemplateAction {

	private static Logger logger =org.slf4j.LoggerFactory.getLogger(PlanTemplateAction.class);
	@Autowired
	protected ICargoPlanTemplateService cargoPlanTemplateService;
	@Autowired
	private IMemberOwnerService memberOwnerService;
	
	//主界面
	@RequestMapping("main")
	@AuthValidation(autyType=Constant.AUTHCHECK_OWNER)
	public ModelAndView main(HttpServletRequest request) throws Exception {
		ModelAndView model=new ModelAndView("plan/template/main");
		MemberVo currUser =SessionManager.getSessionMember(request);
		model.addObject("currUser",currUser);
		return model;
	}
	//重新发布页面
	@RequestMapping("publishView")
	public ModelAndView publishView(HttpServletRequest request, PlanTemplateReq req) throws Exception {
		ModelAndView model=new ModelAndView("plan/template/publish");
		MemberVo currUser =SessionManager.getSessionMember(request);
		model.addObject("currUser",currUser);
		model.addObject("plan",cargoPlanTemplateService.findOne(req));
		
		MemberOwnerReq req2 = new MemberOwnerReq();
		req2.setMemberId(currUser.getId());
		List<MemberOwnerResp> ownerResp = memberOwnerService.queryMyVehiOwnerByCondition(req2);
		model.addObject("ownerResp",ownerResp);
		return model;
	}
	//详情
	@RequestMapping("detail")
	public ModelAndView detail(HttpServletRequest request, PlanTemplateReq req) throws Exception {
		ModelAndView model=new ModelAndView("plan/template/detail");
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		model.addObject("plan",cargoPlanTemplateService.findOne(req));
		return model;
	}
	//查询所有数据
	@RequestMapping("page")
	@ResponseBody
	public Result main(PlanTemplateReq req,HttpServletRequest request) throws Exception {
		Result rs =Result.getSuccessResult();
		MemberVo currUser =SessionManager.getSessionMember(request);
		if( req ==null ){
			req =new PlanTemplateReq();
		}
		req.setCurruId(currUser.getId());
		rs.setData(cargoPlanTemplateService.findPlanTemplat(req));
		return rs;
	}
	//删除模版
	@RequestMapping("del")
	@ResponseBody
	public Result del( PlanTemplateReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs=cargoPlanTemplateService.delTemplat(req);
		return rs;
	}
}
