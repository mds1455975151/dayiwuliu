package com.tianrui.web.action.new_;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.new_.IMemberVehicleNewService;
import com.tianrui.api.intf.new_.IVehicleReg4VehicleService;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverQueryReq;
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
	
	/** TODO 添加驾驶员
	 * @throws Exception */
	@RequestMapping("driverAddView")
	public ModelAndView driveraddView(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		
		view.setViewName("/new/vehicle/driverAddView");
		return view;
	}
	//选中驾驶员
	//删除驾驶员
	

}
