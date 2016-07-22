package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IRouteService;
import com.tianrui.api.req.front.cargoplan.RouteReq;
import com.tianrui.api.resp.front.cargoplan.RouteResp;
import com.tianrui.service.admin.bean.FileCargo;
import com.tianrui.service.admin.bean.FileRoute;
import com.tianrui.service.admin.mapper.FileCargoMapper;
import com.tianrui.service.admin.mapper.FileRouteMapper;
@Service
public class RouteService implements IRouteService{

	@Autowired
	private FileCargoMapper fileCargoMapper;
	@Autowired
	private FileRouteMapper fileRouteMapper;
	@Override
	public List<RouteResp> findRouteCgId(String id) throws Exception{
		FileCargo cargo = fileCargoMapper.selectByPrimaryKey(id);
		FileRoute route = new FileRoute();
		route.setOrganizationid(cargo.getOrganizationid());
		List<FileRoute> list = fileRouteMapper.selectByCondition(route);
		
		return copyProperties(list);
	}
	
	public List<RouteResp> copyProperties(List<FileRoute> list) throws Exception, InvocationTargetException, NoSuchMethodException{
		List<RouteResp> resp = new ArrayList<RouteResp>();
		for(FileRoute route : list){
			RouteResp sp = new RouteResp();
			PropertyUtils.copyProperties(sp, route);
			resp.add(sp);
		}
		return resp;
	}

	@Override
	public RouteResp findRouteById(String id) throws Exception {
		FileRoute fileRoute = fileRouteMapper.selectByPrimaryKey(id); 
		RouteResp route = new RouteResp();
		PropertyUtils.copyProperties(route, fileRoute);
		return route;
	}

	@Override
	public List<RouteResp> findRouteByEntity(RouteReq entity) throws Exception {
		FileRoute route = new FileRoute();
		PropertyUtils.copyProperties(route, entity);
		List<FileRoute> list = fileRouteMapper.selectByCondition(route);
		return copyProperties(list);
	}

}
