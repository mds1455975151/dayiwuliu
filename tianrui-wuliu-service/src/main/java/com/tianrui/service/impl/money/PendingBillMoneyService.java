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
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.req.money.CapitalAccountReq;
import com.tianrui.api.req.money.CapitalRecordReq;
import com.tianrui.api.req.money.FindPendingBillMoneyReq;
import com.tianrui.api.req.money.FindPendingMoneyByIdReq;
import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.api.req.money.UpdateBillMoneyReq;
import com.tianrui.api.resp.money.FindPendingBillMoneyResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.exception.ApplicationExectpion;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.Bill;
import com.tianrui.service.bean.MoneyPendingBillMoney;
import com.tianrui.service.bean.anlian.AnlianBill;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.AnlianBillMapper;
import com.tianrui.service.mapper.BillMapper;
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
	AnlianBillMapper anlianBillMapper;
	@Autowired
	BillMapper billMapper;
	@Autowired
	private CacheClient cache ;
	
	@Override
	public Result selectPendingBillMoneyById(FindPendingMoneyByIdReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MoneyPendingBillMoney bean = billMoneyMapper.selectByPrimaryKey(req.getId());
		Long signtime = null;
		if(bean!=null){
			String billNo = bean.getWaybillno();
			//获取运单签收时间
			signtime = getBillSignTime(signtime, billNo);
		}
		FindPendingBillMoneyResp resp = new FindPendingBillMoneyResp();
		PropertyUtils.copyProperties(resp, bean);
		resp.setOwnerSignTime(signtime);
		rs.setData(resp);
		return rs;
	}
	/** 获取运单签收时间*/
	private Long getBillSignTime(Long signtime, String billNo) {
		Bill ab = new Bill();
		ab.setWaybillno(billNo);
		//通过运单号查询大易运单
		List<Bill> abb = billMapper.selectByCondition(ab);
		if(abb.size() == 1){
			signtime = abb.get(0).getOwnerSigntime();
		}else {
			AnlianBill bill = new AnlianBill();
			bill.setBillno(billNo);
			List<AnlianBill> list = anlianBillMapper.selectByCondition(bill);
			//通过运单号查询安联运单
			if(list.size() == 1){
				signtime = list.get(0).getSigntime();
			}
		}
		return signtime;
	}
	@Override
	public PaginationVO<FindPendingBillMoneyResp> select(FindPendingBillMoneyReq req) throws Exception {
		PaginationVO<FindPendingBillMoneyResp> page = new PaginationVO<FindPendingBillMoneyResp>();
		MoneyPendingBillMoney query = new MoneyPendingBillMoney();
		//查询类型转换
		if(req.getPageNo()!=null){
			//pageNo != null 分页查  pageNo == null 不分页
			query.setPageNo(req.getPageNo()*req.getPageSize());
			query.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		query.setCellphone(req.getCellphone());
		query.setWaybillno(req.getWaybillno());
		query.setUseryhno(req.getUseryhno());
		query.setIfpaid(req.getIfpaid());
		long a = billMoneyMapper.selectByCount(query);
		page.setTotal(a);
		if(a!=0l){
			List<MoneyPendingBillMoney> list = billMoneyMapper.selectByCondition(query);
			//返回类型转换
			page.setList(copyProperties2(list));
		}
		return page;
	}
	/** 返回类型转换*/
	private List<FindPendingBillMoneyResp> copyProperties2(List<MoneyPendingBillMoney> list)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<FindPendingBillMoneyResp> resp = new ArrayList<FindPendingBillMoneyResp>();
		for(MoneyPendingBillMoney mone : list){
			FindPendingBillMoneyResp sp = new FindPendingBillMoneyResp();
			PropertyUtils.copyProperties(sp, mone);
			resp.add(sp);
		}
		return resp;
	}
	
	@Override
	@Transactional
	public Result save(SaveBillMoneyReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		Result rs = Result.getSuccessResult();
		String key = CacheHelper.buildKey(CacheModule.CAPITALACCOUNT, req.getCellphone());
		if(CacheHelper.capitalLock(cache, key)){
			try {
				if(null == req.getWaybillno() || "".equals(req.getWaybillno())){
					rs.setErrorCode(ErrorCode.CAPITAL_WAYBILLNO_NULL);
				}else {
					MoneyPendingBillMoney pendingBill =  billMoneyMapper.selectByWaybillno(req.getWaybillno());
					if(null != pendingBill){
						rs.setErrorCode(ErrorCode.CAPITAL_WAYBILLNO_DISPOSED);
					}else {
						savePendingBillMoney(req, rs);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new ApplicationExectpion("数据保存失败！");
			}finally{
				cache.remove(key);
			}
		}else {
			rs.setErrorCode(ErrorCode.CAPITAL_IN_PROCESS);
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
			billMoneyMapper.insertSelective(mbm);
			CapitalAccountReq accountReq = new CapitalAccountReq();
			accountReq.setCellphone(req.getCellphone());
			accountReq.setUsername(req.getUsername());
			accountReq.setUseryhno(req.getUseryhno());
			accountReq.setPendingmoney(req.getPendingmoney());
			rs = capitalAccountService.saveOrUpdate(accountReq, TransactionType.PENDING);
	}
	@Override
	@Transactional
	public Result update(UpdateBillMoneyReq req) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		String key = CacheHelper.buildKey(CacheModule.CAPITALACCOUNT, req.getCellphone());
		if(CacheHelper.capitalLock(cache, key)){
			try {
				if(null == req.getWaybillno() || "".equals(req.getWaybillno())){
					rs.setErrorCode(ErrorCode.CAPITAL_WAYBILLNO_NULL);
				}else {
		            MoneyPendingBillMoney pendingBill =  billMoneyMapper.selectByWaybillno(req.getWaybillno());
		            if(null == pendingBill){
		            	rs.setErrorCode(ErrorCode.CAPITAL_WAYBILLNO_NOT_PENDING);
		            }else if (pendingBill.getIfpaid() == 1) {
		            	rs.setErrorCode(ErrorCode.CAPITAL_WAYBILLNO_PAYMENT);
					}else{
		            	rs = paidBillMoney(req, pendingBill);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new ApplicationExectpion("数据保存失败！");
			}finally{
				cache.remove(key);
			}
		}else {
			rs.setErrorCode(ErrorCode.CAPITAL_IN_PROCESS);
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
		billMoneyMapper.updateByPrimaryKeySelective(pendingBill);
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
		return rs;
	}
}
