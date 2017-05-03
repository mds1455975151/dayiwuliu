package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IFileVehicleNewService;
import com.tianrui.api.req.admin.vehicle_new.FileVehicleNewReq;
import com.tianrui.api.resp.admin.vehicle_new.FileVehicleNewResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.bean.vehiclereg.FileVehicleNew;
import com.tianrui.service.mapper.FileVehicleNewMapper;
@Service
public class FileVehicleNewService implements IFileVehicleNewService{

	@Autowired
	FileVehicleNewMapper fileVehicleNewMapper;
	
	@Override
	public PaginationVO<FileVehicleNewResp> find(FileVehicleNewReq req) throws Exception {
		PaginationVO<FileVehicleNewResp> page = new PaginationVO<FileVehicleNewResp>();
		
		FileVehicleNew record = new FileVehicleNew();
		PropertyUtils.copyProperties(record, req);
		if(req.getPageNo()!=null){
			record.setStart(req.getPageNo()*req.getPageSize());
			record.setLimit(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		Long a = fileVehicleNewMapper.selectByCount(record);
		List<FileVehicleNew> list = fileVehicleNewMapper.selectByContent(record);
		page.setList(copyProperties(list));
		page.setTotal(a);
		return page;
	}
	
	public List<FileVehicleNewResp> copyProperties(List<FileVehicleNew> list)throws Exception{
		List<FileVehicleNewResp> resp = new ArrayList<FileVehicleNewResp>();
		for(FileVehicleNew n : list){
			FileVehicleNewResp r = new FileVehicleNewResp();
			PropertyUtils.copyProperties(r, n);
			resp.add(r);
		}
		return resp;
	}
	
}
