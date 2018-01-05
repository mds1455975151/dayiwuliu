package com.tianrui.service.mapper;

import com.tianrui.service.bean.SettingRoute;

public interface SettingRouteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SettingRoute record);

    int insertSelective(SettingRoute record);

    SettingRoute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SettingRoute record);

    int updateByPrimaryKey(SettingRoute record);
}