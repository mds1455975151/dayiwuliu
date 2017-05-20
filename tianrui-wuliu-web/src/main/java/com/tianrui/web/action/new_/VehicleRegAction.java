package com.tianrui.web.action.new_;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.new_.IVehicleRegService;
import com.tianrui.api.req.front.vehicle.VechicleRegQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegStep1Req;
import com.tianrui.api.req.front.vehicle.VechicleRegStep2Req;
import com.tianrui.api.req.front.vehicle.VechicleRegStep3Req;
import com.tianrui.api.req.new_.VehiclReqStepReq;
import com.tianrui.common.vo.Result;

/**
 * 车主注册
 * @author jh
 *
 */
@Controller
@RequestMapping("/common/vehicleReg")
public class VehicleRegAction {

	@Autowired
	IVehicleRegService vehicleRegService;
	
	/**
	 * 主页面跳转
	 * @param id
	 * @return
	 */
	@RequestMapping("/regStep1")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/new/vehicleReg/regStep1");
		return view;
	}
	
	@RequestMapping("/regStep2")
	public ModelAndView submitStep1(VechicleRegStep1Req  req){
		ModelAndView view = new ModelAndView();
		
		vehicleRegService.vehicleRegStep1(req);
		
		view.setViewName("/new/vehicleReg/regStep2");
		return view;
	}
	
	@RequestMapping("/regStep3")
	public ModelAndView submitStep2(VechicleRegStep2Req  req){
		ModelAndView view = new ModelAndView();
		
		//vehicleRegService.vehicleRegStep2(req);
		
		view.setViewName("/new/vehicleReg/regStep3");
		return view;
	}
	
	
	
	
	@RequestMapping("/submitStep3")
	public ModelAndView submitStep3(VechicleRegStep3Req  req){
		ModelAndView view = new ModelAndView();
		
		//vehicleRegService.vehicleRegStep3(req);
		view.setViewName("/vehicleReg/success");
		return view;
	}

	@RequestMapping("saveVehicleRegStep")
	@ResponseBody
	public Result saveVehicleRegStep(VehiclReqStepReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleRegService.saveVehicleRegStep(req);
		return rs;
	}
	
	@RequestMapping(value="/checkVehicleNo",method=RequestMethod.POST)
	@ResponseBody
	public Result checkVehicleNo(VechicleRegQueryReq req){
		
		Result rs =vehicleRegService.checkVehicleExist(req);
		return rs ;
	}
	
	
	@RequestMapping(value="/getTempVehicleNo",method=RequestMethod.POST)
	@ResponseBody
	public Result getTempVehicleNo(VechicleRegQueryReq req){
		
		Result rs =vehicleRegService.getTempVechicleNo(req);
		return rs ;
	}
}
