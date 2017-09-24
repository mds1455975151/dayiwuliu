package com.tianrui.trwl.admin.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.tianrui.api.resp.report.ReportBillAllResp;
import com.tianrui.api.resp.report.ReportPayAllResp;
import com.tianrui.common.utils.UUIDUtil;

public class PaylAllReportExcilUtil extends AbstractExcelView {

	private static String[] titles = new String[] { "账单日期", "账单编号","支付对象","计划单号","路线",
			"发货方","发货人","车主","收货方","签收人","车牌号","运单日期","运单号","货物名称","签收量","含税单价","总价","油卡",
			"扣重扣杂","扣款","其他款项","应付金额","付款金额","支付状态","付款方式","收款人","银行名称","收款账户"};
	
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
		sheet = workbook.createSheet("运单报表");

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
		List<ReportPayAllResp> list =  (List<ReportPayAllResp>) model.get("list");
		int userCount = list.size();
		for (int i = 0; i < userCount; i++) {
			ReportPayAllResp data = list.get(i);
			Integer cellNum = i + 1;
			//账单日期
			String no = data.getPayCreateTime().toString();
			cell = getCell(sheet, cellNum, 0);
			cell.setCellStyle(contentStyle);
			setText(cell, no);
			//账单编号
			String type = data.getPayCode();
			cell = getCell(sheet, cellNum, 1);
			cell.setCellStyle(contentStyle);
			setText(cell, type);
			//支付对象
			String planNo = data.getPayMent();
			cell = getCell(sheet, cellNum, 2);
			cell.setCellStyle(contentStyle);
			setText(cell, planNo);
			//计划单号
			String billNo = data.getPlanCode();
			cell = getCell(sheet, cellNum, 3);
			cell.setCellStyle(contentStyle);
			setText(cell, billNo);
			//路线
			String sendMan = data.getRouteName();
			cell = getCell(sheet, cellNum, 4);
			cell.setCellStyle(contentStyle);
			setText(cell, sendMan);
			//发货方
			String sendPersion = data.getSendMan();
			cell = getCell(sheet, cellNum, 5);
			cell.setCellStyle(contentStyle);
			setText(cell, sendPersion);
			//发货人
			String receiptMan = data.getSendPersian();
			cell = getCell(sheet, cellNum, 6);
			cell.setCellStyle(contentStyle);
			setText(cell, receiptMan);
			//车主
			String receiptPersion = data.getVenderName();
			cell = getCell(sheet, cellNum, 7);
			cell.setCellStyle(contentStyle);
			setText(cell, receiptPersion);
			//收货方
			String vehicleno = data.getReceiptMan();
			cell = getCell(sheet, cellNum, 8);
			cell.setCellStyle(contentStyle);
			setText(cell, vehicleno);
			//签收人
			String cargoName = data.getReceiptPerson();
			cell = getCell(sheet, cellNum, 9);
			cell.setCellStyle(contentStyle);
			setText(cell, cargoName);
			//车牌号
			String routeName = data.getVehicleNo();
			cell = getCell(sheet, cellNum, 10);
			cell.setCellStyle(contentStyle);
			setText(cell, routeName);
			//运单日期
			String distinct = data.getBillTime().toString();
			cell = getCell(sheet, cellNum, 11);
			cell.setCellStyle(contentStyle);
			setText(cell, distinct);
			//运单号
			String venderWeight = data.getBillNo();
			cell = getCell(sheet, cellNum, 12);
			cell.setCellStyle(contentStyle);
			setText(cell, venderWeight);
			//货物名称
			String pickupWeight = data.getCargoName();
			cell = getCell(sheet, cellNum, 13);
			cell.setCellStyle(contentStyle);
			setText(cell, pickupWeight);
			//签收量
			String unloadWeight = data.getTrueWeight();
			cell = getCell(sheet, cellNum, 14);
			cell.setCellStyle(contentStyle);
			setText(cell, unloadWeight);
			//含税单价
			String trueWeight = data.getPrice();
			cell = getCell(sheet, cellNum, 15);
			cell.setCellStyle(contentStyle);
			setText(cell, trueWeight);
			
			//总价
			String billStatus = data.getTotalPrice();
			cell = getCell(sheet, cellNum, 16);
			cell.setCellStyle(contentStyle);
			setText(cell, billStatus);
			
			//油卡
			String driverName = data.getOilCard();
			cell = getCell(sheet, cellNum, 17);
			cell.setCellStyle(contentStyle);
			setText(cell, driverName);
			
			//扣重扣杂
			String payMent = data.getWeightMisc();
			cell = getCell(sheet, cellNum, 18);
			cell.setCellStyle(contentStyle);
			setText(cell, payMent);
			
			//扣款
			String billCreaterTime = data.getDeductMoney();
			cell = getCell(sheet, cellNum, 19);
			cell.setCellStyle(contentStyle);
			setText(cell, billCreaterTime);
			
			//其他款项
			String acceptTime = data.getDeductOther();
			cell = getCell(sheet, cellNum, 20);
			cell.setCellStyle(contentStyle);
			setText(cell, acceptTime);
			
			//应付金额
			String pickupTime = data.getAmountPayable();
			cell = getCell(sheet, cellNum, 21);
			cell.setCellStyle(contentStyle);
			setText(cell, pickupTime);
			
			//付款金额
			String unloadTime = data.getPaidAmount();
			cell = getCell(sheet, cellNum, 22);
			cell.setCellStyle(contentStyle);
			setText(cell, unloadTime);
			
			//支付状态
			String signTime = data.getPayStatus();
			cell = getCell(sheet, cellNum, 23);
			cell.setCellStyle(contentStyle);
			setText(cell, signTime);
			
			//付款方式
			String payType = data.getPayType();
			cell = getCell(sheet, cellNum, 24);
			cell.setCellStyle(contentStyle);
			setText(cell, payType);
			
			//收款人
			String payPerson = data.getPayPerson();
			cell = getCell(sheet, cellNum, 25);
			cell.setCellStyle(contentStyle);
			setText(cell, payPerson);
			
			//银行名称
			String payBankName = data.getPayBankName();
			cell = getCell(sheet, cellNum, 26);
			cell.setCellStyle(contentStyle);
			setText(cell, payBankName);
			
			//收款账户
			String payBankCode = data.getPayBankCode();
			cell = getCell(sheet, cellNum, 27);
			cell.setCellStyle(contentStyle);
			setText(cell, payBankCode);
		}
	}

}