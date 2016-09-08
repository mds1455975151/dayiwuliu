package com.tianrui.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcilUtil extends AbstractExcelView {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static String[] titles = new String[] { "货源编号", "运单号","货物类型"};
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// excel 的 sheet（一页）和 cell（格子）
		HSSFSheet sheet;
		HSSFCell cell;
		// 声明时间 并格式化作为 文件名及 响应后的文档类型
		Date date = new Date();
		String filename = "qwead";
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+ filename + ".xls");
		// 命名页名
		sheet = workbook.createSheet("我的运单");

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
//		List<WaybillList> list = (List<WaybillList>) model.get("list");
//		int userCount = list.size();
		for (int i = 0; i < 6; i++) {
			int cellNum = i + 1;

			String cargoNo = "小明";
			cell = getCell(sheet, cellNum, 0);
			cell.setCellStyle(contentStyle);
			setText(cell, cargoNo);

			String billId = "幻化";
			cell = getCell(sheet, cellNum, 1);
			cell.setCellStyle(contentStyle);
			setText(cell, billId);

			String cargoKindStr = "金刚";
			cell = getCell(sheet, cellNum, 2);
			cell.setCellStyle(contentStyle);
			setText(cell, cargoKindStr);
		}
	}

}
