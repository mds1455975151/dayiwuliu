package com.tianrui.service.impl;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.ITransferService;
import com.tianrui.api.req.front.transfer.TransferReq;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.Transfer;
import com.tianrui.service.mapper.TransferMapper;
import com.tianrui.service.mapper.BillMapper;
@Service
public class TransferService implements ITransferService{

	@Autowired
	TransferMapper transferMapper;
	@Autowired
	BillMapper billMapper;
	
	@Override
	public Result update(TransferReq req)throws Exception {
		Result rs = Result.getSuccessResult();
		Transfer record = new Transfer();
		PropertyUtils.copyProperties(record, req);
		//查询司机名下所有运单未完成
		Bill bill = new Bill();
		List<Bill> list = billMapper.selectByCondition(bill);
		int a = transferMapper.updateByPrimaryKeySelective(record);
		
		if(a!=1){
			rs.setCode("1");
			rs.setError("修改失败");
		}
		return rs;
	}

	@Override
	public Result save(TransferReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		Transfer record = new Transfer();
		PropertyUtils.copyProperties(record, req);
		int a = transferMapper.insertSelective(record);
		if(a!=1){
			rs.setCode("1");
			rs.setError("添加失败");
		}
		return rs;
	}

}
