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

import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.service.bean.Plan;
import com.tianrui.service.mongo.CodeGenDao;
import com.tianrui.service.mongo.PlanTemplateDao;

@Repository("planTemplateDao")
public class PlanTemplateDaoImpl extends BaseDaoImpl<Plan,String> implements PlanTemplateDao{

	@Autowired
	protected CodeGenDao codeGenDao;
	@Autowired
	protected MongoTemplate mongoTemplate;
	
	@Override
	public List<Plan>  findWithuId(String uId){
		List<Plan> rs =null;
		if( StringUtils.isNotBlank(uId) ){
			//拼装条件
			Query query =new Query();
			Criteria criteria =Criteria.where("creator").is(uId);
			query.addCriteria(criteria);
			//排序条件
			query.with(new Sort(Direction.DESC,"createtime"));
			rs=mongoTemplate.find(query, Plan.class);
		}
		return rs;
	}
	@Override
	public void save(Plan t){
		if (t !=null) {
			t.setId(UUIDUtil.getId());
			t.setPlancode(codeGenDao.codeGen(3));
		}
		mongoTemplate.save(t);
	}
	@Override
	public void remove(String uId){
		if( StringUtils.isNotBlank(uId) ){
			mongoTemplate.remove(new Query(Criteria.where("_id").is(uId)), Plan.class);
		}
	}
    //这种方法在id与_id对应后
	public Plan findOne(String id){
		Query query = new Query(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, Plan.class);
	}
}
