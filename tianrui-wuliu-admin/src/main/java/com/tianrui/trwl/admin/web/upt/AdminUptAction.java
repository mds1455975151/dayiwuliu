package com.tianrui.trwl.admin.web.upt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("adminUpt")
public class AdminUptAction {

	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminMember/upt/upt_page");;
		return view;
	}
}
