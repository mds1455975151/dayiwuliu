package com.tianrui.service.admin.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IMyVehicleService;
import com.tianrui.api.req.admin.MyVehicleReq;
import com.tianrui.api.resp.admin.MyVehicleResp;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.MyVehicle;
import com.tianrui.service.admin.mapper.MyVehicleMapper;
import com.tianrui.service.bean.MemberVehicle;
import com.tianrui.service.mapper.MemberVehicleMapper;
/**
 * 
 * @类描述：后台管理司机查询
 * @创建人：lsj
 * @创建时间：2016年6月3日上午11:30:04
 *
 * @修改人：lsj
 * @修改时间：2016年6月3日上午11:30:04
 * @修改备注：
 *
 */
@Service
public class MyVehicleService implements IMyVehicleService{

	@Autowired
	private MyVehicleMapper vehicleMapper;
	@Autowired
	MemberVehicleMapper memberVehicleMapper;

	@Override
	public PageResp findByEntity(MyVehicleReq req) throws Exception {
		// TODO Auto-generated method stub
		MyVehicle vehicle = new MyVehicle();
		PropertyUtils.copyProperties(vehicle, req);
		List<MyVehicle> list = vehicleMapper.findByEntity(vehicle);
		long count = vehicleMapper.findByEntityCount(vehicle);
		PageResp page = new PageResp();
		page.setPageNo(req.getPageNo());
		page.setPageSize(req.getPageSize());
		page.setCount(count);
		page.setList(copyProperties(list));
		return page;
	}
	
	public List<MyVehicleResp> copyProperties(List<MyVehicle> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<MyVehicleResp> resp = new ArrayList<MyVehicleResp>();
		for(MyVehicle vehicle : list){
			MyVehicleResp myresp = new MyVehicleResp();
			PropertyUtils.copyProperties(myresp, vehicle);
			resp.add(myresp);
		}
		return resp;
	}

	@Override
	public MyVehicleResp findById(String id) throws Exception {
		// TODO Auto-generated method stub
		MyVehicle m = vehicleMapper.findByid(id);
//		MemberVehicle m = memberVehicleMapper.selectByPrimaryKey(id);
		MyVehicleResp resp = new MyVehicleResp();
		PropertyUtils.copyProperties(resp, m);
		return resp;
	}

	@Override
	public Result updateVehicle(MyVehicleReq req) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		MemberVehicle record = new MemberVehicle();
		record.setId(req.getId());
		record.setRoadtransportcode(req.getRoadtransportcode());
		memberVehicleMapper.updateByPrimaryKeySelective(record);
		return rs;
		
		
	}
}
