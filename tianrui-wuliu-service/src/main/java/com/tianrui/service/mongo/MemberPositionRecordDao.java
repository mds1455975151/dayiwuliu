package com.tianrui.service.mongo;

import java.util.List;

import com.tianrui.service.bean.MemberPositionRecord;

public interface MemberPositionRecordDao extends BaseDao<MemberPositionRecord,String> {


	//根据运单id获取运单轨迹信息
	List<MemberPositionRecord>  findWithBid(String uId,Long beginTime,Long endTime);
}
