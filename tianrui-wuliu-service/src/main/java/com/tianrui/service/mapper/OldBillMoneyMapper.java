package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.service.bean.OldBillMoney;

public interface OldBillMoneyMapper {

	List<OldBillMoney> selectByOldDYBill(OldBillMoney req);
	
	List<OldBillMoney> selectByOldALBill(OldBillMoney req);
	
	List<OldBillMoney> selectByOldUptMoney(OldBillMoney req);
	
	
	
}
