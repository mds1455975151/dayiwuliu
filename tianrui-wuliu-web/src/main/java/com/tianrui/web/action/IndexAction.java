package com.tianrui.web.action;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexAction {
	
	public static Logger loger=org.slf4j.LoggerFactory.getLogger(IndexAction.class);
	
	//成为货主申请
	@RequestMapping("")
	public ModelAndView index() throws IOException{
		ModelAndView model =new ModelAndView("/index");
		return model;
	}
	
	@RequestMapping("/zs")
	public ModelAndView zs() throws IOException{
		ModelAndView model =new ModelAndView("/count/zs");
		return model;
	}

}
