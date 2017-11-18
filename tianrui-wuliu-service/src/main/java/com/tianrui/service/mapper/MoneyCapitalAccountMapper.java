package com.tianrui.service.mapper;

import com.tianrui.service.bean.MoneyCapitalAccount;

public interface MoneyCapitalAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyCapitalAccount record);

    int insertSelective(MoneyCapitalAccount record);

    MoneyCapitalAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoneyCapitalAccount record);

    int updateByPrimaryKey(MoneyCapitalAccount record);
    /**
     * 根据用户银行唯一标识获取用户资金账户
     * @param useryhNO
     * @return
     */
    MoneyCapitalAccount selectByUseryhno(String useryhNO);
    /**
     * 根据用户登录账号获取用户资金账户
     * @param cellphone
     * @return
     */
    MoneyCapitalAccount selectByCellphone(String cellphone);
}