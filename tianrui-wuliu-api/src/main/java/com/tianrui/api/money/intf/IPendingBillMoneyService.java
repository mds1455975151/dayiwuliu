package com.tianrui.api.money.intf;

import com.tianrui.api.req.money.BillMoneyReq;
import com.tianrui.common.vo.Result;

public interface IPendingBillMoneyService {

	/**
	 * 司机卸货完成增加待收入运费记录
	 * @param req
	 * @return
	 */
	Result save(BillMoneyReq req);
}
