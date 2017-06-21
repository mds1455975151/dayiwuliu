package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.SignerBill;

public interface SignerBillMapper {
    int insert(SignerBill record);

    int insertSelective(SignerBill record);
    
    List<SignerBill> selectByCondition(SignerBill record);
    
    long selectBycount(SignerBill record);
}