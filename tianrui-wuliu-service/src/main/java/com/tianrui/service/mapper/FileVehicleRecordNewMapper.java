package com.tianrui.service.mapper;

import com.tianrui.service.bean.vehiclereg.FileVehicleRecordNew;

public interface FileVehicleRecordNewMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileVehicleRecordNew record);

    int insertSelective(FileVehicleRecordNew record);

    FileVehicleRecordNew selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileVehicleRecordNew record);

    int updateByPrimaryKey(FileVehicleRecordNew record);
}