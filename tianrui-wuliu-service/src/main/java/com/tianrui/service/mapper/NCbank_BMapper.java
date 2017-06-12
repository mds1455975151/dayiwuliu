package com.tianrui.service.mapper;

import com.tianrui.service.bean.NCbank_B;

public interface NCbank_BMapper {
    int deleteByPrimaryKey(String id);

    int insert(NCbank_B record);

    int insertSelective(NCbank_B record);

    NCbank_B selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NCbank_B record);

    int updateByPrimaryKey(NCbank_B record);
}