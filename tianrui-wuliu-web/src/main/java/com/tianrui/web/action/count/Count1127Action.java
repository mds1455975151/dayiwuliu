package com.tianrui.web.action.count;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ICountAdminService;
import com.tianrui.api.intf.ICountRouteTableService;
import com.tianrui.api.req.count.CountAdminReq;
import com.tianrui.api.req.count.RouteTableReq;
import com.tianrui.api.resp.count.CountAdminResp;
import com.tianrui.api.resp.count.RouteTableResp;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("27count")
public class Count1127Action {
	
	@Autowired
	ICountAdminService countAdminService;
	@Autowired
	ICountRouteTableService countRouteTableService;
	
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView();
		view.setViewName("count/dyindex-highc");
		return view;
	}
	/** 定时请求数据查询
	 * @throws Exception */
	@RequestMapping("detail")
	@ResponseBody
	public Result detail(CountAdminReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		//1-各省车辆 2-车型分布 3-货物分布 4-车辆总数 5-线路总数
		List<CountAdminResp> list = countAdminService.find(req);
		rs.setData(list);
		return rs;
	}
	/** 定时请求地图数据查询
	 * @throws Exception */
	@RequestMapping("detailMap")
	@ResponseBody
	public Result detailMap() throws Exception{
		Result rs = Result.getSuccessResult();
		CountAdminReq req = new CountAdminReq();
		//1-各省车辆 2-车型分布 3-货物分布 4-车辆总数 5-线路总数
		req.setType("1");
		List<CountAdminResp> vehiclelist = countAdminService.find(req);
		rs.setData(vehiclelist);
		return rs;
	}
	
	/** 定时请求表格数据查询
	 * @throws Exception */
	@RequestMapping("detailRoutetable")
	@ResponseBody
	public Result detailRoutetable(RouteTableReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		List<RouteTableResp> list = countRouteTableService.find(req);
		rs.setData(list);
		return rs;
	}
}
