package com.tianrui.api.intf;

import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.common.vo.Result;

/** 资金交互处理接口*/
public interface IMoenyDisposeService {
	/**卸货完成添加资金流水*/
	Result billSaveMoney(SaveBillMoneyReq req);
}
