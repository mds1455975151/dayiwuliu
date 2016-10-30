package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ICountAddService;
import com.tianrui.api.intf.ICountSumService;
import com.tianrui.api.req.count.CountAddReq;
import com.tianrui.api.req.count.CountSumReq;
import com.tianrui.api.resp.count.CountAddResp;
import com.tianrui.api.resp.count.CountSumResp;
import com.tianrui.service.bean.CountAdd;
import com.tianrui.service.bean.CountSum;
import com.tianrui.service.mapper.CountAddMapper;
import com.tianrui.service.mapper.CountSumMapper;
@Service
public class CountAddService implements ICountAddService{
	@Autowired
	CountAddMapper countAddMapper;
	
	@Override
	public List<CountAddResp> selectByCondition(CountAddReq req)throws Exception {
		// TODO Auto-generated method stub
		CountAdd add = new CountAdd();
		PropertyUtils.copyProperties(add, req);
		List<CountAdd> list = countAddMapper.selectByCondition(add);
		return copyPropertResp(list);
	}
	
	public List<CountAddResp> copyPropertResp(List<CountAdd> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<CountAddResp> t = new ArrayList<CountAddResp>();
		for(CountAdd sum : list){
			CountAddResp resp = new CountAddResp();
			PropertyUtils.copyProperties(resp, sum);
			t.add(resp);
		}
		return t;
	}

}
