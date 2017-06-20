package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IOrgSignerService;
import com.tianrui.api.req.admin.OrgSignerFindReq;
import com.tianrui.api.req.admin.OrgSignerReq;
import com.tianrui.api.resp.admin.OrgSignerResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.OrgSigner;
import com.tianrui.service.mapper.OrgSignerMapper;

@Service
public class OrgSignerService implements IOrgSignerService{

	@Autowired
	OrgSignerMapper orgSignerMapper;
	
	@Override
	public Result insert(OrgSignerReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		OrgSigner record = new OrgSigner();
		PropertyUtils.copyProperties(record, req);
		record.setId(UUIDUtil.getId());
		record.setCreatetime(System.currentTimeMillis());
		orgSignerMapper.insertSelective(record);
		return rs;
	}

	@Override
	public Result delete(String id)throws Exception {
		Result rs = Result.getSuccessResult();
		orgSignerMapper.deleteByPrimaryKey(id);
		return rs;
	}

	@Override
	public Result update(OrgSignerReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		OrgSigner record = new OrgSigner();
		PropertyUtils.copyProperties(record, req);
		record.setUpttime(System.currentTimeMillis());
		orgSignerMapper.updateByPrimaryKeySelective(record);
		return rs;
	}

	@Override
	public PaginationVO<OrgSignerResp> select(OrgSignerFindReq req)throws Exception {
		PaginationVO<OrgSignerResp> page = new PaginationVO<OrgSignerResp>();
		OrgSigner record = new OrgSigner();
		PropertyUtils.copyProperties(record, req);
		if(req.getPageNo() != null){
			record.setPageNo(req.getPageNo()*req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		List<OrgSigner> list = orgSignerMapper.selectByCondition(record);
		Long a = orgSignerMapper.selectByCount(record);
		page.setTotal(a);
		page.setList(copyProperties2(list));
		return page;
	}
	
	protected List<OrgSignerResp> copyProperties2(List<OrgSigner> list)throws Exception{
		List<OrgSignerResp> resp = new ArrayList<OrgSignerResp>();
		for(OrgSigner org : list){
			OrgSignerResp sp = new OrgSignerResp();
			PropertyUtils.copyProperties(sp, org);
			resp.add(sp);
		}
		return resp;
	}

	@Override
	public List<OrgSignerResp> findCellphonr(String cellphone) throws Exception {
		OrgSigner org = new OrgSigner();
		org.setCellphone(cellphone);
		List<OrgSigner> list  = orgSignerMapper.selectByCondition(org);
		return copyProperties2(list);
	}

}
