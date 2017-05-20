package com.tianrui.web.action.version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IAppVersionService;
import com.tianrui.api.resp.front.version.AppVersionResp;

@Controller
@RequestMapping("view/version")
public class VersionAction {
	@Autowired
	IAppVersionService appVersionService;
	@RequestMapping("load")
	public ModelAndView load(String id) throws Exception{
		ModelAndView view = new ModelAndView();
		AppVersionResp resp = appVersionService.selectByid(id);
		view.setViewName("redirect:"+resp.getVersionurl());
		return view;
	}
}
