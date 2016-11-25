package com.tianrui.service.mapper;

import com.tianrui.service.bean.CreditScore;

public interface CreditScoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(CreditScore record);

    int insertSelective(CreditScore record);

    CreditScore selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditScore record);

    int updateByPrimaryKey(CreditScore record);
    
    CreditScore queryByCreditManageId(String creditManageId);
    
}