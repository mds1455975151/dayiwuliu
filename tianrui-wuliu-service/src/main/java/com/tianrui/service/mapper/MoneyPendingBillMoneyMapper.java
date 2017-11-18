package com.tianrui.service.mapper;

import com.tianrui.service.bean.MoneyPendingBillMoney;

public interface MoneyPendingBillMoneyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyPendingBillMoney record);

    int insertSelective(MoneyPendingBillMoney record);

    MoneyPendingBillMoney selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoneyPendingBillMoney record);

    int updateByPrimaryKey(MoneyPendingBillMoney record);
}