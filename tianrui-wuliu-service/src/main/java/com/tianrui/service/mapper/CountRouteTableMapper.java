package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.CountRouteTable;

public interface CountRouteTableMapper {
    int deleteByPrimaryKey(String id);

    int insert(CountRouteTable record);

    int insertSelective(CountRouteTable record);

    CountRouteTable selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CountRouteTable record);

    int updateByPrimaryKey(CountRouteTable record);
    
    List<CountRouteTable> selectByCondition(CountRouteTable record);
}