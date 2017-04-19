package com.tianrui.service.mapper;

import com.tianrui.service.bean.vehiclereg.VehicleDriverNew;

public interface VehicleDriverNewMapper {
    int deleteByPrimaryKey(String id);

    int insert(VehicleDriverNew record);

    int insertSelective(VehicleDriverNew record);

    VehicleDriverNew selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VehicleDriverNew record);

    int updateByPrimaryKey(VehicleDriverNew record);
}