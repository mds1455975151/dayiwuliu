package com.tianrui.api.money.intf;

import com.tianrui.api.req.money.CapitalRecordReq;
import com.tianrui.api.req.money.FindCapitalRecordByIdReq;
import com.tianrui.api.req.money.FindCapitalRecordReq;
import com.tianrui.api.resp.money.FindCapitalRecordResp;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
/***
 * 查询资金流水
 * @author jh
 *
 */
public interface ICapitalRecordService {

	/**
	 * 新增一条资金流水记录
	 * @param req
	 * @param type
	 * @return
	 */
	Result save(CapitalRecordReq req,TransactionType type);
	/**
	 * 查询
	 * @param req
	 * @return
	 * @throws Exception
	 */
	PaginationVO<FindCapitalRecordResp> select(FindCapitalRecordReq req)throws Exception;
	/** 查询详情*/
	Result selectCapitalRecordById(FindCapitalRecordByIdReq req)throws Exception;
}
