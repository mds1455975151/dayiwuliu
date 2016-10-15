package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.pay.PayInvoiceAdviceReq;
import com.tianrui.api.req.front.pay.PayInvoiceQueryReq;
import com.tianrui.api.req.front.pay.PayInvoiceReq;
import com.tianrui.api.resp.pay.PayInvoiceResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 支付发票单
 * @author lixp
 *
 */
public interface IPayInvoiceService {

	//分页查询
	public PaginationVO<PayInvoiceResp> page(PayInvoiceQueryReq req)throws Exception;
	/** 列表查询*/
	public List<PayInvoiceResp> paylist(PayInvoiceQueryReq req)throws Exception;
	/** 定时查询nc支付状态，并修改本地数据*/
	public void queryNCPayStatus(PayInvoiceQueryReq req)throws Exception;
	
	//审核-自审
	public Result advice(PayInvoiceAdviceReq req)throws Exception;
	/** 提交nc支付申请*/
	public Result PayNcSave(PayInvoiceReq req)throws Exception;
	
	//nc回调 变更状态为支付中
	public Result payNcCallBack(PayInvoiceAdviceReq req)throws Exception;
	
	//查询详情
	public PayInvoiceResp queryPayInvoice(PayInvoiceQueryReq req)throws Exception;
	
	//查询详情及明细
	public PayInvoiceResp queryPayInvoiceWithDetail(PayInvoiceQueryReq req)throws Exception;
	/** 收回发票账单*/
	public Result withdrawPay(PayInvoiceReq req)throws Exception;
	
} 
