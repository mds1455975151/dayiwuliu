package com.tianrui.web.app.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IVehicleTicketService;
import com.tianrui.api.req.front.vehicle.VehicleTicketReq;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 移动端车辆开票认证
 * @author jh
 *
 */
@Controller
@RequestMapping("/app/vehicleTicket")
public class AppVehicleTicketAction {

	@Autowired
	IVehicleTicketService vehicleTicketService;
	

	/** 请求开票认证
	 * @throws Exception */
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ApiParamRawType(VehicleTicketReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult add(VehicleTicketReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleTicketService.insert(req);
		return AppResult.valueOf(rs);
	}
	
	/** 开票认证修改
	 * @throws Exception */
	@RequestMapping(value="upt",method = RequestMethod.POST)
	@ApiParamRawType(VehicleTicketReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult upt(VehicleTicketReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleTicketService.upt(req);
		return AppResult.valueOf(rs);
	}
}
