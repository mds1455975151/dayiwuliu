package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.anlian.PositionCounty;

public interface PositionCountyMapper {
    int deleteByPrimaryKey(String id);

    int insert(PositionCounty record);

    int insertSelective(PositionCounty record);

    PositionCounty selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PositionCounty record);

    int updateByPrimaryKey(PositionCounty record);
    
    List<PositionCounty> findCount(PositionCounty record);
}