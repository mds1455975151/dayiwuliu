package com.tianrui.service.mapper;

import com.tianrui.service.bean.NCbankType;

public interface NCbankTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(NCbankType record);

    int insertSelective(NCbankType record);

    NCbankType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NCbankType record);

    int updateByPrimaryKey(NCbankType record);
}