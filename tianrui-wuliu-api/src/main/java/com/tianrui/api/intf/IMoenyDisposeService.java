package com.tianrui.api.intf;

import java.lang.reflect.InvocationTargetException;

import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.common.vo.Result;

/** 资金交互处理接口*/
public interface IMoenyDisposeService {
	/**卸货完成添加资金流水
	 * @throws Exception */
	Result billSaveMoney(SaveBillMoneyReq req) throws Exception;
	/** 处理大易历史单据*/
	Result oldDYSaveBillMoney()throws Exception;
	/** 处理安联历史单据*/
	Result oldALSaveBillMoney()throws Exception;
	Result uptPandMoney() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
}
