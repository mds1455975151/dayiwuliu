package com.tianrui.service.admin.mapper;

import java.util.List;

import com.tianrui.service.admin.bean.PayInvoiceDetail;

public interface PayInvoiceDetailMapper1 {
    int deleteByPrimaryKey(String id);

    int insert(PayInvoiceDetail record);

    int insertSelective(PayInvoiceDetail record);

    PayInvoiceDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PayInvoiceDetail record);

    int updateByPrimaryKey(PayInvoiceDetail record);
    
    List<PayInvoiceDetail> selectByCondition(PayInvoiceDetail record);
    
    List<PayInvoiceDetail> selectByCondition_copy(PayInvoiceDetail record);
    
    long selectBycount(PayInvoiceDetail record);
    
    List<PayInvoiceDetail> selectByPrimary(String payInvoiceId);
    /** 获取当前数据所有车主id列表*/
    List<PayInvoiceDetail> groupByVender(PayInvoiceDetail record);
    
    List<PayInvoiceDetail> selectByfindPlanId(PayInvoiceDetail record);
}