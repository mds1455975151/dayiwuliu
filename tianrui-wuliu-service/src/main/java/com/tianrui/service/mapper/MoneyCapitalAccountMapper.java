package com.tianrui.service.mapper;

import com.tianrui.service.bean.MoneyCapitalAccount;

public interface MoneyCapitalAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyCapitalAccount record);

    int insertSelective(MoneyCapitalAccount record);

    MoneyCapitalAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoneyCapitalAccount record);

    int updateByPrimaryKey(MoneyCapitalAccount record);
}