package com.tianrui.web.action.bankCard;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.bankcard.IMemberBankCardService;
import com.tianrui.api.req.bankcard.MemberBankCardReq;
import com.tianrui.api.resp.bankcard.MemberBankCardResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/bank/card")
public class BankCardAction {

	@Autowired
	IMemberBankCardService memberBankCardService;
	
	/** 我的银行卡
	 * @throws Exception */
	@RequestMapping("page")
	public ModelAndView page(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		
		MemberVo vo = SessionManager.getSessionMember(request);
		MemberBankCardReq req = new MemberBankCardReq();
		req.setCreater(vo.getId());
		PaginationVO<MemberBankCardResp> page = memberBankCardService.selectBankCard(req);
		view.addObject("bank", page.getList());
		view.setViewName("/bank/findBankCard");
		return view;
	}
	
	/** 添加页面
	 * @throws Exception */
	@RequestMapping("savePage")
	public ModelAndView savePage() throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("/bank/saveBankCard");
		return view;
	}
	
	/** 查询*/
	@RequestMapping("find")
	@ResponseBody
	public Result find(MemberBankCardReq req,HttpServletRequest request) throws Exception{
		Result rs = Result .getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreater(vo.getId());
		PaginationVO<MemberBankCardResp> page = memberBankCardService.selectBankCard(req);
		rs.setData(page);
		return rs;
	}
	
	/** 添加
	 * @throws Exception */
	@RequestMapping("save")
	@ResponseBody
	public Result save(MemberBankCardReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreater(vo.getId());
		rs = memberBankCardService.insertBankCard(req);
		return rs;
	}
	
	/** 设置默认银行卡*/
	@RequestMapping("upt")
	@ResponseBody
	public Result upt(MemberBankCardReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreater(vo.getId());
		rs = memberBankCardService.defaulBankCard(req);
		return rs;
	}
	/**删除*/
	@RequestMapping("delt")
	@ResponseBody
	public Result delt(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = memberBankCardService.deltBankCard(id);
		return rs;
	}
	
}
