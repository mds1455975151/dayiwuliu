package com.tianrui.service.admin.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.admin.intf.IMerchantService;
import com.tianrui.api.admin.intf.IPayInvoiceDetail1Service;
import com.tianrui.api.admin.intf.IPayInvoiceService;
import com.tianrui.api.intf.IMessageService;
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.money.intf.IWithdrawRecordService;
import com.tianrui.api.req.admin.PayInvoiceAuditUpdate;
import com.tianrui.api.req.admin.PayInvoiceDriverPush;
import com.tianrui.api.req.admin.PayInvoiceNcCheckParams;
import com.tianrui.api.req.admin.PayInvoiceReq;
import com.tianrui.api.req.admin.PayInvoiceVenderPush;
import com.tianrui.api.req.admin.merchant.MerchantReq;
import com.tianrui.api.req.admin.pay.PayInvoiceDetail1FindReq;
import com.tianrui.api.req.front.message.SendMsgReq;
import com.tianrui.api.req.money.SaveWithdrawReq;
import com.tianrui.api.req.money.UpdateBillMoneyReq;
import com.tianrui.api.req.money.updateWithdrawReq;
import com.tianrui.api.resp.admin.PayInvoiceAuditVo;
import com.tianrui.api.resp.admin.PayInvoiceVo;
import com.tianrui.api.resp.admin.merchant.MerchantResp;
import com.tianrui.api.resp.admin.pay.PayInvoiceDetail1Resp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.constants.HttpUrl;
import com.tianrui.common.constants.NCResultEnum;
import com.tianrui.common.enums.MessageCodeEnum;
import com.tianrui.common.utils.DateUtil;
import com.tianrui.common.utils.HttpUtil;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.ApiResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.PayInvoice;
import com.tianrui.service.admin.bean.PayInvoiceDetail;
import com.tianrui.service.admin.bean.PayInvoiceMsg;
import com.tianrui.service.admin.mapper.PayInvoiceDetailMapper1;
import com.tianrui.service.admin.mapper.PayInvoiceMapper1;
import com.tianrui.service.admin.mapper.PayInvoiceMsgMapper;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.mapper.MemberBankCardMapper;
import com.tianrui.service.mapper.SystemMemberInfoMapper;
import com.tianrui.service.mapper.SystemMemberMapper;

@Service
public class PayInvoiceService1 implements IPayInvoiceService {
	
	private Logger logger = LoggerFactory.getLogger(PayInvoiceService1.class);

	@Autowired
	private PayInvoiceMapper1 payInvoiceMapper;
	@Autowired
	private PayInvoiceDetailMapper1 payInvoiceDetailMapper;
	@Autowired
	private SystemMemberInfoMapper systemMemberInfoMapper;
	@Autowired
	private MemberBankCardMapper memberBankCardMapper;
	@Autowired
	private PayInvoiceMsgMapper payInvoiceMsgMapper;
	@Autowired
    private TaskExecutor taskExecutor;
	@Autowired
	private  SystemMemberMapper systemMemberMapper;
	@Autowired
	IMessageService messageService;
	@Autowired
	IPayInvoiceDetail1Service payInvoiceDetail1Service;
	@Autowired
	IMerchantService merchantService;
	@Autowired
	IPendingBillMoneyService pendingBillMoneyService;
	@Autowired
	IWithdrawRecordService withdrawRecordService;
	
