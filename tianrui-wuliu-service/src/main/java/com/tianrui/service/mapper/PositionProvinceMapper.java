package com.tianrui.service.mapper;

import com.tianrui.service.bean.anlian.PositionProvince;

public interface PositionProvinceMapper {
    int deleteByPrimaryKey(String id);

    int insert(PositionProvince record);

    int insertSelective(PositionProvince record);

    PositionProvince selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PositionProvince record);

    int updateByPrimaryKey(PositionProvince record);
}