package com.tianrui.service.admin.impl.jtbCount;


import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.admin.intf.IJTBCountService;
import com.tianrui.api.req.count.JtbPushCountReq;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.anlian.JtbPushCount;
import com.tianrui.service.mapper.AnlianBillMapper;

@Service
public class JTBCountService implements IJTBCountService{

	@Autowired
	AnlianBillMapper anlianBillMapper;
	@Override
	public Result dyPush(JtbPushCountReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		JtbPushCount record = new JtbPushCount();
		PropertyUtils.copyProperties(record, req);
		long a = anlianBillMapper.selectDYJTBPushCount(record);
		rs.setData(a);
		return rs;
	}
	@Override
	public Result alPush(JtbPushCountReq req)throws Exception {
		Result rs = Result.getSuccessResult();
		JtbPushCount record = new JtbPushCount();
		PropertyUtils.copyProperties(record, req);
		long a = anlianBillMapper.selectAlJTBPushCount(record);
		rs.setData(a);
		return rs;
	}
}
