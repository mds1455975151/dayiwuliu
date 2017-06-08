package com.tianrui.web.app.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.bankcard.IMemberBankCardService;
import com.tianrui.api.req.bankcard.MemberBankCardReq;
import com.tianrui.api.resp.bankcard.MemberBankCardResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/bank/card")
public class AppMemberBankAction {

	@Autowired
	IMemberBankCardService memberBankCardService;
	
	/** 添加
	 * @throws Exception */
	@RequestMapping("save")
	@ApiParamRawType(MemberBankCardReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult save(AppParam<MemberBankCardReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		Head vo = appParam.getHead();
		MemberBankCardReq req = appParam.getBody();
		req.setCreater(vo.getId());
		rs = memberBankCardService.insertBankCard(req);
		return AppResult.valueOf(rs);
	}
	
	/** 查询*/
	@RequestMapping("find")
	@ApiParamRawType(MemberBankCardReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult find(AppParam<MemberBankCardReq> appParam) throws Exception{
		Result rs = Result .getSuccessResult();
		Head vo = appParam.getHead();
		MemberBankCardReq req = appParam.getBody();
		req.setCreater(vo.getId());
		PaginationVO<MemberBankCardResp> page = memberBankCardService.selectBankCard(req);
		rs.setData(page);
		return AppResult.valueOf(rs);
	}
	
	/** 设置默认银行卡*/
	@RequestMapping("upt")
	@ApiParamRawType(MemberBankCardReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult upt(AppParam<MemberBankCardReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberBankCardReq req = appParam.getBody();
		Head head = appParam.getHead();
		req.setCreater(head.getId());
		rs = memberBankCardService.defaulBankCard(req);
		return AppResult.valueOf(rs);
	}
}    
