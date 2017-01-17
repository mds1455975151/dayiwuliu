package com.tianrui.web.app.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IVehicleTicketService;
import com.tianrui.api.req.front.vehicle.OwnerDriverReq;
import com.tianrui.api.req.front.vehicle.TicketFindReq;
import com.tianrui.api.req.front.vehicle.VehicleTicketReq;
import com.tianrui.common.vo.AppParam;
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
	

	/** 请求开票认证 AppParam<OwnerDriverReq>
	 * @throws Exception */
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ApiParamRawType(VehicleTicketReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult add(AppParam<VehicleTicketReq> req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleTicketService.insert(req.getBody());
		return AppResult.valueOf(rs);
	}
	
	/** 开票认证修改
	 * @throws Exception */
	@RequestMapping(value="upt",method = RequestMethod.POST)
	@ApiParamRawType(VehicleTicketReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult upt(AppParam<VehicleTicketReq> req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = vehicleTicketService.upt(req.getBody());
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 查询开票车辆信息*/
	@RequestMapping(value="uptdata",method = RequestMethod.POST)
	@ApiParamRawType(TicketFindReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult uptdata(AppParam<TicketFindReq> req) throws Exception{
		Result rs = vehicleTicketService.findByVehicleId(req.getBody());
		return AppResult.valueOf(rs);
	}
	
}
