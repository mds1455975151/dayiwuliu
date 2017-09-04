package com.tianrui.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.service.bean.WuliuVehicle;

public interface WuliuVehicleMapper {
    int deleteByPrimaryKey(String id);

    int insert(WuliuVehicle record);

    int insertSelective(WuliuVehicle record);

    WuliuVehicle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WuliuVehicle record);

    int updateByPrimaryKey(WuliuVehicle record);
    
    List<WuliuVehicle> listVehicle(@Param("start")Integer start, @Param("limit")Integer limit);
}