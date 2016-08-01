package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.Transfer;

public interface TransferMapper {
    int deleteByPrimaryKey(String id);

    int insert(Transfer record);

    int insertSelective(Transfer record);

    Transfer selectByPrimaryKey(String id);
    
    List<Transfer> selectByCondition(Transfer record);

    int updateByPrimaryKeySelective(Transfer record);

    int updateByPrimaryKey(Transfer record);
}