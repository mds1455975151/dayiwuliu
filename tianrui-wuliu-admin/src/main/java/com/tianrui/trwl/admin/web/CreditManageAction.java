package com.tianrui.trwl.admin.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.req.front.CreditScore.CreditScoreReq;
import com.tianrui.api.req.front.creditManage.CreditManageReq;
import com.tianrui.api.resp.front.creditManage.CreditManageResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.CreditManageService;

@RequestMapping("/creditManage")
@Controller
public class CreditManageAction {

	private Logger log = LoggerFactory.getLogger(CreditManageAction.class);
	
	@Autowired
	private CreditManageService creditManageService;
	
	@RequestMapping("/venderCreditMain")
	public ModelAndView venderCreditMain(){
		ModelAndView view = new ModelAndView("file/creditManage/venderCredit");
		return view;
	}
	
	@RequestMapping("/queryCredit")
	@ResponseBody
	public Result queryCredit(CreditManageReq req){
		Result rs = Result.getSuccessResult();
		try {
			List<CreditManageResp> list = creditManageService.queryCredit(req);
			rs.setData(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setError("查询失败，请稍后重试！");
		}
		return rs;
	}
	
	@RequestMapping("updateCreditScore")
	@ResponseBody
	public Result updateCreditScore(CreditScoreReq req){
		Result rs = Result.getSuccessResult();
		try {
			int index = creditManageService.updateCreditScore(req);
			if(index == 1){
				rs.setCode("000000");
				rs.setData("保存成功！");
			}else{
				rs.setCode("-1");
				rs.setData("保存失败！");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setError("保存失败，请稍后重试！");
		}
		return rs;
	}
	
	@RequestMapping("venderCreditReportMain")
	public ModelAndView venderCreditReportMain(){
		ModelAndView view = new ModelAndView("file/creditManage/venderCreditReport");
		return view;
	}
	
	@RequestMapping("venderCreditGradeMain")
	public ModelAndView venderCreditGradeMain(){
		ModelAndView view = new ModelAndView("file/creditManage/venderCreditGrade");
		return view;
	}
	
}
