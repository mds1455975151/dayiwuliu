package com.tianrui.trwl.admin.web.report;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IAnlianBillReportService;
import com.tianrui.api.req.front.adminReport.AnlianBillReportReq;
import com.tianrui.api.resp.admin.AnlianBillReportResp;
import com.tianrui.api.resp.front.adminReport.StatReportOfBillResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.trwl.admin.util.BillReportExcilUtil;

@Controller
@RequestMapping("report")
public class ReportAction {

	@Autowired
	IAnlianBillReportService anlianBillReportService;
	
	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/statReport/new_billStat");
		return view;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(AnlianBillReportReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<AnlianBillReportResp> page = anlianBillReportService.select(req);
		rs.setData(page);
		return rs;
	}
	@RequestMapping("reload")
	public ModelAndView reload(AnlianBillReportReq req) throws Exception{
		
		PaginationVO<AnlianBillReportResp> page = anlianBillReportService.select(req);
		Map map = new HashMap();
    	map.put("list", page.getList());
    	BillReportExcilUtil excilUtil = new BillReportExcilUtil(); 
	   return new ModelAndView(excilUtil, map); 
	}
}
