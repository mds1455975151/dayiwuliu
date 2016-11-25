package com.tianrui.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ICountAdminService;
import com.tianrui.api.req.count.CountAdminReq;
import com.tianrui.api.resp.count.CountAdminResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.CountAdmin;
import com.tianrui.service.mapper.CountAdminMapper;
@Service
public class CountAdminService implements ICountAdminService{
	@Autowired
	CountAdminMapper countAdminMapper;
	
	@Override
	public List<CountAdminResp> find(CountAdminReq req) throws Exception{
		CountAdmin record = new CountAdmin();
		PropertyUtils.copyProperties(record, req);
		List<CountAdmin> list = countAdminMapper.selectByCondition(record);
		return copyProperties(list);
	}
	@Override
	public List<CountAdminResp> findWithType(String type) throws Exception{
		CountAdmin record = new CountAdmin();
		record.setType(type);
		List<CountAdmin> list = countAdminMapper.selectByCondition(record);
		return copyProperties(list);
	}

	public List<CountAdminResp> copyProperties(List<CountAdmin> list)throws Exception{
		List<CountAdminResp> resp = new ArrayList<CountAdminResp>();
		for(CountAdmin con : list){
			CountAdminResp ca = new CountAdminResp();
			PropertyUtils.copyProperties(ca, con);
			resp.add(ca);
		}
		return resp;
	} 
	
	@Override
	public CountAdminResp findId(String id)throws Exception{
		CountAdmin count = countAdminMapper.selectByPrimaryKey(id);
		CountAdminResp resp = new CountAdminResp();
		PropertyUtils.copyProperties(resp, count);
		return resp;
	}

	@Override
	public boolean save(CountAdminReq req) throws Exception{
		CountAdmin record = new CountAdmin();
		PropertyUtils.copyProperties(record, req);
		int a = countAdminMapper.insertSelective(record);
		if(a == 1){
			return true;
		}
		return false;
	}

	@Override
	public boolean update(CountAdminReq req) throws Exception{
		CountAdmin record = new CountAdmin();
		PropertyUtils.copyProperties(record, req);
		int a = countAdminMapper.updateByPrimaryKeySelective(record);
		
		if(a==1){
			return true;
		}
		return false;
	}
	@Override
	public Result updateWithId(String id,String val) throws Exception{
		Result rs =Result.getErrorResult();
		CountAdmin countAdmin=countAdminMapper.selectByPrimaryKey(id);
		if( countAdmin !=null ){
			CountAdmin update = new CountAdmin();
			update.setId(id);
			update.setData(val);
			countAdminMapper.updateByPrimaryKeySelective(update);
			rs=Result.getSuccessResult();
		}
		
		return rs;
	}

	@Override
	public boolean delete(String id) throws Exception{
		int a = countAdminMapper.deleteByPrimaryKey(id);
		if(a == 1){
			return true;
		}
		return false;
	}

}
