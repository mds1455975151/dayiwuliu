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

import com.mongodb.BasicDBObject;
import com.mongodb.QueryOperators;
import com.tianrui.service.bean.MemberPositionRecord;
import com.tianrui.service.mongo.MemberPositionRecordDao;

@Repository("memberPositionRecordDao")
public class MemberPositionRecordDaoImpl extends BaseDaoImpl<MemberPositionRecord,String> implements MemberPositionRecordDao{

	
	@Autowired
	protected MongoTemplate mongoTemplate;


	@Override
	public List<MemberPositionRecord> findWithBid(String uId, Long beginTime, Long endTime) {
		List<MemberPositionRecord> rs =null;
		if( StringUtils.isNotBlank(uId) ){
			//拼装条件
			Query query =new Query();
			Criteria cri = Criteria.where("$gt").is(beginTime).and("$lt").is(endTime);  
			Criteria criteria =Criteria.where("memberid").is(uId).where("createtime").elemMatch(cri);
			query.addCriteria(criteria);
			//排序条件
			query.with(new Sort(Direction.ASC,"createtime"));
			rs=mongoTemplate.find(query, MemberPositionRecord.class);
		}
		return rs;
	}
	
	public void save(MemberPositionRecord t){
		mongoTemplate.save(t);
	}
	
	
}
