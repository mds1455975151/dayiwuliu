package com.tianrui.service.impl.money;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.money.intf.ICapitalAccountService;
import com.tianrui.api.money.intf.ICapitalRecordService;
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.req.money.CapitalAccountReq;
import com.tianrui.api.req.money.CapitalRecordReq;
import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.api.req.money.UpdateBillMoneyReq;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MoneyPendingBillMoney;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.MoneyPendingBillMoneyMapper;

@Service
public class PendingBillMoneyService implements IPendingBillMoneyService {

	Logger logger=LoggerFactory.getLogger(PendingBillMoneyService.class);
	@Autowired
	private MoneyPendingBillMoneyMapper billMoneyMapper;
	@Autowired
	private ICapitalAccountService capitalAccountService;
	@Autowired
	private ICapitalRecordService capitalRecordService;
	@Autowired
	private CacheClient cache ;
	@Override
	public Result save(SaveBillMoneyReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		Result rs = Result.getSuccessResult();
		String key = CacheHelper.buildKey(CacheModule.CAPITALACCOUNT, req.getCellphone());
		if(CacheHelper.capitalLock(cache, key)){
			try {
				if(null == req.getWaybillno() || "".equals(req.getWaybillno())){
					logger.error("运单编号不能为空");
					rs.setCode("0");
					rs.setError("运单编号不能为空");
				}else {
					MoneyPendingBillMoney pendingBill =  billMoneyMapper.selectByWaybillno(req.getWaybillno());
					if(null != pendingBill){
						logger.error("运单编号对应的运费记录已存在，请勿重复操作");
						rs.setCode("1");
						rs.setError("运单编号对应的运费记录已存在，请勿重复操作");
					}else {
						savePendingBillMoney(req, rs);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				rs.setCode("02");
				rs.setError("数据保存失败！");
			}finally{
				cache.remove(key);
			}
		}else {
			logger.error("资金账户正在处理中，请稍后。");
			rs.setCode("666111");
			rs.setError("资金账户正在处理中，请稍后。");
		}
		return rs;
	}
	/**
	 * 保存待收入运费
	 * @param req
	 * @param rs
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private void savePendingBillMoney(SaveBillMoneyReq req, Result rs) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		MoneyPendingBillMoney mbm = new MoneyPendingBillMoney();
			PropertyUtils.copyProperties(mbm, req);
			mbm.setCapitalno(req.getWaybillno());
			int r = 0;
			r = billMoneyMapper.insertSelective(mbm);
			CapitalAccountReq accountReq = new CapitalAccountReq();
			accountReq.setCellphone(req.getCellphone());
			accountReq.setUsername(req.getUsername());
			accountReq.setUseryhno(req.getUseryhno());
			accountReq.setPendingmoney(req.getPendingmoney());
			rs = capitalAccountService.saveOrUpdate(accountReq, TransactionType.PENDING);
			if(r == 0 ){
				rs.setCode("2");
				rs.setError("数据保存失败");
			}
	}
	@Override
	public Result update(UpdateBillMoneyReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		String key = CacheHelper.buildKey(CacheModule.CAPITALACCOUNT, req.getCellphone());
		if(CacheHelper.capitalLock(cache, key)){
			try {
				if(null == req.getWaybillno() || "".equals(req.getWaybillno())){
					rs.setCode("0");
					rs.setError("运单编号不能为空");
				}else {
		            MoneyPendingBillMoney pendingBill =  billMoneyMapper.selectByWaybillno(req.getWaybillno());
		            if(null == pendingBill){
		            	rs.setCode("1");
		    			rs.setError("运单编号对应的运费记录不存在，请确认参数正确");
		            }else {
		            	rs = paidBillMoney(req, pendingBill);
					}
				}
			} catch (Exception e) {
				rs.setCode("02");
				rs.setError("数据保存失败！");
			}finally{
				cache.remove(key);
			}
		}else {
			rs.setCode("666111");
			rs.setError("资金账户正在处理中，请稍后。");
		}
		return rs;
	}
	/**
	 * 运费入账
	 * @param req
	 * @param pendingBill
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private Result paidBillMoney(UpdateBillMoneyReq req, MoneyPendingBillMoney pendingBill)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs;
		pendingBill.setCapitalno(req.getCapitalno());
		pendingBill.setIfpaid((short)1);
		pendingBill.setPaidtime(req.getPaidtime());
		pendingBill.setPaidmoney(req.getPaidmoney());
		pendingBill.setDeductionmoney(req.getDeductionmoney());
		int r = 0;
		r = billMoneyMapper.updateByPrimaryKeySelective(pendingBill);
		CapitalRecordReq recordReq = new CapitalRecordReq();
		recordReq.setAvailablemoney(req.getPaidmoney());
		recordReq.setCellphone(pendingBill.getCellphone());
		recordReq.setUseryhno(pendingBill.getUseryhno());
		recordReq.setUsername(pendingBill.getUsername());
		recordReq.setCapitalno(req.getCapitalno());
		rs = capitalRecordService.save(recordReq, TransactionType.PAID);
		CapitalAccountReq accountReq = new CapitalAccountReq();
		accountReq.setCellphone(pendingBill.getCellphone());
		accountReq.setUsername(pendingBill.getUsername());
		accountReq.setUseryhno(pendingBill.getUseryhno());
		accountReq.setAvailablemoney(req.getPaidmoney());
		accountReq.setPendingmoney(pendingBill.getPendingmoney());
		if("000000".equals(rs.getCode())){
			rs = capitalAccountService.saveOrUpdate(accountReq, TransactionType.PAID);
		}
		if(r == 0 ){
			rs.setCode("2");
			rs.setError("数据保存失败");
		}
		return rs;
	}

}
