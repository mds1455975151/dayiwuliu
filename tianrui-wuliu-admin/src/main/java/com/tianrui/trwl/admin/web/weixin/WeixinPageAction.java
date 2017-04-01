package com.tianrui.trwl.admin.web.weixin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("weixin/page")
public class WeixinPageAction {

	@RequestMapping("driverdetail")
	public ModelAndView driver(String id){
		ModelAndView view = new ModelAndView();
		view.setViewName("/weixin/driver_detail");
		return view;
	}
	
	@RequestMapping("memberdetail")
	public ModelAndView member(String id){
		ModelAndView view = new ModelAndView();
		view.setViewName("/weixin/member_detail");
		return view;
	}
	
	@RequestMapping("vehicledetail")
	public ModelAndView vehicle(String id){
		ModelAndView view = new ModelAndView();
		view.setViewName("/weixin/vehicle_detail");
		return view;
	}
	
	@RequestMapping("kaipiaodetail")
	public ModelAndView kaipiao(String id){
		ModelAndView view = new ModelAndView();
		view.setViewName("/weixin/kaipiao_detail");
		return view;
	}
}
