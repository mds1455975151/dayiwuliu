package com.tianrui.web.action.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IVehicleTicketService;
import com.tianrui.api.req.front.vehicle.TicketFindReq;
import com.tianrui.api.req.front.vehicle.VehicleTicketReq;
import com.tianrui.common.vo.Result;

/**
 * 请求开票认证
 * @author jh
 *
 */
@Controller
@RequestMapping("/trwuliu/Member/vehicleticket")
public class VehicleTicketAction {

	@Autowired
	IVehicleTicketService vehicleTicketService;
	
	/** 开票认证页面跳转*/
	@RequestMapping("/kaipiaoPage")
	public ModelAndView kaipiaoPage(String id){
		ModelAndView view = new ModelAndView();
		view.addObject("vehicleid", id);
		view.setViewName("/member/vehicle/kaipiao");
		return view;
	}
	
	/** 开票认证页面跳转
	 * @throws Exception */
	@RequestMapping("/kaipiaoUpdate")
	public ModelAndView kaipiaoUpdate(String vehicleid) throws Exception{
		ModelAndView view = new ModelAndView();
		TicketFindReq req = new TicketFindReq();
		req.setId(vehicleid);
		Result rs = vehicleTicketService.findByVehicleId(req);
		view.addObject("ticket", rs.getData());
		view.setViewName("/member/vehicle/kaipiaoupt");
		return view;
	}
	
	/** 请求开票认证
	 * @throws Exception */
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public Result add(VehicleTicketReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleTicketService.insert(req);
		return rs;
	}
	
	/** 请求开票认证修改
	 * @throws Exception */
	@RequestMapping(value="upt",method = RequestMethod.POST)
	@ResponseBody
	public Result upt(VehicleTicketReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleTicketService.upt(req);
		return rs;
	}
	
}
