package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.V_vehicle_driver;

public interface V_vehicle_driverMapper {

    List<V_vehicle_driver> selectByCondition(V_vehicle_driver record);
    
    long selectBycount(V_vehicle_driver record);
    
}