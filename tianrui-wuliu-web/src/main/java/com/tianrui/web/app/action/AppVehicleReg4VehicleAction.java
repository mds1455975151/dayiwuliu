package com.tianrui.web.app.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.new_.IVehicleReg4VehicleService;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverSaveReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleAuthReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleTicketAuthReq;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 车辆注册  车辆端
 * @author lixp 2017年4月10日 09:37:24
 *
 */
@Controller
@RequestMapping("/app/vehicleReg4vehicle")
public class AppVehicleReg4VehicleAction {
	
	@Autowired
	IVehicleReg4VehicleService  vehicleReg4VehicleService;

	//我的驾驶员列表
	@RequestMapping(value="/driverPage",method=RequestMethod.POST)
	@ApiParamRawType(VechicleRegDriverQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult driverPage(AppParam<VechicleRegDriverQueryReq> appParam){
		Result rs = vehicleReg4VehicleService.driverPage(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	//添加驾驶员
	@RequestMapping(value="/driverSave",method=RequestMethod.POST)
	@ApiParamRawType(VechicleRegDriverSaveReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult driverSave(AppParam<VechicleRegDriverSaveReq> appParam){
		Result rs = vehicleReg4VehicleService.driverSave(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	//删除驾驶员
	@RequestMapping(value="/driverDel",method=RequestMethod.POST)
	@ApiParamRawType(VechicleRegDriverQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult driverDel(AppParam<VechicleRegDriverQueryReq> appParam){
		Result rs = vehicleReg4VehicleService.driverDel(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	//选中驾驶员
	@RequestMapping(value="/driverCheck",method=RequestMethod.POST)
	@ApiParamRawType(VechicleRegDriverQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult driverCheck(AppParam<VechicleRegDriverQueryReq> appParam){
		Result rs = vehicleReg4VehicleService.driverCheck(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	//我的车辆
	@RequestMapping(value="/vehicleDetil",method=RequestMethod.POST)
	@ApiParamRawType(VechicleRegDriverQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult vehicleDetil(AppParam<VechicleRegDriverQueryReq> appParam){
		Result rs = vehicleReg4VehicleService.vehicleDetil(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	//车辆补全信息
	@RequestMapping(value="/vehicleAuth",method=RequestMethod.POST)
	@ApiParamRawType(VechicleRegVehicleAuthReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult vehicleAuth(AppParam<VechicleRegVehicleAuthReq> appParam){
		Result rs = vehicleReg4VehicleService.vehicleAuth(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	//车辆卡票认证
	@RequestMapping(value="/vehicleAuthTicket",method=RequestMethod.POST)
	@ApiParamRawType(VechicleRegVehicleTicketAuthReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult vehicleAuthTicket(AppParam<VechicleRegVehicleTicketAuthReq> appParam){
		Result rs = vehicleReg4VehicleService.vehicleAuthTicket(appParam.getBody());
		return AppResult.valueOf(rs);
	}

}
