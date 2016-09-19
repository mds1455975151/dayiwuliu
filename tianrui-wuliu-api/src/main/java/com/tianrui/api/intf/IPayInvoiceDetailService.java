package com.tianrui.api.intf;

import com.tianrui.api.req.front.pay.PayInvoiceDetailQueryReq;
import com.tianrui.api.req.front.pay.PayInvoiceDetailSaveReq;
import com.tianrui.api.req.front.pay.PayInvoiceGenalReq;
import com.tianrui.api.resp.pay.PayInvoiceDetailResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 支付帐单
 * @author lixp
 *
 */
public interface IPayInvoiceDetailService {

	//保存 运价确认
	public Result saveByBillPriceConfirm(PayInvoiceDetailSaveReq req)throws Exception;
	
	//分页查询
	public PaginationVO<PayInvoiceDetailResp> page(PayInvoiceDetailQueryReq req)throws Exception;
	
	//生成运价发票单
	public Result generalPayInvoice(PayInvoiceGenalReq req)throws Exception;
	
	//查询详情
	public PayInvoiceDetailResp queryPayInvoice(PayInvoiceDetailQueryReq req)throws Exception;
	
}
