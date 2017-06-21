package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ISignerBillService;
import com.tianrui.api.req.front.bill.SignerBillReq;
import com.tianrui.api.resp.front.bill.SignerBillResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.bean.SignerBill;
import com.tianrui.service.mapper.SignerBillMapper;

@Service
public class SignerBillService implements ISignerBillService{

	@Autowired
	SignerBillMapper signerBillMapper;
	
	@Override
	public PaginationVO<SignerBillResp> select(SignerBillReq req) throws Exception {
		PaginationVO<SignerBillResp> page = new PaginationVO<SignerBillResp>();
		SignerBill bill = new SignerBill();
		PropertyUtils.copyProperties(bill, req);
		if(req.getPageNo()!=null){
			bill.setPageNo(req.getPageNo()*req.getPageSize());
			bill.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		List<SignerBill> list = signerBillMapper.selectByCondition(bill);
		Long a = signerBillMapper.selectBycount(bill);
		page.setList(copyProperties2(list));
		page.setTotal(a);
		return page;
	}
	protected List<SignerBillResp> copyProperties2(List<SignerBill> list) throws Exception{
		List<SignerBillResp> resp = new ArrayList<SignerBillResp>();
		for(SignerBill bill : list){
			SignerBillResp sb = new SignerBillResp();
			PropertyUtils.copyProperties(sb, bill);
			resp.add(sb);
		}
		return resp;
	}

}
