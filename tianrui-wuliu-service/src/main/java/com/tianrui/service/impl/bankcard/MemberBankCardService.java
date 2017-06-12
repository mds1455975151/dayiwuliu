package com.tianrui.service.impl.bankcard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.bankcard.IMemberBankCardService;
import com.tianrui.api.req.bankcard.MemberBankCardReq;
import com.tianrui.api.resp.bankcard.MemberBankCardResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.bean.NCbankType;
import com.tianrui.service.bean.NCbank_B;
import com.tianrui.service.mapper.MemberBankCardMapper;
import com.tianrui.service.mapper.NCbankTypeMapper;
import com.tianrui.service.mapper.NCbank_BMapper;

@Service
public class MemberBankCardService implements IMemberBankCardService{

	@Autowired
	MemberBankCardMapper memberBankCardMapper;
	@Autowired
	NCbank_BMapper ncbank_BMapper;
	@Autowired
	NCbankTypeMapper ncbank_TypeMapper;
	
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

	@Override
	public Result findBankAddress() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result insertBankAddress(JSONArray array) throws Exception {
		Result rs = Result.getSuccessResult();
		for (int i = 0; i < array.size(); i++) {
			NCbank_B ncb = new NCbank_B();
			JSONObject obj = (JSONObject) array.get(i);
			ncb.setId(UUIDUtil.getId());
			ncb.setAddress(obj.get("address").toString());
			ncb.setCode(obj.get("code").toString());
			ncb.setInnercode(obj.get("innercode").toString());
			ncb.setName(obj.get("name").toString());
			ncbank_BMapper.insertSelective(ncb);
		}
		return rs;
	}

	@Override
	public Result insertBankType(JSONArray array) throws Exception {
		// TODO Auto-generated method stub
		
		Result rs = Result.getSuccessResult();
		for (int i = 0; i < array.size(); i++) {
			NCbankType ncb = new NCbankType();
			JSONObject obj = (JSONObject) array.get(i);
			ncb.setId(UUIDUtil.getId());
			ncb.setBankkey(obj.get("bank_key").toString());
		
			ncb.setBankname(obj.get("name").toString());

			ncb.setBanktype(obj.get("code").toString());
			ncbank_TypeMapper.insertSelective(ncb);
		}
		return rs;
	}

}
