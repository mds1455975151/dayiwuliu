package com.tianrui.service.impl.money;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.money.intf.ICapitalAccountService;
import com.tianrui.api.money.intf.ICapitalRecordService;
import com.tianrui.api.money.intf.IWithdrawRecordService;
import com.tianrui.api.req.money.CapitalAccountReq;
import com.tianrui.api.req.money.CapitalRecordReq;
import com.tianrui.api.req.money.SaveWithdrawReq;
import com.tianrui.api.req.money.updateWithdrawReq;
import com.tianrui.api.resp.money.CapitalAccountResp;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MoneyWithdrawRecord;
import com.tianrui.service.mapper.MoneyWithdrawRecordMapper;
@Service
public class WithdrawRecordService implements IWithdrawRecordService {

	Logger logger=LoggerFactory.getLogger(WithdrawRecordService.class);
	@Autowired
	private MoneyWithdrawRecordMapper withdrawRecordMapper;
	@Autowired
	private ICapitalAccountService capitalAccountService;
	@Autowired
	private ICapitalRecordService capitalRecordService;
	
	
	@Override
	public Result save(SaveWithdrawReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		CapitalAccountResp accountResp = null;
		if(null != req.getCellphone() && !"".equals(req.getCellphone())){
			accountResp = capitalAccountService.getByCellphone(req.getCellphone());
		}else if(null != req.getUseryhno() && !"".equals(req.getUseryhno())){
			accountResp = capitalAccountService.getByUseryhno(req.getUseryhno());
		}
		if(null == accountResp){
			rs.setCode("01");
			rs.setError("用户资金账户异常，无法提现！");
		}else if(accountResp.getAvailablemoney() < req.getMoney()){
			rs.setCode("02");
			rs.setError("用户资金账户金额不足，无法提现！");
		}else {
			CapitalRecordReq recordReq = new CapitalRecordReq();
			recordReq.setUseryhno(accountResp.getUseryhno());
			recordReq.setAvailablemoney(req.getMoney());
			recordReq.setCapitalno(req.getCapitalno());
			Result rrs = capitalRecordService.save(recordReq, TransactionType.TXSQ);
			CapitalAccountReq accountReq = new CapitalAccountReq();
			accountReq.setUseryhno(accountResp.getUseryhno());
			accountReq.setAvailablemoney(req.getMoney());
			accountReq.setLockmoney(req.getMoney());
			Result qrs = capitalAccountService.saveOrUpdate(accountReq, TransactionType.TXSQ);
			MoneyWithdrawRecord mwr = new MoneyWithdrawRecord();
			PropertyUtils.copyProperties(mwr, req);
			mwr.setTransactionstate((short)1);
			mwr.setAvailablemoney(accountResp.getAvailablemoney() -req.getMoney() );
			int r = 0;
			r = withdrawRecordMapper.insert(mwr);
			if(r == 0 || !"000000".equals(rrs)|| !"000000".equals(qrs)){
				rs.setCode("2");
				rs.setError("数据保存失败");
			}
		}
		return rs;
	}

	@Override
	public Result update(updateWithdrawReq req) {
		Result rs = Result.getSuccessResult();
		MoneyWithdrawRecord wred = withdrawRecordMapper.selectByCapitalno(req.getCapitalno());
		if(null == wred ){
			rs.setCode("01");
			rs.setError("未发现的提现流水号！");
		}else {
			wred.setEndtime(req.getEndtime());
			if(req.isFlag()){
				rs = withdrawSuccess(req, wred);
			}else {
				rs = wiehdrawFail(req, wred);
			}
			int r = 0;
			r = withdrawRecordMapper.updateByPrimaryKeySelective(wred);
			if(r == 0 ){
				rs.setCode("2");
				rs.setError("数据保存失败");
			}
		}
		return rs;
	}
	/**
	 * 提现失败
	 * @param req
	 * @param wred
	 * @return
	 */
	private Result wiehdrawFail(updateWithdrawReq req, MoneyWithdrawRecord wred) {
		Result rs;
		wred.setTransactionstate((short)3);
		wred.setErrorcode(req.getErrorcode());
		wred.setErrormessage(req.getErrormessage());
		CapitalRecordReq recordReq = new CapitalRecordReq();
		recordReq.setUseryhno(wred.getUseryhno());
		recordReq.setAvailablemoney(wred.getMoney());
		recordReq.setCapitalno(req.getCapitalno());
		rs = capitalRecordService.save(recordReq, TransactionType.TXFAIL);
		CapitalAccountReq accountReq = new CapitalAccountReq();
		accountReq.setUseryhno(wred.getUseryhno());
		accountReq.setAvailablemoney(wred.getMoney());
		accountReq.setLockmoney(wred.getMoney());
		try {
			if("000000".equals(rs.getCode())){
				rs = capitalAccountService.saveOrUpdate(accountReq, TransactionType.TXFAIL);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 提现成功
	 * @param req
	 * @param wred
	 * @return
	 */
	private Result withdrawSuccess(updateWithdrawReq req, MoneyWithdrawRecord wred) {
		Result rs;
		wred.setTransactionstate((short)2);
		wred.setActualamount(req.getActualamount());
		CapitalRecordReq recordReq = new CapitalRecordReq();
		recordReq.setUseryhno(wred.getUseryhno());
		recordReq.setAvailablemoney(wred.getMoney());
		recordReq.setCapitalno(req.getCapitalno());
		rs = capitalRecordService.save(recordReq, TransactionType.TXSUCESS);
		CapitalAccountReq accountReq = new CapitalAccountReq();
		accountReq.setUseryhno(wred.getUseryhno());
		accountReq.setAvailablemoney(wred.getMoney());
		accountReq.setLockmoney(wred.getMoney());
		try {
			if("000000".equals(rs.getCode())){
				rs = capitalAccountService.saveOrUpdate(accountReq, TransactionType.TXSUCESS);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
