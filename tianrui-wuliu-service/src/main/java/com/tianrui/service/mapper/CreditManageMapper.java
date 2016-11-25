package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.CreditManage;

public interface CreditManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(CreditManage record);

    int insertSelective(CreditManage record);

    CreditManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditManage record);

    int updateByPrimaryKey(CreditManage record);
    
    List<CreditManage> selectByPrimaryKeySelective(CreditManage record);
}