package com.tianrui.service.mongo;

import java.util.List;

import com.tianrui.service.bean.BillAnlianPosition;

public interface BillAnlianPositionDao extends BaseDao<BillAnlianPosition, String>{

	List<BillAnlianPosition> findPosition(String shipmentno);
}
