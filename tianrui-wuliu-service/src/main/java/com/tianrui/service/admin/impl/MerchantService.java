package com.tianrui.service.admin.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IMerchantService;
import com.tianrui.api.req.admin.merchant.MerchantReq;
import com.tianrui.api.resp.admin.merchant.MerchantResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Merchant;
import com.tianrui.service.admin.mapper.MerchantMapper;

@Service
public class MerchantService implements IMerchantService {

	@Autowired
	MerchantMapper merchantMapper;
	
	@Override
	public PaginationVO<MerchantResp> find(MerchantReq req)throws Exception {
		PaginationVO<MerchantResp> page = new PaginationVO<MerchantResp>();
		Merchant record = new Merchant();
		PropertyUtils.copyProperties(record, req);
		long a = merchantMapper.selectByCount(record);
		page.setTotal(a);
		if(a != (long)0){
			List<Merchant> merchant = merchantMapper.selectByCondition(record);
			page.setList(copyProperties(merchant));
		}else{
			page.setList(null);
		}
		return page;
	}
	
	public List<MerchantResp> copyProperties(List<Merchant> merchant) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<MerchantResp> list = new ArrayList<MerchantResp>();
		for(Merchant m : merchant){
			MerchantResp resp = new MerchantResp();
			PropertyUtils.copyProperties(resp, m);
			list.add(resp);
		}
		return list;
	}

	@Override
	public Result insert(MerchantReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if(!code(req.getCode())){
			rs.setCode("2");
			rs.setError("客商编码已存在");
			return rs;
		}
		if(!onlycode(req.getOnlycode())){
			rs.setCode("3");
			rs.setError("唯一识别码已存在");
			return rs;
		}
		Merchant record = new Merchant();
		PropertyUtils.copyProperties(record, req);
		int a = merchantMapper.insertSelective(record);
		if(a!=1){
			rs.setCode("1");
			rs.setError("操作失败");
		}
		return rs;
	}
	/** 验证唯一识别码*/
	public boolean onlycode(String onlycode){
		Merchant mer = new Merchant();
		mer.setCode(onlycode);
		Long a = merchantMapper.selectByOnly(mer);
		if(a == (long) 0){
			return true;
		}
		return false;
	}
	/** 验证编码*/
	public boolean code(String code){
		Merchant mer = new Merchant();
		mer.setCode(code);
		Long a = merchantMapper.selectByOnly(mer);
		if(a == (long) 0){
			return true;
		}
		return false;
	}
	

	@Override
	public Result update(MerchantReq req)throws Exception {
		Result rs = Result.getSuccessResult();
		Merchant record = new Merchant();
		PropertyUtils.copyProperties(record, req);
		int a = merchantMapper.updateByPrimaryKeySelective(record);
		if(a!=1){
			rs.setCode("1");
			rs.setError("操作失败");
		}
		return rs;
	}

	@Override
	public Result delete(MerchantReq req)throws Exception {
		Result rs = Result.getSuccessResult();
		Merchant record = new Merchant();
		record.setId(req.getId());
		record.setDesc1("0");
		int a = merchantMapper.updateByPrimaryKeySelective(record);
		if(a!=1){
			rs.setCode("1");
			rs.setError("操作失败");
		}
		return rs;
	}

	@Override
	public MerchantResp findByid(String id) throws Exception {
		Merchant mer = merchantMapper.selectByPrimaryKey(id);
		MerchantResp resp = new MerchantResp();
		PropertyUtils.copyProperties(resp, mer);
		return resp;
	}

}
