package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ICargoService;
import com.tianrui.api.req.front.cargoplan.CargoReq;
import com.tianrui.api.resp.front.cargoplan.CargoResp;
import com.tianrui.service.admin.bean.FileCargo;
import com.tianrui.service.admin.mapper.FileCargoMapper;
@Service
public class CargoService implements ICargoService{
	@Autowired
	private FileCargoMapper fileCargoMapper;
	@Override
	public List<CargoResp> findCargoEntity(CargoReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		FileCargo cargo = new FileCargo();
		PropertyUtils.copyProperties(cargo, req);
		List<FileCargo> list = fileCargoMapper.selectCargoByCondition(cargo);
		List<CargoResp> resp = copyProperties(list);
		return resp;
	}
	
	public List<CargoResp> copyProperties(List<FileCargo> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<CargoResp> respList = new ArrayList<CargoResp>();
		for(FileCargo cargo : list){
			CargoResp resp = new CargoResp();
			PropertyUtils.copyProperties(resp, cargo);
			respList.add(resp);
		}
		return respList;
	}

	@Override
	public CargoResp findCargoById(String id) throws Exception {
		// TODO Auto-generated method stub
		FileCargo cargo = fileCargoMapper.selectByPrimaryKey(id);
		CargoResp resp = new CargoResp();
		PropertyUtils.copyProperties(resp, cargo);
		return resp;
	}

}
