package com.tianrui.trwl.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @类描述：后台运价策略审核
 * @创建人：lsj
 * @创建时间：2016年8月20日下午2:22:13
 *
 * @修改人：lsj
 * @修改时间：2016年8月20日下午2:22:13
 * @修改备注：
 *
 */
@Controller
@RequestMapping("freightinfo")
public class FileFreightInfoAction {

	@RequestMapping("freightlist")
	public ModelAndView freightlist(){
		ModelAndView view =new ModelAndView();
		view.setViewName("/file/filePrice/file_priceinfo");
		return view;
	}
}
