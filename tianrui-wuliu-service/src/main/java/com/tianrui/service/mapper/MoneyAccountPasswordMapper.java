package com.tianrui.service.mapper;

import com.tianrui.service.bean.MoneyAccountPassword;

public interface MoneyAccountPasswordMapper {
    int deleteByPrimaryKey(String id);

    int insert(MoneyAccountPassword record);

    int insertSelective(MoneyAccountPassword record);

    MoneyAccountPassword selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MoneyAccountPassword record);

    int updateByPrimaryKey(MoneyAccountPassword record);
}