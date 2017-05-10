package com.tianrui.trwl.admin.web.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IOrganizationService;
import com.tianrui.api.intf.IAnlianBillReportService;
import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.req.admin.OrganizationReq;
import com.tianrui.api.req.front.adminReport.AnlianBillReportReq;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.resp.admin.AnlianBillReportResp;
import com.tianrui.api.resp.admin.OrganizationResp;
import com.tianrui.api.resp.front.bill.BillPositionResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.BillService;
import com.tianrui.trwl.admin.util.BillReportExcilUtil;

@Controller
@RequestMapping("report")
public class ReportAction {

	@Autowired
	private BillService billService;
	@Autowired
	private IAnlianBillService anlianBillService;
	@Autowired
	IAnlianBillReportService anlianBillReportService;
	
	@Autowired
	public IOrganizationService organizationService;
	
	@RequestMapping("page")
	public ModelAndView page() throws Exception{
		ModelAndView view = new ModelAndView();
		OrganizationReq req = new OrganizationReq();
		List<OrganizationResp> list = organizationService.findCondition(req);
		view.addObject("orgin", list);
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
	@RequestMapping("upt")
	@ResponseBody
	public Result upt() throws Exception{
		Result rs = Result.getSuccessResult();
		anlianBillReportService.uptDistance();	
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
	@RequestMapping("map")
	public ModelAndView map(String id,String type){
		ModelAndView view = new ModelAndView();
		view.addObject("id", id);
		if(StringUtils.equals("w", type)){
			view.setViewName("/file/map/bill_map");
		}else{
			view.setViewName("/file/map/anlian_bill_map");
		}
		return view;
	}
	
	@RequestMapping("billPositiondata")
	@ResponseBody
	public Result billPositiondata(WaybillQueryReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			List<BillPositionResp> list=billService.getBillPosition(req.getId());
			rs.setData(list);
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
		}
		return rs;
	}
	@RequestMapping("billPositiondataAnlian")
	@ResponseBody
	public Result billPositiondataAnlian(AnlianBillFindReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			rs = anlianBillService.findPosition(req);
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
		}
		return rs;
	}
}
