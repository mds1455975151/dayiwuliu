package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.Transfer;

public interface TransferMapper {
    int deleteByPrimaryKey(String id);

    int insert(Transfer record);

    int insertSelective(Transfer record);

    Transfer selectByPrimaryKey(String id);
    
    List<Transfer> selectByCondition(Transfer record);

    int updateByPrimaryKeySelective(Transfer record);
    /** 用户接收-拒绝转运请求*/
    int updateByStatus(Transfer transfer);

    int updateByPrimaryKey(Transfer record);
    
    int updateByBillId(Transfer transfer);
    
    int deleteByBillId(String billId);
}