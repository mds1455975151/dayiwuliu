package com.tianrui.service.impl.new_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.davidcarboni.cryptolite.HashMac;
import com.tianrui.api.intf.new_.IMemberVehicleNewService;
import com.tianrui.api.req.new_.MemberVehicleNewReq;
import com.tianrui.api.req.new_.V_vehicle_driverReq;
import com.tianrui.api.resp.new_.V_vehicle_driverResp;
import com.tianrui.api.resp.new_.VehicleNewResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.V_vehicle_driver;
import com.tianrui.service.bean.vehiclereg.FileVehicleNew;
import com.tianrui.service.bean.vehiclereg.MemberVehicleNew;
import com.tianrui.service.bean.vehiclereg.VehicleDriverNew;
import com.tianrui.service.mapper.FileVehicleNewMapper;
import com.tianrui.service.mapper.MemberVehicleNewMapper;
import com.tianrui.service.mapper.V_vehicle_driverMapper;
import com.tianrui.service.mapper.VehicleDriverNewMapper;

@Service
public class MemberVehicleNewService implements IMemberVehicleNewService{

	@Autowired
	V_vehicle_driverMapper vehicle_driverMapper;
	@Autowired
	FileVehicleNewMapper fileVehicleNewMapper;
	@Autowired
	VehicleDriverNewMapper vehicleDriverNewMapper;
	@Autowired
	MemberVehicleNewMapper memberVehicleNewMapper;
	
	@Override
	public PaginationVO<V_vehicle_driverResp> select(V_vehicle_driverReq req) throws Exception{
		PaginationVO<V_vehicle_driverResp> page = new PaginationVO<V_vehicle_driverResp>();
		V_vehicle_driver v = new V_vehicle_driver();
		PropertyUtils.copyProperties(v, req);
		if(req.getPageNo()!=null){
			v.setPageNo(req.getPageNo()*req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		List<V_vehicle_driver> list = vehicle_driverMapper.selectByCondition(v);
		long a = vehicle_driverMapper.selectBycount(v);
		page.setList(copyProperties2(list));
		page.setTotal(a);
		return page;
	}
	
	protected List<V_vehicle_driverResp> copyProperties2(List<V_vehicle_driver> list) throws Exception{
		List<V_vehicle_driverResp> resp = new ArrayList<V_vehicle_driverResp>();
		for(V_vehicle_driver v : list){
			V_vehicle_driverResp r = new V_vehicle_driverResp();
			PropertyUtils.copyProperties(r, v);
			resp.add(r);
		}
		return resp;
	}

	@Override
	public Result selectVehicle(String vehicleno) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		
		FileVehicleNew vehicle = new FileVehicleNew();
		vehicle.setVehicleno(vehicleno);
		List<FileVehicleNew> list = fileVehicleNewMapper.selectByContent(vehicle);
		if(list.size()==1){
			FileVehicleNew nv = list.get(0);
			VehicleDriverNew driver = vehicleDriverNewMapper.selectByPrimaryKey(nv.getDriverid());
			VehicleNewResp resp = new VehicleNewResp();
			resp.setDriverid(driver.getId());
			resp.setDrivername(driver.getDrivername());
			resp.setDrivertel(driver.getDriverlinktel());
			resp.setVehicleid(nv.getId());
			resp.setVehicletype(nv.getVehicletype());
			rs.setData(resp);
		}else{
			rs.setCode("1");
			rs.setError("无此车辆");
		}
		return rs;
	}

	@Override
	public Result saveMyVehicle(MemberVehicleNewReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MemberVehicleNew record = new MemberVehicleNew();
		record.setId(UUIDUtil.getId());
		record.setMemberid(req.getMemberid());
		record.setVehicleid(req.getVehicleid());
		record.setCreatetime(System.currentTimeMillis());
		memberVehicleNewMapper.insertSelective(record);
		return rs;
	}

	@Override
	public Result delect(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		memberVehicleNewMapper.deleteByPrimaryKey(id);
		return rs;
	}

}
