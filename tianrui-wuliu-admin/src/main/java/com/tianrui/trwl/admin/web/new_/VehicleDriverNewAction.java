package com.tianrui.trwl.admin.web.new_;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/** 后台司机认证_new*/
@Controller
@RequestMapping("admin/vehicleDriver/new")
public class VehicleDriverNewAction {

	/** 司机管理页面*/
	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminVehicle/vehicle_driver_new");
		return view;
	}
}
