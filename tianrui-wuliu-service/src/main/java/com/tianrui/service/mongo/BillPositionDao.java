package com.tianrui.service.mongo;

import java.util.List;

import com.tianrui.service.bean.BillPosition;

public interface BillPositionDao extends BaseDao<BillPosition, String> {

	List<BillPosition> findwithBillId(String Billid);
	/**根据运单id 运单状态 查询信息*/
	BillPosition findBillIdAndStatus(String billid,String status);
}