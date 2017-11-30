package com.tianrui.web.action.plan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IFileOrgCargoService;
import com.tianrui.api.admin.intf.IMerchantService;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.intf.IFreightService;
import com.tianrui.api.intf.IOrgSignerService;
import com.tianrui.api.intf.IRouteService;
import com.tianrui.api.intf.planGoods.IPlanGoodsService;
import com.tianrui.api.req.admin.FileOrgCargoReq;
import com.tianrui.api.req.admin.OrgSignerFindReq;
import com.tianrui.api.req.admin.merchant.MerchantReq;
import com.tianrui.api.req.front.cargoplan.PlanSaveReq;
import com.tianrui.api.req.front.cargoplan.RouteReq;
import com.tianrui.api.req.goods.PlanGoodsReq;
import com.tianrui.api.resp.admin.FileOrgCargoResp;
import com.tianrui.api.resp.admin.OrgSignerResp;
import com.tianrui.api.resp.admin.merchant.MerchantResp;
import com.tianrui.api.resp.front.cargoplan.RouteResp;
import com.tianrui.api.resp.goods.PlanGoodsResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

/***
 * 前台货源发布
 * @author jh
 *
 */
@Controller
@RequestMapping("/trwuliu/goods")
public class GoodsAction {

	private static Logger logger =org.slf4j.LoggerFactory.getLogger(GoodsAction.class);
	@Autowired
	protected ICargoPlanService cargoPlanService;
	@Autowired
	private IFileOrgCargoService fileOrgCargoService;
	@Autowired
	protected IFreightService freightService;
	@Autowired
	protected IRouteService routeService;
	@Autowired
	private IMerchantService merchantService;
	@Autowired
	IOrgSignerService  orgSignerService;
	@Autowired
	IPlanGoodsService planGoodsService;
	
	/**计划新增操作*/
	@RequestMapping("save")
	@ResponseBody
	public Result plansave( PlanSaveReq req,HttpServletRequest request) throws Exception {
		MemberVo currUser =SessionManager.getSessionMember(request);
		req.setCurruId(currUser.getId());
		Result rs= planGoodsService.savePlanGoods(req);
		return rs;
	}
	
	/**
	 * 查询发布的货源
	 */
	@RequestMapping("select")
	@ResponseBody
	public Result select(PlanGoodsReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo currUser =SessionManager.getSessionMember(request);
		PaginationVO<PlanGoodsResp> page = planGoodsService.select(req);
		rs.setData(page);
		return rs;
	}
	@RequestMapping("goodsPage")
	public ModelAndView goodsPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("plan/owner/goodsMain");
		return view;
	}
	
	/**计划新增页面*/
	@RequestMapping("goodsSavePage")
	@AuthValidation(autyType=Constant.AUTHCHECK_OWNER)
	public ModelAndView goodsSavePage(HttpServletRequest request) throws Exception {
		ModelAndView model=new ModelAndView("plan/owner/goodsSave");
		MemberVo currUser =SessionManager.getSessionMember(request);
		model.addObject("currUser",currUser);
		model.addObject("nowTime", DateUtil.getDateString());
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
		//客商信息 不分组织
		MerchantReq merreq = new MerchantReq();
		merreq.setDesc1("1");
		PaginationVO<MerchantResp> merchant = merchantService.find(merreq);
		if(merchant.getTotalInt()!=0){
			model.addObject("merchant",merchant.getList());
		}
		OrgSignerFindReq orgSigner = new OrgSignerFindReq();
		orgSigner.setOrgid(currUser.getOrgid());
		List<OrgSignerResp> signer = orgSignerService.findlist(orgSigner);
		model.addObject("signer",signer);
		return model;
	}
}
