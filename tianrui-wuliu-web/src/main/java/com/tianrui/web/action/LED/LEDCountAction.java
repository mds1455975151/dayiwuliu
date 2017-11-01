package com.tianrui.web.action.LED;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("LEDCount")
public class LEDCountAction {

	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("LED/LED_index");
		return view;
	}
	
	
}
