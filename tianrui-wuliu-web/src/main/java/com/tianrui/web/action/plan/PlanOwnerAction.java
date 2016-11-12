package com.tianrui.web.action.plan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IFileOrgCargoService;
import com.tianrui.api.admin.intf.IFreightInfoService;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.intf.IFreightService;
import com.tianrui.api.intf.IMemberOwnerService;
import com.tianrui.api.intf.IRouteService;
import com.tianrui.api.req.admin.FileOrgCargoReq;
import com.tianrui.api.req.front.cargoplan.FreightReq;
import com.tianrui.api.req.front.cargoplan.PlanConfirmReq;
import com.tianrui.api.req.front.cargoplan.PlanEditReq;
import com.tianrui.api.req.front.cargoplan.PlanQueryReq;
import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.req.front.cargoplan.RouteReq;
import com.tianrui.api.req.front.vehicle.MemberOwnerReq;
import com.tianrui.api.resp.admin.FileOrgCargoResp;
import com.tianrui.api.resp.front.cargoplan.FreightResp;
import com.tianrui.api.resp.front.cargoplan.PlanResp;
import com.tianrui.api.resp.front.cargoplan.RouteResp;
import com.tianrui.api.resp.front.vehicle.MemberOwnerResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
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
@RequestMapping("/trwuliu/planowner")
public class PlanOwnerAction {

	private static Logger logger =org.slf4j.LoggerFactory.getLogger(PlanOwnerAction.class);
	@Autowired
	protected ICargoPlanService cargoPlanService;
	@Autowired
	private IMemberOwnerService memberOwnerService;
	@Autowired
	private IFileOrgCargoService fileOrgCargoService;
	@Autowired
	protected IFreightService freightService;
	@Autowired
	protected IRouteService routeService;
	@Autowired
	private IFreightInfoService freightInfoService;

