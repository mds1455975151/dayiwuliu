package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.AccessLog;

public interface AccessLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(AccessLog record);

    int insertSelective(AccessLog record);

    AccessLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccessLog record);

    int updateByPrimaryKey(AccessLog record);
    
    List<AccessLog> selectByCondition(AccessLog record);
    
    long selectByCount(AccessLog record);
}