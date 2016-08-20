package com.tianrui.service.admin.mapper;

import com.tianrui.service.admin.bean.FreightInfo;

public interface FreightInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(FreightInfo record);

    int insertSelective(FreightInfo record);

    FreightInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FreightInfo record);

    int updateByPrimaryKey(FreightInfo record);
}