package com.tianrui.service.mongo;

import java.util.List;

import com.tianrui.service.bean.BillTrack;

public interface BillTrackDao extends BaseDao<BillTrack,String> {

	//根据运单id获取运单轨迹信息
	List<BillTrack>  findWithBid(String bId);
	/** 根据运单id和运单状态查询轨迹信息*/
	List<BillTrack> findWithBidAndStatus(String bId ,String status);
}
