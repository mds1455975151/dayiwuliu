package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.CustomRcord;

public interface CustomRcordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CustomRcord record);

    int insertSelective(CustomRcord record);

    CustomRcord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomRcord record);

    int updateByPrimaryKey(CustomRcord record);
    /**
     * 获取在途车辆未处理的异常轨迹
     * @param customerId
     * @return
     */
    CustomRcord selectByCustomerId(String  customerId);
    
    List<CustomRcord> selectByCondition(CustomRcord record);
    
    long selectByCount(CustomRcord record);
}