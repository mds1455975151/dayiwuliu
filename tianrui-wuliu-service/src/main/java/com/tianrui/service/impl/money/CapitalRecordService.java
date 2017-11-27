package com.tianrui.service.impl.money;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.money.intf.ICapitalRecordService;
import com.tianrui.api.req.money.CapitalRecordReq;
import com.tianrui.api.req.money.FindCapitalRecordByIdReq;
import com.tianrui.api.req.money.FindCapitalRecordReq;
import com.tianrui.api.resp.money.FindCapitalRecordResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MoneyCapitalRecord;
import com.tianrui.service.mapper.MoneyCapitalRecordMapper;

@Service
public class CapitalRecordService implements ICapitalRecordService {

	Logger logger=LoggerFactory.getLogger(CapitalRecordService.class);
	@Autowired 
	private MoneyCapitalRecordMapper recordMapper;
	
	@Override
	public Result selectCapitalRecordById(FindCapitalRecordByIdReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MoneyCapitalRecord bean = recordMapper.selectByPrimaryKey(req.getId());
		FindCapitalRecordResp resp = new FindCapitalRecordResp();
		PropertyUtils.copyProperties(resp, bean);
		rs.setData(resp);
		return rs;
	}
	
	@Override
	public PaginationVO<FindCapitalRecordResp> select(FindCapitalRecordReq req) throws Exception {
		// TODO Auto-generated method stub
		PaginationVO<FindCapitalRecordResp> page = new PaginationVO<FindCapitalRecordResp>();
		MoneyCapitalRecord query = new MoneyCapitalRecord();
		if(req.getPageNo()!=null){
			query.setPageNo(req.getPageNo()*req.getPageSize());
			query.setPageSize(req.getPageSize());
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		query.setCellphone(req.getCellphone());
		query.setUsername(req.getUsername());
		query.setUseryhno(req.getUseryhno());
		query.setTransactiontype(req.getTransactiontype());
		long a = recordMapper.selectByCount(query);
		page.setTotal(a);
		if(a != 0l){
			List<MoneyCapitalRecord> list = recordMapper.selectByCondition(query);
			page.setList(copyProperties2(list));
		}
		return page;
	}
	/** 返回数据类型转换*/
	private List<FindCapitalRecordResp> copyProperties2(List<MoneyCapitalRecord> list)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<FindCapitalRecordResp> resp = new ArrayList<FindCapitalRecordResp>();
		for(MoneyCapitalRecord rec : list){
			FindCapitalRecordResp sp = new FindCapitalRecordResp();
			PropertyUtils.copyProperties(sp, rec);
			resp.add(sp);
		}
		return resp;
	}
	
