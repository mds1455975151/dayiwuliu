package com.tianrui.trwl.admin.web;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IOrganizationService;
import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.ICargoPlanService;
import com.tianrui.api.req.front.adminReport.StatReportReq;
import com.tianrui.api.resp.front.adminReport.StatReportOfBillResp;
import com.tianrui.api.resp.front.adminReport.StatReportOfPlanResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("/statReport")
public class StatReportAction {
	public static Logger logger = LoggerFactory.getLogger(StatReportAction.class);
	
	@Autowired
	private ICargoPlanService planService;
	@Autowired
	private IBillService billService;
	@Autowired
	private IOrganizationService orgService;
	
	@RequestMapping("/planStat")
	public ModelAndView planStat(HttpServletRequest request){
		ModelAndView view = new ModelAndView("/file/statReport/planStat");
		try {
			view.addObject("orgCode", orgService.findOne(SessionManager.getSessionMember(request).getOrgid()).getOrganizationno());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("getPlanStatReport")
	@ResponseBody
	public Result getPlanStatReport(StatReportReq req, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		PaginationVO<StatReportOfPlanResp> page = null;
		try {
			String orgid = SessionManager.getSessionMember(request).getOrgid();
			if(StringUtils.isNotBlank(orgid)){
				String orgCode = orgService.findOne(orgid).getOrganizationno();
				if(!StringUtils.equals(orgCode, "0000")){
					req.setOrgid(orgid);
				}
			}
			page = planService.queryAdminStatReport(req);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setCode("-1");
			result.setData(e.getMessage());
			return result;
		}
		return result;
	}
	
	@RequestMapping("getPlanStatReportCount")
	@ResponseBody
	public Result getPlanStatReportCount(StatReportReq req, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			String orgid = SessionManager.getSessionMember(request).getOrgid();
			if(StringUtils.isNotBlank(orgid)){
				String orgCode = orgService.findOne(orgid).getOrganizationno();
				if(!StringUtils.equals(orgCode, "0000")){
					req.setOrgid(orgid);
				}
			}
			result.setData(planService.queryAdminStatReportCount(req));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setCode("-1");
			result.setData(e.getMessage());
			return result;
		}
		return result;
	}
	
	@RequestMapping("/planStatExport")
	public void planStatExport(StatReportReq req, HttpServletRequest request, HttpServletResponse response){
		List<StatReportOfPlanResp> list = null;
		HSSFWorkbook workbook = null;
		OutputStream out = null;
		try {
			String orgid = SessionManager.getSessionMember(request).getOrgid();
			if(StringUtils.isNotBlank(orgid)){
				String orgCode = orgService.findOne(orgid).getOrganizationno();
				if(!StringUtils.equals(orgCode, "0000")){
					req.setOrgid(orgid);
				}
			}
			list = planService.queryAdminAllStatReport(req);
			if(list == null || list.size() == 0){
				return;
			}
			String[] heads = {"序号","计划单号","货主名称","车主名称","运输货物","计划开始时间","计划结束时间","运输路线","计划总量","实际执行量","含税单价","含税金额","计划执行情况"};
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("计划报表");
			HSSFRow headRow = sheet.createRow(0);
			for(int i=0;i<heads.length;i++){
				HSSFCell cell = headRow.createCell(i);
				cell.setCellValue(heads[i]);
			}
			double totalSum = 0.0;
			double completedSum = 0.0;
			double completePriceSum = 0.0;
			for(int i=0;i<list.size();i++){
				HSSFRow row = sheet.createRow(i+1);
				StatReportOfPlanResp obj = list.get(i);
				if(StringUtils.isNotBlank(obj.getTotalplanned())){
					totalSum += Double.valueOf(obj.getTotalplanned());
				}
				if(StringUtils.isNotBlank(obj.getCompleted())){
					completedSum += Double.valueOf(obj.getCompleted());
				}
				if(StringUtils.isNotBlank(obj.getPrice()) && StringUtils.isNotBlank(obj.getCompleted())){
					completePriceSum += Double.valueOf(obj.getPrice())*Double.valueOf(obj.getCompleted());
				}
				setPlanCellValue(i, obj, row);
			}
			HSSFRow row = sheet.createRow(list.size()+1);
			for(int i=0;i<heads.length;i++){
				HSSFCell cell = row.createCell(i);
				if(i == 0){
					cell.setCellValue("总计");
				}
				if(StringUtils.equals(heads[i], "计划总量")){
					cell.setCellValue(new BigDecimal(totalSum).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				if(StringUtils.equals(heads[i], "实际执行量")){
					cell.setCellValue(new BigDecimal(completedSum).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				if(StringUtils.equals(heads[i], "含税金额")){
					cell.setCellValue(new BigDecimal(completePriceSum).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			out = response.getOutputStream();
			response.reset();     
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("货运计划报表.xls", "UTF-8"));  
			response.setContentType("application/msexcel; charset=utf-8");
			workbook.write(out);
			
			response.flushBuffer();
			out.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if(workbook != null){
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private void setPlanCellValue(int index, StatReportOfPlanResp obj, HSSFRow row) {
		row.createCell(0).setCellValue(index+1);
		row.createCell(1).setCellValue(obj.getPlanCode());
		row.createCell(2).setCellValue(obj.getOwnerName());
		row.createCell(3).setCellValue(obj.getVenderName());
		row.createCell(4).setCellValue(obj.getCargoName());
		row.createCell(5).setCellValue(obj.getStarttimeStr());
		row.createCell(6).setCellValue(obj.getEndtimeStr());
		row.createCell(7).setCellValue(obj.getRouteName());
		if(StringUtils.isNotBlank(obj.getTotalplanned())){
			row.createCell(8).setCellValue(new BigDecimal(obj.getTotalplanned()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		if(StringUtils.isNotBlank(obj.getCompleted())){
			row.createCell(9).setCellValue(new BigDecimal(obj.getCompleted()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		if(StringUtils.isNotBlank(obj.getPrice())){
			row.createCell(10).setCellValue(new BigDecimal(obj.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		if(StringUtils.isNotBlank(obj.getPrice()) && StringUtils.isNotBlank(obj.getCompleted())){
			row.createCell(11).setCellValue(new BigDecimal(Double.valueOf(obj.getPrice())*Double.valueOf(obj.getCompleted())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		String status = obj.getStatus();
		switch (status) {
		case "0":
			status = "新建";
			break;
		case "2":
			status = "执行中";
			break;
		case "3":
			status = "已完成";
			break;
		default:
			status = "";
			break;
		}
		row.createCell(12).setCellValue(status);
	}
	
	@RequestMapping("/billStat")
	public ModelAndView billStat(HttpServletRequest request){
		ModelAndView view = new ModelAndView("/file/statReport/billStat");
		try {
			view.addObject("orgCode", orgService.findOne(SessionManager.getSessionMember(request).getOrgid()).getOrganizationno());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("getBillStatReport")
	@ResponseBody
	public Result getBillStatReport(StatReportReq req, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		PaginationVO<StatReportOfBillResp> page = null;
		try {
			String orgid = SessionManager.getSessionMember(request).getOrgid();
			if(StringUtils.isNotBlank(orgid)){
				String orgCode = orgService.findOne(orgid).getOrganizationno();
				if(!StringUtils.equals(orgCode, "0000")){
					req.setOrgid(orgid);
				}
			}
			page = billService.queryAdminStatReport(req);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setCode("-1");
			result.setData(e.getMessage());
			return result;
		}
		return result;
	}
	

	@RequestMapping("getBillStatReportCount")
	@ResponseBody
	public Result getBillStatReportCount(StatReportReq req, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			String orgid = SessionManager.getSessionMember(request).getOrgid();
			if(StringUtils.isNotBlank(orgid)){
				String orgCode = orgService.findOne(orgid).getOrganizationno();
				if(!StringUtils.equals(orgCode, "0000")){
					req.setOrgid(orgid);
				}
			}
			result.setData(billService.queryAdminStatReportCount(req));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setCode("-1");
			result.setData(e.getMessage());
			return result;
		}
		return result;
	}
	
	@RequestMapping("/billStatExport")
	public void billStatExport(StatReportReq req, HttpServletRequest request, HttpServletResponse response){
		List<StatReportOfBillResp> list = null;
		HSSFWorkbook workbook = null;
		OutputStream out = null;
		try {
			String orgid = SessionManager.getSessionMember(request).getOrgid();
			if(StringUtils.isNotBlank(orgid)){
				String orgCode = orgService.findOne(orgid).getOrganizationno();
				if(!StringUtils.equals(orgCode, "0000")){
					req.setOrgid(orgid);
				}
			}
			list = billService.queryAdminAllStatReport(req);
			if(list == null || list.size() == 0){
				return;
			}
			String[] heads = {"序号","计划单号","运单号","货主名称","车主名称","车牌号","司机姓名","运输货物","运单开始时间","运单结束时间","运输路线","预提数量","实际执行量","含税单价","含税金额","运单执行情况"};
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("运单报表");
			HSSFRow headRow = sheet.createRow(0);
			for(int i=0;i<heads.length;i++){
				HSSFCell cell = headRow.createCell(i);
				cell.setCellValue(heads[i]);
			}
			double YTSum = 0.0;
			double SJSum = 0.0;
			double ZJESum = 0.0;
			for(int i=0;i<list.size();i++){
				HSSFRow row = sheet.createRow(i+1);
				StatReportOfBillResp obj = list.get(i);
				if(StringUtils.isNotBlank(obj.getWeight())){
					YTSum += Double.valueOf(obj.getWeight());
				}
				if(StringUtils.isNotBlank(obj.getTrueweight())){
					SJSum += Double.valueOf(obj.getTrueweight());
				}
				if(StringUtils.isNotBlank(obj.getPrice()) && StringUtils.isNotBlank(obj.getTrueweight())){
					ZJESum += Double.valueOf(obj.getPrice())*Double.valueOf(obj.getTrueweight());
				}
				setBillCellValue(i, obj, row);
			}
			HSSFRow row = sheet.createRow(list.size()+1);
			for(int i=0;i<heads.length;i++){
				HSSFCell cell = row.createCell(i);
				if(i == 0){
					cell.setCellValue("总计");
				}
				if(StringUtils.equals(heads[i], "预提数量")){
					cell.setCellValue(new BigDecimal(YTSum).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				if(StringUtils.equals(heads[i], "实际执行量")){
					cell.setCellValue(new BigDecimal(SJSum).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				if(StringUtils.equals(heads[i], "含税金额")){
					cell.setCellValue(new BigDecimal(ZJESum).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			out = response.getOutputStream();
			response.reset();     
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("货运运单报表.xls", "UTF-8"));  
			response.setContentType("application/msexcel; charset=utf-8");
			workbook.write(out);
			
			response.flushBuffer();
			out.flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if(workbook != null){
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	private void setBillCellValue(int index, StatReportOfBillResp obj, HSSFRow row) {
		row.createCell(0).setCellValue(index+1);
		row.createCell(1).setCellValue(obj.getPlanCode());
		row.createCell(2).setCellValue(obj.getBillCode());
		row.createCell(3).setCellValue(obj.getOwnerName());
		row.createCell(4).setCellValue(obj.getVenderName());
		row.createCell(5).setCellValue(obj.getVehicleno());
		row.createCell(6).setCellValue(obj.getDriverName());
		row.createCell(7).setCellValue(obj.getCargoName());
		row.createCell(8).setCellValue(obj.getStarttimeStr());
		row.createCell(9).setCellValue(obj.getEndtimeStr());
		row.createCell(10).setCellValue(obj.getRouteName());
		if(StringUtils.isNotBlank(obj.getWeight())){
			row.createCell(11).setCellValue(new BigDecimal(obj.getWeight()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		if(StringUtils.isNotBlank(obj.getTrueweight())){
			row.createCell(12).setCellValue(new BigDecimal(obj.getTrueweight()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		if(StringUtils.isNotBlank(obj.getPrice())){
			row.createCell(13).setCellValue(new BigDecimal(obj.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		if(StringUtils.isNotBlank(obj.getPrice()) && StringUtils.isNotBlank(obj.getTrueweight())){
			row.createCell(14).setCellValue(new BigDecimal(Double.valueOf(obj.getPrice())*Double.valueOf(obj.getTrueweight())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
		String status = obj.getStatus();
		switch (status) {
		case "0":
			status = "新建";
			break;
		case "2":
			status = "已提货";
			break;
		case "5":
			status = "已卸货";
			break;
		case "6":
			status = "已完成";
			break;
		default:
			status = "";
			break;
		}
		row.createCell(15).setCellValue(status);
	}
	
}
