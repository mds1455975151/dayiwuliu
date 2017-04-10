package com.tianrui.api.intf;

import com.tianrui.api.req.front.vehicle.VechicleRegDriverQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverSaveReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleAuthReq;
import com.tianrui.common.vo.Result;

/**
 * 车辆注册优化认证  车辆短
 * @author lixp 2017年3月27日 15:30:40
 *
 */
public interface IVehicleReg4VehicleService {
	
	
	//我的驾驶员列表
	public Result driverPage(VechicleRegDriverQueryReq req); 
	//添加驾驶员
	public Result driverSave(VechicleRegDriverSaveReq req); 
	//删除驾驶员
	public Result driverDel(VechicleRegDriverQueryReq req); 
	//选中驾驶员
	public Result driverCheck(VechicleRegDriverQueryReq req); 
	//我的车辆详情
	public Result vehicleDetil(VechicleRegDriverQueryReq req); 
	//车辆完全认证
	public Result vehicleAuth(VechicleRegVehicleAuthReq req); 
	//车辆开票认证
	public Result vehicleAuthTicket(VechicleRegVehicleAuthReq req); 


}
