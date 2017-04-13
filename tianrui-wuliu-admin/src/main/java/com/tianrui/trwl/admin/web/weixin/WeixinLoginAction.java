package com.tianrui.trwl.admin.web.weixin;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.admin.intf.IWXUserService;
import com.tianrui.api.req.admin.weixin.WeixinUserReq;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.CommonUtil;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("/weixin/login")
public class WeixinLoginAction {

	@Autowired
	IWXUserService wxUserService;
	
	@RequestMapping("/MP_verify_yjjzuHc971cUloGB.txt")
	@ResponseBody
	public String readText(){
		return "yjjzuHc971cUloGB";
	}
	
	/**微信登录页面跳转*/
	@RequestMapping("/loginPage")
	public ModelAndView loginPage(WeixinUserReq req){
		ModelAndView view = new ModelAndView(); 
		view.addObject("openid", req.getOpenid());
		view.addObject("state",req.getState());
		view.setViewName("/weixin/login_weixin");
		return view;
	}
	
	/** 登录*/
	@RequestMapping("loginin")
	@ResponseBody
	public Result loginin(HttpServletRequest request,WeixinUserReq req){
		Result rs = wxUserService.login(req);
		if(StringUtils.equals("000000", rs.getCode())){
			Users user = (Users) rs.getData();
			SessionManager.setSessionMember(request, user);
		}
		return rs;
	}
	@RequestMapping("loginSuccess")
	public ModelAndView loginSuccess(String state,String openid){
		ModelAndView view = new ModelAndView();
		switch (state) {
		case "member":
			view.setViewName("/weixin/member");
			break;
		case "vehicle":
			view.setViewName("/weixin/vehicle");
			break;
		case "kaipiao":
			view.setViewName("/weixin/kaipiao");
			break;
		case "driver":
			view.setViewName("/weixin/driver");
			break;
		case "mation":
			view.setViewName("/weixin/mation");
			break;
		default:
			view.addObject("state", state);
			view.addObject("openid", openid);
			view.setViewName("redirect:/weixin/login/loginPage");
			break;
		}
		return view;
	}
	@RequestMapping("yiShenPage")
	public ModelAndView yiShenPage(String state,String searchKey){
		ModelAndView view = new ModelAndView();
		switch (state) {
		case "member":
			view.setViewName("/weixin/member");
			break;
		case "vehicle":
			view.setViewName("/weixin/vehicle");
			break;
		case "kaipiao":
			view.setViewName("/weixin/kaipiao");
			break;
		case "driver":
			view.setViewName("/weixin/driver");
			break;
		case "member1":
			view.setViewName("/weixin/member1");
			break;
		case "vehicle1":
			view.setViewName("/weixin/vehicle1");
			break;
		case "kaipiao1":
			view.setViewName("/weixin/kaipiao1");
			break;
		case "driver1":
			view.setViewName("/weixin/driver1");
			break;
		default:
			view.setViewName("redirect:/weixin/login/loginPage");
			break;
		}
		view.addObject("searchKey", searchKey);
		return view;
	}
	
	/** 微信登录*/
	@RequestMapping("wxLogin")
	public ModelAndView wxLogin(HttpServletRequest request,String code,String state){
		ModelAndView view = new ModelAndView();
		String openid = getopenId(code);
		
		view.addObject("state", state);
		view.addObject("openid", openid);
		
		WeixinUserReq req = new WeixinUserReq();
		req.setOpenid(openid);
		Result rs = wxUserService.wxLogin(req);
		if(StringUtils.equals("000000", rs.getCode())){
			Users user = (Users) rs.getData();
			SessionManager.setSessionMember(request, user);
			switch (state) {
			case "member":
				view.setViewName("/weixin/member");
				break;
			case "vehicle":
				view.setViewName("/weixin/vehicle");
				break;
			case "kaipiao":
				view.setViewName("/weixin/kaipiao");
				break;
			case "driver":
				view.setViewName("/weixin/driver");
				break;
			case "mation":
				view.setViewName("/weixin/mation");
				break;
			default:
				view.setViewName("redirect:/weixin/login/loginPage");
				break;
			}
		}else{
			view.setViewName("redirect:/weixin/login/loginPage");
		}
		return view;
	}
	
	/** 网页授权 获取openid*/
	public String getopenId(String code){
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		JSONObject js = CommonUtil.httpsRequest(url.replace("APPID", Constant.WEIXIN_APPID).replace("SECRET", Constant.WEIXIN_SECRET).replace("CODE", code), "POST", null);
		String openid=null;
		try {
			openid = js.getString("openid");
		} catch (Exception e) {
			openid = "";
		}
		return openid;
	}
}
