package com.tianrui.service.mapper;

import com.tianrui.service.bean.ZJXLVehicle;

public interface ZJXLVehicleMapper {
    int deleteByPrimaryKey(String id);

    int insert(ZJXLVehicle record);

    int insertSelective(ZJXLVehicle record);

    ZJXLVehicle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ZJXLVehicle record);

    int updateByPrimaryKey(ZJXLVehicle record);
}