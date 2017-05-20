package com.tianrui.web.action.new_;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IDataService;
import com.tianrui.api.intf.new_.IVehicleRegService;
import com.tianrui.api.req.data.WebDictReq;
import com.tianrui.api.req.front.vehicle.VechicleRegQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegStep1Req;
import com.tianrui.api.req.front.vehicle.VechicleRegStep2Req;
import com.tianrui.api.req.front.vehicle.VechicleRegStep3Req;
import com.tianrui.api.req.new_.VehiclReqStepReq;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.SessionManager;

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
	@Autowired
	IDataService dataService;
	/**
	 * 主页面跳转
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/regStep1")
	public ModelAndView main() throws Exception{
		ModelAndView view = new ModelAndView();
		WebDictReq req = new WebDictReq();
		req.setType("vehicle");
		view.addObject("vt", dataService.find(req));
		view.setViewName("/new/vehicleReg/regStep1");
		return view;
	}
	
	/** 添加车辆*/
	@RequestMapping("saveVehicleRegStep")
	@ResponseBody
	public Result saveVehicleRegStep(VehiclReqStepReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreaterId(vo.getId());
		rs = vehicleRegService.saveVehicleRegStep(req);
		return rs;
	}
	/** 验证车牌号
	 * @throws Exception */
	@RequestMapping(value="/checkVehicleNo",method=RequestMethod.POST)
	@ResponseBody
	public Result checkVehicleNo(VechicleRegQueryReq req) throws Exception{
		Result rs = vehicleRegService.checkVehicleExist(req);
		return rs ;
	}
	
	
	@RequestMapping(value="/getTempVehicleNo",method=RequestMethod.POST)
	@ResponseBody
	public Result getTempVehicleNo(VechicleRegQueryReq req){
		
		Result rs =vehicleRegService.getTempVechicleNo(req);
		return rs ;
	}
}
