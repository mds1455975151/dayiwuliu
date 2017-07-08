package com.tianrui.service.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.service.admin.bean.PayInvoiceMsg;

public interface PayInvoiceMsgMapper {
    int deleteByPrimaryKey(String id);

    int insert(PayInvoiceMsg record);

    int insertSelective(PayInvoiceMsg record);

    PayInvoiceMsg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PayInvoiceMsg record);

    int updateByPrimaryKey(PayInvoiceMsg record);
    
    List<PayInvoiceMsg> selectByCondition(PayInvoiceMsg record);
    
    int updateLastPayStatusByPayInvoiceId(PayInvoiceMsg record);
    
    List<PayInvoiceMsg> selectByPayInvoiceIdList(@Param("list")List<String> list);
}