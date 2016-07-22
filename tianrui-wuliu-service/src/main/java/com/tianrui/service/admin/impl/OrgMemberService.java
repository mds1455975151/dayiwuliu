package com.tianrui.service.admin.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IOrgMemberService;
import com.tianrui.api.req.admin.OrgMemberReq;
import com.tianrui.api.resp.admin.OrgMemberResp;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.service.admin.bean.OrgMember;
import com.tianrui.service.admin.mapper.OrgMemberMapper;
@Service
public class OrgMemberService implements IOrgMemberService{
	@Autowired
	private OrgMemberMapper orgMemberMapper;
	@Override
	public boolean saveEntity(OrgMemberReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		// TODO Auto-generated method stub
		OrgMember om = new OrgMember();
		PropertyUtils.copyProperties(om, req);
		if(orgMemberMapper.insertEntity(om)!=1){
			return false;
		}
		return true;
	}
	@Override
	public PageResp<OrgMemberResp> findByEntity(OrgMemberReq req) throws Exception {
		// TODO Auto-generated method stub
		OrgMember member = new OrgMember();
		PropertyUtils.copyProperties(member, req);
		List<OrgMember> list = orgMemberMapper.findByEntity(member);
		long count = orgMemberMapper.findByEntityCount(member);
		PageResp<OrgMemberResp> page = new PageResp<OrgMemberResp>();
		page.setCount(count);
		page.setList(copyProperties(list));
		page.setPageNo(req.getPageNo());
		page.setPageSize(req.getPageSize());
		return page;
	}
	
	public List<OrgMemberResp> copyProperties(List<OrgMember> list)throws Exception{
		List<OrgMemberResp> resp = new ArrayList<OrgMemberResp>();
		for(OrgMember member : list){
			OrgMemberResp r = new OrgMemberResp();
			PropertyUtils.copyProperties(r, member);
			resp.add(r);
		}
		return resp;
	}
	@Override
	public boolean updateEntity(OrgMemberReq req) throws Exception {
		// TODO Auto-generated method stub
		OrgMember member = new OrgMember();
		PropertyUtils.copyProperties(member, req);
		if(orgMemberMapper.update(member)==1){
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteEntity(String id) throws Exception {
		// TODO Auto-generated method stub
		int a = orgMemberMapper.delectById(id);
		if(a==1){
			return true;
		}
		return false;
	}
	@Override
	public OrgMemberResp findById(String id) throws Exception {
		// TODO Auto-generated method stub
		OrgMember om = orgMemberMapper.findById(id);
		OrgMemberResp resp = new OrgMemberResp();
		PropertyUtils.copyProperties(resp, om);
		return resp;
	}
	@Override
	public List<OrgMemberResp> findlist(OrgMemberReq req) throws Exception {
		// TODO Auto-generated method stub
		OrgMember member = new OrgMember();
		PropertyUtils.copyProperties(member, req);
		List<OrgMember> list = orgMemberMapper.findByEntity(member);
		return copyProperties(list);
	}
}
