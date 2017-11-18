package com.tianrui.service.mapper;

import com.tianrui.service.bean.MoneyCapitalRecord;

public interface MoneyCapitalRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyCapitalRecord record);

    int insertSelective(MoneyCapitalRecord record);

    MoneyCapitalRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoneyCapitalRecord record);

    int updateByPrimaryKey(MoneyCapitalRecord record);
}