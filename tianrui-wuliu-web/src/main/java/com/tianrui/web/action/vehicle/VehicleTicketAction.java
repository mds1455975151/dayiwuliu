package com.tianrui.web.action.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IVehicleTicketService;
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
	
	/** 请求开票认证
	 * @throws Exception */
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public Result add(VehicleTicketReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleTicketService.insert(req);
		return rs;
	}
	
}
