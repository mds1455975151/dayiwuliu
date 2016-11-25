package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ICountRouteTableService;
import com.tianrui.api.req.count.RouteTableReq;
import com.tianrui.api.resp.count.RouteTableResp;
import com.tianrui.service.bean.CountRouteTable;
import com.tianrui.service.mapper.CountRouteTableMapper;
@Service
public class CountRouteTableService implements ICountRouteTableService{

	@Autowired
	CountRouteTableMapper countRouteTableMapper;
	
	@Override
	public List<RouteTableResp> find(RouteTableReq req) throws Exception {
		CountRouteTable record = new CountRouteTable();
		PropertyUtils.copyProperties(record, req);
		record.setLimit(req.getPageNo());
		List<CountRouteTable> list = countRouteTableMapper.selectByCondition(record);
		return copyProperties(list);
	}
	
	public List<RouteTableResp> copyProperties(List<CountRouteTable> list) throws Exception{
		List<RouteTableResp> resp = new ArrayList<RouteTableResp>();
		
		for(CountRouteTable cr : list){
			RouteTableResp a = new RouteTableResp();
			PropertyUtils.copyProperties(a, cr);
			resp.add(a);
		}
		return resp;
	}

	@Override
	public boolean update(RouteTableReq req) throws Exception {
		CountRouteTable record = new CountRouteTable();
		PropertyUtils.copyProperties(record, req);
		int a = countRouteTableMapper.updateByPrimaryKeySelective(record);
		if(a==1){
			return true;
		}
		return false;
	}

}
