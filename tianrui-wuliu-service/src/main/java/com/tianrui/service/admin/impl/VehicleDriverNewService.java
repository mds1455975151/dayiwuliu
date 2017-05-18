package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IVehicleDriverNewService;
import com.tianrui.api.req.admin.vehicle_new.VehicleDriverNewReq;
import com.tianrui.api.resp.admin.vehicle_new.VehicleDriverNewResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.bean.vehiclereg.FileVehicleNew;
import com.tianrui.service.bean.vehiclereg.VehicleDriverNew;
import com.tianrui.service.mapper.FileVehicleNewMapper;
import com.tianrui.service.mapper.VehicleDriverNewMapper;
@Service
public class VehicleDriverNewService implements IVehicleDriverNewService{

	@Autowired 
	VehicleDriverNewMapper vehicleDriverNewMapper;
	
	@Autowired
	FileVehicleNewMapper fileVehicleNewMapper;

	@Override
	public PaginationVO<VehicleDriverNewResp> find(VehicleDriverNewReq req) throws Exception {
		// TODO Auto-generated method stub
		PaginationVO<VehicleDriverNewResp> page = new PaginationVO<VehicleDriverNewResp>();
		
		VehicleDriverNew record = new VehicleDriverNew();
		PropertyUtils.copyProperties(record, req);
		List<VehicleDriverNew> list = vehicleDriverNewMapper.selectByCondition(record);
		Long a = vehicleDriverNewMapper.countByCondition(record);
		page.setTotal(a);
		page.setList(copyProperties2(list));
		return page;
	}
	
	protected List<VehicleDriverNewResp> copyProperties2(List<VehicleDriverNew> list)throws Exception{

		List<VehicleDriverNewResp> resp = new ArrayList<VehicleDriverNewResp>();
		for(VehicleDriverNew w : list){
			VehicleDriverNewResp r = new VehicleDriverNewResp();
			PropertyUtils.copyProperties(r, w);
			FileVehicleNew veh = fileVehicleNewMapper.selectByPrimaryKey(w.getVehicleid());
			r.setVehicleno(veh.getVehicleno());
			resp.add(r);
		}
		return resp;
	}

	@Override
	public VehicleDriverNewResp selectBykey(String id) throws Exception {
		VehicleDriverNew vd = vehicleDriverNewMapper.selectByPrimaryKey(id);
		VehicleDriverNewResp resp = new VehicleDriverNewResp();
		PropertyUtils.copyProperties(resp, vd);
		
		return resp;
	}
}
