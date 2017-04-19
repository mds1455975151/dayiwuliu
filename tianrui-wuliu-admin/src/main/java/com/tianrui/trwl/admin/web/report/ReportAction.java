package com.tianrui.trwl.admin.web.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.resp.front.adminReport.StatReportOfBillResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("report")
public class ReportAction {

	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/statReport/new_billStat");
		return view;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(){
		Result rs = Result.getSuccessResult();
		PaginationVO<StatReportOfBillResp> page = new PaginationVO<StatReportOfBillResp>();
		page.setList(null);
		page.setTotal(10);
		page.setPageNo(1);
		rs.setData(page);
		return rs;
	}
}
