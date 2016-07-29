package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IFreightService;
import com.tianrui.api.req.front.cargoplan.FreightReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.cargoplan.FreightResp;
import com.tianrui.api.resp.front.cargoplan.FreightlistResp;
import com.tianrui.api.resp.front.cargoplan.FreightsResp;
import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.bean.Freight;
import com.tianrui.service.admin.mapper.FileFreightMapper;
@Service
public class FreightService implements IFreightService{
	@Autowired
	private FileFreightMapper fileFreightMapper;
	@Override
	public List<FreightResp> findByEntity(FreightReq req) throws Exception{
		FileFreight freight = new FileFreight();
		PropertyUtils.copyProperties(freight, req);
		List<FileFreight> list = fileFreightMapper.findByEntity(freight);
		return copyProperties(list);
	}
	
	public List<FreightResp> findByStatus(FreightReq req) throws Exception{
		FileFreight freight = new FileFreight();
		PropertyUtils.copyProperties(freight, req);
		List<FileFreight> list = fileFreightMapper.selectByStatus(freight);
		return copyProperties(list);
	}
	
	public List<FreightResp> copyProperties(List<FileFreight> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<FreightResp> resplist = new ArrayList<FreightResp>();
		for(FileFreight freight : list){
			FreightResp resp = new FreightResp();
			PropertyUtils.copyProperties(resp, freight);
			resplist.add(resp);
		}
		return resplist;
	}

	@Override
	public FreightResp findById(String id) throws Exception {
		FileFreight freight = fileFreightMapper.selectByPrimaryKey(id);
		FreightResp resp = new FreightResp();
		PropertyUtils.copyProperties(resp, freight);
		return resp;
	}

	@Override
	public boolean delctcByIdStr(String idStr) throws Exception {
		return false;
	}

	@Override
	public PageResp<FreightlistResp> findByLisrEntity(FreightReq req) throws Exception {
		FileFreight freight = new FileFreight();
		PropertyUtils.copyProperties(freight, req);
		List<FileFreight> list = fileFreightMapper.findByToName(freight);
		long count = fileFreightMapper.findByToNameCount(freight);
		PageResp<FreightlistResp> page = new PageResp<FreightlistResp>(); 
		page.setCount(count);
		page.setPageNo(req.getPageNo());
		page.setPageSize(req.getPageSize());
		page.setList(copyPropertiesL(list));
		return page;
	}
	
	public List<FreightlistResp> copyPropertiesL(List<FileFreight> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<FreightlistResp> resplist = new ArrayList<FreightlistResp>();
		for(FileFreight freight : list){
			FreightlistResp resp = new FreightlistResp();
			PropertyUtils.copyProperties(resp, freight);
			resplist.add(resp);
		}
		return resplist;
	}

	@Override
	public boolean saveEntity(FreightReq req) throws Exception {
		FileFreight freight = new FileFreight();
		PropertyUtils.copyProperties(freight, req);
		if(fileFreightMapper.insertSelective(freight)!=1){
			return false;
		}
		return true;
	}

	@Override
	public boolean updateEntity(FreightReq req) throws Exception {
		FileFreight freight = new FileFreight();
		PropertyUtils.copyProperties(freight, req);
		if(fileFreightMapper.updateByPrimaryKeySelective(freight)==1){
			return true;
		}
		return false;
	}

	@Override
	public FreightsResp findsById(String id) throws Exception {
		Freight f = fileFreightMapper.selectByKey(id);
		FreightsResp r = new FreightsResp();
		PropertyUtils.copyProperties(r, f);
		return r;
	}

	@Override
	public String findByName(String desc1Name) throws Exception {
		FileFreight freight = new FileFreight();
		freight.setFreightName(desc1Name);
		List<FileFreight> ls =  fileFreightMapper.findByName(freight);
		if(ls != null && ls.size() > 0){
			return ls.get(0).getId();
		}
		return null;
	}

	@Override
	public boolean batchUpdate(FreightReq req) throws Exception {
		// TODO Auto-generated method stub
		FileFreight freight = new FileFreight();
		PropertyUtils.copyProperties(freight, req);
		int a = fileFreightMapper.batchUpdateByPrimaryKeySelective(freight);
		if(a!=0){
			return true;
		}
		return false;
		
	}

}
