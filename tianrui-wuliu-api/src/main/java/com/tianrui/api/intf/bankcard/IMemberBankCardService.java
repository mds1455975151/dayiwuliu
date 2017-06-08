package com.tianrui.api.intf.bankcard;

import com.tianrui.api.req.bankcard.MemberBankCardReq;
import com.tianrui.api.resp.bankcard.MemberBankCardResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/** 我的银行卡*/
public interface IMemberBankCardService {
	
	/** 添加银行卡*/
	public Result insertBankCard(MemberBankCardReq req)throws Exception;
	/** 审核银行卡*/
	public Result autidBankCard(MemberBankCardReq req)throws Exception;
	/** 设置默认银行卡*/
	public Result defaulBankCard(MemberBankCardReq req)throws Exception;
	/** 删除银行卡*/
	public Result deltBankCard(String id)throws Exception;
	/** 查询银行卡*/
	public PaginationVO<MemberBankCardResp> selectBankCard(MemberBankCardReq req)throws Exception;
	/** 查询银行卡详情*/
	public Result findBankCardById(String id)throws Exception;
}
