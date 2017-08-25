package com.tianrui.service.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.api.req.admin.ZJXLVehicleReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.ZJXLVehicleResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.ZJXLVehicle;
import com.tianrui.service.mapper.ZJXLVehicleMapper;

@Service
public class CrossVehicleService implements ICrossVehicleService{

	@Autowired
	ZJXLVehicleMapper zjxlVehicleMapper;

	@Override
	public PageResp<ZJXLVehicleResp> find(ZJXLVehicleReq req) throws Exception {
		// TODO Auto-generated method stub
		ZJXLVehicle zjxlVehicle = new ZJXLVehicle();
		
		zjxlVehicle.setPageNo(req.getPageNo());
		zjxlVehicle.setPageSize(req.getPageSize());
		zjxlVehicle.setLimit(req.getLimit());
		List<ZJXLVehicle> list = zjxlVehicleMapper.selectByCondition(zjxlVehicle);
		long a = zjxlVehicleMapper.findsZJXLVehicleListCount(zjxlVehicle);
		PageResp<ZJXLVehicleResp> page = new PageResp<ZJXLVehicleResp>();
		page.setList(copyProperties(list));
		page.setPageNo(req.getPageNo());
		page.setPageSize(req.getPageSize());
		page.setCount(a);
		return page;
	} 
	
	public List<ZJXLVehicleResp> copyProperties(List<ZJXLVehicle> list)throws Exception{
		List<ZJXLVehicleResp> resp = new ArrayList<ZJXLVehicleResp>();
		for(ZJXLVehicle con : list){
			ZJXLVehicleResp ca = new ZJXLVehicleResp();
			PropertyUtils.copyProperties(ca, con);
			resp.add(ca);
		}
		return resp;
	}

	@Override
	public Result insert(ZJXLVehicleReq req) throws Exception {
		ZJXLVehicle  record = new ZJXLVehicle();
		record.setVehicleid(req.getVehicleid());
		record.setVehiclelogo(req.getVehiclelogo());
		record.setVehicleno(req.getVehicleno());
		zjxlVehicleMapper.insertSelective(record);
		return null;
	} 
}
