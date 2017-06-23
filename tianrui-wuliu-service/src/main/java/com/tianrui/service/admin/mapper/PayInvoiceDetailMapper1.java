package com.tianrui.service.admin.mapper;

import com.tianrui.service.admin.bean.PayInvoiceDetail;

public interface PayInvoiceDetailMapper1 {
    int deleteByPrimaryKey(String id);

    int insert(PayInvoiceDetail record);

    int insertSelective(PayInvoiceDetail record);

    PayInvoiceDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PayInvoiceDetail record);

    int updateByPrimaryKey(PayInvoiceDetail record);
}