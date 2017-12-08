package com.tianrui.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IMoenyDisposeService;
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.money.intf.IWithdrawRecordService;
import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.api.req.money.SaveWithdrawReq;
import com.tianrui.api.req.money.UpdateBillMoneyReq;
import com.tianrui.api.req.money.updateWithdrawReq;
import com.tianrui.common.constants.NCResultEnum;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.PayInvoice;
import com.tianrui.service.admin.bean.PayInvoiceDetail;
import com.tianrui.service.admin.mapper.PayInvoiceDetailMapper1;
import com.tianrui.service.admin.mapper.PayInvoiceMapper1;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.bean.OldBillMoney;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.SystemMemberInfo;
import com.tianrui.service.mapper.MemberBankCardMapper;
import com.tianrui.service.mapper.OldBillMoneyMapper;
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
	@Autowired
	OldBillMoneyMapper oldBillMoneyMapper;
	@Autowired
	PayInvoiceDetailMapper1 payInvoiceDetailMapper;
	@Autowired
	private MemberBankCardMapper memberBankCardMapper;
	@Autowired
	private PayInvoiceMapper1 payInvoiceMapper;
	@Autowired
	IWithdrawRecordService withdrawRecordService;
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
	@Override
	public Result oldDYSaveBillMoney() throws Exception {
		List<OldBillMoney> list = oldBillMoneyMapper.selectByOldDYBill(null);
		for(OldBillMoney old : list){
			SaveBillMoneyReq bm = new SaveBillMoneyReq();
			bm.setWaybillno(old.getWaybillno());
			bm.setCreatetime(old.getUnloadtime());
			bm.setPendingmoney((long) (old.getPrice()*old.getWeight()*100));
			bm.setUseryhno(old.getDriverid());//身份证号
			Result rs = billSaveMoney(bm);
			System.out.println(rs.getCode()+rs.getError());
		}
		return null;
	}
	
	@Override
	public Result oldALSaveBillMoney() throws Exception {
		List<OldBillMoney> list = oldBillMoneyMapper.selectByOldALBill(null);
		int a = 0;
		for(OldBillMoney old : list){
			a = a+1;
			SaveBillMoneyReq bm = new SaveBillMoneyReq();
			bm.setWaybillno(old.getWaybillno());
			bm.setCreatetime(old.getUnloadtime());
			bm.setPendingmoney((long) (old.getPrice()*old.getWeight()*100));
			bm.setUseryhno(old.getDriverid());//身份证号
			Result rs = billSaveMoney(bm);
			System.out.println(list.size()+"----->"+a+rs.getCode()+rs.getError());
		}
		return null;
	}
	@SuppressWarnings("unused")
	@Override
	public Result uptPandMoney() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result result = Result.getSuccessResult();
		List<OldBillMoney> om = oldBillMoneyMapper.selectByOldUptMoney(null);
		for(OldBillMoney old : om){
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(old.getPayid());
			MemberBankCard bank = memberBankCardMapper.selectByPrimaryKey(old.getBankid());
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
			req.setCellphone(payInvoice.getPayeeAccount());
			req.setWaybillno(waybillno);
			//支付总额
			req.setPaidmoney(payInvoice.getAmountPayable().longValue() * 100);
			//计算扣款总额
			Double doc = payInvoice.getBackstageDeductMoney()+payInvoice.getBackstageDeductOilCard()+payInvoice.getBackstageDeductOther()+payInvoice.getBackstageDeductWeightMisc();
			req.setDeductionmoney(doc.longValue()*100);
			req.setPaidtime(System.currentTimeMillis());
			req.setCapitalno(UUIDUtil.getId());
			result = pendingBillMoneyService.update(req);
			System.out.println(result.getCode()+result.getError());
		}
			
		return result;
	}
	
	private Result saveWithDrawList(PayInvoice payInvoice, MemberBankCard bank, String capitalno){
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
		System.out.println(result.getCode()+result.getError());
		return result;
	}
	@Override
	public Result saveWithDraw() throws Exception {
		List<OldBillMoney> om = oldBillMoneyMapper.selectByOldWithDraw(null);
		for(OldBillMoney mon : om){
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(mon.getPayid());
			MemberBankCard bank = memberBankCardMapper.selectByPrimaryKey(mon.getBankid());
			saveWithDrawList(payInvoice,bank,mon.getPayid());
		}
		return null;
	}
	@Override
	public Result saveWithDrawSuccess() throws Exception {
		List<OldBillMoney> om = oldBillMoneyMapper.selectByOldWithDrawSuccess(null);
		for(OldBillMoney mon : om){
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(mon.getPayid());
			SystemMember member = systemMemberMapper.selectByPrimaryKey(payInvoice.getPayeeId());
			updateWithdrawReq req = new updateWithdrawReq();
			req.setCellphone(member.getCellphone());
			req.setCapitalno(payInvoice.getId());
			req.setEndtime(System.currentTimeMillis());
			req.setFlag(true);
			req.setActualamount(payInvoice.getPaidAmount().longValue()*100);
			req.setErrorcode("");
			req.setErrormessage(NCResultEnum.getMessage("历史数据处理"));
			Result rs = withdrawRecordService.update(req);
			System.out.println(rs.getCode()+rs.getError());
		}
		return null;
	}
	@Override
	public Result saveWithDrawFail() throws Exception {
		List<OldBillMoney> om = oldBillMoneyMapper.selectByOldWithDrawFail(null);
		for(OldBillMoney mon : om){
			PayInvoice payInvoice = payInvoiceMapper.selectByPrimaryKey(mon.getPayid());
			SystemMember member = systemMemberMapper.selectByPrimaryKey(payInvoice.getPayeeId());
			updateWithdrawReq req = new updateWithdrawReq();
			req.setCellphone(member.getCellphone());
			req.setCapitalno(payInvoice.getId());
			req.setEndtime(System.currentTimeMillis());
			req.setFlag(false);
			req.setActualamount(0l);
			req.setErrorcode("");
			req.setErrormessage(NCResultEnum.getMessage("历史数据处理"));
			Result rs = withdrawRecordService.update(req);
			System.out.println(rs.getCode()+rs.getError());
		}
		return null;
	}

}
