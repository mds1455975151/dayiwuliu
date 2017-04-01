package com.tianrui.service.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.tianrui.service.bean.VehicleReg;
import com.tianrui.service.mongo.VehicleRegDao;

@Repository("vehicleRegDao")
public class VehicleRegDaoImpl extends BaseDaoImpl<VehicleReg, String>  implements VehicleRegDao{

	@Autowired
	protected MongoTemplate mongoTemplate;
	

	public VehicleRegDaoImpl() {
		super(VehicleReg.class);
	}

	

}
