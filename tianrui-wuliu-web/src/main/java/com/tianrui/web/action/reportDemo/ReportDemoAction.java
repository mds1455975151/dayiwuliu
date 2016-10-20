package com.tianrui.web.action.reportDemo;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.ReportVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.BillService;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("trwuliu/reportdemo")
public class ReportDemoAction {
	
	private Logger log = LoggerFactory.getLogger(ReportDemoAction.class);
	
	@Autowired
	private BillService billService;
	
	@RequestMapping("main")
	@AuthValidation(autyType=Constant.AUTHCHECK_OWNER)
	public ModelAndView main(){
		ModelAndView model = new ModelAndView("reportDemo/main");
		return model;
	}
	
	@RequestMapping("report")
	@AuthValidation(autyType=Constant.AUTHCHECK_OWNER)
	public ModelAndView report(ReportVo vo, String item, String groups, String statistical, Boolean summation, Boolean subtotal, HttpServletRequest request){
		ModelAndView model = new ModelAndView("reportDemo/report");
		try {
			MemberVo memberVo = SessionManager.getSessionMember(request);
			vo.setMemberid(memberVo.getId());
			List<WaybillResp> list = billService.queryReportBill(vo);
			model.addObject("list", JSON.toJSON(list));
			model.addObject("item", item);
			model.addObject("groups", URLDecoder.decode(groups, "UTF-8"));
			model.addObject("statistical", URLDecoder.decode(statistical, "UTF-8"));
			model.addObject("summation", summation);
			model.addObject("subtotal", subtotal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping("getReport")
	@ResponseBody
	public Result getReport(ReportVo vo){
		Result result = Result.getErrorResult();
		try {
			List<WaybillResp> list = billService.queryReportBill(vo);
			if(list != null){
				result.setData(list);
			}else{
				result.setCode("000001");
				result.setData("暂无数据");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
}
