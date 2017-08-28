package com.tianrui.service.mongo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.tianrui.service.bean.VehicleGpsZjxl;
import com.tianrui.service.mongo.VehicleGpsZjxlDao;


@Repository("vehicleGpsZjxlDao")
public class VehicleGpsZjxlDaoImpl extends BaseDaoImpl<VehicleGpsZjxl, String>  implements VehicleGpsZjxlDao{

	@Autowired
	protected MongoTemplate mongoTemplate;
	
	public void save(VehicleGpsZjxl t){
		mongoTemplate.save(t);
	}


	@Override
	public List<VehicleGpsZjxl> getVehicleTrack(String vehicleNo, long beginTime, long endTime) {
		List<VehicleGpsZjxl> rs =null;
		if( StringUtils.isNotBlank(vehicleNo) && beginTime !=0 && endTime!=0  ){
			//TODO 只能获取3天内的数据
			Query query =new Query();
			
			Criteria criteria =Criteria.where("utc").gt(beginTime).lt(endTime).and("vehicleNo").is(vehicleNo);
			query.addCriteria(criteria);
			
			query.with(new Sort(Direction.ASC,"utc"));
			rs = mongoTemplate.find(query, VehicleGpsZjxl.class);
		}
		
		return rs;
	}

	@Override
	public VehicleGpsZjxl getVehiclePosition(String vehicleNo) {
		VehicleGpsZjxl bean =null;
		if( StringUtils.isNotBlank(vehicleNo)  ){
			Query query =new Query();
			Criteria criteria =Criteria.where("vehicleNo").is(vehicleNo);
			query.addCriteria(criteria);
			query.with(new Sort(Direction.DESC,"utc"));
			query.limit(1);
			List<VehicleGpsZjxl> rs=mongoTemplate.find(query, VehicleGpsZjxl.class);
			if( CollectionUtils.isNotEmpty(rs)){
				bean=rs.get(0);
			}
		}
		return bean;
	}

}
