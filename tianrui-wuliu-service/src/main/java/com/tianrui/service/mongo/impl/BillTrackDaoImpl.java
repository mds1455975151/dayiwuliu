package com.tianrui.service.mongo.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tianrui.service.bean.BillTrack;
import com.tianrui.service.mongo.BillTrackDao;

@Repository("billTrackDao")
public class BillTrackDaoImpl extends BaseDaoImpl<BillTrack,String> implements BillTrackDao{

	
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	
	
	public List<BillTrack>  findWithBid(String bId){
		List<BillTrack> rs =null;
		if( StringUtils.isNotBlank(bId) ){
			//拼装条件
			Query query =new Query();
			Criteria criteria =Criteria.where("billId").is(bId).and("isShow").is(1);
			query.addCriteria(criteria);
			//排序条件
			query.with(new Sort(Direction.ASC,"timestamp"));
			rs=mongoTemplate.find(query, BillTrack.class);
		}
		return rs;
	}

	@Override
	public List<BillTrack> findWithBidAndStatus(String bId, String status) {
		// TODO Auto-generated method stub
		List<BillTrack> rs =null;
		if( StringUtils.isNotBlank(bId)&&StringUtils.isNotBlank(status) ){
			//拼装条件
			Query query =new Query();
			Criteria criteria =Criteria.where("billId").is(bId).and("isShow").is(1).and("status").is(Integer.valueOf(status));
			query.addCriteria(criteria);
			//排序条件
			query.with(new Sort(Direction.ASC,"timestamp"));
			rs=mongoTemplate.find(query, BillTrack.class);
		}
		return rs;
	}
}
