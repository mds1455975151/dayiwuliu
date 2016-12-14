package com.tianrui.trwl.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 图片查看器
 * @author jh
 *
 */
@Controller
@RequestMapping("imageView")
public class ImageViewAction {

	@RequestMapping("index")
	public ModelAndView index(String imageUrl){
		ModelAndView view = new ModelAndView();
		view.addObject("imageUrl", imageUrl);
		view.setViewName("img");
		return view;
	}
}
