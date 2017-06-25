package com.tianrui.trwl.admin.web.bank;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.bankcard.IMemberBankCardService;
import com.tianrui.api.req.bankcard.MemberBankCardReq;
import com.tianrui.api.resp.bankcard.MemberBankCardResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

/** 后台银行卡管理*/
@Controller
@RequestMapping("/admin/bank/card")
public class MemberBankAction {

	@Autowired
	IMemberBankCardService memberBankCardService;
	
	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/bank/bank_card");
		return view;
	}
	@RequestMapping("find")
	@ResponseBody
	public Result find(MemberBankCardReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<MemberBankCardResp> page = memberBankCardService.selectBankCard(req);
		rs.setData(page);
		return rs;
	}
	/** 审核*/
	@RequestMapping("autid")
	@ResponseBody
	public Result autid(MemberBankCardReq req, HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setAuditor(user.getAccount());
		rs = memberBankCardService.autidBankCard(req);
		return rs;
	}
	/** 查询详情
	 * @throws Exception */
	@RequestMapping("findId")
	@ResponseBody
	public Result findId(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = memberBankCardService.findBankCardById(id);
		return rs;
	}
	
}
