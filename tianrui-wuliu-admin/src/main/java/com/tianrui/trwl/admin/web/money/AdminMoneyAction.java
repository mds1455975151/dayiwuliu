package com.tianrui.trwl.admin.web.money;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.money.intf.ICapitalAccountService;
import com.tianrui.api.money.intf.ICapitalRecordService;
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.money.intf.IWithdrawRecordService;
import com.tianrui.api.req.money.FindCapitalRecordByIdReq;
import com.tianrui.api.req.money.FindCapitalRecordReq;
import com.tianrui.api.req.money.FindPendingBillMoneyReq;
import com.tianrui.api.req.money.FindPendingMoneyByIdReq;
import com.tianrui.api.req.money.FindWithdrawByIdReq;
import com.tianrui.api.req.money.FindWithdrawRecordReq;
import com.tianrui.api.resp.money.CapitalAccountResp;
import com.tianrui.api.resp.money.FindCapitalRecordResp;
import com.tianrui.api.resp.money.FindPendingBillMoneyResp;
import com.tianrui.api.resp.money.FindWithdrawRecordResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("/admin/money")
public class AdminMoneyAction {

	@Autowired
	ICapitalAccountService capitalAccountService;
	@Autowired
	ICapitalRecordService capitalRecordService;
	@Autowired
	IPendingBillMoneyService pendingBillMoneyService;
	@Autowired
	IWithdrawRecordService withdrawRecordService;
	
	/**
	 * 查询用户资金账户
	 * */
	@RequestMapping(value="/getCapitalAccount",method=RequestMethod.POST)
	@ResponseBody
	public Result getCapitalAccount(String cellPhone) throws Exception{
		Result rs = Result.getSuccessResult();
		CapitalAccountResp resp = capitalAccountService.getByCellphone(cellPhone);
		if(resp == null){
			rs.setCode("1");
			rs.setError("未找到资金账户");
		}else{
			rs.setData(resp);
		}
		return rs;
	}
	
	/**
	 * 用户提现记录
	 * */
	@RequestMapping(value="/withdrawRecordSelect",method=RequestMethod.POST)
	@ResponseBody
	public Result withdrawRecordSelect(FindWithdrawRecordReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<FindWithdrawRecordResp> page = withdrawRecordService.select(req);
		rs.setData(page);
		return rs;
	}
	
	/**
	 * 用户提现详情
	 * */
	@RequestMapping(value="/withdrawSelectByid",method=RequestMethod.POST)
	@ResponseBody
	public Result withdrawSelectByid(FindWithdrawByIdReq req) throws Exception{
		Result rs = withdrawRecordService.selectByWithdrawId(req);
		return rs;
	}
	

	/**
	 * 司机运费记录
	 * */
	@RequestMapping(value="/pendingBillMoneySelect",method=RequestMethod.POST)
	@ResponseBody
	public Result pendingBillMoneySelect(FindPendingBillMoneyReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<FindPendingBillMoneyResp> page = pendingBillMoneyService.select(req);
		rs.setData(page);
		return rs;
	}
	
	/**
	 * 司机运费详情
	 * */
	@RequestMapping(value="/pendingBillMoneyById",method=RequestMethod.POST)
	@ResponseBody
	public Result pendingBillMoneyById(FindPendingMoneyByIdReq req) throws Exception{
		Result rs = pendingBillMoneyService.selectPendingBillMoneyById(req);
		return rs;
	}
	
	/**
	 * 用户资金流水
	 * */
	@RequestMapping(value="/capitalRecordSelect",method=RequestMethod.POST)
	@ResponseBody
	public Result capitalRecordSelect(FindCapitalRecordReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<FindCapitalRecordResp> page = capitalRecordService.select(req);
		rs.setData(page);
		return rs;
	}
	
	/**
	 * 用户资金流水详情
	 * */
	@RequestMapping(value="/capitalRecordById",method=RequestMethod.POST)
	@ResponseBody
	public Result capitalRecordById(FindCapitalRecordByIdReq req) throws Exception{
		Result rs = capitalRecordService.selectCapitalRecordById(req);
		return rs;
	}
}
