package com.tianrui.trwl.admin.web.banner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IBannerManageService;
import com.tianrui.api.req.admin.BannerManagerReq;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("/admin/banner")
public class BannerManageAction {
	
	@Autowired
	private IBannerManageService bannerManageService;

	/**
	 * 跳转到AppBanner管理主页面
	 * @author xcy
	 * @return
	 */
	@RequestMapping("/bannerpage")
	public ModelAndView bannerPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/appbanner/appbanner");		
		return view;
	}
	
	/**
	 * 跳转到AppBanner管理新增页面
	 * @author xcy
	 * @return
	 */
	@RequestMapping("/bannerAddPage")
	public ModelAndView bannerAddPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/appbanner/uploading");		
		return view;
	}
	
	/**
	 * 跳转到AppBanner管理图片待发布页面
	 * @author xcy
	 * @return
	 */
	@RequestMapping("/bannerPushPage")
	public ModelAndView bannerPushPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/appbanner/appbanner_push");		
		return view;
	}
	
	/**
	 * 查询banner管理图片信息
	 * @author xcy
	 * @return
	 */
	@RequestMapping("queryBanner")
	@ResponseBody
	public Result queryBanner(BannerManagerReq bannerReq){
		Result result = bannerManageService.queryBanner(bannerReq);
		return result;
	}
	
	
	/**
	 * banner管理图片信息新增
	 * @author xcy
	 * @param bannerReq
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Result bannerAdd(HttpServletRequest request,BannerManagerReq bannerReq){
		Result result = Result.getSuccessResult();
		Users users = SessionManager.getSessionMember(request);
		bannerReq.setCreator(users.getAccount());
		int count = bannerManageService.bannerAdd(bannerReq);
		if(count>0){
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			return result;
		}
		result.setError("新增失败！");
		return result;
	}
	
	/**
	 * 删除banner管理图片信息
	 * @author xcy
	 * @param bannerReq
	 * @return
	 */
	@RequestMapping("delBanner")
	@ResponseBody
	public Result delBanner(HttpServletRequest request,BannerManagerReq bannerReq){
		Users users = SessionManager.getSessionMember(request);
		bannerReq.setModifier(users.getAccount());
		Result result = bannerManageService.delBanner(bannerReq);
		return result;
	}
	
	/**
	 * 发布banner管理图片信息
	 * @author xcy
	 * @param bannerReq
	 * @return
	 */
	@RequestMapping("pushBanner")
	@ResponseBody
	public Result pushBnner(HttpServletRequest request,BannerManagerReq bannerReq){
		Users users = SessionManager.getSessionMember(request);
		bannerReq.setModifier(users.getAccount());
		Result result = bannerManageService.pushBnner(bannerReq);
		return result;
	}
	
	/**
	 * 启用或禁用banner管理图片信息
	 * @author xcy
	 * @return
	 */
	@RequestMapping("enableOrDisable")
	@ResponseBody
	public Result enableOrDisable(HttpServletRequest request,BannerManagerReq bannerReq){
		Users users = SessionManager.getSessionMember(request);
		bannerReq.setModifier(users.getAccount());
		Result result = bannerManageService.enableOrDisable(bannerReq);
		return result;
	}
	
	/**
	 * 查询待发布的banner图片
	 * @author xcy
	 * @param request
	 * @return
	 */
	@RequestMapping("queryPushBanner")
	@ResponseBody
	public Result queryPushBanner(HttpServletRequest request,BannerManagerReq bannerReq){
		Result result = bannerManageService.queryPushBanner(bannerReq);
		return result;
	}
}
