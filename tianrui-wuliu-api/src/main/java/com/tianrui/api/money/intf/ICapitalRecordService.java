package com.tianrui.api.money.intf;

import com.tianrui.api.req.money.CapitalRecordReq;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.vo.Result;

public interface ICapitalRecordService {

	/**
	 * 新增一条资金流水记录
	 * @param req
	 * @param type
	 * @return
	 */
	Result save(CapitalRecordReq req,TransactionType type);
}
