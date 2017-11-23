package com.tianrui.api.money.intf;

import java.lang.reflect.InvocationTargetException;

import com.tianrui.api.req.money.FindPendingBillMoneyReq;
import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.api.req.money.UpdateBillMoneyReq;
import com.tianrui.api.resp.money.FindPendingBillMoneyResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IPendingBillMoneyService {

	/**
	 * 司机卸货完成增加待收入运费记录
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	Result save(SaveBillMoneyReq req) throws Exception;
	
	/**
	 * 财务确认运费后修改收入运费记录
	 * @param req
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	Result update(UpdateBillMoneyReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	PaginationVO<FindPendingBillMoneyResp> select(FindPendingBillMoneyReq req)throws Exception;
}
