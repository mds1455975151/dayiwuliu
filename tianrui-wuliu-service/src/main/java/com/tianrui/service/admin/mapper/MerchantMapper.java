package com.tianrui.service.admin.mapper;

import java.util.List;

import com.tianrui.service.admin.bean.Merchant;

public interface MerchantMapper {
    int deleteByPrimaryKey(String id);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    Merchant selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);
    
    List<Merchant> selectByCondition(Merchant record);
    
    long selectByOnly(Merchant record);
    
    long selectByCount(Merchant record);
}