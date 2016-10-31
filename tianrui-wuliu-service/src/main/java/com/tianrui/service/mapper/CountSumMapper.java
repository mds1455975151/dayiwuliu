package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.CountSum;

public interface CountSumMapper {
    int deleteByPrimaryKey(String id);

    int insert(CountSum record);

    int insertSelective(CountSum record);

    CountSum selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CountSum record);

    int updateByPrimaryKey(CountSum record);
    
    List<CountSum> selectByCondition(CountSum record);
}