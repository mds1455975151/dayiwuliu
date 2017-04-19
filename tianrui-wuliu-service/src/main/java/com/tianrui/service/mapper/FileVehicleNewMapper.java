package com.tianrui.service.mapper;

import com.tianrui.service.bean.vehiclereg.FileVehicleNew;

public interface FileVehicleNewMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileVehicleNew record);

    int insertSelective(FileVehicleNew record);

    FileVehicleNew selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileVehicleNew record);

    int updateByPrimaryKey(FileVehicleNew record);
}