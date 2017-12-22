package com.tianrui.service.mapper;

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
}