package com.tianrui.web.app.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.req.front.report.ReportReq;
import com.tianrui.api.resp.front.report.ReportResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.impl.BillReportService;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
 * @author zhanggaohao
 * @createtime 2016年10月19日 下午3:03:19
 * @classname AppDriverReportAction.java
 */
@Controller
@RequestMapping("/app/driverreport")
public class AppDriverReportAction {
	
	public Logger logger = LoggerFactory.getLogger(AppDriverReportAction.class);
	
	@Autowired
	private BillReportService billReportService;
	
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ApiParamRawType(ReportReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult page(AppParam<ReportReq> appParam){
		AppResult appResult = new AppResult();
		try {
			//获取当前用户
			String uId =appParam.getHead().getId();
			//拼装查询条件
			ReportReq query =appParam.getBody();
			query.setDriverid(uId);
			PaginationVO<ReportResp> page = billReportService.queryReport(query);
			appResult.setCode("000000");
			appResult.setTotal(page.getTotalInt());
			appResult.setReturnData(page.getList());
		} catch (Exception e) {
			appResult.setCode("000001");
			appResult.setMessage("系统异常!");
			logger.error(e.getMessage(), e);
		}
		return appResult;
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ApiParamRawType(ReportReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult detail(AppParam<ReportReq> appParam){
		AppResult appResult = new AppResult();
		try {
			//拼装查询条件
			ReportReq query =appParam.getBody();
			ReportResp resp = billReportService.querReportDetail(query, false);
			appResult.setCode("000000");
			appResult.setReturnData(resp);
		} catch (Exception e) {
			appResult.setCode("000001");
			appResult.setMessage("系统异常!");
			logger.error(e.getMessage(), e);
		}
		return appResult;
	}
	
}
