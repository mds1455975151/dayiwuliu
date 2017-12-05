package com.tianrui.service.impl.money;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.money.intf.ICapitalAccountService;
import com.tianrui.api.req.money.CapitalAccountReq;
import com.tianrui.api.req.money.CheckPasswordReq;
import com.tianrui.api.req.money.SavePasswordReq;
import com.tianrui.api.resp.money.CapitalAccountResp;
import com.tianrui.common.constants.ErrorCode;
import com.tianrui.common.enums.MoenyPWCheckEnum;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.MoneyAccountPassword;
import com.tianrui.service.bean.MoneyCapitalAccount;
import com.tianrui.service.mapper.MoneyAccountPasswordMapper;
import com.tianrui.service.mapper.MoneyCapitalAccountMapper;

@Service
public class CapitalAccountService implements ICapitalAccountService {

	Logger logger=LoggerFactory.getLogger(CapitalAccountService.class);
	@Autowired 
	private MoneyCapitalAccountMapper moneyAccountMapper;
	@Autowired
	MoneyAccountPasswordMapper moneyAccountPasswordMapper;
	@Override
	public Result deleteAcountPassword(String id) {
		Result rs = Result.getSuccessResult();
		moneyAccountPasswordMapper.deleteByPrimaryKey(id);
		return rs;
	}
	
