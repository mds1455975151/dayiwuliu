package com.tianrui.service.mapper;

import com.tianrui.service.bean.SettingCargo;

public interface SettingCargoMapper {
    int deleteByPrimaryKey(String id);

    int insert(SettingCargo record);

    int insertSelective(SettingCargo record);

    SettingCargo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SettingCargo record);

    int updateByPrimaryKey(SettingCargo record);
}