package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.anlian.AnlianBill;

public interface AnlianBillMapper {
    int deleteByPrimaryKey(String id);

    int insert(AnlianBill record);

    int insertSelective(AnlianBill record);

    AnlianBill selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AnlianBill record);

    int updateByPrimaryKey(AnlianBill record);
    
    List<AnlianBill> selectByCondition(AnlianBill record);
   
    long selectByCount(AnlianBill record);

}