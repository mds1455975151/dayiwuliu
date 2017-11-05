package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.LEDCountData;

public interface LEDCountDataMapper {
    int deleteByPrimaryKey(String id);

    int insert(LEDCountData record);

    int insertSelective(LEDCountData record);

    LEDCountData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LEDCountData record);

    int updateByPrimaryKey(LEDCountData record);
    
    long selectByCount(LEDCountData record);
    
    List<LEDCountData> selectByCondition(LEDCountData record);
}