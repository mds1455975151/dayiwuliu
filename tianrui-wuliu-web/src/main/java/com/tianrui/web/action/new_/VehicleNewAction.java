package com.tianrui.web.action.new_;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.new_.IMemberVehicleNewService;
import com.tianrui.api.intf.new_.IVehicleReg4VehicleService;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleTicketAuthReq;
import com.tianrui.api.resp.front.vehicle.VehicleRegVehicleDetailResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.SessionManager;

/***
 * 我是车辆
 * @author jh
 *
 */
@Controller
@RequestMapping("trwuliu/vehicle/new")
public class VehicleNewAction {
	
	@Autowired
	IVehicleReg4VehicleService  vehicleReg4VehicleService;

	@Autowired
	IMemberVehicleNewService memberVehicleNewService;
	
	
	/** 我的驾驶员*/
	@RequestMapping("driverpage")
	public ModelAndView page(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		//驾驶员列表
		MemberVo vo =SessionManager.getSessionMember(request);
		VechicleRegDriverQueryReq req =new VechicleRegDriverQueryReq();
		req.setCurrVId(vo.getId());
		Result rs =vehicleReg4VehicleService.driverPage(req);
		view.addObject("driverPage", rs.getData());
		
		view.setViewName("/new/vehicle/driverpage");
		return view;
	}
	
	
	/** TODO 添加驾驶员
	 * @throws Exception */
	@RequestMapping("driverAddView")
	public ModelAndView driveraddView(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/new/vehicle/driverAddView");
		return view;
	}
	//选中驾驶员
	@RequestMapping("driverCheck")
	@ResponseBody
	public Result driverCheck(HttpServletRequest request,VechicleRegDriverQueryReq req) throws Exception{
		Result rs =Result.getErrorResult();
		MemberVo vo =SessionManager.getSessionMember(request);
		if( req !=null &&  vo!=null && StringUtils.isNotBlank(req.getId())){
			req.setCurrVId(vo.getId());
			rs =vehicleReg4VehicleService.driverCheck(req);
		}
		return rs;
	}
	//删除驾驶员
	@RequestMapping("driverDel")
	@ResponseBody
	public Result driverDel(HttpServletRequest request,VechicleRegDriverQueryReq req) throws Exception{
		Result rs =Result.getErrorResult();
		MemberVo vo =SessionManager.getSessionMember(request);
		if( req !=null &&  vo!=null && StringUtils.isNotBlank(req.getId())){
			req.setCurrVId(vo.getId());
			rs =vehicleReg4VehicleService.driverDel(req);
		}
		return rs;
	}
	/** 我的车辆*/
	@RequestMapping("vehicledetail")
	public ModelAndView pageAdd(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		//车辆信息
		MemberVo vo =SessionManager.getSessionMember(request);
		VechicleRegDriverQueryReq req =new VechicleRegDriverQueryReq();
		req.setCurrVId(vo.getId());
		Result rs =vehicleReg4VehicleService.vehicleDetil(req);
		if( rs.getData() !=null){
			view.addObject("vehicle", rs.getData());
		}else{
			view.addObject("vehicle", new VehicleRegVehicleDetailResp());
		}

		view.setViewName("/new/vehicle/vehicledetail");
		return view;
	}
	
	/** TODO 开票认证页面
	 * @throws Exception */
	@RequestMapping("ticketAuthView")
	public ModelAndView ticketAuthView(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo =SessionManager.getSessionMember(request);
		view.addObject("id", vo.getId());
		view.setViewName("/new/vehicle/vehicleTicketAuth");
		return view;
	}
	
	/** TODO 开票认证动作
	 * @throws Exception */
	@RequestMapping("ticketAtuh")
	@ResponseBody
	public Result ticketAuth(HttpServletRequest request,VechicleRegVehicleTicketAuthReq req) throws Exception{
		Result rs =Result.getErrorResult();
		MemberVo vo =SessionManager.getSessionMember(request);
		if( req !=null &&  vo!=null && StringUtils.isNotBlank(req.getMotor()) && StringUtils.isNotBlank(req.getQuality())){
			req.setCurrVId(vo.getId());
			rs =vehicleReg4VehicleService.vehicleAuthTicket(req);
		}
		return rs;
	}
}
