package com.tianrui.service.impl.bankcard;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMemberVoService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.intf.bankcard.IMemberBankCardService;
import com.tianrui.api.req.bankcard.MemberBankCardReq;
import com.tianrui.api.req.bankcard.PushNCBankCard;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.resp.bankcard.MemberBankCardResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.constants.HttpUrl;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.utils.HttpRequestUtil;
import com.tianrui.common.utils.HttpUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.ApiResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.BankSubbranch;
import com.tianrui.service.bean.BankType;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.bean.OwnerDriver;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.mapper.BankSubbranchMapper;
import com.tianrui.service.mapper.BankTypeMapper;
import com.tianrui.service.mapper.MemberBankCardMapper;
import com.tianrui.service.mapper.OwnerDriverMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
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
	SystemMemberInfoMapper systemMemberInfoMapper;
	@Autowired
	IMemberVoService memberVoService;
	@Autowired
	private TaskExecutor taskExecutor;
	@Autowired
	IMessageService messageService;
	@Autowired
	OwnerDriverMapper ownerDriverMapper;
	
	@Override
	public Result insertBankCard(MemberBankCardReq req) throws Exception {
		Result rs = Result.getErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getBankcard())
				&& (StringUtils.isNotBlank(req.getBankSubbranchId())
				|| StringUtils.isNotBlank(req.getBankSubbranchName()))){
			MemberBankCard record = new MemberBankCard();
			//MemberVo memberVo = memberVoService.get(req.getCreater());
			SystemMember member = systemMemberMapper.selectByPrimaryKey(req.getCreater());
			if (member != null && validateUserRole(rs, member, req.getDesc4(), req.getBankimg())) {
				record.setCreater(req.getCreater());
				List<MemberBankCard> list = memberBankCardMapper.selectByCondition(record);
				record.setBankcard(req.getBankcard());
				List<MemberBankCard> only = memberBankCardMapper.selectByCondition(record);
				if(req.getBankcard().length()>19){
					rs.setCode("8");
					rs.setError("银行卡号最多19位");
					return rs;
				}
				MemberBankCard size = new MemberBankCard();
				size.setCreater(req.getCreater());
				int  a =(int) memberBankCardMapper.selectBycount(size);
				if(a>=5){
					rs.setCode("8");
					rs.setError("您最多只能添加五张银行卡！");
					return rs;
				}
				if(only.size()!=0){
					rs.setCode("1");
					rs.setError("您已添加过该银行卡");
					return rs;
				} 
				record.setId(UUIDUtil.getId());
//				String name = HttpRequestUtil.putRequest(req.getBankcard());
//				record.setBankname(req.getBankname());
//				BankType bankType = new BankType();
//				bankType.setName(req.getBankname());
				BankType list1 = bankTypeMapper.selectByPrimaryKey(req.getBankTypeId());
				if (list1!=null) {
					record.setBankcode(list1.getAbbrName());
					record.setDesc3(list1.getId());
					record.setBankname(list1.getName());
				}
				if(StringUtils.isNotBlank(req.getBankSubbranchId())){
					record.setDesc2(req.getBankSubbranchId());
					BankSubbranch bs = bankSubbranchMapper.selectByPrimaryKey(req.getBankSubbranchId());
					if(bs != null){
						record.setDesc1(bs.getName());
					}
				}
				if (StringUtils.isBlank(req.getBankSubbranchId()) && StringUtils.isNotBlank(req.getBankSubbranchName())) {
					record.setDesc1(req.getBankSubbranchName());
					record.setDesc2(null);
				}
				SystemMemberInfo memberInfo = systemMemberInfoMapper.selectByPrimaryKey(member.getId());
				if(memberInfo != null){
					if (StringUtils.equals(req.getDesc4(), Constant.BANK_ACCOUNT_PERSON_IDENTITY_GS)) {
						record.setIdcard(memberInfo.getCompanycode());
						record.setIdname(memberInfo.getCompanyname());
						record.setIdcardimg(memberInfo.getLicenseImagePath());
						record.setTelphone(memberInfo.getCompanytel());
					}
					if (StringUtils.equals(req.getDesc4(), Constant.BANK_ACCOUNT_PERSON_IDENTITY_GR)) {
						record.setIdcard(memberInfo.getIdcard());
						record.setIdname(memberInfo.getUsername());
						record.setIdcardimg(memberInfo.getIdcardimage());
						record.setTelphone(memberInfo.getTelphone());
					}
				}
				record.setDesc4(req.getDesc4());
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
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}
	
	private boolean validateUserRole(Result rs, SystemMember memberVo, String desc4, String bankimg) {
		boolean flag = false;
		if (StringUtils.equals(desc4, Constant.BANK_ACCOUNT_PERSON_IDENTITY_GS)) {
			if (memberVo.getCompanypercheck() == Constant.AUTHSTATUS_PASS_SHORT) {
				flag = true;
			} else {
				rs.setCode("1");
				rs.setError("您还未进行或未通过企业认证，请认证成功后再来吧！");
				flag = false;
			}
		} else if(StringUtils.equals(desc4, Constant.BANK_ACCOUNT_PERSON_IDENTITY_GR)) {
			if(memberVo.getUserpercheck() == Constant.AUTHSTATUS_PASS_SHORT){
				if (StringUtils.isNotBlank(bankimg)){
					flag = true;
				}else{
					rs.setCode("1");
					rs.setError("请先上传银行卡图片！");
					flag = false;
				}
			} else {
				rs.setCode("2");
				rs.setError("您还未进行或未通过个人认证，请认证成功后再来吧！");
				flag = false;
			}
		} else {
			rs.setCode("3");
			rs.setError("请选择用户角色！");
			flag = false;
		}
		return flag;
	}

	@Override
	public Result update(MemberBankCardReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		if(req != null && StringUtils.isNotBlank(req.getBankcard())
				&& StringUtils.isNotBlank(req.getId())
				&& (StringUtils.isNotBlank(req.getBankSubbranchId())
				|| StringUtils.isNotBlank(req.getBankSubbranchName()))){
			MemberBankCard card = memberBankCardMapper.selectByPrimaryKey(req.getId());
			if(card == null){
				rs.setError("id有误");
				rs.setCode("1");
			}else{
				if(StringUtils.equals(card.getDesc4(), Constant.BANK_ACCOUNT_PERSON_IDENTITY_GR)
						&& StringUtils.isBlank(req.getBankimg())) {
					rs.setCode("1");
					rs.setError("请先上传银行卡图片！");
					return rs;
				}
				if(req.getBankcard().length()>19){
					rs.setCode("1");
					rs.setError("银行卡号最多19位");
					return rs;
				}else{
					card.setBankcard(req.getBankcard());
				}

				BankType list1 = bankTypeMapper.selectByPrimaryKey(req.getBankTypeId());
				if (list1!=null) {
					card.setBankcode(list1.getAbbrName());
					card.setDesc3(list1.getId());
					card.setBankname(list1.getName());
				}
				if(StringUtils.isNotBlank(req.getBankSubbranchId())){
					card.setDesc2(req.getBankSubbranchId());
					BankSubbranch bs = bankSubbranchMapper.selectByPrimaryKey(req.getBankSubbranchId());
					if(bs != null){
						card.setDesc1(bs.getName());
					}
				}
				if (StringUtils.isBlank(req.getBankSubbranchId()) && StringUtils.isNotBlank(req.getBankSubbranchName())) {
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
		bank.setAuditMassage(req.getAuditMassage());
		bank.setAuditortime(System.currentTimeMillis());
		SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(bank.getCreater());
		//消息推送
		SendMsgReq mreq = new SendMsgReq();
		List<String> strs = new ArrayList<String>();
//		strs.add(bank.getTelphone());
//		strs.add(bank.getAuditMassage());
		mreq.setParams(strs);
		mreq.setType("1");
		mreq.setRecid(bank.getCreater());
		mreq.setRecname(bank.getIdname());
		if(StringUtils.equals("1", req.getBankautid())){
			//银行卡认证成功
			if(info.getPushStatus()==Constant.YES_PUSH && info.getNcStatus()==Constant.NC_MEMBER_PUSH_STATUS_YES_ORG){
				//用户已推送 已分配组织
				if (StringUtils.equals(bank.getBankautid(), Constant.AUTHSTATUS_PASS) 
						&& info.getPushStatus() == Constant.YES_PUSH) {
					pushBankCard(bank);
				}
				strs.add(bank.getBankcard());
				mreq.setCodeEnum(MessageCodeEnum.ADMIN_BANKCARD_PASS);
				mreq.setRecType(MessageCodeEnum.ADMIN_BANKCARD_PASS.getType());
				messageService.sendMessageInside(mreq);
				memberBankCardMapper.updateByPrimaryKeySelective(bank);
			}else{
				rs.setCode("1");
				rs.setError("该用户暂未符合NC推送状态");
			}
		}else{
			strs.add(bank.getBankcard());
			strs.add(bank.getAuditMassage());
			mreq.setCodeEnum(MessageCodeEnum.ADMIN_BANKCARD_NOTPASS);
			mreq.setRecType(MessageCodeEnum.ADMIN_BANKCARD_NOTPASS.getType());
			messageService.sendMessageInside(mreq);
			memberBankCardMapper.updateByPrimaryKeySelective(bank);
		}
		
		return rs;
	}
	
	@Override
	public void pushBankCardAndCallBackPushStatus(){
		MemberBankCard record = new MemberBankCard();
		record.setPushStatus(Constant.NOT_PUSH);
		//1审核通过
		record.setBankautid("1");
		List<MemberBankCard> list = memberBankCardMapper.selectSelective(record);
		if (CollectionUtils.isNotEmpty(list)) {
			for (MemberBankCard bankCard : list) {
				SystemMemberInfo info = systemMemberInfoMapper.selectByPrimaryKey(bankCard.getCreater());
				if (info.getPushStatus() == Constant.YES_PUSH) {
					pushBankCard(bankCard);
				}
			}
		}
	}
	
	//银行卡认证通过推送给NC
	private void pushBankCard(final MemberBankCard bankCard){
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				PushNCBankCard push = new PushNCBankCard();
				push.setId(bankCard.getId());
				push.setBankCardNo(bankCard.getBankcard());
				push.setAccountPersonId(bankCard.getCreater());
				push.setAccountPersonName(bankCard.getIdname());
				if (StringUtils.equals(bankCard.getDesc4(), Constant.BANK_ACCOUNT_PERSON_IDENTITY_GR)) {
 					push.setAccountPersonIdentity("1");
				} else if (StringUtils.equals(bankCard.getDesc4(), Constant.BANK_ACCOUNT_PERSON_IDENTITY_GS)) {
					push.setAccountPersonIdentity("0");
				} else {
					push.setAccountPersonIdentity("");
				}
				push.setBankTypeId(bankCard.getDesc3());
				push.setBankSubbranchId(bankCard.getDesc2());
				push.setBankSubbranchName(bankCard.getDesc1());
			
				push.setName(bankCard.getIdname());
				push.setVbusinlicense(bankCard.getIdcard());
				
				ApiResult apiResult = HttpUtil.post(push, HttpUrl.NC_URL_IP_PORT + HttpUrl.BANK_CARD_PUSH_NC);
				if (apiResult != null){
					if(StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
						MemberBankCard bankCard = new MemberBankCard();
						bankCard.setId(push.getId());
						bankCard.setPushStatus(Constant.YES_PUSH);
						bankCard.setPushTime(System.currentTimeMillis());
						memberBankCardMapper.updateByPrimaryKeySelective(bankCard);
					//接口调用失败 银行类别+账号重复。
					}else if(StringUtils.equals(apiResult.getCode(), "222222")){
						MemberBankCard bankCard = new MemberBankCard();
						bankCard.setId(push.getId());
						bankCard.setPushStatus(Constant.YES_PUSH);
						bankCard.setPushTime(System.currentTimeMillis());
						memberBankCardMapper.updateByPrimaryKeySelective(bankCard);
					} else {
						MemberBankCard bankCard = new MemberBankCard();
						bankCard.setId(push.getId());
						bankCard.setErrorMassage(apiResult.getMessage());
						memberBankCardMapper.updateByPrimaryKeySelective(bankCard);
						LoggerFactory.getLogger("pushBankCard").info("推送银行卡错误信息: " + apiResult.getMessage());
					}
				} 
			}
		});
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
		MemberBankCardResp resp = new MemberBankCardResp();
		MemberBankCard card = memberBankCardMapper.selectByPrimaryKey(id);
		if(card != null){
			PropertyUtils.copyProperties(resp, card);
			BankSubbranch subbranch = bankSubbranchMapper.selectByPrimaryKey(card.getDesc2());
			if(subbranch != null){
				resp.setBankLineCode(subbranch.getCode());
				resp.setBankLineNumber(subbranch.getBankLineNumber());
			}
		}
		rs.setData(resp);
		return rs;
	}

	@Override
	public Result findBankType() throws Exception {
		Result rs = Result.getSuccessResult();
		List<BankType> list = bankTypeMapper.selectByCondtion(null);
		rs.setData(list);
		return rs;
	}
	
	@Override
	public Result findBankSubbranch(String backTypeId) throws Exception {
		Result result = Result.getSuccessResult();
		if (StringUtils.isNotBlank(backTypeId)) {
			BankSubbranch bankSubbranch = new BankSubbranch();
			bankSubbranch.setBankTypeId(backTypeId);
			List<BankSubbranch> list = bankSubbranchMapper.selectByCondtion(bankSubbranch);
			result.setData(list);
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
		//查询银行卡连行号
		@Override
		public Result findBankNum(String id) throws Exception {
			Result result = Result.getSuccessResult();
			if (StringUtils.isNotBlank(id)) {
				BankSubbranch bankSubbranch = new BankSubbranch();
				bankSubbranch.setId(id);
				BankSubbranch bankNum = bankSubbranchMapper.selectByPrimaryKey(id);
				result.setData(bankNum);
			}else{
				result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			}
			return result;
		}
	@Override
	public Result findBankOnly(String memberid, String code) throws Exception {
		Result rs = Result.getSuccessResult();
		if(StringUtils.isNotBlank(memberid) && StringUtils.isNotBlank(code)){
			MemberBankCard find = new MemberBankCard();
			find.setCreater(memberid);
			find.setBankcard(code);
			List<MemberBankCard> only = memberBankCardMapper.selectByCondition(find);
			if(only.size()!=0){
				rs.setCode("1");
				rs.setError("您已添加过该银行卡");
				return rs;
			}
		}
		return rs;
	}
	
	@Override
	public Result selectBankTypeByName(String bankcode) {
		Result rs = Result.getSuccessResult();
		if (StringUtils.isNotBlank(bankcode)) {
			String name = HttpRequestUtil.putRequest(bankcode);
			if (StringUtils.isNotBlank(name)) {
				BankType bankType = new BankType();
				bankType.setName(name);
				List<BankType> list = bankTypeMapper.selectByCondtion(bankType);
				if (CollectionUtils.isNotEmpty(list)) {
					rs.setData(list.get(0));
				} else {
					rs.setErrorCode(ErrorCode.NOT_FIND_BANK);
				}
			} else {
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result findBankSubbranchLike(String likeBankSubbranchName) {
		Result result = Result.getSuccessResult();
		if (StringUtils.isNotBlank(likeBankSubbranchName)) {
			List<BankSubbranch> list = bankSubbranchMapper.selectlikeBankSubbranchName(likeBankSubbranchName);
			result.setData(list);
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public Result findAuditBankCard(MemberBankCardReq req) {
		Result result = Result.getErrorResult();
		MemberBankCard record = new MemberBankCard();
		record.setBankautid(Constant.ONE_STR);
		record.setCreater(req.getCreater());
		List<MemberBankCard> list = memberBankCardMapper.selectSelective(record);
		result.setData(list);
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}
	
	/**
	 * 根据司机id查询车主银行卡信息
	 * @throws Exception 
	 */
	@Override
	public List<MemberBankCardResp> findOwnerById(String id) throws Exception {
		// TODO Auto-generated method stub
		OwnerDriver  ownerDriver = new OwnerDriver();
		ownerDriver.setDriverid(id);
		List<OwnerDriver> lists = ownerDriverMapper.selectOwnerDriver(ownerDriver);
		List <MemberBankCard>list = new ArrayList<MemberBankCard>();
		MemberBankCard memberBankCards =null;
		for( OwnerDriver ownerDrivers :lists){
			MemberBankCard memberBankCard = new MemberBankCard();
			memberBankCard.setCreater(ownerDrivers.getVehicleownerid());
			memberBankCard.setBankstatus("1");
			memberBankCards  = memberBankCardMapper.selectOwnerCard(memberBankCard);
			if(memberBankCards!=null){
				list.add(memberBankCards);
			}
		}
//		List<MemberBankCardResp> list = new ArrayList<MemberBankCardResp>();
//		copyProperties_2(list);
//		list.add(memberBankCards);
		return (copyProperties_2(list));
	}
}
