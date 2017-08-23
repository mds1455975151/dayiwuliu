package com.tianrui.service.mongo;

import java.util.List;

import com.tianrui.service.bean.VehicleGpsZjxl;
/**
 * 位置信息
 * @author lixp 2017年8月17日 09:28:23
 *
 */
public interface VehicleGpsZjxlDao extends BaseDao<VehicleGpsZjxl,String> {
	//根据车辆获取轨迹
	public List<VehicleGpsZjxl> getVehicleTrack(String vehicleNo,long beginTime,long endTime);

	//获取车辆位置
	public VehicleGpsZjxl getVehiclePosition(String vehicleNo);
}
