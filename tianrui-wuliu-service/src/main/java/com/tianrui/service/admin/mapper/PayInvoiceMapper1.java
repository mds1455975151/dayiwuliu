package com.tianrui.service.admin.mapper;

import java.util.List;

import com.tianrui.api.req.admin.PayInvoiceReq;
import com.tianrui.api.resp.admin.PayInvoiceAuditVo;
import com.tianrui.api.resp.admin.PayInvoiceVo;
import com.tianrui.service.admin.bean.PayInvoice;

public interface PayInvoiceMapper1 {
    int deleteByPrimaryKey(String id);

    int insert(PayInvoice record);

    int insertSelective(PayInvoice record);

    PayInvoice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PayInvoice record);

    int updateByPrimaryKey(PayInvoice record);
    
    List<PayInvoiceVo> selectPayInvoicePage(PayInvoiceReq req);
    
    long selectPayInvoicePageCount(PayInvoiceReq req);
    
    PayInvoiceAuditVo selectPayInvoiceAuditData(String id);

	List<PayInvoice> selectByCondition(PayInvoice record);
}