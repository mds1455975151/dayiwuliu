package com.tianrui.api.intf;

import com.tianrui.api.req.front.vehicle.VechicleRegVenderQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVenderSaveReq;
import com.tianrui.common.vo.Result;

/**
 * 车辆注册优化认证 车主端
 * @author lixp 2017年3月27日 15:30:40
 *
 */
public interface IVehicleReg4VenderService {

	/**
	 * 搜索运力
	 * @param req
	 * @return
	 */
	public Result searchVehicle(VechicleRegVenderQueryReq req);
	//添加
	public Result saveVehicle(VechicleRegVenderSaveReq req);
	//我的运力列表
	public Result vehiclePage(VechicleRegVenderQueryReq req);
	
}
