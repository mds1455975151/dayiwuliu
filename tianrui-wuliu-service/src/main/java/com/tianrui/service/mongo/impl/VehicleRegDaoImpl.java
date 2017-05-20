package com.tianrui.service.mongo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.tianrui.api.req.admin.vehicle_new.FileVehicleNewReq;
import com.tianrui.api.req.admin.vehicle_new.VehicleMGoReq;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.bean.BillPosition;
import com.tianrui.service.bean.VehicleReg;
import com.tianrui.service.mongo.VehicleRegDao;

@Repository("vehicleRegDao")
public class VehicleRegDaoImpl extends BaseDaoImpl<VehicleReg, String>  implements VehicleRegDao{

	@Autowired
	protected MongoTemplate mongoTemplate;
	

	public VehicleRegDaoImpl() {
		super(VehicleReg.class);
	}
	
	public PaginationVO<VehicleReg> findVehicleReq(VehicleMGoReq req){
		PaginationVO<VehicleReg> page = new PaginationVO<VehicleReg>();
		List<VehicleReg> list = new ArrayList<VehicleReg>();
		Query query =new Query();
		query.with(new Sort(Direction.DESC,"createtime"));
		//不分页
		list=mongoTemplate.find(query, VehicleReg.class);
		page.setTotal(list.size());
		if(req.getPageNo()!=null){
			query.skip(req.getPageNo()*req.getPageSize());
			query.limit(req.getPageSize());
			list=mongoTemplate.find(query, VehicleReg.class);
			page.setList(list);
		}else{
			page.setList(list);
		}
		
		return page;
	}

	@Override
	public VehicleReg findVehicleById(String id) {
		// TODO Auto-generated method stub
		VehicleReg resp = new VehicleReg();
		resp=mongoTemplate.findById(id, VehicleReg.class);
		return resp;
	}

	

}
