package com.tianrui.api.money.intf;

import java.lang.reflect.InvocationTargetException;

import com.tianrui.api.req.money.CapitalAccountReq;
import com.tianrui.api.req.money.CheckPasswordReq;
import com.tianrui.api.req.money.SavePasswordReq;
import com.tianrui.api.resp.money.CapitalAccountResp;
import com.tianrui.common.enums.TransactionType;
import com.tianrui.common.vo.Result;

public interface ICapitalAccountService {
	/** 设置账户密码*/
	Result saveOrUptAcountPassord(SavePasswordReq req);
	/** 校验用户密码*/
	Result checkPassword(CheckPasswordReq req);
	/**
	 * 根据不同交易类型 修改或新增用户资金账户
	 * @param req
	 * @param type
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	Result saveOrUpdate(CapitalAccountReq req,TransactionType type) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException;
	
	/**
	 * 根据登录账号获取资金账户信息
	 * @param cellphone
	 * @return
	 */
	CapitalAccountResp getByCellphone(String cellphone);
	
	/**
	 * 根据银行编号（身份证）获取资金账户信息
	 * @param useryhno
	 * @return
	 */
	CapitalAccountResp getByUseryhno(String useryhno);
}
