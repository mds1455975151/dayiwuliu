package com.tianrui.trwl.admin.web;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

import com.tianrui.api.req.count.AuditReportReq;
import com.tianrui.api.req.front.adminReport.StatReportReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.count.AuditReportResp;
import com.tianrui.api.resp.front.adminReport.StatReportOfBillResp;
import com.tianrui.api.resp.front.adminReport.StatReportOfPlanResp;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.AuditReportService;
import com.tianrui.trwl.admin.util.SessionManager;

/**
 * 
  * <p>Title:StatusStatisticsAction </p>
  * <p>Description:类型状态统计表 </p>
  * <p>Company: </p> 
  * @author   yangbobo
  * @date   2017年7月21日 下午4:35:54
 */

@Controller
@RequestMapping("statusStatistics")
public class StatusStatisticsAction {
	public static Logger logger = LoggerFactory.getLogger(StatusStatisticsAction.class);
	@Autowired
	AuditReportService auditReportService;
	/**
	 * 
	 * @Title: index 
	 * @Description: 进入审核统计展示页面
	 * @param @return   
	 * @return ModelAndView    
	 * @throws
	 */
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminMember/statistics");
		return view;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(AuditReportReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PageResp<AuditReportResp> list = auditReportService.find(req);
		rs.setData(list);
		return rs;
	}
	@RequestMapping("getAuditReportCount")
	@ResponseBody
	public Result getAuditReportCount(AuditReportReq req){
		Result result = Result.getSuccessResult();
		try {
			result.setData(auditReportService.queryAuditReportCount(req));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setCode("-1");
			result.setData(e.getMessage());
			return result;
		}
		return result;
	}
	
	@RequestMapping("/auditReportExport")
	public void auditReportExport(AuditReportReq req, HttpServletRequest request, HttpServletResponse response){
		List<AuditReportResp> list = null;
		HSSFWorkbook workbook = null;
		OutputStream out = null;
		try {
		
			list = auditReportService.queryAuditReport(req);
			if(list == null || list.size() == 0){
				return;
			}
			String[] heads = {"序号","审核时间","用户审核失败数量","用户审核成功数量","司机审核失败数量","司机审核成功数量","车辆审核失败数量","车辆审核成功数量","银行卡审核失败数量","银行卡审核成功数量","大易/交通部运单未推送数量","大易/交通部运单已推送数量","安联/交通部运单未推送数量","安联/交通部运单已推送数量","创建时间"};
			workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("审核报表");
			HSSFRow headRow = sheet.createRow(0);
			for(int i=0;i<heads.length;i++){
				HSSFCell cell = headRow.createCell(i);
				cell.setCellValue(heads[i]);
			}
			for(int i=0;i<list.size();i++){
				HSSFRow row = sheet.createRow(i+1);
				AuditReportResp obj = list.get(i);
				setBillCellValue(i, obj, row);
			}
//			HSSFRow row = sheet.createRow(list.size()+1);
//			for(int i=0;i<heads.length;i++){
//				HSSFCell cell = row.createCell(i);
//			}
			out = response.getOutputStream();
			response.reset();     
			response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("大易物流审核数据报表.xls", "UTF-8"));  
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
	
	
	private void setBillCellValue(int index, AuditReportResp obj, HSSFRow row) {
		row.createCell(0).setCellValue(index+1);
		row.createCell(1).setCellValue(obj.getReviewTimes());
		row.createCell(2).setCellValue(obj.getUserByNum());
		row.createCell(3).setCellValue(obj.getUserFailNum());
		row.createCell(4).setCellValue(obj.getDriverByNum());
		row.createCell(5).setCellValue(obj.getDriverFailNum());
		row.createCell(6).setCellValue(obj.getVehicleByNum());
		row.createCell(7).setCellValue(obj.getVehicleFailNum());
		row.createCell(8).setCellValue(obj.getBankcardByNum());
		row.createCell(9).setCellValue(obj.getBankcardFailNum());
		row.createCell(10).setCellValue(obj.getWaybillByPushDJ());
		row.createCell(10).setCellValue(obj.getWaybillFailPushDJ());
		row.createCell(10).setCellValue(obj.getWaybillByPushAJ());
		row.createCell(10).setCellValue(obj.getWaybillFailPushAJ());
		row.createCell(10).setCellValue(obj.getCreatertimes());
//		if(StringUtils.isNotBlank(obj.getWeight())){
//			row.createCell(11).setCellValue(new BigDecimal(obj.getWeight()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//		}
//		if(StringUtils.isNotBlank(obj.getTrueweight())){
//			row.createCell(12).setCellValue(new BigDecimal(obj.getTrueweight()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//		}
//		if(StringUtils.isNotBlank(obj.getPrice())){
//			row.createCell(13).setCellValue(new BigDecimal(obj.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//		}
//		if(StringUtils.isNotBlank(obj.getPrice()) && StringUtils.isNotBlank(obj.getTrueweight())){
//			row.createCell(14).setCellValue(new BigDecimal(Double.valueOf(obj.getPrice())*Double.valueOf(obj.getTrueweight())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
//		}
//		String status = obj.getStatus();
//		switch (status) {
//		case "0":
//			status = "新建";
//			break;
//		case "2":
//			status = "已提货";
//			break;
//		case "5":
//			status = "已卸货";
//			break;
//		case "6":
//			status = "已完成";
//			break;
//		default:
//			status = "";
//			break;
//		}
//		row.createCell(15).setCellValue(status);
	}
		
	
}
