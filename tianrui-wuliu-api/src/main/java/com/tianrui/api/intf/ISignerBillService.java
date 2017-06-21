package com.tianrui.api.intf;

import com.tianrui.api.req.front.bill.SignerBillReq;
import com.tianrui.api.resp.front.bill.SignerBillResp;
import com.tianrui.common.vo.PaginationVO;

/** 签收运单查询*/
public interface ISignerBillService {

	public PaginationVO<SignerBillResp> select(SignerBillReq req) throws Exception;
}
