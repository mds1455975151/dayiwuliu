package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.MoneyCapitalRecord;

public interface MoneyCapitalRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyCapitalRecord record);

    int insertSelective(MoneyCapitalRecord record);

    MoneyCapitalRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoneyCapitalRecord record);

    int updateByPrimaryKey(MoneyCapitalRecord record);
    
    List<MoneyCapitalRecord> selectByCondition(MoneyCapitalRecord record);
    
    long selectByCount(MoneyCapitalRecord record);
    
    /**
     * 根据用户银行唯一标识获取用户最新的资金流水
     * @param useryhNO
     * @return
     */
    MoneyCapitalRecord selectByUseryhno(String useryhNO);
    /**
     * 根据用户银行唯一标识获取用户最新的资金流水
     * @param cellphone
     * @return
     */
    MoneyCapitalRecord selectByCellphone(String cellphone);
}