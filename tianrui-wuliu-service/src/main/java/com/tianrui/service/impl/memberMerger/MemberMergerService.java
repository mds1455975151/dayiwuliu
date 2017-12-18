package com.tianrui.service.impl.memberMerger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.intf.memberMerger.IMemberMergerService;
import com.tianrui.api.req.front.member.MemberFindReq;
import com.tianrui.api.req.memberMerger.MergerQueryReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.front.member.MemberResp;

@Service
public class MemberMergerService implements IMemberMergerService{
 
	@Autowired
	private ISystemMemberService systemMemberService;
	
	@Override
	public PageResp<MemberResp> selectMergerCellhpone(MergerQueryReq req) throws Exception {
		MemberFindReq member = new MemberFindReq();
		member.setIdCard(req.getIdCard());
		PageResp<MemberResp> list = systemMemberService.findMemberlist(member);
		return list;
	}

}
