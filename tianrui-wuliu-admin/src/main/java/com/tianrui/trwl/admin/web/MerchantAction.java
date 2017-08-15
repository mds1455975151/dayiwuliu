package com.tianrui.trwl.admin.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IMerchantService;
import com.tianrui.api.req.admin.merchant.MerchantReq;
import com.tianrui.api.resp.admin.merchant.MerchantResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("merchant")
public class MerchantAction {
	
	@Autowired
	IMerchantService merchantService;
	
	@RequestMapping("upt")
	@ResponseBody
	public Result upt() throws Exception{
		Result rs = Result.getErrorResult();
		merchantService.uptErrorMassage();
		return rs;
	}

	
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/merchant/file_merchants");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(MerchantReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<MerchantResp> vo = merchantService.find(req);
		rs.setData(vo);
		return rs;
	}
	@RequestMapping("insert")
	@ResponseBody
	public Result insert(MerchantReq req,HttpServletRequest request) throws Exception{
		
		Users user = SessionManager.getSessionMember(request);
		
		req.setOrgid(user.getOrgid());
		req.setCreater(user.getAccount());
		req.setCreatetime(System.currentTimeMillis());
		req.setId(UUIDUtil.getId());
		Result rs = merchantService.insert(req);
		return rs;
	}
	@RequestMapping("update")
	@ResponseBody
	public Result update(MerchantReq req,HttpServletRequest request) throws Exception{
		
		Users user = SessionManager.getSessionMember(request);
		req.setModify(user.getAccount());
		req.setModifytime(System.currentTimeMillis());
		Result rs = merchantService.update(req);
		return rs;
	}
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(MerchantReq req,HttpServletRequest request) throws Exception{
		Result rs = merchantService.delete(req);
		return rs;
	}
	
}
