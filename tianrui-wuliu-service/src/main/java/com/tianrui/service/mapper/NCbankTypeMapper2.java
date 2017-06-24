package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.NCbankType2;

public interface NCbankTypeMapper2 {
    int deleteByPrimaryKey(String id);

    int insert(NCbankType2 record);

    int insertSelective(NCbankType2 record);

    NCbankType2 selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NCbankType2 record);

    int updateByPrimaryKey(NCbankType2 record);
    
    List<NCbankType2> selectByCondtion(NCbankType2 record);
}