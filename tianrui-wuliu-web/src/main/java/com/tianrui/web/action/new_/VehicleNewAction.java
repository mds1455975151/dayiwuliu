package com.tianrui.web.action.new_;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.new_.IMemberVehicleNewService;
import com.tianrui.api.req.new_.MemberVehicleNewReq;
import com.tianrui.api.req.new_.V_vehicle_driverReq;
import com.tianrui.api.resp.new_.V_vehicle_driverResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
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
	IMemberVehicleNewService memberVehicleNewService;
	
	
	/** 我的驾驶员*/
	@RequestMapping("driverpage")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/new/vehicle/driverpage");
		return view;
	}
	/** 我的车辆*/
	@RequestMapping("vehicledetail")
	public ModelAndView pageAdd(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/new/vehicle/vehicledetail");
		return view;
	}
	
	/** 添加驾驶员
	 * @throws Exception */
	@RequestMapping("find")
	@ResponseBody
	public Result find(V_vehicle_driverReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<V_vehicle_driverResp> page = memberVehicleNewService.select(req);
		rs.setData(page);
		return rs;
	}
	

}
