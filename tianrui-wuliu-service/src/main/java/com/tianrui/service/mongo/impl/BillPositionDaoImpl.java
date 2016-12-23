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

import com.tianrui.service.bean.BillPosition;
import com.tianrui.service.mongo.BillPositionDao;
@Repository("billPositionDao")
public class BillPositionDaoImpl extends BaseDaoImpl<BillPosition, String> implements BillPositionDao{

	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@Override
	public List<BillPosition> findwithBillId(String billid) {
		List<BillPosition> rs = null;
		if(StringUtils.isNotBlank(billid)){
			//拼装条件
			Query query =new Query();
			Criteria criteria =Criteria.where("billid").is(billid);
			query.addCriteria(criteria);
			query.with(new Sort(Direction.ASC,"createtime"));
			rs=mongoTemplate.find(query, BillPosition.class);
		}
		return rs;
	}
	
	public void save(BillPosition t){
		mongoTemplate.save(t);
	}
}
