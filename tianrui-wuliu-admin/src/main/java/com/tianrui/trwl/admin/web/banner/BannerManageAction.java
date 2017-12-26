package com.tianrui.trwl.admin.web.banner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IBannerManageService;
import com.tianrui.api.req.admin.BannerManagerReq;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("banner")
public class BannerManageAction {
	
	@Autowired
	private IBannerManageService bannerManageService;

	/**
	 * 查询banner管理图片信息
	 * @author xcy
	 * @return
	 */
	@RequestMapping("queryBanner")
	@ResponseBody
	public Result queryBanner(){
		Result result = bannerManageService.queryBanner();
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
}
