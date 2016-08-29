package com.tianrui.service.mapper;

import com.tianrui.service.bean.AppVersion;

public interface AppVersionMapper {
    int deleteByPrimaryKey(String id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);
}