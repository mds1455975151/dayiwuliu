package com.tianrui.service.impl.bankcard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberVoService;
import com.tianrui.api.intf.bankcard.IMemberBankCardService;
import com.tianrui.api.req.bankcard.MemberBankCardReq;
import com.tianrui.api.resp.bankcard.MemberBankCardResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.utils.HttpRequestUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.BankSubbranch;
import com.tianrui.service.bean.BankType;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.mapper.BankSubbranchMapper;
import com.tianrui.service.mapper.BankTypeMapper;
import com.tianrui.service.mapper.MemberBankCardMapper;
import com.tianrui.service.mapper.SystemMemberMapper;

@Service
public class MemberBankCardService implements IMemberBankCardService{

	@Autowired
	MemberBankCardMapper memberBankCardMapper;
	@Autowired
	BankSubbranchMapper bankSubbranchMapper;
	@Autowired
	BankTypeMapper bankTypeMapper;
	@Autowired
	SystemMemberMapper systemMemberMapper;
	@Autowired
	IMemberVoService memberVoService;
	
	@Override
	public Result insertBankCard(MemberBankCardReq req) throws Exception {

		Result rs = Result.getSuccessResult();
		if(req != null && StringUtils.isNotBlank(req.getBankcard())
				&& (StringUtils.isNotBlank(req.getBankSubbranchId())
				|| StringUtils.isNotBlank(req.getBankSubbranchName()))
				&& StringUtils.isNotBlank(req.getBankimg())){
			MemberVo memberVo = memberVoService.get(req.getCreater());
			if(!StringUtils.equals(memberVo.getDriverpercheck(), Constant.AUTHSTATUS_PASS)){
				rs.setCode("1");
				rs.setError("司机尚未认证成功，请先去进行司机认证");
				return rs;
			}
			MemberBankCard find = new MemberBankCard();
			find.setCreater(req.getCreater());
			List<MemberBankCard> list = memberBankCardMapper.selectByCondition(find);
			find.setBankcard(req.getBankcard());
			List<MemberBankCard> only = memberBankCardMapper.selectByCondition(find);
			if(only.size()!=0){
				rs.setCode("1");
				rs.setError("您已添加过该银行卡");
				return rs;
			}
			String name = HttpRequestUtil.putRequest(req.getBankcard());
			MemberBankCard record = new MemberBankCard();
			record.setId(UUIDUtil.getId());
			record.setBankcard(req.getBankcard());
			record.setBankname(name);
			BankType bankType = new BankType();
			bankType.setName(name);
			List<BankType> list1 = bankTypeMapper.selectByCondtion(bankType);
			if (CollectionUtils.isNotEmpty(list1)) {
				record.setBankcode(list1.get(0).getAbbrName());
				record.setDesc3(list1.get(0).getId());
			}
			if(StringUtils.isNotBlank(req.getBankSubbranchId())){
				record.setDesc2(req.getBankSubbranchId());
				BankSubbranch bs = bankSubbranchMapper.selectByPrimaryKey(req.getBankSubbranchId());
				if(bs != null){
					record.setDesc1(bs.getName());
				}
			}
			if (StringUtils.isNotBlank(req.getBankSubbranchName())) {
				record.setDesc1(req.getBankSubbranchName());
				record.setDesc2(null);
			}
			if(memberVo != null){
				record.setIdcard(memberVo.getIdcard());
				record.setIdname(memberVo.getRealName());
				record.setIdcardimg(memberVo.getIdcardimage());
				record.setTelphone(memberVo.getCellphone());
			}
			record.setBankimg(req.getBankimg());
			record.setCreater(req.getCreater());
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
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	
	@Override
	public Result update(MemberBankCardReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if(req != null && StringUtils.isNotBlank(req.getBankcard())
				&& StringUtils.isNotBlank(req.getId())
				&& (StringUtils.isNotBlank(req.getBankSubbranchId())
				|| StringUtils.isNotBlank(req.getBankSubbranchName()))
				&& StringUtils.isNotBlank(req.getBankimg())){
			MemberBankCard card = memberBankCardMapper.selectByPrimaryKey(req.getId());
			if(card == null){
				rs.setError("id有误");
				rs.setCode("1");
			}else{
				String name = HttpRequestUtil.putRequest(req.getBankcard());
				card.setBankcard(req.getBankcard());
				card.setBankname(name);
				BankType bankType = new BankType();
				bankType.setName(name);
				List<BankType> list1 = bankTypeMapper.selectByCondtion(bankType);
				if (CollectionUtils.isNotEmpty(list1)) {
					card.setBankcode(list1.get(0).getAbbrName());
					card.setDesc3(list1.get(0).getId());
				}
				if(StringUtils.isNotBlank(req.getBankSubbranchId())){
					card.setDesc2(req.getBankSubbranchId());
					BankSubbranch bs = bankSubbranchMapper.selectByPrimaryKey(req.getBankSubbranchId());
					if(bs != null){
						card.setDesc1(bs.getName());
					}
				}
				if (StringUtils.isNotBlank(req.getBankSubbranchName())) {
					card.setDesc1(req.getBankSubbranchName());
					card.setDesc2(null);
				}
				card.setBankimg(req.getBankimg());
				card.setBankautid("2");
				memberBankCardMapper.updateByPrimaryKeySelective(card);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
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

//	@Override
//	public Result insertBankAddress(JSONArray array) throws Exception {
//		Result rs = Result.getSuccessResult();
//		for (int i = 0; i < array.size(); i++) {
//			NCbank_B ncb = new NCbank_B();
//			JSONObject obj = (JSONObject) array.get(i);
//			ncb.setId(UUIDUtil.getId());
//			ncb.setAddress(obj.get("address").toString());
//			ncb.setCode(obj.get("code").toString());
//			ncb.setInnercode(obj.get("innercode").toString());
//			ncb.setName(obj.get("name").toString());
//			ncbank_BMapper.insertSelective(ncb);
//		}
//		return rs;
//	}

//	@Override
//	public Result insertBankType(JSONArray array) throws Exception {
//		// TODO Auto-generated method stub
//		
//		Result rs = Result.getSuccessResult();
//		for (int i = 0; i < array.size(); i++) {
//			NCbankType ncb = new NCbankType();
//			JSONObject obj = (JSONObject) array.get(i);
//			ncb.setId(UUIDUtil.getId());
//			ncb.setBankkey(obj.get("bank_key").toString());
//		
//			ncb.setBankname(obj.get("name").toString());
//
//			ncb.setBanktype(obj.get("code").toString());
//			ncbank_TypeMapper.insertSelective(ncb);
//		}
//		return rs;
//	}

	@Override
	public Result findBankType() throws Exception {
		Result rs = Result.getSuccessResult();
		List<BankType> list = bankTypeMapper.selectByCondtion(null);
		rs.setData(list);
		return rs;
	}
	
	@Override
	public Result findBankSubbranch(String backTypeId) throws Exception {
		Result rs = Result.getSuccessResult();
		BankSubbranch bankSubbranch = new BankSubbranch();
		bankSubbranch.setBankTypeId(backTypeId);
		List<BankSubbranch> list = bankSubbranchMapper.selectByCondtion(bankSubbranch);
		rs.setData(list);
		return rs;
	}

	@Override
	public Result findBankOnly(String memberid, String code) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		MemberBankCard find = new MemberBankCard();
		find.setCreater(memberid);
		find.setBankcard(code);
		List<MemberBankCard> only = memberBankCardMapper.selectByCondition(find);
		if(only.size()!=0){
			rs.setCode("1");
			rs.setError("您已添加过该银行卡");
			return rs;
		}
		return rs;
	}
	
	@Override
	public Result selectBankTypeByName(String name) {
		Result rs = Result.getSuccessResult();
		if (StringUtils.isNotBlank(name)) {
			BankType bankType = new BankType();
			bankType.setName(name);
			List<BankType> list = bankTypeMapper.selectByCondtion(bankType);
			if (CollectionUtils.isNotEmpty(list)) {
				rs.setData(list.get(0));
			} else {
				rs.setErrorCode(ErrorCode.NOT_FIND_PANK);
			}
		} else {
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
}
