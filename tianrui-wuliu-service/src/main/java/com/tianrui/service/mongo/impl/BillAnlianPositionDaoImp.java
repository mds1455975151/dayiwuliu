package com.tianrui.service.mongo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tianrui.service.bean.BillAnlianPosition;
import com.tianrui.service.mongo.BillAnlianPositionDao;
@Repository("billAnlianPositionDao")
public class BillAnlianPositionDaoImp extends BaseDaoImpl<BillAnlianPosition, String>  implements BillAnlianPositionDao{

	@Autowired
	protected MongoTemplate mongoTemplate;
	
	public void save(BillAnlianPosition t){
		mongoTemplate.save(t);
	}

	@Override
	public List<BillAnlianPosition> findPosition(String shipmentno) {
		// TODO Auto-generated method stub
		List<BillAnlianPosition> rs = null;
		//拼装条件
		Query query =new Query();
		Criteria criteria =Criteria.where("shipmentno").is(shipmentno);
		query.addCriteria(criteria);
		//排序条件
		query.with(new Sort(Direction.ASC,"createtime"));
		rs = mongoTemplate.find(query, BillAnlianPosition.class);
		return rs;
	}
}
