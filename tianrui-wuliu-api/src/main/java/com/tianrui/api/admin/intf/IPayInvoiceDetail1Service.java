package com.tianrui.api.admin.intf;


import com.tianrui.api.req.admin.pay.PayInviceSave1Req;
import com.tianrui.api.req.admin.pay.PayInvoiceDetail1FindReq;
import com.tianrui.api.req.admin.pay.PayInvoiceDetail1Req;
import com.tianrui.api.resp.admin.pay.PayInvoiceDetail1Resp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IPayInvoiceDetail1Service {
	/**后台运价确认查询
	 * @throws Exception */
	PaginationVO<PayInvoiceDetail1Resp> select(PayInvoiceDetail1FindReq req) throws Exception;
	
	PayInvoiceDetail1Resp selectById(String id)throws Exception;
	/**后台运价确认
	 * @throws Exception */
	public Result uptPrice(PayInvoiceDetail1Req req) throws Exception;
	/** 生成账单*/
	public Result savePayInvoice(PayInviceSave1Req req)throws Exception;
	/** 通过运单id 查询运价确认量*/
	public Result billSelectPrice(PayInvoiceDetail1Req req)throws Exception;
}
