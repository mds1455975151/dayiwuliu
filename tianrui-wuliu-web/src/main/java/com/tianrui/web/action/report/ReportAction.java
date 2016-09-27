package com.tianrui.web.action.report;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.common.vo.ReportVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.BillService;

@Controller
@RequestMapping("trwuliu/billreport")
public class ReportAction {
	
	private Logger log = LoggerFactory.getLogger(ReportAction.class);
	
	@Autowired
	private BillService billService;
	
	@RequestMapping("main")
	public ModelAndView report(){
		ModelAndView model = new ModelAndView("report/owner/main");
		return model;
	}
	
	@RequestMapping("getReport")
	@ResponseBody
	public Result getReport(ReportVo vo){
		Result result = Result.getErrorResult();
		try {
			List<WaybillResp> list = billService.queryTJBillByParams(vo);
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
