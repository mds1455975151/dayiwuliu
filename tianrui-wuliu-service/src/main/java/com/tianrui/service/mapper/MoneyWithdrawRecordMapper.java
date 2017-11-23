package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.MoneyWithdrawRecord;

public interface MoneyWithdrawRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyWithdrawRecord record);

    int insertSelective(MoneyWithdrawRecord record);

    MoneyWithdrawRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoneyWithdrawRecord record);

    int updateByPrimaryKey(MoneyWithdrawRecord record);
    
    List<MoneyWithdrawRecord> selectByCondition(MoneyWithdrawRecord record);
    
    long selectByCount(MoneyWithdrawRecord record);
    
    /**
     * 根据流水号获取提现记录
     * @param capitalNO
     * @return
     */
    MoneyWithdrawRecord selectByCapitalno(String capitalNO);
}