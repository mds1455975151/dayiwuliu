package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.OldBillMoney;

public interface OldBillMoneyMapper {
	/** 历史运单卸货确认*/
	List<OldBillMoney> selectByOldDYBill(OldBillMoney req);
	/** 历史运单卸货确认*/
	List<OldBillMoney> selectByOldALBill(OldBillMoney req);
	/** 历史运单运价确认*/
	List<OldBillMoney> selectByOldUptMoney(OldBillMoney req);
	/** 历史账单提现申请*/
	List<OldBillMoney> selectByOldWithDraw(OldBillMoney req);
	/** 历史账单提现成功*/
	List<OldBillMoney> selectByOldWithDrawSuccess(OldBillMoney req);
	/** 历史账单提现失败*/
	List<OldBillMoney> selectByOldWithDrawFail(OldBillMoney req);
}
