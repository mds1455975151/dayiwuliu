package com.tianrui.api.money.intf;

import java.lang.reflect.InvocationTargetException;

import com.tianrui.api.req.money.FindCapitalRecordReq;
import com.tianrui.api.req.money.FindWithdrawByIdReq;
import com.tianrui.api.req.money.FindWithdrawRecordReq;
import com.tianrui.api.req.money.SaveWithdrawReq;
import com.tianrui.api.req.money.updateWithdrawReq;
import com.tianrui.api.resp.money.FindCapitalRecordResp;
import com.tianrui.api.resp.money.FindWithdrawRecordResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IWithdrawRecordService {
	/**
	 * 保存提现申请
	 * @param req
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	Result save(SaveWithdrawReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	/**
	 * 提现结果反馈
	 * @param req
	 * @return
	 */
	Result update(updateWithdrawReq req);
	/** 用户提现记录*/
	PaginationVO<FindWithdrawRecordResp> select(FindWithdrawRecordReq req)throws Exception;
	/** 查询id*/
	Result selectByWithdrawId(FindWithdrawByIdReq req)throws Exception;
}