	@Override
	public Result save(CapitalRecordReq req,TransactionType type) {
		Result rs = Result.getSuccessResult();
		MoneyCapitalRecord mRecord = null;
		if(null != req.getUseryhno() && !"".equals(req.getUseryhno())){
			mRecord = recordMapper.selectByUseryhno(req.getUseryhno());
		}else if (null != req.getCellphone() && !"".equals(req.getCellphone())) {
			mRecord = recordMapper.selectByCellphone(req.getCellphone());
		}else {
			rs.setErrorCode(ErrorCode.CAPITAL_NOT_CELLPHONE_OR_USERYHNO);
			return rs;
		}
		MoneyCapitalRecord newRecord = new MoneyCapitalRecord();
		newRecord.setTransactiontype((long) type.getType());//交易类型
		newRecord.setCreatetime(new Date().getTime());//记录时间
		newRecord.setCapitalno(req.getCapitalno());//交易流水号
		if(null != mRecord){
			newRecord.setUsername(mRecord.getUsername());//用户姓名
			newRecord.setUseryhno(mRecord.getUseryhno());//用户银行唯一标识
			newRecord.setCellphone(mRecord.getCellphone());//登录账号
		}else {
			newRecord.setUsername(req.getUsername());//用户姓名
			newRecord.setUseryhno(req.getUseryhno());//用户银行唯一标识
			newRecord.setCellphone(req.getCellphone());//登录账号
		}
		newRecord.setMoney(req.getAvailablemoney());//交易金额
		if(type == TransactionType.PAID){//收入运费
			incomeFreight(req, mRecord, newRecord);
		}else if (type == TransactionType.TXSQ) {//提现申请
			withdrawCash(req, rs, mRecord, newRecord);
		}else if (type == TransactionType.TXSUCESS) {//提现成功
			withdrawCashSuccess(req, rs, mRecord, newRecord);
		}else if (type == TransactionType.TXFAIL) {//提现失败
			withdrawCashFail(req, rs, mRecord, newRecord);
		}else {
			rs.setErrorCode(ErrorCode.CAPITAL_NOT_TRANSACTIONTYPE);
		}
		recordMapper.insert(newRecord);
		return rs;
	}
	/**
	 * 提现失败资金流水
	 * @param req
	 * @param rs
	 * @param mRecord
	 * @param newRecord
	 */
	private void withdrawCashFail(CapitalRecordReq req, Result rs, MoneyCapitalRecord mRecord,
			MoneyCapitalRecord newRecord) {
		if(null == mRecord){
			rs.setErrorCode(ErrorCode.CAPITAL_RECORD_NULL);
		}else{
			//在原有资金流水基础上 新增资金流水
			newRecord.setAvailablemoney(mRecord.getAvailablemoney()+req.getAvailablemoney());//可用余额增加
			newRecord.setExpenditure(0L);//支出金额
			newRecord.setIncome(0L);//收入金额
			newRecord.setTotalmoney(mRecord.getTotalmoney());//账户总额不变
			newRecord.setOtherlockmoney(mRecord.getOtherlockmoney());//其他冻结金额不变
			newRecord.setWithdrawalslockmoney(mRecord.getWithdrawalslockmoney()-req.getAvailablemoney());//提现冻结金额减少
		}
	}
	/**
	 * 提现成功资金流水
	 * @param req
	 * @param rs
	 * @param mRecord
	 * @param newRecord
	 */
	private void withdrawCashSuccess(CapitalRecordReq req, Result rs, MoneyCapitalRecord mRecord,
			MoneyCapitalRecord newRecord) {
		if(null == mRecord){
			rs.setErrorCode(ErrorCode.CAPITAL_RECORD_NULL);
		}else{
			//在原有资金流水基础上 新增资金流水
			newRecord.setAvailablemoney(mRecord.getAvailablemoney());//可用余额不变
			newRecord.setExpenditure(req.getAvailablemoney());//支出金额
			newRecord.setIncome(0L);//收入金额
			newRecord.setTotalmoney(mRecord.getTotalmoney()-req.getAvailablemoney());//账户总额减少
			newRecord.setOtherlockmoney(mRecord.getOtherlockmoney());//其他冻结金额不变
			newRecord.setWithdrawalslockmoney(mRecord.getWithdrawalslockmoney()-req.getAvailablemoney());//提现冻结金额减少
		}
	}
	/**
	 * 提现申请资金流水
	 * @param req
	 * @param rs
	 * @param mRecord
	 * @param newRecord
	 */
	private void withdrawCash(CapitalRecordReq req, Result rs, MoneyCapitalRecord mRecord,
			MoneyCapitalRecord newRecord) {
		if(null == mRecord){
			rs.setErrorCode(ErrorCode.CAPITAL_RECORD_NULL);
		}else{
			//在原有资金流水基础上 新增资金流水
			newRecord.setAvailablemoney(mRecord.getAvailablemoney()-req.getAvailablemoney());//可用余额减少
			newRecord.setExpenditure(0L);//支出金额
			newRecord.setIncome(0L);//收入金额
			newRecord.setTotalmoney(mRecord.getTotalmoney());//账户总额不变
			newRecord.setOtherlockmoney(mRecord.getOtherlockmoney());//其他冻结金额不变
			newRecord.setWithdrawalslockmoney(mRecord.getWithdrawalslockmoney()+req.getAvailablemoney());//提现冻结金额增加
		}
	}
	/**
	 * 收入运费资金流水
	 * @param req
	 * @param mRecord
	 * @param newRecord
	 */
	private void incomeFreight(CapitalRecordReq req, MoneyCapitalRecord mRecord, MoneyCapitalRecord newRecord) {
		if(null == mRecord){
				//创建第一条资金流水
			newRecord.setAvailablemoney(req.getAvailablemoney());//可用余额
			newRecord.setExpenditure(0L);//支出金额
			newRecord.setIncome(req.getAvailablemoney());//收入金额
			newRecord.setTotalmoney(req.getAvailablemoney());//账户总额
			newRecord.setOtherlockmoney(0L);//其他冻结金额
			newRecord.setWithdrawalslockmoney(0L);//提现冻结金额
		}else{
			//在原有资金流水基础上 新增资金流水
			newRecord.setAvailablemoney(mRecord.getAvailablemoney()+req.getAvailablemoney());//可用余额增加
			newRecord.setExpenditure(0L);//支出金额 0 
			newRecord.setIncome(req.getAvailablemoney());//收入金额
			newRecord.setTotalmoney(mRecord.getTotalmoney() + req.getAvailablemoney());//账户总额增加
			newRecord.setOtherlockmoney(mRecord.getOtherlockmoney());//其他冻结金额不变
			newRecord.setWithdrawalslockmoney(mRecord.getWithdrawalslockmoney());//提现冻结金额不变
		}
	}
}
