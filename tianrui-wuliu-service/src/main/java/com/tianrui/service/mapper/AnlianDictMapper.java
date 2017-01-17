package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.AnlianDict;

public interface AnlianDictMapper {
    int deleteByPrimaryKey(String id);

    int insert(AnlianDict record);

    int insertSelective(AnlianDict record);

    AnlianDict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AnlianDict record);

    int updateByPrimaryKey(AnlianDict record);
    
    List<AnlianDict> selectByCondition(AnlianDict record);
}