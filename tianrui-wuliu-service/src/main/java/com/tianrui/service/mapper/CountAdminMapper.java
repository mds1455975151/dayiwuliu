package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.CountAdmin;

public interface CountAdminMapper {
    int deleteByPrimaryKey(String id);

    int insert(CountAdmin record);

    int insertSelective(CountAdmin record);

    CountAdmin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CountAdmin record);

    int updateByPrimaryKey(CountAdmin record);
    
    List<CountAdmin> selectByCondition(CountAdmin record);
}