package com.tianrui.service.mapper;

import com.tianrui.service.bean.Transfer;

public interface TransferMapper {
    int deleteByPrimaryKey(String id);

    int insert(Transfer record);

    int insertSelective(Transfer record);

    Transfer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Transfer record);

    int updateByPrimaryKey(Transfer record);
}