	//计划修改页面
	@RequestMapping("editView")
	public ModelAndView planUpdateView( String id,HttpServletRequest request) {
		ModelAndView model =new ModelAndView("plan/owner/edit");
		try {
			MemberVo currUser =SessionManager.getSessionMember(request);
			
			PlanQueryReq query =new PlanQueryReq();
			query.setId(id);
			PlanResp plan = cargoPlanService.detail(query);
			model.addObject("plan",plan);
			
			MemberOwnerReq req = new MemberOwnerReq();
			req.setMemberId(currUser.getId());
			List<MemberOwnerResp> ownerResp = memberOwnerService.queryMyVehiOwnerByCondition(req);
			model.addObject("ownerResp",ownerResp);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return model;
	}

	//计划修改操作
	@RequestMapping("update")
	@ResponseBody
	public Result planUpdate( PlanEditReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs= cargoPlanService.editPlan(req);
		return rs;
	}
	
	//计划收回操作
	@RequestMapping("cancle")
	@ResponseBody
	public Result planCancle( PlanConfirmReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs= cargoPlanService.cancleConfirm(req);
		return rs;
	}
	//计划完成操作
	@RequestMapping("complete")
	@ResponseBody
	public Result complete( PlanConfirmReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs= cargoPlanService.completePlan(req);
		return rs;
	}
	
	//计划删除操作
	@RequestMapping("plandel")
	@ResponseBody
	public Result plandel( PlanConfirmReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		
		Result rs= cargoPlanService.ownerDeleteConfirm(req);
		
		return rs;
	}
	
	//计划新增操作
	@RequestMapping("save")
	@ResponseBody
	public Result plansave( PlanSaveReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs= cargoPlanService.savePlan(req);
		return rs;
	}
	
	
	//计划新增操作
	@RequestMapping("create")
	@AuthValidation(autyType=Constant.AUTHCHECK_OWNER)
	public ModelAndView plansaveView(HttpServletRequest request) throws Exception {
		ModelAndView model=new ModelAndView("plan/owner/create");
		MemberVo currUser =SessionManager.getSessionMember(request);
		model.addObject("currUser",currUser);
		
		MemberOwnerReq req = new MemberOwnerReq();
		req.setMemberId(currUser.getId());
		req.setStatus("1");//0-待确认，1-已同意，2-已拒绝
		List<MemberOwnerResp> ownerResp = memberOwnerService.queryMyVehiOwnerByCondition(req);
		model.addObject("ownerResp",ownerResp);
		//货物信息
		FileOrgCargoReq fr = new FileOrgCargoReq();
		fr.setOrgId(currUser.getOrgid());
		fr.setState("1");
		List<FileOrgCargoResp> cargoList =fileOrgCargoService.queryMyCargoInfo(fr);
		model.addObject("cargoList",cargoList);
		//路径信息
		RouteReq rreq = new RouteReq();
		rreq.setStatus("1");
		rreq.setOrganizationid(currUser.getOrgid());
		List<RouteResp> routeList = routeService.findRouteByEntity(rreq);
		model.addObject("routeList",routeList);
		return model;
	}
	
	//查询运价策略
	@RequestMapping("queryFreights")
	@ResponseBody
	public List<FreightResp> queryFreightList(String cargoId,String routeId,HttpServletRequest request){
		List<FreightResp> list =new ArrayList<FreightResp>();
		
		FreightReq req =new FreightReq();
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setOrganizationid(currUser.getOrgid());
		try {
			req.setCargoid(cargoId);
			req.setcStatus("1");
			req.setRouteid(routeId);
			req.setrStatus("1");
			req.setStatus("0");
			req.setAuditstatus("1");//审核通过
			list = freightService.findByEntity(req);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}
	/** 查询本条运价策略当前状态
	 * @throws Exception */
	@RequestMapping("queryFreightInfo")
	@ResponseBody
	public Result queryFreightInfo(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		//获取当前时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = sdf.format(date);
		Date te = sdf.parse(dateStr);
		rs = freightInfoService.findFreightInfo(id, te);
		return rs;
	}
	//查询运价策略是否可用
	@RequestMapping("/queryFreightById")
	@ResponseBody
	public Result queryFreightById(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		FreightReq req =new FreightReq();
		req.setId(id);
		List<FreightResp> list = freightService.findByEntity(req);
		FreightResp resp = list.get(0);
		if(!"1".equals(resp.getrStatus())){
			rs.setCode("1");
			rs.setError("路径已被禁用");
		}
		if(!"0".equals(resp.getStatus())){
			rs.setCode("1");
			rs.setError("运价策略已被禁用");
		}
		if(!"1".equals(resp.getcStatus())){
			rs.setCode("1");
			rs.setError("货物已被禁用");
		}
		return rs;
	}
	//查询运价策略
	@RequestMapping("queryCargo")
	@ResponseBody
	public Result queryCargo(String cargoId,HttpServletRequest request){
		Result rs=null;
		try {
			rs = fileOrgCargoService.findOne(cargoId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return rs;
	}
	//查询运价策略
	@RequestMapping("queryRoute")
	@ResponseBody
	public Result queryRoute(String routeId,HttpServletRequest request){
		Result rs =Result.getSuccessResult();
		try {
			RouteResp route =routeService.findRouteById(routeId);
			rs.setData(route);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}
	
	//我发布的计划页面
	@RequestMapping("main")
	@AuthValidation(autyType=Constant.AUTHCHECK_OWNER)
	public ModelAndView main(String pageNo, HttpServletRequest request) throws Exception {
		ModelAndView model=new ModelAndView("plan/owner/main");
		MemberVo currUser =SessionManager.getSessionMember(request);
		model.addObject("currUser",currUser);
		model.addObject("pageNo",pageNo);
		return model;
	}
	//发布计划页面
	@RequestMapping("detail")
	public ModelAndView detail(HttpServletRequest request,PlanQueryReq req) throws Exception {
		ModelAndView model=new ModelAndView("plan/owner/detail");
		MemberVo currUser =SessionManager.getSessionMember(request);
		model.addObject("currUser",currUser);
		model.addObject("pageNo", req.getPageNo());
		model.addObject("plan",cargoPlanService.detail(req));
		return model;
	}
	//发布计划页面
	@RequestMapping("page")
	@ResponseBody
	public Result main(PlanQueryReq req ,HttpServletRequest request) throws Exception {
		Result rs =Result.getSuccessResult();
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setOwnerId(currUser.getId());
		rs.setData(cargoPlanService.pageForApp(req));
		return rs;
	}
	//计划进度详情页面
	@RequestMapping("progress")
	public ModelAndView progreass(HttpServletRequest request,PlanQueryReq req) throws Exception {
		ModelAndView model=new ModelAndView("plan/owner/progress");
		MemberVo currUser =SessionManager.getSessionMember(request);
		model.addObject("currUser",currUser);
		PlanResp planResp = cargoPlanService.detail(req);
		model.addObject("plan",planResp);
		return model;
	}
}
