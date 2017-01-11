package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.VehicleTicket;

public interface VehicleTicketMapper {
    int deleteByPrimaryKey(String id);

    int insert(VehicleTicket record);

    int insertSelective(VehicleTicket record);

    VehicleTicket selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VehicleTicket record);

    int updateByPrimaryKey(VehicleTicket record);
    
    List<VehicleTicket> selectByCondition(VehicleTicket record);
}