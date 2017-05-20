package com.tianrui.api.intf.new_;

import com.tianrui.api.req.front.vehicle.VechicleRegQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegStep1Req;
import com.tianrui.api.req.front.vehicle.VechicleRegStep2Req;
import com.tianrui.api.req.front.vehicle.VechicleRegStep3Req;
import com.tianrui.api.req.new_.VehiclReqStepReq;
import com.tianrui.common.vo.Result;

/**
 * 车辆注册优化认证
 * @author lixp 2017年3月27日 15:30:40
 *
 */
public interface IVehicleRegService {

	//注册步骤一
	public Result vehicleRegStep1(VechicleRegStep1Req req); 
	//注册步骤二
	public Result vehicleRegStep2(VechicleRegStep2Req req); 
	//注册步骤三  驾驶员信息
	public Result vehicleRegStep3(VechicleRegStep3Req req); 
	
	/** 添加认证信息
	 * @throws Exception */
	public Result saveVehicleRegStep(VehiclReqStepReq req) throws Exception;
	/** 验证车牌号是否存在
	 * @throws Exception */
	public Result checkVehicleExist(VechicleRegQueryReq req) throws Exception;
	//交验车辆账户是否存在
	public Result checkVehicleAccountExist(VechicleRegQueryReq req);
	//获取临时车牌
	public Result getTempVechicleNo(VechicleRegQueryReq req);

}
