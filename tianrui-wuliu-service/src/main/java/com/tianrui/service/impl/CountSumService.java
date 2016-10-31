package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ICountSumService;
import com.tianrui.api.req.count.CountSumReq;
import com.tianrui.api.resp.count.CountSumResp;
import com.tianrui.service.bean.CountSum;
import com.tianrui.service.mapper.CountSumMapper;
@Service
public class CountSumService implements ICountSumService{
	@Autowired
	CountSumMapper countSumMapper;
	
	@Override
	public List<CountSumResp> selectByCondition(CountSumReq req)throws Exception {
		// TODO Auto-generated method stub
		CountSum sum = new CountSum();
		PropertyUtils.copyProperties(sum, req);
		List<CountSum> list = countSumMapper.selectByCondition(sum);
		return copyPropertResp(list);
	}
	
	public List<CountSumResp> copyPropertResp(List<CountSum> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<CountSumResp> t = new ArrayList<CountSumResp>();
		for(CountSum sum : list){
			CountSumResp resp = new CountSumResp();
			PropertyUtils.copyProperties(resp, sum);
			t.add(resp);
		}
		return t;
	}

}
