package com.tianrui.trwl.admin.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ICountRouteTableService;
import com.tianrui.api.req.count.RouteTableReq;
import com.tianrui.api.resp.count.RouteTableResp;
import com.tianrui.common.vo.Result;
@Controller
@RequestMapping("countRoute")
public class CountRouteAction {
	
	@Autowired
	ICountRouteTableService countRouteTableService;

	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/system/count1/route");
		return view;
	}
	@RequestMapping("find")
	@ResponseBody
	public Result find(RouteTableReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		List<RouteTableResp> list = countRouteTableService.find(req);
		rs.setData(list);
		return rs;
	}
	@RequestMapping("update")
	@ResponseBody
	public Result update(RouteTableReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		if(!countRouteTableService.update(req)){
			rs.setCode("1");
			rs.setError("修改失败，请稍后再试");
		}
		return rs;
	}
}
