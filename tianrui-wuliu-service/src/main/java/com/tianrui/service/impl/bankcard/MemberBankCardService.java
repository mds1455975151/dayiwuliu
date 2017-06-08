package com.tianrui.service.impl.bankcard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.bankcard.IMemberBankCardService;
import com.tianrui.api.req.bankcard.MemberBankCardReq;
import com.tianrui.api.resp.bankcard.MemberBankCardResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.mapper.MemberBankCardMapper;

@Service
public class MemberBankCardService implements IMemberBankCardService{

	@Autowired
	MemberBankCardMapper memberBankCardMapper;
	
	@Override
	public Result insertBankCard(MemberBankCardReq req) throws Exception {

		Result rs = Result.getSuccessResult();
		MemberBankCard find = new MemberBankCard();
		find.setCreater(req.getCreater());
		List<MemberBankCard> list = memberBankCardMapper.selectByCondition(find);
		MemberBankCard record = new MemberBankCard();
		PropertyUtils.copyProperties(record, req);
		record.setId(UUIDUtil.getId());
		record.setCreatetime(System.currentTimeMillis());
		//认证中
		record.setBankautid("2");
		if(list.size()==0){
			record.setBankstatus("1");
		}else{
			//非默认
			record.setBankstatus("0");
		}
		memberBankCardMapper.insertSelective(record);
		
		return rs;
	}

	@Override
	public Result autidBankCard(MemberBankCardReq req) throws Exception {
		
		Result rs = Result.getSuccessResult();
		MemberBankCard bank = memberBankCardMapper.selectByPrimaryKey(req.getId());
		bank.setBankautid(req.getBankautid());
		bank.setAuditor(req.getAuditor());
		bank.setAuditortime(System.currentTimeMillis());
		memberBankCardMapper.updateByPrimaryKeySelective(bank);
		return rs;
	}

	/** 设置用户名下银行卡为非默认*/
	protected void defaulCard_0(String creater) {
		MemberBankCard record = new MemberBankCard();
		record.setBankstatus("1");
		record.setCreater(creater);
		List<MemberBankCard> list = memberBankCardMapper.selectByCondition(record);
		for(MemberBankCard bank : list){
			bank.setBankstatus("0");
			memberBankCardMapper.updateByPrimaryKeySelective(bank);
		}
	}
	
	@Override
	public Result defaulBankCard(MemberBankCardReq req) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		defaulCard_0(req.getCreater());
		MemberBankCard upt = new MemberBankCard();
		upt.setId(req.getId());
		upt.setBankstatus("1");
		memberBankCardMapper.updateByPrimaryKeySelective(upt);
		return rs;
	}

	@Override
	public Result deltBankCard(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		memberBankCardMapper.deleteByPrimaryKey(id);
		return rs;
	}

	@Override
	public PaginationVO<MemberBankCardResp> selectBankCard(MemberBankCardReq req) throws Exception {
		PaginationVO<MemberBankCardResp> page = new PaginationVO<MemberBankCardResp>();
		MemberBankCard record = new MemberBankCard();
		PropertyUtils.copyProperties(record, req);
		if(req.getPageNo()!=null){
			record.setPageNo(req.getPageNo()*req.getPageSize());
			record.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		List<MemberBankCard> list = memberBankCardMapper.selectByCondition(record);
		long a = memberBankCardMapper.selectBycount(record);
		page.setList(copyProperties_2(list));
		page.setTotal(a);
		return page;
	}
	
	protected List<MemberBankCardResp> copyProperties_2(List<MemberBankCard> list) throws Exception {
		List<MemberBankCardResp> resp = new ArrayList<MemberBankCardResp>();
		for(MemberBankCard card : list){
			MemberBankCardResp bank = new MemberBankCardResp();
			PropertyUtils.copyProperties(bank, card);
			resp.add(bank);
		}
		return resp;
	}

	@Override
	public Result findBankCardById(String id) throws Exception {
		Result rs = Result.getSuccessResult();
		MemberBankCard resp = new MemberBankCard();
		MemberBankCard card = memberBankCardMapper.selectByPrimaryKey(id);
		PropertyUtils.copyProperties(resp, card);
		rs.setData(resp);
		return rs;
	}

}
