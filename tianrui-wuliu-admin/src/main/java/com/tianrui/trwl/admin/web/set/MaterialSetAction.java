package com.tianrui.trwl.admin.web.set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IMaterialSetService;
import com.tianrui.api.req.admin.MaterialReq;
import com.tianrui.api.req.admin.MaterielRouteReq;
import com.tianrui.api.req.front.cargoplan.RouteReq;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("material")
public class MaterialSetAction {
	
	@Autowired
	private IMaterialSetService materialSetService;
	
	/**
	 * 跳转到机车物料设置页面
	 * @author xcy
	 * @return
	 */
	@RequestMapping("/materialPage")
	public ModelAndView materialPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/linematerial/material");
		return view;
	}
	
	/**
	 * 跳转到机车路线设置页面
	 * @author xcy
	 * @return  
	 */
	@RequestMapping("/routePage")
	public ModelAndView routePage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/linematerial/line");
		return view;
	}

	/**
	 * 同步档案全部物料
	 * @author xcy
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/query")
	@ResponseBody
	public Result queryMaterial(MaterialReq req){
		 Result result = materialSetService.queryMaterial(req);
		return result;
	}
	
	/**
	 * 搜索已选物料
	 * @author xcy
	 * @return
	 */
	@RequestMapping(value="/queryee")
	@ResponseBody
	public Result querySelecedMaterial(MaterialReq req){
		Result result = materialSetService.querySelecedMaterial(req);
		return result;
	}
	
	/**
	 * 设置到已选物料
	 * @author xcy
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/selected")
	@ResponseBody
	public Result setSelectedMaterial(HttpServletRequest request,MaterialReq req){
		Users users = SessionManager.getSessionMember(request);
		//设置创建人
		req.setCreator(users.getAccount());
		Result result = materialSetService.setSelectedMaterial(req);
		return result;
	}
	
	/**
	 * 查询档案路线
	 * @author xcy
	 * @return
	 */
	@RequestMapping(value="/queryRoute")
	@ResponseBody
	public Result queryRoute(RouteReq req){
		Result result = materialSetService.queryRoute(req);
		return result;
	}
	
	/**
	 * 设置路线和物料为已选
	 * @author xcy
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/setRoute")
	@ResponseBody
	public Result setRoute(HttpServletRequest request,MaterielRouteReq req){
		Users users = SessionManager.getSessionMember(request);
		req.setCreator(users.getAccount());
		Result result = materialSetService.setRoute(req);
		return result;
	}
	
	/**
	 * 查询已选路线
	 * @author xcy
	 * @return
	 */
	@RequestMapping(value="/querySr")
	@ResponseBody
	public Result querySelecedRoute(MaterielRouteReq req){
		Result result = materialSetService.querySelecedRoute(req);
		return result;
	}
	
	/**
	 * 删除已选物料
	 * @author xcy
	 * @return
	 */
	@RequestMapping(value="/delMaterial")
	@ResponseBody
	public Result delMaterial(String id){
		Result result = materialSetService.delMaterial(id);
		return result;
	}
	
	/**
	 * 删除已选路线
	 * @author xcy
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delRoute")
	@ResponseBody
	public Result delRoute(String id){
		Result result = materialSetService.delRoute(id);
		return result;
	}
}