	@Override
	public Result saveOrUptAcountPassword(SavePasswordReq req) {
		Result rs = Result.getSuccessResult();
		MoneyAccountPassword pass = moneyAccountPasswordMapper.selectByPrimaryKey(req.getId());
		if(pass == null){
			//用户第一次设置密码
			savePassWord(req);
		}else {
			//修改密码
			if(MoenyPWCheckEnum.checkType1.getStatus().equals(req.getCheckType())){
				//密码支付-修改
				uptPassword(req, rs, pass);
			}else if(MoenyPWCheckEnum.checkType2.getStatus().equals(req.getCheckType())){
				//手势密码-修改
				uptGesPassword(req, rs, pass);
			}else{
				logger.error(ErrorCode.MONEY_CHECK_PW_TYPE+"未选择密码校验类型");
				rs.setErrorCode(ErrorCode.MONEY_CHECK_PW_TYPE);
			}
		}
		return rs;
	}
	private void uptGesPassword(SavePasswordReq req, Result rs, MoneyAccountPassword pass) {
		if(StringUtils.isNotBlank(req.getGesturepass())&&(StringUtils.isBlank(pass.getGesturepass())||StringUtils.equals(req.getOldGesturepass(), pass.getGesturepass()))){
			MoneyAccountPassword upt = new MoneyAccountPassword();
			upt.setId(pass.getId());
			upt.setGestureStatus(MoenyPWCheckEnum.gestureStatus1.getStatus());
			upt.setGesturepass(req.getGesturepass());
			moneyAccountPasswordMapper.updateByPrimaryKeySelective(upt);
		}else{
			logger.error(ErrorCode.MONEY_CHECK_PW_FALL+"密码校验失败");
			rs.setErrorCode(ErrorCode.MONEY_CHECK_PW_FALL);
		}
	}
	private void uptPassword(SavePasswordReq req, Result rs, MoneyAccountPassword pass) {
		if(StringUtils.isNotBlank(req.getPassword())&&(StringUtils.isBlank(pass.getPassword())||StringUtils.equals(req.getOldPassword(), pass.getPassword()))){
			//旧密码不为空(密码支付)且 (原支付密码为空 或 新旧密码相同)
			MoneyAccountPassword upt = new MoneyAccountPassword();
			upt.setId(pass.getId());
			upt.setPassword(req.getPassword());
			moneyAccountPasswordMapper.updateByPrimaryKeySelective(upt);
		}else{
			logger.error(ErrorCode.MONEY_CHECK_PW_FALL+"密码校验失败");
			rs.setErrorCode(ErrorCode.MONEY_CHECK_PW_FALL);
		}
	}
	private void savePassWord(SavePasswordReq req) {
		MoneyAccountPassword save = new MoneyAccountPassword();
		save.setId(req.getId());
		save.setCellphone(req.getCellphone());
		save.setCreatertime(System.currentTimeMillis());
		if(MoenyPWCheckEnum.checkType1.getStatus().equals(req.getCheckType())){
			//支付密码 -手势密码未开启
			save.setGestureStatus(MoenyPWCheckEnum.gestureStatus0.getStatus());
			save.setPassword(req.getPassword());
		}else if(MoenyPWCheckEnum.checkType2.getStatus().equals(req.getCheckType())){
			//手势密码- 手势密码开启
			save.setGestureStatus(MoenyPWCheckEnum.gestureStatus1.getStatus());
			save.setGesturepass(req.getGesturepass());
		}
		moneyAccountPasswordMapper.insertSelective(save);
	}
	@Override
	public Result checkPassword(CheckPasswordReq req) {
		Result rs = Result.getSuccessResult();
		MoneyAccountPassword pass = moneyAccountPasswordMapper.selectByPrimaryKey(req.getId());
		if(pass!=null){
			if("1".equals(req.getCheckType())){
				//密码支付
				if(!(StringUtils.isNotBlank(pass.getPassword())&&//支付密码不为空 
						StringUtils.equals(pass.getPassword(), req.getPassword())//支付密码和输入密码相同
						)){
					logger.error(ErrorCode.MONEY_CHECK_PW_TP1+"支付密码校验未通过");
					rs.setErrorCode(ErrorCode.MONEY_CHECK_PW_TP1);
				}
			}else if("2".equals(req.getCheckType())){
				//手势支付
				if(!(StringUtils.equals("1", pass.getGestureStatus())&& //开启手势支付
						StringUtils.isNotBlank(pass.getGesturepass())&& //手势支付密码不为空
							StringUtils.equals(pass.getGesturepass(), req.getGesturepass())//支付密码 和输入密码相同
							)){
					logger.error(ErrorCode.MONEY_CHECK_PW_TP2+"手势密码校验未通过或未开启手势密码");
					rs.setErrorCode(ErrorCode.MONEY_CHECK_PW_TP2);
				}
			}else{
				logger.error(ErrorCode.MONEY_CHECK_PW_TYPE+"未选择密码校验类型");
				rs.setErrorCode(ErrorCode.MONEY_CHECK_PW_TYPE);
			}
		}else{
			logger.error(ErrorCode.MONEY_CHECK_PW_NULL+"用户未设置支付密码");
			rs.setErrorCode(ErrorCode.MONEY_CHECK_PW_NULL);
		}
		return rs;
	}

	
	@Override
	public Result saveOrUpdate(CapitalAccountReq req,TransactionType type) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Result rs = Result.getSuccessResult();
		MoneyCapitalAccount mAccount = null;
		if(null != req.getUseryhno() && !"".equals(req.getUseryhno())){
			mAccount = moneyAccountMapper.selectByUseryhno(req.getUseryhno());
		}else if (null != req.getCellphone() && !"".equals(req.getCellphone())) {
			mAccount = moneyAccountMapper.selectByCellphone(req.getCellphone());
		}else {
			rs.setErrorCode(ErrorCode.CAPITAL_NOT_CELLPHONE_OR_USERYHNO);
			return rs;
		}
		if(null == mAccount){
			if(type == TransactionType.PENDING ){
				logger.info("没有资金账户时，开一个资金账户");
				rs = saveCapitalAccount(req, type, rs);
			}else {
				logger.error("业务异常，未发现前置资金操作，无法进行此操作");
				rs.setErrorCode(ErrorCode.CAPITAL_ACCOUNT_NULL);
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
				rs.setErrorCode(ErrorCode.CAPITAL_PENDINGMONEY_ZERO);
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
				rs.setErrorCode(ErrorCode.CAPITAL_PENDMONEY_ZERO);
			}
		}else if (type == TransactionType.TXSQ) {//提现申请
			if( req.getAvailablemoney() > 0 && req.getLockmoney() > 0){
				mAccount.setAvailablemoney(mAccount.getAvailablemoney() - req.getAvailablemoney());//可用余额减少
				mAccount.setLockmoney(mAccount.getLockmoney() +req.getLockmoney() );//冻结金额增加
				moneyAccountMapper.updateByPrimaryKeySelective(mAccount);
			}else {
				logger.error("提现金额必须大于0");
				rs.setErrorCode(ErrorCode.CAPITAL_WITHDROW_ZERO);
			}
		}else if (type == TransactionType.TXSUCESS) {//提现成功
			if( req.getAvailablemoney() > 0 && req.getLockmoney() > 0){
				mAccount.setLockmoney(mAccount.getLockmoney() - req.getLockmoney() );//冻结金额减少
				mAccount.setTotalmoney(mAccount.getTotalmoney() - req.getAvailablemoney());//账户总金额减少
				moneyAccountMapper.updateByPrimaryKeySelective(mAccount);
			}else {
				logger.error("提现金额必须大于0");
				rs.setErrorCode(ErrorCode.CAPITAL_WITHDROW_ZERO);
			}
		}else if (type == TransactionType.TXFAIL) {//提现失败
			if( req.getAvailablemoney() > 0 && req.getLockmoney() > 0){
				mAccount.setLockmoney(mAccount.getLockmoney() - req.getLockmoney() );//冻结金额减少
				mAccount.setAvailablemoney(mAccount.getAvailablemoney() + req.getAvailablemoney());//账户可用余额增加
				moneyAccountMapper.updateByPrimaryKeySelective(mAccount);
			}else {
				logger.error("提现金额必须大于0");
				rs.setErrorCode(ErrorCode.CAPITAL_WITHDROW_ZERO);
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
				rs.setErrorCode(ErrorCode.PARAM_ERROR);
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
