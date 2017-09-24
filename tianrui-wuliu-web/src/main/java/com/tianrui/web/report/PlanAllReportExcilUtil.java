package com.tianrui.web.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.tianrui.api.resp.report.ReportPlanAllResp;
import com.tianrui.common.utils.UUIDUtil;

public class PlanAllReportExcilUtil extends AbstractExcelView {

	private static String[] titles = new String[] {"计划日期", "计划单号","计划开始时间","计划结束时间","计划总量",
			"计划已完成量","完成进度","计划状态","货物名称","路线","发货方","发货人","车主","收货方","签收人","运距","单价","税率",
			"支付对象"};
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// excel 的 sheet（一页）和 cell（格子）
		HSSFSheet sheet;
		HSSFCell cell;
		// 声明时间 并格式化作为 文件名及 响应后的文档类型
		String filename = UUIDUtil.getId();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+ filename + ".xls");
		// 命名页名
		sheet = workbook.createSheet("计划报表");

		// Excel 样式
		HSSFCellStyle headerStyle = workbook.createCellStyle(); // 标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont(); // 标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short) 11);
		headerStyle.setFont(headerFont);

		short width = 20, height = 25 * 20;
		sheet.setDefaultColumnWidth(width);
		for (int i = 0; i < titles.length; i++) { // 设置标题
			String title = titles[i];
			cell = getCell(sheet, 0, i);
			cell.setCellStyle(headerStyle);
			setText(cell, title);
		}
		sheet.getRow(0).setHeight(height);

		HSSFCellStyle contentStyle = workbook.createCellStyle(); // 内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		List<ReportPlanAllResp> list =  (List<ReportPlanAllResp>) model.get("list");
		int userCount = list.size();
		for (int i = 0; i < userCount; i++) {
			ReportPlanAllResp data = list.get(i);
			Integer cellNum = i + 1;
			//计划日期
			String no = data.getPlanCreateTimeStr();
			cell = getCell(sheet, cellNum, 0);
			cell.setCellStyle(contentStyle);
			setText(cell, no);
			//计划单号
			String type = data.getPlanCode();
			cell = getCell(sheet, cellNum, 1);
			cell.setCellStyle(contentStyle);
			setText(cell, type);
			//计划开始时间
			String planNo = data.getPlanBeginTimeStr();
			cell = getCell(sheet, cellNum, 2);
			cell.setCellStyle(contentStyle);
			setText(cell, planNo);
			//计划结束时间
			String billNo = data.getPlanEndTimeStr();
			cell = getCell(sheet, cellNum, 3);
			cell.setCellStyle(contentStyle);
			setText(cell, billNo);
			//计划总量
			String sendMan = data.getPlanWeight();
			cell = getCell(sheet, cellNum, 4);
			cell.setCellStyle(contentStyle);
			setText(cell, sendMan);
			//计划已完成量
			String sendPersion = data.getComplitWeight();
			cell = getCell(sheet, cellNum, 5);
			cell.setCellStyle(contentStyle);
			setText(cell, sendPersion);
			//完成进度
			String receiptMan = data.getTempo();
			cell = getCell(sheet, cellNum, 6);
			cell.setCellStyle(contentStyle);
			setText(cell, receiptMan);
			//计划状态
			String receiptPersion = data.getPlanStatus();
			if(StringUtils.equals("0", data.getPlanStatus())){
				receiptPersion = "新建";
			}else if(StringUtils.equals("1", data.getPlanStatus())){
				receiptPersion = "待接";
			}else if(StringUtils.equals("2", data.getPlanStatus())){
				receiptPersion = "执行中";
			}else if(StringUtils.equals("3", data.getPlanStatus())){
				receiptPersion = "已完成";
			}else if(StringUtils.equals("-1", data.getPlanStatus())){
				receiptPersion = "已删除";
			}
			cell = getCell(sheet, cellNum, 7);
			cell.setCellStyle(contentStyle);
			setText(cell, receiptPersion);
			//货物名称
			String vehicleno = data.getCargoName();
			cell = getCell(sheet, cellNum, 8);
			cell.setCellStyle(contentStyle);
			setText(cell, vehicleno);
			//路线
			String cargoName = data.getRouteName();
			cell = getCell(sheet, cellNum, 9);
			cell.setCellStyle(contentStyle);
			setText(cell, cargoName);
			//发货方
			String routeName = data.getSendMan();
			cell = getCell(sheet, cellNum, 10);
			cell.setCellStyle(contentStyle);
			setText(cell, routeName);
			//发货人
			String distinct = data.getSendPersion();
			cell = getCell(sheet, cellNum, 11);
			cell.setCellStyle(contentStyle);
			setText(cell, distinct);
			//车主
			String venderWeight = data.getVenderName();
			cell = getCell(sheet, cellNum, 12);
			cell.setCellStyle(contentStyle);
			setText(cell, venderWeight);
			//收货方
			String pickupWeight = data.getReceiptMan();
			cell = getCell(sheet, cellNum, 13);
			cell.setCellStyle(contentStyle);
			setText(cell, pickupWeight);
			//签收人
			String unloadWeight = data.getReceiptPersion();
			cell = getCell(sheet, cellNum, 14);
			cell.setCellStyle(contentStyle);
			setText(cell, unloadWeight);
			//运距
			String trueWeight = data.getDistant();
			cell = getCell(sheet, cellNum, 15);
			cell.setCellStyle(contentStyle);
			setText(cell, trueWeight);
			
			//单价
			String billStatus = data.getPrice();
			cell = getCell(sheet, cellNum, 16);
			cell.setCellStyle(contentStyle);
			setText(cell, billStatus);
			
			//税率
			String driverName = data.getTax();
			cell = getCell(sheet, cellNum, 17);
			cell.setCellStyle(contentStyle);
			setText(cell, driverName);
			
			//支付对象
			String payMent = data.getPayMent();
			if(StringUtils.equals("1", data.getPayMent())){
				payMent = "司机";
			}else if(StringUtils.equals("2", data.getPayMent())){
				payMent = "车主";
			}
			cell = getCell(sheet, cellNum, 18);
			cell.setCellStyle(contentStyle);
			setText(cell, payMent);
			
		}
	}

}
