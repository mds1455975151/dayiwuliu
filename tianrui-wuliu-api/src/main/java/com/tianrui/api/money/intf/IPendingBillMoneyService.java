package com.tianrui.api.money.intf;

import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.api.req.money.UpdateBillMoneyReq;
import com.tianrui.common.vo.Result;

public interface IPendingBillMoneyService {

	/**
	 * 司机卸货完成增加待收入运费记录
	 * @param req
	 * @return
	 */
	Result save(SaveBillMoneyReq req);
	
	/**
	 * 财务确认运费后修改收入运费记录
	 * @param req
	 * @return
	 */
	Result update(UpdateBillMoneyReq req);
	
}
