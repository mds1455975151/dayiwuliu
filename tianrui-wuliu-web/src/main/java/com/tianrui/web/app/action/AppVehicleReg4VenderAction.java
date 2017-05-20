package com.tianrui.web.app.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.new_.IVehicleReg4VenderService;
import com.tianrui.api.req.front.vehicle.VechicleRegVenderQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVenderSaveReq;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 车辆注册  车辆段
 * @author lixp 2017年4月10日 09:37:24
 *
 */
@Controller
@RequestMapping("/app/vehicleReg4vender")
public class AppVehicleReg4VenderAction {
	
	@Autowired
	IVehicleReg4VenderService  vehicleReg4VenderService;
	//搜索运力
	@RequestMapping(value="/searchVehicle",method=RequestMethod.POST)
	@ApiParamRawType(VechicleRegVenderQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult searchVehicle(AppParam<VechicleRegVenderQueryReq> appParam){
		Result rs = vehicleReg4VenderService.searchVehicle(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	//添加运力
	@RequestMapping(value="/saveVehicle",method=RequestMethod.POST)
	@ApiParamRawType(VechicleRegVenderSaveReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult saveVehicle(AppParam<VechicleRegVenderSaveReq> appParam){
		Result rs = vehicleReg4VenderService.saveVehicle(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	//运力列表
	@RequestMapping(value="/vehiclePage",method=RequestMethod.POST)
	@ApiParamRawType(VechicleRegVenderQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult vehiclePage(AppParam<VechicleRegVenderQueryReq> appParam){
		Result rs = vehicleReg4VenderService.vehiclePage(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	
	

}
