package com.tianrui.service.mapper;

import com.tianrui.service.bean.anlian.PositionCity;

public interface PositionCityMapper {
    int deleteByPrimaryKey(String id);

    int insert(PositionCity record);

    int insertSelective(PositionCity record);

    PositionCity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PositionCity record);

    int updateByPrimaryKey(PositionCity record);
}