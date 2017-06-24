package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.NCbank_B2;

public interface NCbank_BMapper2 {
    int deleteByPrimaryKey(String id);

    int insert(NCbank_B2 record);

    int insertSelective(NCbank_B2 record);

    NCbank_B2 selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NCbank_B2 record);

    int updateByPrimaryKey(NCbank_B2 record);
    
    List<NCbank_B2> selectByCondition(NCbank_B2 record);
}