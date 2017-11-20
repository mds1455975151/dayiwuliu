package com.tianrui.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMoenyDisposeService;
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberMapper;
@Service
public class MoenyDisposeService implements IMoenyDisposeService{

	Logger logger=LoggerFactory.getLogger(MoenyDisposeService.class);
	@Autowired
	IPendingBillMoneyService pendingBillMoneyService;
	@Autowired
	SystemMemberInfoMapper systemMemberInfoMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Override
	public Result billSaveMoney(SaveBillMoneyReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getUseryhno());
		SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(req.getUseryhno());
		if(StringUtils.isNotBlank(info.getIdcard())){
			SystemMemberInfo query = new SystemMemberInfo();
			query.setIdcard(info.getIdcard());
			List<SystemMemberInfo> list = systemMemberInfoMapper.selectSelective(query);
			for(SystemMemberInfo mf : list){
				if(mf.getIdcard().equalsIgnoreCase(info.getIdcard())&&!mf.getId().equals(info.getId())){
					logger.info("司机身份证号重复:"+info.getIdcard()+",运单号:"+req.getWaybillno());
					rs.setCode("10");
					rs.setError("司机身份证号重复,"+info.getIdcard());
					return rs;
				}
			}
		}else{
			logger.info("未找到司机身份证号,运单号:"+req.getWaybillno());
			rs.setCode("10");
			rs.setError("未找到司机身份证号");
			return rs;
		}
		req.setCellphone(member.getCellphone());
		req.setUsername(member.getRemarkname());
		req.setUseryhno(info.getIdcard());
		rs = pendingBillMoneyService.save(req);
		return rs;
	}

}
