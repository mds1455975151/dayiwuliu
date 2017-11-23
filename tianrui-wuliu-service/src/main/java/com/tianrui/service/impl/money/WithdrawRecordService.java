package com.tianrui.service.impl.money;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.money.intf.ICapitalAccountService;
import com.tianrui.api.money.intf.ICapitalRecordService;
import com.tianrui.api.money.intf.IWithdrawRecordService;
import com.tianrui.api.req.money.CapitalAccountReq;
import com.tianrui.api.req.money.CapitalRecordReq;
import com.tianrui.api.req.money.FindWithdrawRecordReq;
import com.tianrui.api.req.money.SaveWithdrawReq;
import com.tianrui.api.req.money.updateWithdrawReq;
import com.tianrui.api.resp.money.CapitalAccountResp;
import com.tianrui.api.resp.money.FindWithdrawRecordResp;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.exception.ApplicationExectpion;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MoneyWithdrawRecord;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
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
	@Autowired
	private CacheClient cache ;
	
	@Override
	public PaginationVO<FindWithdrawRecordResp> select(FindWithdrawRecordReq req) throws Exception {
		PaginationVO<FindWithdrawRecordResp> page = new PaginationVO<FindWithdrawRecordResp>();
		//查询类型转化
		MoneyWithdrawRecord query = new MoneyWithdrawRecord();
		if(req.getPageNo()!=null){
			//pageNo == null 全部查询  pageNo != null 分页查询
			query.setPageNo(req.getPageNo()*req.getPageSize());
			query.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		query.setCellphone(req.getCellPhone());
		query.setUseryhno(req.getUseryhno());
		//查询总条数
		long a = withdrawRecordMapper.selectByCount(query);
		page.setTotal(a);
		if(a != 0l){
			List<MoneyWithdrawRecord> list = withdrawRecordMapper.selectByCondition(query);
			//转换返回值类型
			page.setList(copyProperties2(list));
		}
		return page;
	}
	/** 转换返回值类型*/
	private List<FindWithdrawRecordResp> copyProperties2(List<MoneyWithdrawRecord> list)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<FindWithdrawRecordResp> rpList = new ArrayList<FindWithdrawRecordResp>();
		for(MoneyWithdrawRecord rec : list){
			FindWithdrawRecordResp resp = new FindWithdrawRecordResp();
			PropertyUtils.copyProperties(resp, rec);
			rpList.add(resp);
		}
		return rpList;
	}
	
	@Override
	@Transactional
	public Result save(SaveWithdrawReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		String key = CacheHelper.buildKey(CacheModule.CAPITALACCOUNT, req.getCellphone());
		if(CacheHelper.capitalLock(cache, key)){
			try {
				rs = withdraw(req, rs);
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new ApplicationExectpion("数据保存失败！");
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
	 * 提现申请保存
	 * @param req
	 * @param rs
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private Result withdraw(SaveWithdrawReq req, Result rs)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
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
			rs = capitalRecordService.save(recordReq, TransactionType.TXSQ);
			CapitalAccountReq accountReq = new CapitalAccountReq();
			accountReq.setUseryhno(accountResp.getUseryhno());
			accountReq.setAvailablemoney(req.getMoney());
			accountReq.setLockmoney(req.getMoney());
			if("000000".equals(rs.getCode())){
				rs = capitalAccountService.saveOrUpdate(accountReq, TransactionType.TXSQ);
				if("000000".equals(rs.getCode())){
					MoneyWithdrawRecord mwr = new MoneyWithdrawRecord();
					PropertyUtils.copyProperties(mwr, req);
					mwr.setUsername(accountResp.getUsername());
					mwr.setUseryhno(accountResp.getUseryhno());
					mwr.setTransactionstate((short)1);
					mwr.setAvailablemoney(accountResp.getAvailablemoney() -req.getMoney() );
					withdrawRecordMapper.insert(mwr);
				}
			}
		}
		return rs;
	}

	@Override
	@Transactional
	public Result update(updateWithdrawReq req) {
		Result rs = Result.getSuccessResult();
		String key = CacheHelper.buildKey(CacheModule.CAPITALACCOUNT, req.getCellphone());
		if(CacheHelper.capitalLock(cache, key)){
			try {
				MoneyWithdrawRecord wred = withdrawRecordMapper.selectByCapitalno(req.getCapitalno());
				if(null == wred ){
					rs.setCode("01");
					rs.setError("未发现的提现流水号！");
				}else if (null != wred.getEndtime()) {
					rs.setCode("02");
					rs.setError("提现流水号已经处理完成！");
				}else {
					wred.setEndtime(req.getEndtime());
					if(req.isFlag()){
						rs = withdrawSuccess(req, wred);
					}else {
						rs = wiehdrawFail(req, wred);
					}
					withdrawRecordMapper.updateByPrimaryKeySelective(wred);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new ApplicationExectpion("数据保存失败！");
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
