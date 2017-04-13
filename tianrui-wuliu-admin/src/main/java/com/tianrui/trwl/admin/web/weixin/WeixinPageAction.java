package com.tianrui.trwl.admin.web.weixin;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IMyVehicleService;
import com.tianrui.api.admin.intf.IWXUserService;
import com.tianrui.api.intf.ISystemMemberInfoRecordService;
import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.intf.IVehicleTicketService;
import com.tianrui.api.req.admin.weixin.WeixinUserReq;
import com.tianrui.api.req.front.vehicle.TicketFindReq;
import com.tianrui.api.resp.admin.MyVehicleResp;
import com.tianrui.api.resp.front.member.MemberInfoRecordResp;
import com.tianrui.api.resp.front.vehicle.VehicleTicketResp;
import com.tianrui.common.vo.Result;
@Controller
@RequestMapping("weixin/page")
public class WeixinPageAction {

	@Autowired
	protected ISystemMemberInfoRecordService systemMemberInfoRecordService;
	@Autowired
	private IMyVehicleService vehicleService;
	@Autowired
	private IVehicleTicketService vehicleTicketService;
	@Autowired
	ISystemMemberInfoService systemMemberInfoService;
	@Autowired
	IWXUserService wxUserService;
	
	@RequestMapping("excitLogin")
	@ResponseBody
	public Result excitLogin(WeixinUserReq req){
		Result rs = Result.getSuccessResult();
		rs = wxUserService.exciWXtLogin(req);
		return rs;
	}
	
	@RequestMapping("driverdetail")
	public ModelAndView driver(String id,String type) throws Exception{
		MemberInfoRecordResp resp = null;
		if(StringUtils.equals("1", type)){
			resp = systemMemberInfoService.selectMemberInfo(id);
		}else if(StringUtils.equals("2", type)){
			resp = systemMemberInfoRecordService.findByMemberId(id);
		}else{
			resp = systemMemberInfoRecordService.findByMemberRecordId(id);
		}
		ModelAndView view = new ModelAndView();
		view.addObject("memberInfo", resp);
		view.setViewName("/weixin/driver_detail");
		return view;
	}
	
	
	@RequestMapping("memberdetail")
	public ModelAndView member(String id,String type) throws Exception{
		MemberInfoRecordResp resp = null;
		if(StringUtils.equals("1", type)){
			resp = systemMemberInfoService.selectMemberInfo(id);
		}else if(StringUtils.equals("2", type)){
			resp = systemMemberInfoRecordService.findByMemberId(id);
		}else{  
			resp = systemMemberInfoRecordService.findByMemberRecordId(id);
		}
		ModelAndView view = new ModelAndView();
		view.addObject("memberInfo", resp);
		view.setViewName("/weixin/member_detail");

		return view;
	}
	
	@RequestMapping("vehicledetail")
	public ModelAndView vehicle(String id) throws Exception{
		ModelAndView view = new ModelAndView();
		MyVehicleResp veh = vehicleService.findById(id);
		view.addObject("Vehicle", veh);
		view.setViewName("/weixin/vehicle_detail");
		return view;
	}
	
	@RequestMapping("kaipiaodetail")
	public ModelAndView kaipiao(String id) throws Exception{
		ModelAndView view = new ModelAndView();
		TicketFindReq req = new TicketFindReq();
		VehicleTicketResp resp = vehicleTicketService.findById(id);
		view.addObject("tick", resp);
		view.setViewName("/weixin/kaipiao_detail");
		return view;
	}
}
