package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.SignerBill;

public interface SignerBillMapper {
    
    List<SignerBill> selectByCondition(SignerBill record);
    
    long selectBycount(SignerBill record);
    
    List<SignerBill> DyselectByCondition(SignerBill record);
    
    long DyselectBycount(SignerBill record);
    
    List<SignerBill> AlselectByCondition(SignerBill record);
    
    long AlselectBycount(SignerBill record);
}