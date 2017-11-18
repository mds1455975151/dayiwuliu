package com.tianrui.api.money.intf;

import com.tianrui.api.req.money.CapitalAccountReq;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.vo.Result;

public interface ICapitalAccountService {

	/**
	 * 根据不同交易类型 修改或新增用户资金账户
	 * @param req
	 * @param type
	 * @return
	 */
	Result saveOrUpdate(CapitalAccountReq req,TransactionType type);
}
