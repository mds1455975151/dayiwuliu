package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.vehiclereg.VehicleDriverNew;

public interface VehicleDriverNewMapper {
    int deleteByPrimaryKey(String id);

    int insert(VehicleDriverNew record);

    int insertSelective(VehicleDriverNew record);

    VehicleDriverNew selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VehicleDriverNew record);

    int updateByPrimaryKey(VehicleDriverNew record);
    
    List<VehicleDriverNew> selectByCondition(VehicleDriverNew record);
    
    long countByCondition(VehicleDriverNew record);
}