package com.tianrui.service.impl;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberVoService;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.FileOrg;
import com.tianrui.service.admin.mapper.FileOrgMapper;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberMapper;

@Service
public class MemberVoService implements IMemberVoService{

	@Autowired
	CacheClient cacheClient;
	@Autowired
	SystemMemberMapper memberMapper;
	@Autowired
	SystemMemberInfoMapper memberInfoMapper;
	@Autowired
	FileOrgMapper fileOrgMapper;
	
	@Override
	public MemberVo get(String id) {
		MemberVo vo =null;
		if( StringUtils.isNotBlank(id) ){
			String key =CacheHelper.buildKey(CacheModule.MEMBERVO,id);
			vo = cacheClient.getObj(key, MemberVo.class);
			if( vo ==null ){
				vo =getMemberVo(id);
				cacheClient.saveObject(key, vo);
			}
		}
		
		return vo;
	}

	@Override
	public MemberVo get(String id, boolean isFlush) {
		MemberVo vo = null;
		if (StringUtils.isNotBlank(id)) {
			String key = CacheHelper.buildKey(CacheModule.MEMBERVO, id);
			if (isFlush) {
				vo = getMemberVo(id);
				cacheClient.saveObject(key, vo);
			} else {
				vo = get(id);
			}
		}

		return vo;
	}


	@Override
	public Result flush(String id) {
		if( StringUtils.isNotBlank(id) ){
			String key =CacheHelper.buildKey(CacheModule.MEMBERVO,id);
			MemberVo vo =getMemberVo(id);
			cacheClient.saveObject(key, vo);
		}
		return Result.getSuccessResult();
	}

	private MemberVo getMemberVo(String id ){
		MemberVo memberVo =null;
		FileOrg org = null;
		SystemMember meber =memberMapper.selectByPrimaryKey(id);
		SystemMemberInfo memberInfo=memberInfoMapper.selectByPrimaryKey(id);
		if(meber !=null && meber.getOrgid()!=null&&!meber.getOrgid().isEmpty()){
			org = fileOrgMapper.selectByPrimaryKey(meber.getOrgid());
		}
		memberVo =new MemberVo();
		if(org != null){
			memberVo.setOrgStatus(org.getStatus());
		}
		if( meber !=null){
			memberVo.setId(meber.getId());
			memberVo.setOpenid(meber.getOpenid());
			memberVo.setCellphone(meber.getCellphone());
			memberVo.setNickname(meber.getNickname());
			memberVo.setAvatarspath(meber.getAvatarspath());
			memberVo.setCompanypercheck(""+meber.getCompanypercheck());
			memberVo.setUserpercheck(""+meber.getUserpercheck());
			memberVo.setDriverpercheck(""+meber.getDriverpercheck());
			memberVo.setOrgid(meber.getOrgid());
		}
		if( memberInfo!=null  ){
			memberVo.setUserName(memberInfo.getUsername());
			memberVo.setCompanyName(memberInfo.getCompanyname());
			memberVo.setIdcard(memberInfo.getIdcard());
			memberVo.setIdcardimage(memberInfo.getIdcardimage());
			memberVo.setDriverimage(memberInfo.getDriverimage());
		}
		return memberVo;
	}
	
}
