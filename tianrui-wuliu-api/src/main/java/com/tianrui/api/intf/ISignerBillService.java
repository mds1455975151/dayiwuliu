package com.tianrui.api.intf;

import com.tianrui.api.req.front.bill.BillConfirmPriceReq;
import com.tianrui.api.req.front.bill.SignerBillFindReq;
import com.tianrui.api.resp.front.bill.SignerBillResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/** 签收运单查询*/
public interface ISignerBillService {

	public PaginationVO<SignerBillResp> select(SignerBillFindReq req) throws Exception;
	/** 前台运价确认*/
	public Result BillConfirmPrice(BillConfirmPriceReq req)throws Exception;
}
