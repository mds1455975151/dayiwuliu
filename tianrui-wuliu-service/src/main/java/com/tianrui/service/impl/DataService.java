package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IDataService;
import com.tianrui.api.req.data.WebDictReq;
import com.tianrui.api.resp.data.WebDictResp;
import com.tianrui.service.bean.AnlianDict;
import com.tianrui.service.mapper.AnlianDictMapper;
@Service
public class DataService implements IDataService{

	@Autowired
	AnlianDictMapper anlianDictMapper;
	
	@Override
	public List<WebDictResp> find(WebDictReq req) throws Exception {
		// TODO Auto-generated method stub
		AnlianDict ad = new AnlianDict();
		
		PropertyUtils.copyProperties(ad, req);
		 List<AnlianDict> list = anlianDictMapper.selectByCondition(ad);
		
		return copy(list);
	}
	/** 类型转换*/
	public List<WebDictResp> copy(List<AnlianDict> list) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<WebDictResp> wd = new ArrayList<WebDictResp>();
		for(AnlianDict ad : list){
			WebDictResp w = new WebDictResp();
			PropertyUtils.copyProperties(w, ad);
			wd.add(w);
		}
		return wd;
	}

}
