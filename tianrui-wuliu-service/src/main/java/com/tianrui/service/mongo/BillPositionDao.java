package com.tianrui.service.mongo;

import java.util.List;

import com.tianrui.service.bean.BillPosition;

public interface BillPositionDao extends BaseDao<BillPosition, String> {

	List<BillPosition> findwithBillId(String Billid);
}