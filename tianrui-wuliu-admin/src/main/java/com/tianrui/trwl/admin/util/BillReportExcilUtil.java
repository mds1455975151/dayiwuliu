package com.tianrui.trwl.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.tianrui.api.resp.admin.AnlianBillReportResp;
import com.tianrui.common.utils.UUIDUtil;

public class BillReportExcilUtil extends AbstractExcelView {

	private static String[] titles = new String[] { "序号", "运单类型","业务日期","计划单号","运单号",
			"发货方","收货方","车主姓名","车牌号","司机姓名","货物名称","间隔分钟数","间隔距离","提货时间","到货时间","签收时间","提货地偏差","卸货地偏差",
			"提货数量","运输路线","计划总量","卸货量","执行总量","运单状态"};
	
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
		List<AnlianBillReportResp> list =  (List<AnlianBillReportResp>) model.get("list");
		int userCount = list.size();
		for (int i = 0; i < userCount; i++) {
			Integer cellNum = i + 1;
			//"序号"
			String no = cellNum.toString();
			cell = getCell(sheet, cellNum, 0);
			cell.setCellStyle(contentStyle);
			setText(cell, no);
			//"运单类型"
			String type = list.get(i).getType();
			cell = getCell(sheet, cellNum, 1);
			cell.setCellStyle(contentStyle);
			setText(cell, type);
			//"业务日期"
			String createtime = list.get(i).getCreatetimeStr();
			cell = getCell(sheet, cellNum, 2);
			cell.setCellStyle(contentStyle);
			setText(cell, createtime);
			//"计划单号"
			String plancode = list.get(i).getPlancode();
			cell = getCell(sheet, cellNum, 3);
			cell.setCellStyle(contentStyle);
			setText(cell, plancode);
			//"运单号"
			String waybillno = list.get(i).getWaybillno();
			cell = getCell(sheet, cellNum, 4);
			cell.setCellStyle(contentStyle);
			setText(cell, waybillno);
			//发货方
			String shipper = list.get(i).getShippermerchantname();
			cell = getCell(sheet, cellNum, 5);
			cell.setCellStyle(contentStyle);
			setText(cell, shipper);
			//收货方
			String consign = list.get(i).getConsigneemerchantname();
			cell = getCell(sheet, cellNum, 6);
			cell.setCellStyle(contentStyle);
			setText(cell, consign);
			//车主姓名
			String remarkname = list.get(i).getRemarkname();
			cell = getCell(sheet, cellNum, 7);
			cell.setCellStyle(contentStyle);
			setText(cell, remarkname);
			//车牌号
			String vehicleno = list.get(i).getVehicleno();
			cell = getCell(sheet, cellNum, 8);
			cell.setCellStyle(contentStyle);
			setText(cell, vehicleno);
			//司机姓名
			String drivername = list.get(i).getDrivername();
			cell = getCell(sheet, cellNum, 9);
			cell.setCellStyle(contentStyle);
			setText(cell, drivername);
			//货物名称
			String cargoname = list.get(i).getCargoname();
			cell = getCell(sheet, cellNum, 10);
			cell.setCellStyle(contentStyle);
			setText(cell, cargoname);
			//间隔分钟数
			cell = getCell(sheet, cellNum, 11);
			cell.setCellStyle(contentStyle);
			if(list.get(i).getInterTime() != null){
				Long inetTime = list.get(i).getInterTime()/(60*1000);
				setText(cell, inetTime.toString()+"分钟");
			}else{
				setText(cell, "");
			}
			//间隔距离
			cell = getCell(sheet, cellNum, 12);
			cell.setCellStyle(contentStyle);
			if(list.get(i).getInterDistance() != null){
				Double inetDist = list.get(i).getInterDistance();
				setText(cell, inetDist.toString()+"米");
			}else{
				setText(cell, "");
			}
			//提货时间
			String begintime = list.get(i).getBegintimeStr();
			cell = getCell(sheet, cellNum, 13);
			cell.setCellStyle(contentStyle);
			setText(cell, begintime);
			//到货时间
			String un = list.get(i).getUnloadtimeStr();
			cell = getCell(sheet, cellNum, 14);
			cell.setCellStyle(contentStyle);
			setText(cell, un);
			//签收时间
			cell = getCell(sheet, cellNum, 15);
			cell.setCellStyle(contentStyle);
			if(list.get(i).getOwnerSigntime() != null){
				Long ownerTime = list.get(i).getOwnerSigntime();
				setText(cell, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(ownerTime)));
			}else{
				setText(cell, "");
			}
			
			//提货地偏差
			cell = getCell(sheet, cellNum, 16);
			cell.setCellStyle(contentStyle);
			if(list.get(i).getQ_deviation()!=null){
				Double op = list.get(i).getQ_deviation();
				setText(cell, op.toString()+"米");
			}else{
				setText(cell, null);
			}
			
			//卸货地偏差
			cell = getCell(sheet, cellNum, 17);
			cell.setCellStyle(contentStyle);
			if(list.get(i).getD_deviation()!=null){
				Double dp = list.get(i).getD_deviation();
				setText(cell, dp.toString()+"米");
			}else{
				setText(cell, null);
			}
			
			
			//提货数量
			Double pickup = null;
			cell = getCell(sheet, cellNum, 18);
			cell.setCellStyle(contentStyle);
			if(list.get(i).getPickupweight() != null){
				pickup = list.get(i).getPickupweight();
				setText(cell, pickup.toString());
			}else{
				setText(cell, null);
			}
			//运输路线
			String routetime = list.get(i).getRoutename();
			cell = getCell(sheet, cellNum, 19);
			cell.setCellStyle(contentStyle);
			setText(cell, routetime);
			//计划总量
			String weight = list.get(i).getWeight();
			cell = getCell(sheet, cellNum, 20);
			cell.setCellStyle(contentStyle);
			setText(cell, weight);
			//卸货量
			Double sign = null;
			cell = getCell(sheet, cellNum, 21);
			cell.setCellStyle(contentStyle);
			if(list.get(i).getSignweight() != null){
				sign = list.get(i).getSignweight();
				setText(cell, sign.toString());
			}else{
				setText(cell, null);
			}
			//执行总量
			Double trueweight = null;
			cell = getCell(sheet, cellNum, 22);
			cell.setCellStyle(contentStyle);
			if(list.get(i).getTrueweight()!= null){
				trueweight = list.get(i).getTrueweight();
				setText(cell, trueweight.toString());
			}else{
				setText(cell, null);
			}
			//运单状态
			String sta = "";
			if(list.get(i).getStatus() != null){
				sta = list.get(i).getStatus().toString();
			}
			String status = "";
			switch (sta) {
			case "-1":
				status = "车主回收";
				break;
			case "0":
				status = "司机未确认";
				break;
			case "1":
				status = "司机已接受";
				break;	
			case "2":
				status = "司机已装货";
				break;
			case "3":
				status = "司机运输中";
				break;
			case "4":
				status = "司机已到达";
				break;
			case "5":
				status = "司机已卸货";
				break;	
			case "6":
				status = "已签收";
				break;
			case "7":
				status = "司机拒绝接单";
				break;	
			default:
				status = "安联运单";
				break;
			}
			cell = getCell(sheet, cellNum, 23);
			cell.setCellStyle(contentStyle);
			setText(cell, status);
		}
	}

}
