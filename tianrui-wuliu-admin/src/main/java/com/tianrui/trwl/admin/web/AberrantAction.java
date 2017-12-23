package com.tianrui.trwl.admin.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/aberrant")
public class AberrantAction {
	
	@RequestMapping("/aberdeal")//浏览器访问路径  /admin/aberrant  +  /aberdeal
	public ModelAndView aberdeal(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/aberrant/aberdeal");//项目中页面放置的路径
		return view;
	}
	@RequestMapping("/abermessage")
	public ModelAndView abermessage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/aberrant/abermessage");
		return view;
	}
	@RequestMapping("/abergroup")
	public ModelAndView abergroup(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/aberrant/abergroup");
		return view;
	}

}
