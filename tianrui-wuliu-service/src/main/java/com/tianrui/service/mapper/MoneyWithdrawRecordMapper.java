package com.tianrui.service.mapper;

import com.tianrui.service.bean.MoneyWithdrawRecord;

public interface MoneyWithdrawRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyWithdrawRecord record);

    int insertSelective(MoneyWithdrawRecord record);

    MoneyWithdrawRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoneyWithdrawRecord record);

    int updateByPrimaryKey(MoneyWithdrawRecord record);
}