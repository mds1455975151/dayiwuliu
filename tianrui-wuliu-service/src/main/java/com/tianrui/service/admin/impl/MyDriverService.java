package com.tianrui.service.admin.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IMyDriverService;
import com.tianrui.api.req.admin.MyDriverReq;
import com.tianrui.api.resp.admin.MyDriverResp;
import com.tianrui.service.admin.bean.MyDriver;
import com.tianrui.service.admin.mapper.MyDriverMapper;
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
public class MyDriverService implements IMyDriverService{

	@Autowired
	private MyDriverMapper driverMapper;

	@Override
	public List<MyDriverResp> findByEntity(MyDriverReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		MyDriver driver = new MyDriver();
		PropertyUtils.copyProperties(driver, req);
		List<MyDriver> list = driverMapper.findByEntity(driver);
		return copyProperties(list);
	}
	
	public List<MyDriverResp> copyProperties(List<MyDriver> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<MyDriverResp> resp = new ArrayList<MyDriverResp>();
		for(MyDriver driver : list){
			MyDriverResp re = new MyDriverResp();
			PropertyUtils.copyProperties(re, driver);
			resp.add(re);
		}
		return resp;
	}
}
