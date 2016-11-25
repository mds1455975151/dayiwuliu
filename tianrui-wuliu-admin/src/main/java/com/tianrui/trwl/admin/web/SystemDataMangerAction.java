package com.tianrui.trwl.admin.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.resp.count.CountAdminResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.CountAdminService;
@Controller
@RequestMapping("systemDataManger")
public class SystemDataMangerAction {
	
	@Autowired
	CountAdminService countAdminService;

	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/system/count/data_mgr");
		return view;
	}
	
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(String type) throws Exception{
		Result rs = Result.getSuccessResult();
		if(StringUtils.isBlank(type)){type ="1";}
		List<CountAdminResp> list = countAdminService.findWithType(type);
		rs.setData(list);
		return rs;
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public Result update(String id,String value) throws Exception{
		Result rs = Result.getErrorResult();
		if(StringUtils.isNotBlank(id)){
			rs =countAdminService.updateWithId(id, value);
		}
		return rs;
	}
}
