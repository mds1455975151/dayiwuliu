package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.CountAdd;

public interface CountAddMapper {
    int deleteByPrimaryKey(String id);

    int insert(CountAdd record);

    int insertSelective(CountAdd record);

    CountAdd selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CountAdd record);

    int updateByPrimaryKey(CountAdd record);
    
    List<CountAdd> selectByCondition(CountAdd record);
}