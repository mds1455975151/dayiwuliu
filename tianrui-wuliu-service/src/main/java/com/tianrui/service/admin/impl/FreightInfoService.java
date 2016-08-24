package com.tianrui.service.admin.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IFreightInfoService;
import com.tianrui.api.req.admin.freight.AdminFreightReq;
import com.tianrui.api.resp.admin.freight.AdminFreightResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.admin.bean.FileFreight;
import com.tianrui.service.admin.bean.Freight;
import com.tianrui.service.admin.bean.FreightInfo;
import com.tianrui.service.admin.mapper.FreightInfoMapper;

@Service
public class FreightInfoService implements IFreightInfoService{

	@Autowired
	FreightInfoMapper freightInfoMapper;
	
	@Override
	public PaginationVO<AdminFreightResp> find(AdminFreightReq req) throws Exception {
		// TODO Auto-generated method stub
		FreightInfo record = new FreightInfo();
		PropertyUtils.copyProperties(record, req);
		List<FileFreight> list = freightInfoMapper.selectByCondition(record);
		PaginationVO<AdminFreightResp> vo = new PaginationVO<AdminFreightResp>();
		vo.setList(copyProperties(list));
		return vo;
	}
	
	protected List<AdminFreightResp> copyProperties(List<FileFreight> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<AdminFreightResp> resp = new ArrayList<AdminFreightResp>();
		for(FileFreight info : list){
			AdminFreightResp ad = new AdminFreightResp();
			PropertyUtils.copyProperties(ad, info);
			resp.add(ad);
		}
		return resp;
	}
	
	

}
