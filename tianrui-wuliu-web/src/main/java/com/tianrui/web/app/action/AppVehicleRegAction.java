package com.tianrui.web.app.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.new_.IVehicleRegService;
import com.tianrui.api.req.front.vehicle.VechicleRegQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegStep1Req;
import com.tianrui.api.req.front.vehicle.VechicleRegStep2Req;
import com.tianrui.api.req.front.vehicle.VechicleRegStep3Req;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;

@Controller
@RequestMapping("/app/vehicleReg")
public class AppVehicleRegAction {

	@Autowired
	IVehicleRegService vehicleRegService;

	/**
	 * 注册步骤一
	 * 
	 * @throws Exception
	 */
	@RequestMapping("step1")
	@ApiParamRawType(VechicleRegStep1Req.class)
	@ResponseBody
	public AppResult step1(AppParam<VechicleRegStep1Req> appParam) throws Exception {
		Result rs = vehicleRegService.vehicleRegStep1(appParam.getBody());
		return AppResult.valueOf(rs);
	}

	/**
	 * 注册步骤二
	 * 
	 * @throws Exception
	 */
	@RequestMapping("step2")
	@ApiParamRawType(VechicleRegStep2Req.class)
	@ResponseBody
	public AppResult step2(AppParam<VechicleRegStep2Req> appParam) throws Exception {
		Result rs = vehicleRegService.vehicleRegStep2(appParam.getBody());
		return AppResult.valueOf(rs);
	}

	/**
	 * 注册步骤三
	 * 
	 * @throws Exception
	 */
	@RequestMapping("step3")
	@ApiParamRawType(VechicleRegStep3Req.class)
	@ResponseBody
	public AppResult step3(AppParam<VechicleRegStep3Req> appParam) throws Exception {
		Result rs = vehicleRegService.vehicleRegStep3(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 获取临时车牌
	 * 
	 * @throws Exception
	 */
	@RequestMapping("getTempVechicleNo")
	@ApiParamRawType(VechicleRegQueryReq.class)
	@ResponseBody
	public AppResult getTempVechicleNo(AppParam<VechicleRegQueryReq> appParam) throws Exception {
		Result rs = vehicleRegService.getTempVechicleNo(appParam.getBody());
		return AppResult.valueOf(rs);
	}
	
	/**
	 * 校验车牌号码
	 * @throws Exception
	 */
	@RequestMapping("checkVehicleExist")
	@ApiParamRawType(VechicleRegQueryReq.class)
	@ResponseBody
	public AppResult checkVehicleExist(AppParam<VechicleRegQueryReq> appParam) throws Exception {
		Result rs = vehicleRegService.checkVehicleExist(appParam.getBody());
		return AppResult.valueOf(rs);
	}

}