	@Override
	public PaginationVO<PayInvoiceVo> page(PayInvoiceReq req) {
		logger.info("into service: driver pay invoice page.");
		PaginationVO<PayInvoiceVo> page = null;
		if(req != null){
			logger.info("into service: driver pay invoice page params: " + req.toString());
			page = new PaginationVO<PayInvoiceVo>();
			long count = payInvoiceMapper.selectPayInvoicePageCount(req);
			logger.info("select driver pay invoice page count : " + count);
			if(count > 0){
				req.setOrderColumn("code");
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<PayInvoiceVo> list = payInvoiceMapper.selectPayInvoicePage(req);
				logger.info("select driver pay invoice page : pageListSize:" + list.size());
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		logger.info("out service: driver pay invoice page.");
		return page;
	}
	
	@Override
	public Result payInvoiceUpdateData(String id, boolean isAudit){
		logger.info("into service: driver pay invoice updateData.");
		Result result = Result.getErrorResult();
		if (StringUtils.isNotBlank(id)) {
			logger.info("into service: driver pay invoice page params: id=" + id);
			PayInvoice bean = payInvoiceMapper.selectByPrimaryKey(id);
			PayInvoiceAuditVo vo = new PayInvoiceAuditVo();
			if (bean != null) {
				vo.setId(bean.getId());
				vo.setPayeeName(bean.getPayeeName());
				vo.setPayeeBankCardNumber(bean.getPayeeBankCardNumber());
				if (isAudit) {
					vo.setBillTotalPrice(String.valueOf(bean.getReceptionBillTotalPrice()));
					vo.setDeductWeightMisc(String.valueOf(bean.getReceptionDeductWeightMisc()));
					vo.setDeductMoney(String.valueOf(bean.getReceptionDeductMoney()));
					vo.setDeductOther(String.valueOf(bean.getReceptionDeductOther()));
					vo.setDeductOilCard(String.valueOf(bean.getReceptionDeductOilCard()));
				} else {
					vo.setBillTotalPrice(String.valueOf(bean.getBackstageBillTotalPrice()));
					vo.setDeductWeightMisc(String.valueOf(bean.getBackstageDeductWeightMisc()));
					vo.setDeductMoney(String.valueOf(bean.getBackstageDeductMoney()));
					vo.setDeductOther(String.valueOf(bean.getBackstageDeductOther()));
					vo.setDeductOilCard(String.valueOf(bean.getBackstageDeductOilCard()));
				}
				vo.setAmountPayable(String.valueOf(bean.getAmountPayable()));
			}
			result.setData(vo);
			logger.info("into service: driver pay invoice page params: id=" + vo.toString());
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		logger.info("out service: driver pay invoice updateData.");
		return result;
	}

	@Override
	public Result payInvoiceUpdate(PayInvoiceAuditUpdate auditUpdate, boolean isAudit) {
		logger.info("into service: driver pay invoice update.");
		Result result = Result.getErrorResult();
		if (validateAuditUpdate(auditUpdate)) {
			logger.info("into service: driver pay invoice update selectByPrimaryKey");
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(auditUpdate.getId());
			logger.info("into service: driver pay invoice update selectByPrimaryKey bean: " + payInvoice.toString());
			PayInvoice bean = new PayInvoice();
			bean.setId(payInvoice.getId());
			bean.setBackstageBillTotalPrice(auditUpdate.getBillTotalPrice());
			bean.setBackstageDeductWeightMisc(auditUpdate.getDeductWeightMisc());
			bean.setBackstageDeductMoney(auditUpdate.getDeductMoney());
			bean.setBackstageDeductOther(auditUpdate.getDeductOther());
			bean.setBackstageDeductOilCard(auditUpdate.getDeductOilCard());
			bean.setAmountPayable(bean.getBackstageBillTotalPrice() 
										- bean.getBackstageDeductMoney() 
										- bean.getBackstageDeductOilCard() 
										- bean.getBackstageDeductOther() 
										- bean.getBackstageDeductWeightMisc());
			logger.info("into service: driver pay invoice isAudit: " + isAudit);
			if (isAudit) {
				bean.setAuditStatus(Constant.YES_AUDIT);
				bean.setAuditTime(System.currentTimeMillis());
			}
			logger.info("into service: driver pay invoice update Bean: " + bean.toString());
			if (payInvoiceMapper.updateByPrimaryKeySelective(bean) == 1) {
				logger.info("into service: driver pay invoice audit success");
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}else{
			logger.info("into service: driver pay invoice validate failure. because " + ErrorCode.PARAM_ERROR);
			result.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return result;
	}

	private boolean validateAuditUpdate(PayInvoiceAuditUpdate auditUpdate) {
		logger.info("into service: driver pay invoice audit validate params: " + auditUpdate.toString());
		if(auditUpdate != null
				&& StringUtils.isNotBlank(auditUpdate.getId())
				&& auditUpdate.getDeductMoney() != null
				&& auditUpdate.getDeductOilCard() != null
				&& auditUpdate.getDeductOther() != null
				&& auditUpdate.getDeductWeightMisc() != null){
			logger.info("into service: driver pay invoice audit validate params: true");
			return true;
		}
		logger.info("into service: driver pay invoice audit validate params: false");
		return false;
	}

	@Transactional
	@Override
	public synchronized Result driverPush(String id) {
		Result result = Result.getErrorResult();
		if (StringUtils.isNotBlank(id)) {
			logger.info("into service: driver pay invoice push params: id=" + id);
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(id);
			if (payInvoice != null) {
				//修改原（Constant.NOT_PUSH 未推单） 改（Constant.PUSH_ING 推单中）
				if (payInvoice.getPushStatus() == Constant.PUSH_ING || (payInvoice.getPushStatus() == Constant.YES_PUSH && payInvoice.getPayStatus() == Constant.THREE)) {
					if (validate(payInvoice.getPayeeId(), payInvoice.getPayeeBankCardId(), result)) {
						 if (validatePayInvoiceMsg(id)) {
							//TODO 财务确认运费后修改收入运费记录
//							MemberBankCard bankCard = memberBankCardMapper.selectByPrimaryKey(payInvoice.getPayeeBankCardId());
//							result = uptPandMoney(result, payInvoice ,bankCard);
//							if(!"000000".equals(result.getCode())){
//								return result;
//							}
							String capitalno = UUIDUtil.getId();//流水号
							PayInvoiceMsg payInvoiceMsg = loggerPayInvoiceMsg(payInvoice,capitalno);
							PayInvoiceDriverPush push = setPayInvoiceDriver(payInvoice, payInvoiceMsg);
							logger.info(HttpUrl.NC_URL_IP_PORT + HttpUrl.PAY_INVOICE_DRIVER_PUSH);
							ApiResult apiResult = HttpUtil.post_longlong(push, HttpUrl.NC_URL_IP_PORT + HttpUrl.PAY_INVOICE_DRIVER_PUSH);
							if (apiResult != null) {
								logger.info("into service: driver pay invoice push NC http result{}: =" + JSON.toJSONString(apiResult).toString());
								if (StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
									PayInvoice bean = callBackPushStatus(payInvoice);
									logger.info("into service: driver pay invoice update pushStatus Bean{}: " + JSON.toJSONString(bean).toString());
									if (payInvoiceMapper.updateByPrimaryKeySelective(bean) == 1) {
										result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
									} else {
										result.setErrorCode(ErrorCode.SYSTEM_ERROR);
									}
									logger.info("into service: driver pay invoice insert payInvoiceMsg{}: " + payInvoiceMsg.toString());
									payInvoiceMsgMapper.insertSelective(payInvoiceMsg);
									//TODO 提现申请
//									result =  saveWithDraw(payInvoice,bankCard,capitalno);
//									if(!"000000".equals(result.getCode())){
//										return result;
//									}
								} else {
									result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR);
									result.setError(result.getError() + ": "+ apiResult.getMessage());
								}
							} else {
								result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR);
							}
						} else {
							result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR1);
						}
					}
				} else {
					result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR1);
				}
			} else {
				result.setErrorCode(ErrorCode.PAY_DATA_NOT_EXISTSS);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		logger.info("out service: driver pay invoice push.");
		return result;
	}

	private Result uptPandMoney(Result result, PayInvoice payInvoice ,MemberBankCard bank) {
		try {
			if(bank == null){
				result.setCode("11");
				result.setError("银行卡信息有误");
				return result;
			}
			PayInvoiceDetail pay = new PayInvoiceDetail();
			pay.setPayInvoiceId(payInvoice.getId());
			List<PayInvoiceDetail> list = payInvoiceDetailMapper.selectByCondition(pay);
			String waybillno = "";
			for(PayInvoiceDetail bill : list){
				waybillno = waybillno+bill.getBillCode();
			}
			UpdateBillMoneyReq req = new UpdateBillMoneyReq();
			req.setCellphone(bank.getTelphone());
			req.setWaybillno(waybillno);
			//支付总额
			req.setPaidmoney(payInvoice.getAmountPayable().longValue() * 100);
			//计算扣款总额
			Double doc = payInvoice.getBackstageDeductMoney()+payInvoice.getBackstageDeductOilCard()+payInvoice.getBackstageDeductOther()+payInvoice.getBackstageDeductWeightMisc();
			req.setDeductionmoney(doc.longValue()*100);
			req.setPaidtime(System.currentTimeMillis());
			req.setCapitalno(UUIDUtil.getId());
			result = pendingBillMoneyService.update(req);
			
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			result.setCode("1");
			result.setError(e.getMessage());
		}
		return result;
	}

	private Result saveWithDraw(PayInvoice payInvoice, MemberBankCard bank, String capitalno){
		Result result = Result.getSuccessResult();
		SaveWithdrawReq sd = new SaveWithdrawReq();
		sd.setCellphone(bank.getTelphone());
		sd.setUseryhno(bank.getIdcard());
		sd.setMoney(payInvoice.getAmountPayable().longValue() * 100);
		sd.setExpectpaycompany("NC");
		sd.setBankname(bank.getBankname());
		sd.setBankcode(bank.getBankcode());
		sd.setBankcodeno(bank.getBankcard());
		sd.setBegintime(System.currentTimeMillis());
		sd.setCapitalno(capitalno);
		try {
			result = withdrawRecordService.save(sd);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result.setCode("1");
			result.setError(e.getMessage());
		}
		return result;
	}

	@Transactional
	@Override
	public synchronized Result venderPush(String id) {
		logger.info("into service: vender pay invoice push.");
		Result result = Result.getErrorResult();
		if (StringUtils.isNotBlank(id)) {
			logger.info("into service: vender pay invoice push params: id=" + id);
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(id);
			if (payInvoice != null) {
				//修改验证条件——原（Constant.NOT_PUSH-未推送） -改（Constant.PUSH_ING -前台用户推单中）
				if (payInvoice.getPushStatus() == Constant.PUSH_ING || (payInvoice.getPushStatus() == Constant.YES_PUSH && payInvoice.getPayStatus() == Constant.THREE)) {
					if (validate(payInvoice.getPayeeId(), payInvoice.getPayeeBankCardId(), result)) {
						if (validatePayInvoiceMsg(id)) {
							MemberBankCard bankCard = memberBankCardMapper.selectByPrimaryKey(payInvoice.getPayeeBankCardId());
							//TODO 财务确认运费后修改收入运费记录
//							result = uptPandMoney(result, payInvoice ,bankCard);
//							if(!"000000".equals(result.getCode())){
//								return result;
//							}
							String capitalno = UUIDUtil.getId();
							PayInvoiceMsg payInvoiceMsg = loggerPayInvoiceMsg(payInvoice,capitalno);
							PayInvoiceVenderPush push = setPayInvoiceVender(payInvoice, payInvoiceMsg);
							ApiResult apiResult = HttpUtil.post_longlong(push, HttpUrl.NC_URL_IP_PORT + HttpUrl.PAY_INVOICE_VENDER_PUSH);
							if (apiResult != null) {
								if (StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
									PayInvoice bean = callBackPushStatus(payInvoice);
									if (payInvoiceMapper.updateByPrimaryKeySelective(bean) == 1) {
										result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
									} else {
										result.setErrorCode(ErrorCode.SYSTEM_ERROR);
									}
									//TODO 提现申请
//									result =  saveWithDraw(payInvoice,bankCard,capitalno);
//									if(!"000000".equals(result.getCode())){
//										return result;
//									}
									payInvoiceMsgMapper.insertSelective(payInvoiceMsg);
								} else {
									result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR);
									result.setError(result.getError() + ": "+ apiResult.getMessage());
								}
							} else {
								result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR);
							}
						} else {
							result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR1);
						}
					}
				} else {
					result.setErrorCode(ErrorCode.PAY_DATA_NOT_EXISTSS);
				}
			} else {
				result.setErrorCode(ErrorCode.PAY_DATA_NOT_EXISTSS);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		logger.info("out service: vender pay invoice push.");
		return result;
	}
	/**
	 * @annotation 推送前 校验用户NC已审核和银行卡已推送
	 * @param payeeId
	 * @param bankCardId
	 * @param result
	 * @return boolean
	 */
	private boolean validate(String payeeId, String bankCardId, Result result) {
		boolean flag = false;
		SystemMemberInfo memberInfo = systemMemberInfoMapper.selectByPrimaryKey(payeeId);
		if (memberInfo.getNcStatus() == Constant.NC_MEMBER_PUSH_STATUS_YES_ORG) {
			MemberBankCard bankCard = memberBankCardMapper.selectByPrimaryKey(bankCardId);
			if (bankCard.getPushStatus() == Constant.YES_PUSH) {
				flag = true;
			} else {
				result.setErrorCode(ErrorCode.NOT_PUSH_BANK);
			}
		} else {
			result.setErrorCode(ErrorCode.MEMBER_NC_NOT_ORG);
		}
		
		return flag;
	}
	/**
	 * @annotation 校验账单支付信息日志最后一次推送是否失败
	 * @param payInvoiceId
	 * @return boolean
	 */
	public boolean validatePayInvoiceMsg(String payInvoiceId) {
		boolean flag = false;
		PayInvoiceMsg record = new PayInvoiceMsg();
		record.setPayInvoiceId(payInvoiceId);
		List<PayInvoiceMsg> list = payInvoiceMsgMapper.selectByCondition(record);
		if (CollectionUtils.isNotEmpty(list)) {
			Collections.sort(list, new Comparator<PayInvoiceMsg>() {
				@Override
				public int compare(PayInvoiceMsg o1, PayInvoiceMsg o2) {
					return (int) (o2.getPayTime() - o1.getPayTime());
				}
			});
			if (list.get(0).getPayStatus() == Constant.ZERO) {
				flag = true;
			}
		} else {
			flag = true;
		}
		return flag;
	}
	/**
	 * @annotation 赋值待推送NC司机支付账单
	 * @param payInvoice
	 * @param payInvoiceMsg 
	 * @return PayInvoiceDriverPush
	 */
	private PayInvoiceDriverPush setPayInvoiceDriver(PayInvoice payInvoice, PayInvoiceMsg payInvoiceMsg) {
		PayInvoiceDriverPush push = new PayInvoiceDriverPush();
		push.setId(payInvoiceMsg.getId());
		push.setBillNo(payInvoice.getCode());
		push.setSupplierId(payInvoice.getPayeeId());
		push.setName(payInvoice.getPayeeName());
		push.setBankCardId(payInvoice.getPayeeBankCardId());
		push.setBankCard(payInvoice.getPayeeBankCardNumber());
		push.setDrivercode(payInvoice.getPayeeIdNo());
		push.setInvoiceType(payInvoice.getMaterialCode());
		push.setBillTotalPrice(String.valueOf(payInvoice.getAmountPayable()));
		push.setSignTime(DateUtil.getDateString(payInvoice.getApplicationTime(), DateUtil.Y_M_D_H_M_S));
		addConsignee(payInvoice, push);
		MemberBankCard bankCard = memberBankCardMapper.selectByPrimaryKey(payInvoice.getPayeeBankCardId());
		if (bankCard != null) {
			push.setBankTypeId(bankCard.getDesc3());
		}
		return push;
	}

	/**
	 * 添加收货方名称和编码
	 * @param payInvoice
	 * @param push
	 */
	private void addConsignee(PayInvoice payInvoice, PayInvoiceDriverPush push) {
		PayInvoiceDetail1FindReq req = new PayInvoiceDetail1FindReq();
		req.setPayInvoiceId(payInvoice.getId());
		try {
			//根据账单ID获取运价单明细
			PaginationVO<PayInvoiceDetail1Resp> page = payInvoiceDetail1Service.select(req);
			StringBuffer nameStr = new StringBuffer();
			StringBuffer noStr = new StringBuffer();
			for(PayInvoiceDetail1Resp p : page.getList()){
				String [] names = nameStr.toString().split("-");
				List<String> stringlist=Arrays.asList(names);
				if(stringlist.contains(p.getConsignee())){
					continue;
				}
				nameStr.append(p.getConsignee());
				nameStr.append("-");
				MerchantReq oreq = new MerchantReq();
				oreq.setName(p.getConsignee());
				PaginationVO<MerchantResp> lso = merchantService.find(oreq);
				if(null !=  lso.getList() && lso.getList().size() > 0 ){
					noStr.append(lso.getList().get(0).getCode());
					noStr.append("-");
				}
				
			}
			push.setConsigneeName(nameStr.substring(0, nameStr.length() -1));
			push.setConsigneeNO(noStr.substring(0, noStr.length() -1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @annotation 赋值待推送NC司机支付账单
	 * @param payInvoice
	 * @param payInvoiceMsg 
	 * @return PayInvoiceDriverPush
	 */
	private PayInvoiceVenderPush setPayInvoiceVender(PayInvoice payInvoice, PayInvoiceMsg payInvoiceMsg) {
		PayInvoiceVenderPush push = new PayInvoiceVenderPush();
		push.setId(payInvoiceMsg.getId());
		push.setPayInvoiceMsgId(payInvoiceMsg.getId());
		push.setBillNo(payInvoice.getCode());
		push.setInvoiceType(payInvoice.getMaterialCode());
		push.setSupplierId(payInvoice.getPayeeId());
		push.setApplyDate(DateUtil.getDateString(payInvoice.getApplicationTime(), DateUtil.Y_M_D_H_M_S));
		push.setPayDealPrice(String.valueOf(payInvoice.getAmountPayable()));
		push.setBankCard(payInvoice.getPayeeBankCardNumber());
		push.setBankCardId(payInvoice.getPayeeBankCardId());
		push.setName(payInvoice.getPayeeName());
		push.setVbusinlicense(payInvoice.getPayeeIdNo());
		addConsignee(payInvoice, push);
		MemberBankCard bankCard = memberBankCardMapper.selectByPrimaryKey(payInvoice.getPayeeBankCardId());
		if (bankCard != null) {
			push.setBankTypeId(bankCard.getDesc3());
		}
		return push;
	}

	private void addConsignee(PayInvoice payInvoice, PayInvoiceVenderPush push) {
		PayInvoiceDetail1FindReq req = new PayInvoiceDetail1FindReq();
		req.setPayInvoiceId(payInvoice.getId());
		try {
			//根据账单ID获取运价单明细
			PaginationVO<PayInvoiceDetail1Resp> page = payInvoiceDetail1Service.select(req);
			StringBuffer nameStr = new StringBuffer();
			StringBuffer noStr = new StringBuffer();
			for(PayInvoiceDetail1Resp p : page.getList()){
				String [] names = nameStr.toString().split("-");
				List<String> stringlist=Arrays.asList(names);
				if(stringlist.contains(p.getConsignee())){
					continue;
				}
				nameStr.append(p.getConsignee());
				nameStr.append("-");
				MerchantReq oreq = new MerchantReq();
				oreq.setName(p.getConsignee());
				PaginationVO<MerchantResp> lso = merchantService.find(oreq);
				if(null !=  lso.getList() && lso.getList().size() > 0 ){
					noStr.append(lso.getList().get(0).getCode());
					noStr.append("-");
				}
				
			}
			push.setConsigneeName(nameStr.substring(0, nameStr.length() -1));
			push.setConsigneeNO(noStr.substring(0, noStr.length() -1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @annotation 回写账单推送状态
	 * @param payInvoice
	 * @return PayInvoice
	 */
	private PayInvoice callBackPushStatus(PayInvoice payInvoice) {
		PayInvoice bean = new PayInvoice();
		bean.setId(payInvoice.getId());
		bean.setPushStatus(Constant.YES_PUSH);
		bean.setPayStatus(Constant.PAY_ING);
		bean.setPushTime(System.currentTimeMillis());
		return bean;
	}
	/**
	 * @annotation 增加支付信息日志
	 * @param payInvoice
	 * @return 
	 */
	private PayInvoiceMsg loggerPayInvoiceMsg(PayInvoice payInvoice,String capitalno) {
		PayInvoiceMsg payInvoiceMsg = new PayInvoiceMsg();
		payInvoiceMsg.setId(capitalno);
		payInvoiceMsg.setPayInvoiceId(payInvoice.getId());
		payInvoiceMsg.setPayeeId(payInvoice.getPayeeId());
		payInvoiceMsg.setPayeeBankCardId(payInvoice.getPayeeBankCardId());
		payInvoiceMsg.setAmountPayable(payInvoice.getAmountPayable());
		payInvoiceMsg.setPayeeBankCardNumber(payInvoice.getPayeeBankCardNumber());
		payInvoiceMsg.setPaidAmount(payInvoice.getPaidAmount());
		payInvoiceMsg.setPayStatus(Constant.PAY_ING);
		payInvoiceMsg.setPayTime(System.currentTimeMillis());
		return payInvoiceMsg;
	}
	
	@Override
	public Result payAudit(String id) {
		Result rs = Result.getSuccessResult();
		PayInvoice upt = new PayInvoice();
		upt.setId(id);
		upt.setAuditStatus(2);
		payInvoiceMapper.updateByPrimaryKeySelective(upt);
		return rs;
	}

	@Override
	public Result payPush(String id) {
		Result rs = Result.getSuccessResult();
		PayInvoice upt = new PayInvoice();
		upt.setId(id);
		upt.setPushStatus(1);
		payInvoiceMapper.updateByPrimaryKeySelective(upt);
		return rs;
	}

	@Override
	public Result pushBack(String id) {
		Result rs = Result.getSuccessResult();
		PayInvoice pay = payInvoiceMapper.selectByPrimaryKey(id);
		//已推送运单，不能操作
		if(pay.getPushStatus()==2){
			rs.setCode("1");
			rs.setError("已推送运单，不能操作");
		}else{
			PayInvoice upt = new PayInvoice();
			upt.setId(id);
			upt.setPushStatus(0);
			payInvoiceMapper.updateByPrimaryKeySelective(upt);
		}
		return rs;
	}

	@Override
	public Result payDelete(String id) {
		Result rs = Result.getSuccessResult();
		
		PayInvoice pa = payInvoiceMapper.selectByPrimaryKey(id);
		if(pa.getPushStatus()==2){
			rs.setCode("1");
			rs.setError("已推送运单，不能操作");
			return rs;
		}
		
		PayInvoiceDetail query = new PayInvoiceDetail();
		query.setPayInvoiceId(id);
		List<PayInvoiceDetail> list = payInvoiceDetailMapper.selectByCondition(query);
		for(PayInvoiceDetail pay : list){
			PayInvoiceDetail upt = new PayInvoiceDetail();
			upt.setId(pay.getId());
			upt.setPayInvoiceId("");
			upt.setWhetherClose(false);
			payInvoiceDetailMapper.updateByPrimaryKeySelective(upt);
		}
		payInvoiceMapper.deleteByPrimaryKey(id);
		return rs;
	}

	@Transactional
	@Override
	public void callBackPayInvoicePaidAmount() {
		PayInvoice record = new PayInvoice();
		record.setPushStatus(Constant.YES_PUSH);
		record.setPayStatus(Constant.PAY_ING);
		List<PayInvoice> list = payInvoiceMapper.selectByCondition(record);
		if (CollectionUtils.isNotEmpty(list)) {
			List<String> driverParams = new ArrayList<String>();
			List<String> venderParams = new ArrayList<String>();
			for (PayInvoice payInvoice : list) {
				if (payInvoice.getPayeeIdentity() == Constant.PAY_INVOICE_DRIVER) {
					driverParams.add(payInvoice.getId());
				}
				if (payInvoice.getPayeeIdentity() == Constant.PAY_INVOICE_VENDER) {
					venderParams.add(payInvoice.getId());
				}
			}
			postNcDriverCallBack(driverParams);
			postNcVenderCallBack(venderParams);
		}
	}

	private void postNcDriverCallBack(List<String> params) {
		if (CollectionUtils.isNotEmpty(params)) {
			//回写已付金额
			ApiResult apiResult = HttpUtil.post_longlong(params, HttpUrl.NC_URL_IP_PORT + HttpUrl.PAY_INVOICE_DRIVER_CALLBACK_PAIDAMOUNT);
			if (apiResult != null && StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				JSONArray array = JSONArray.parseArray(apiResult.getData().toString());
				if (CollectionUtils.isNotEmpty(array)) {
					callBackPaidAmount(array);
				}
			}
		}
		
	}

	private void postNcVenderCallBack(List<String> params) {
		if (CollectionUtils.isNotEmpty(params)) {
			//回写已付金额
			ApiResult apiResult = HttpUtil.post_longlong(params, HttpUrl.NC_URL_IP_PORT + HttpUrl.PAY_INVOICE_VENDER_CALLBACK_PAIDAMOUNT);
			if (apiResult != null && StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				JSONArray array = JSONArray.parseArray(apiResult.getData().toString());
				if (CollectionUtils.isNotEmpty(array)) {
					callBackPaidAmount(array);
				}
			}
		}
		
	}

	/**
	 * @annotation 回写支付账单已付金额
	 * @param array
	 */
	private void callBackPaidAmount(JSONArray array) {
		for (Object object : array) {
			JSONObject jsonObject = (JSONObject) object;
			String id = jsonObject.getString("id");
			String paidAmount = jsonObject.getString("value");
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(id);
			if (payInvoice != null) {
				PayInvoice bean = new PayInvoice();
				bean.setId(id);
				bean.setPaidAmount(Double.valueOf(paidAmount));
				if (bean.getPaidAmount().doubleValue() == payInvoice.getAmountPayable().doubleValue()) {
					bean.setPayStatus(Constant.YES_PAY);
				}
				payInvoiceMapper.updateByPrimaryKeySelective(bean);
			}
		}
	}

	@Override
	public Result payDetail(String id) {
		Result rs = Result.getSuccessResult();
		PayInvoice pay = payInvoiceMapper.selectByPrimaryKey(id);
		rs.setData(pay);
		return rs;
	}

	@Override
	public Result updateBankCard(String id, String bankCardId) {
		logger.info("into the payInvoiceService updateBankCard.");
		Result result = Result.getSuccessResult();
		if (StringUtils.isNotBlank(id) && StringUtils.isNotBlank(bankCardId)) {
			logger.info("out the payInvoiceService updateBankCard success.");
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(id);
			if (payInvoice != null) {
				if (payInvoice.getPayStatus() == Constant.THREE) {
					MemberBankCard bankCard = memberBankCardMapper.selectByPrimaryKey(bankCardId);
					if (bankCard != null) {
						if (StringUtils.equals(bankCard.getBankautid(), Constant.ONE_STR)) {
							PayInvoice bean = new PayInvoice();
							bean.setId(id);
							bean.setPayeeBankCardId(bankCardId);
							bean.setPayeeBankCardNumber(bankCard.getBankcard());
							payInvoiceMapper.updateByPrimaryKeySelective(bean);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							logger.info("out the payInvoiceService updateBankCard success.");
							runTheadPoolTask(id);
						} else {
							result.setErrorCode(ErrorCode.BANK_CARD_NOT_AUDIT);
							logger.info("out the payInvoiceService updateBankCard error: " + result.getError());
						}
					} else {
						result.setErrorCode(ErrorCode.BANK_CARD_NOT_EXIST);
						logger.info("out the payInvoiceService updateBankCard error: " + result.getError());
					}
				} else {
					result.setErrorCode(ErrorCode.PAY_INVOICE_ERROR2);
					logger.info("out the payInvoiceService updateBankCard error: " + result.getError());
				}
			} else {
				result.setErrorCode(ErrorCode.PAY_DATA_NOT_EXISTSS);
				logger.info("out the payInvoiceService updateBankCard error: " + result.getError());
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			logger.info("out the payInvoiceService updateBankCard error: " + result.getError());
		}
		logger.info("out the payInvoiceService updateBankCard.");
		return result;
	}
	
	/**
	 * @annotation 司机修改银行卡后推送一次账单
	 * @param id
	 */
	private void runTheadPoolTask(final String id) {
		try {
			taskExecutor.execute(new Runnable() {

				@Override
				public void run() {
					driverPush(id);
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void callBackPayInvoicePayStatus() {
		PayInvoice record = new PayInvoice();
		record.setPushStatus(Constant.YES_PUSH);
		record.setPayStatus(Constant.PAY_ING);
		List<PayInvoice> list = payInvoiceMapper.selectByCondition(record);
		List<String> driverids = new ArrayList<String>();
		List<String> venderids = new ArrayList<String>();
		List<JSONObject> postData = new ArrayList<JSONObject>();
		if (CollectionUtils.isNotEmpty(list)) {
			for (PayInvoice payInvoice : list) {
				if (payInvoice.getPayeeIdentity() == Constant.PAY_INVOICE_DRIVER) {
					//司机
					driverids.add(payInvoice.getId());
				}else if(payInvoice.getPayeeIdentity() == Constant.PAY_INVOICE_VENDER){
					//车主
					venderids.add(payInvoice.getId());
				}
			}
			//查询司机
			List<PayInvoiceMsg> driverMsgList = payInvoiceMsgMapper.selectByPayInvoiceIdList(driverids);
			if (CollectionUtils.isNotEmpty(driverMsgList)) {
				for (PayInvoiceMsg payInvoiceMsg : driverMsgList) {
					JSONObject obj = new JSONObject();
					obj.put("billIds", payInvoiceMsg.getId());
					obj.put("payMent", "1");
					postData.add(obj);
					if(postData.size()>=20){
						//超过20条数据
						postDataPush(postData);
						postData.clear();
					}
				}
			}
			//查询车主
			List<PayInvoiceMsg> venderMsgList = payInvoiceMsgMapper.selectByPayInvoiceIdList(venderids);
			if (CollectionUtils.isNotEmpty(venderMsgList)) {
				for (PayInvoiceMsg payInvoiceMsg : venderMsgList) {
					JSONObject obj = new JSONObject();
					obj.put("billIds", payInvoiceMsg.getId());
					obj.put("payMent", "2");
					postData.add(obj);
					if(postData.size()>=20){
						//超过20条数据
						postDataPush(postData);
						postData.clear();
					}
				}
			}
		}
		postDataPush(postData);
	}
	
	protected void postDataPush(List<JSONObject> postData) {
		if (CollectionUtils.isNotEmpty(postData)) {
			//回写支付状态
			logger.info("NC请求数据："+postData.toString());
			ApiResult apiResult = HttpUtil.post(postData, HttpUrl.NC_URL_IP_PORT + HttpUrl.PAY_INVOICE_PAY_STATUS);
			logger.info("NC请求回显数据："+apiResult.toString());
			if (apiResult != null && StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				JSONArray array = JSONArray.parseArray(apiResult.getData().toString());
				if (CollectionUtils.isNotEmpty(array)) {
					callBackPayStatus(array);
				}
			}
		}
	}

	private void callBackPayStatus(JSONArray array) {
		for (Object object : array) {
			JSONObject jsonObject = (JSONObject) object;
			String id = jsonObject.getString("billId");
			String payStatus = jsonObject.getString("payStatus");
			PayInvoiceMsg bean = null;
			if(StringUtils.isNotBlank(id)){
				bean = payInvoiceMsgMapper.selectByPrimaryKey(id);
			}
			if (bean != null) {
				SystemMember member = systemMemberMapper.selectByPrimaryKey(bean.getPayeeId());
				PayInvoiceMsg payInvoiceMsg = new PayInvoiceMsg();
				PayInvoice payInvoice = new PayInvoice();
				//支付成功
				if (StringUtils.equals(payStatus, NCResultEnum.NC_RESULT_ENUM_11.getCode())) {
					//TODO
//					updateWithdrawReq req = new updateWithdrawReq();
//					req.setCellphone(member.getCellphone());
//					req.setCapitalno(id);
//					req.setEndtime(System.currentTimeMillis());
//					req.setFlag(true);
//					req.setActualamount(bean.getPaidAmount().longValue()*100);
//					req.setErrorcode(payStatus);
//					req.setErrormessage(NCResultEnum.getMessage(payStatus));
//					Result rs = withdrawRecordService.update(req);
//					if("000000".equals(rs.getCode())){
//					}
					
					payInvoiceMsg.setId(id);
					payInvoiceMsg.setPaidAmount(bean.getAmountPayable());
					payInvoiceMsg.setPayStatus(Constant.TWO);
					payInvoiceMsgMapper.updateByPrimaryKeySelective(payInvoiceMsg);
					
					payInvoice.setId(bean.getPayInvoiceId());
					payInvoice.setPaidAmount(bean.getAmountPayable());
					payInvoice.setPayStatus(Constant.TWO);
					payInvoiceMapper.updateByPrimaryKeySelective(payInvoice);
					payPassMessage(payInvoice);
					payPassMessages(payInvoice);
				}
				//支付中
				if (StringUtils.equals(payStatus, NCResultEnum.NC_RESULT_ENUM_12.getCode())) {
					payInvoiceMsg.setId(id);
					payInvoiceMsg.setPayStatus(Constant.ONE);
					payInvoiceMsgMapper.updateByPrimaryKeySelective(payInvoiceMsg);
					
					payInvoice.setId(bean.getPayInvoiceId());
					payInvoice.setPayStatus(Constant.ONE);
					payInvoiceMapper.updateByPrimaryKeySelective(payInvoice);
				}
				//支付失败
				if (StringUtils.equals(payStatus, NCResultEnum.NC_RESULT_ENUM_13.getCode())) {
					//TODO支付失败
//					updateWithdrawReq req = new updateWithdrawReq();
//					req.setCellphone(member.getCellphone());
//					req.setCapitalno(id);
//					req.setEndtime(System.currentTimeMillis());
//					req.setFlag(false);
//					req.setActualamount(0l);
//					req.setErrorcode(payStatus);
//					req.setErrormessage(NCResultEnum.getMessage(payStatus));
//					Result rs = withdrawRecordService.update(req);
//					if("000000".equals(rs.getCode())){
//					}
					payInvoiceMsg.setId(id);
					payInvoiceMsg.setPayStatus(Constant.ZERO);
					payInvoiceMsgMapper.updateByPrimaryKeySelective(payInvoiceMsg);
					payInvoice.setId(bean.getPayInvoiceId());
					payInvoice.setPayStatus(Constant.THREE);
					payInvoiceMapper.updateByPrimaryKeySelective(payInvoice);
					payNotPassMessage(payInvoice);
					payNotPassMessages(payInvoice);
				} 
			} else {
				LoggerFactory.getLogger("callbackPayInvoice").info("账单回写状态和金额错误信息: 该帐单流水查不到！");
			}
		}
	}
	
	@Override
	public ApiResult checkPayInvoice(PayInvoiceNcCheckParams apiParam) {
		ApiResult apiResult = ApiResult.getErrorResult();
		apiResult.setData(0);
		if (apiParam != null 
				&& StringUtils.isNotBlank(apiParam.getId())
				&& StringUtils.isNotBlank(apiParam.getAmountPayable())
				&& StringUtils.isNotBlank(apiParam.getPayeeName())
				&& StringUtils.isNotBlank(apiParam.getPayeeIdNo())
				&& StringUtils.isNotBlank(apiParam.getPayeeBankCardNumber())) {
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(apiParam.getId());
			if (payInvoice != null) {
				if (StringUtils.equals(String.valueOf(payInvoice.getAmountPayable()), apiParam.getAmountPayable())) {
					if (StringUtils.equals(payInvoice.getPayeeName(), apiParam.getPayeeName())) {
						if (StringUtils.equals(payInvoice.getPayeeBankCardNumber(), apiParam.getPayeeBankCardNumber())) {
							apiResult.setData(1);
							apiResult.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						} else {
							apiResult.setErrorCode(ErrorCode.PAY_INVOICE_ERROR7);
						}
					} else {
						apiResult.setErrorCode(ErrorCode.PAY_INVOICE_ERROR6);
					}
				} else {
					apiResult.setErrorCode(ErrorCode.PAY_INVOICE_ERROR4);
				}
			} else {
				apiResult.setErrorCode(ErrorCode.PAY_INVOICE_ERROR3);
			}
		}
		return apiResult;
	}
	
	//支付成功后司机消息推送
	public void payPassMessage(PayInvoice payInvoice) {
		try {
			PayInvoice pay = payInvoiceMapper.selectByPrimaryKey(payInvoice.getId());
			SendMsgReq mreq = new SendMsgReq();
			List<String> strs = new ArrayList<String>();
			strs.add(pay.getCode());//订单编号
			mreq.setType("1");
			mreq.setRecid(pay.getPayeeId());
			mreq.setRecname(pay.getPayeeName());
			mreq.setParams(strs);
			mreq.setCodeEnum(MessageCodeEnum.PAY_INVOICE_PASS);
			mreq.setRecType(MessageCodeEnum.PAY_INVOICE_PASS.getType());
			messageService.sendMessageInside(mreq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//支付成功后司机消息推送
	public void payNotPassMessage(PayInvoice payInvoice) {
		try {
			PayInvoice pay = payInvoiceMapper.selectByPrimaryKey(payInvoice.getId());
			SendMsgReq mreq = new SendMsgReq();
			List<String> strs = new ArrayList<String>();
			strs.add(pay.getCode());//订单编号
			mreq.setType("1");
			mreq.setRecid(pay.getPayeeId());
			mreq.setRecname(pay.getPayeeName());
			mreq.setParams(strs);
			mreq.setCodeEnum(MessageCodeEnum.PAY_INVOICE_NOTPASS);
			mreq.setRecType(MessageCodeEnum.PAY_INVOICE_NOTPASS.getType());
			messageService.sendMessageInside(mreq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//支付成功后签收者消息推送
		public void payPassMessages(PayInvoice payInvoice) {
			try {
				List<PayInvoiceDetail> payInvoiceDetail = payInvoiceDetailMapper.selectByPrimary(payInvoice.getId());
				for(PayInvoiceDetail pay : payInvoiceDetail){
					SystemMember systemMember = systemMemberMapper.selectByPrimaryKey(pay.getCreator());
					SendMsgReq mreq = new SendMsgReq();
					List<String> strs = new ArrayList<String>();
					strs.add(pay.getCode());//订单编号
					mreq.setType("1");
					mreq.setRecid(pay.getCreator());
					mreq.setRecname(systemMember.getRemarkname());
					mreq.setParams(strs);
					mreq.setCodeEnum(MessageCodeEnum.PAY_INVOICE_PASS_REC);
					mreq.setRecType(MessageCodeEnum.PAY_INVOICE_PASS_REC.getType());
					messageService.sendMessageInside(mreq);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//支付成功后签收者消息推送
		public void payNotPassMessages(PayInvoice payInvoice){
			try {
				List<PayInvoiceDetail> payInvoiceDetail = payInvoiceDetailMapper.selectByPrimary(payInvoice.getId());
				for(PayInvoiceDetail pay : payInvoiceDetail){
					SystemMember systemMember = systemMemberMapper.selectByPrimaryKey(pay.getCreator());
					SendMsgReq mreq = new SendMsgReq();
					List<String> strs = new ArrayList<String>();
					strs.add(pay.getCode());//订单编号
					mreq.setType("1");
					mreq.setRecid(pay.getCreator());
					mreq.setRecname(systemMember.getRemarkname());
					mreq.setParams(strs);
					mreq.setCodeEnum(MessageCodeEnum.PAY_INVOICE_NOTPASS_REC);
					mreq.setRecType(MessageCodeEnum.PAY_INVOICE_NOTPASS_REC.getType());
					messageService.sendMessageInside(mreq);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}