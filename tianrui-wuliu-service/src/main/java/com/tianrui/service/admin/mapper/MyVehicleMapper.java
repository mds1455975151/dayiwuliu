package com.tianrui.service.admin.mapper;

import java.util.List;

import com.tianrui.service.admin.bean.MyVehicle;
/**
 * 
 * @类描述：后台车辆管理查询
 * @创建人：lsj
 * @创建时间：2016年6月17日下午1:44:00
 *
 * @修改人：lsj
 * @修改时间：2016年6月17日下午1:44:00
 * @修改备注：
 *
 */
public interface MyVehicleMapper {

	List<MyVehicle> findByEntity(MyVehicle vehicle);
	
	long findByEntityCount(MyVehicle vehicle);
	
	MyVehicle findByid(String id);
}
