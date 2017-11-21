package com.tianrui.service.impl.money;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.money.intf.ICapitalAccountService;
import com.tianrui.api.req.money.CapitalAccountReq;
import com.tianrui.api.resp.money.CapitalAccountResp;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MoneyCapitalAccount;
import com.tianrui.service.mapper.MoneyCapitalAccountMapper;

@Service
public class CapitalAccountService implements ICapitalAccountService {

	Logger logger=LoggerFactory.getLogger(CapitalAccountService.class);
	@Autowired 
	private MoneyCapitalAccountMapper moneyAccountMapper;
	 
	@Override
	public Result saveOrUpdate(CapitalAccountReq req,TransactionType type) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		MoneyCapitalAccount mAccount = null;
		if(null != req.getUseryhno() && !"".equals(req.getUseryhno())){
			mAccount = moneyAccountMapper.selectByUseryhno(req.getUseryhno());
		}else if (null != req.getCellphone() && !"".equals(req.getCellphone())) {
			mAccount = moneyAccountMapper.selectByCellphone(req.getCellphone());
		}else {
			logger.error("用户登录账号和银行唯一标识不能为空");
			rs.setCode("01");
			rs.setError("用户登录账号和银行唯一标识不能为空");
			return rs;
		}
		if(null == mAccount){
			if(type.getType() == 1){
				logger.info("没有资金账户时，开一个资金账户");
				rs = saveCapitalAccount(req, type, rs);
			}else {
				logger.error("业务异常，未发现前置资金操作，无法进行此操作");
				rs.setCode("1006");
				rs.setError("业务异常，未发现前置资金操作，无法进行此操作");
			}
		}else {
			logger.info("修改资金账户");
			rs = updateCapitalAccount(req, type, rs, mAccount);
		}
		return rs;
	}
	/**
	 * 修改资金账户
	 * @param req
	 * @param type
	 * @param rs
	 * @param mAccount
	 */
	private Result updateCapitalAccount(CapitalAccountReq req, TransactionType type, Result rs,
			MoneyCapitalAccount mAccount) {
		if(type == TransactionType.PENDING ){//待收入运费增加
			if(req.getPendingmoney() > 0){
				mAccount.setPendingbill(mAccount.getPendingbill() + 1);//待收入运单 +1
				mAccount.setTotalbill(mAccount.getTotalbill() + 1);//总运输运单+1
				mAccount.setPendingmoney(mAccount.getPendingmoney() + req.getPendingmoney());//待收入运费增加
				moneyAccountMapper.updateByPrimaryKeySelective(mAccount);
			}else {
				logger.error("待收入运费金额必须大于0");
				rs.setCode("011");
				rs.setError("待收入运费金额必须大于0");
			}
		}else if (type == TransactionType.PAID) {//收入运费
			if(req.getPendingmoney() > 0 && req.getAvailablemoney() > 0){
				mAccount.setPendingbill(mAccount.getPendingbill() - 1);//待收入运单 -1
				mAccount.setPaidbill(mAccount.getPaidbill() + 1);//已到账运单+1
				mAccount.setPendingmoney(mAccount.getPendingmoney() - req.getPendingmoney());//未到账金额减少
				mAccount.setAvailablemoney(mAccount.getAvailablemoney() + req.getAvailablemoney());//账户可用余额增加
				mAccount.setPaidmoney(mAccount.getPaidmoney() + req.getAvailablemoney());//已到账金额增加
				mAccount.setTotalmoney(mAccount.getTotalmoney() + req.getAvailablemoney());//账户总金额增加
				moneyAccountMapper.updateByPrimaryKeySelective(mAccount);
			}else {
				logger.error("收入运费金额必须大于0");
				rs.setCode("011");
				rs.setError("收入运费金额必须大于0");
			}
		}else if (type == TransactionType.TXSQ) {//提现申请
			if( req.getAvailablemoney() > 0 && req.getLockmoney() > 0){
				mAccount.setAvailablemoney(mAccount.getAvailablemoney() - req.getAvailablemoney());//可用余额减少
				mAccount.setLockmoney(mAccount.getLockmoney() +req.getLockmoney() );//冻结金额增加
				moneyAccountMapper.updateByPrimaryKeySelective(mAccount);
			}else {
				logger.error("提现金额必须大于0");
				rs.setCode("011");
				rs.setError("提现金额必须大于0");
			}
		}else if (type == TransactionType.TXSUCESS) {//提现成功
			if( req.getAvailablemoney() > 0 && req.getLockmoney() > 0){
				mAccount.setLockmoney(mAccount.getLockmoney() - req.getLockmoney() );//冻结金额减少
				mAccount.setTotalmoney(mAccount.getTotalmoney() - req.getAvailablemoney());//账户总金额减少
				moneyAccountMapper.updateByPrimaryKeySelective(mAccount);
			}else {
				logger.error("待收入运费金额必须大于0");
				rs.setCode("011");
				rs.setError("待收入运费金额必须大于0");
			}
		}else if (type == TransactionType.TXFAIL) {//提现失败
			if( req.getAvailablemoney() > 0 && req.getLockmoney() > 0){
				mAccount.setLockmoney(mAccount.getLockmoney() - req.getLockmoney() );//冻结金额减少
				mAccount.setAvailablemoney(mAccount.getAvailablemoney() + req.getAvailablemoney());//账户可用余额增加
				moneyAccountMapper.updateByPrimaryKeySelective(mAccount);
			}else {
				logger.error("待收入运费金额必须大于0");
				rs.setCode("011");
				rs.setError("待收入运费金额必须大于0");
			}
		}
		return rs;
	}
	/**
	 * 没有资金账户时，开一个资金账户
	 * @param req
	 * @param type
	 * @param rs
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private Result saveCapitalAccount(CapitalAccountReq req, TransactionType type, Result rs)  {
		MoneyCapitalAccount account = new MoneyCapitalAccount();
			try {
				PropertyUtils.copyProperties(account,req);
				if(req.getAvailablemoney()!=null&&req.getLockmoney()!=null){
					account.setTotalmoney(req.getAvailablemoney() + req.getLockmoney());
				}else if(req.getAvailablemoney()!=null){
					account.setTotalmoney(req.getAvailablemoney());
				}else {
					account.setTotalmoney(req.getLockmoney());
				}
				if(type.getType() == 1){//待收入运费增加
					account.setPendingbill(1);
					account.setTotalbill(1);
				}
				logger.info(account.toString());
				moneyAccountMapper.insertSelective(account);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				logger.info(e.getMessage());
				rs.setCode("2");
				rs.setError("数据转换失败");
			}
		return rs;
	}
	@Override
	public CapitalAccountResp getByCellphone(String cellphone) {
		MoneyCapitalAccount mAccount = moneyAccountMapper.selectByCellphone(cellphone);
		if(null != mAccount){
			CapitalAccountResp  resp = new CapitalAccountResp();
			try {
				PropertyUtils.copyProperties(resp,mAccount);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			return resp;
		}
		return null;
	}
	@Override
	public CapitalAccountResp getByUseryhno(String useryhno) {
		MoneyCapitalAccount mAccount = moneyAccountMapper.selectByUseryhno(useryhno);
		if(null != mAccount){
			CapitalAccountResp  resp = new CapitalAccountResp();
			try {
				PropertyUtils.copyProperties(resp,mAccount);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			return resp;
		}
		return null;
	}

}
