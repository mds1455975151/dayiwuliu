package com.tianrui.trwl.admin.report;

import java.math.BigDecimal;
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

import com.tianrui.api.resp.report.ReportBillAllResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.service.bean.BillCount;

public class BillAllReportExcilUtil extends AbstractExcelView {

	private static String[] titles = new String[] { "运单类型", "业务日期","计划单号","运单号","发货方",
			"发货人","收货方","签收人","车牌号","货物名称","路线","运距","车主派单量","提货榜单净重","卸货榜单净重","签收量","运单状态","司机姓名","车主姓名",
			"支付对象","运单创建时间","接受运单时间","提货确认时间","卸货确认时间","签收运单时间"};
	
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
		List<ReportBillAllResp> list =  (List<ReportBillAllResp>) model.get("list");
		 BillCount count =(BillCount) model.get("count");
		int userCount = list.size();
		for (int i = 0; i < userCount; i++) {
			ReportBillAllResp data = list.get(i);
			Integer cellNum = i + 1;
			//运单类型
			String no = data.getBillType();
			if(StringUtils.equals("al", data.getBillType())){
				no = "安联运单";
			}else if(StringUtils.equals("dy", data.getBillType())){
				no = "大易运单";
			}
			cell = getCell(sheet, cellNum, 0);
			cell.setCellStyle(contentStyle);
			setText(cell, no);
			//业务日期
			String type = data.getBusinessTimeStr();
			cell = getCell(sheet, cellNum, 1);
			cell.setCellStyle(contentStyle);
			setText(cell, type);
			//计划单号
			String planNo = data.getPlanNo();
			cell = getCell(sheet, cellNum, 2);
			cell.setCellStyle(contentStyle);
			setText(cell, planNo);
			//运单号
			String billNo = data.getBillNo();
			cell = getCell(sheet, cellNum, 3);
			cell.setCellStyle(contentStyle);
			setText(cell, billNo);
			//发货方
			String sendMan = data.getSendMan();
			cell = getCell(sheet, cellNum, 4);
			cell.setCellStyle(contentStyle);
			setText(cell, sendMan);
			//发货人
			String sendPersion = data.getSendPersion();
			cell = getCell(sheet, cellNum, 5);
			cell.setCellStyle(contentStyle);
			setText(cell, sendPersion);
			//收货方
			String receiptMan = data.getReceiptMan();
			cell = getCell(sheet, cellNum, 6);
			cell.setCellStyle(contentStyle);
			setText(cell, receiptMan);
			//签收人
			String receiptPersion = data.getReceiptPersion();
			cell = getCell(sheet, cellNum, 7);
			cell.setCellStyle(contentStyle);
			setText(cell, receiptPersion);
			//车牌号
			String vehicleno = data.getVehicleNo();
			cell = getCell(sheet, cellNum, 8);
			cell.setCellStyle(contentStyle);
			setText(cell, vehicleno);
			//货物名称
			String cargoName = data.getCargoName();
			cell = getCell(sheet, cellNum, 9);
			cell.setCellStyle(contentStyle);
			setText(cell, cargoName);
			//路线
			String routeName = data.getRouteName();
			cell = getCell(sheet, cellNum, 10);
			cell.setCellStyle(contentStyle);
			setText(cell, routeName);
			//运距
			String distinct = data.getDistinct();
			cell = getCell(sheet, cellNum, 11);
			cell.setCellStyle(contentStyle);
			setText(cell, distinct);
			//车主派单量
			String venderWeight = data.getVenderWeight();
			cell = getCell(sheet, cellNum, 12);
			cell.setCellStyle(contentStyle);
			setText(cell, venderWeight);
			//提货榜单净重
			String pickupWeight = data.getPickupWeight();
			cell = getCell(sheet, cellNum, 13);
			cell.setCellStyle(contentStyle);
			setText(cell, pickupWeight);
			//卸货榜单净重
			String unloadWeight = data.getUnloadWeight();
			cell = getCell(sheet, cellNum, 14);
			cell.setCellStyle(contentStyle);
			setText(cell, unloadWeight);
			//签收量
			String trueWeight = data.getTrueWeight();
			cell = getCell(sheet, cellNum, 15);
			cell.setCellStyle(contentStyle);
			setText(cell, trueWeight);
			
			//运单状态
			String billStatus = getBillStatus(data.getBillStatus());
			cell = getCell(sheet, cellNum, 16);
			cell.setCellStyle(contentStyle);
			setText(cell, billStatus);
			
			//司机姓名
			String driverName = data.getDriverName();
			cell = getCell(sheet, cellNum, 17);
			cell.setCellStyle(contentStyle);
			setText(cell, driverName);
			//车主姓名
			String ownerName = data.getOwnerName();
			cell = getCell(sheet, cellNum, 18);
			cell.setCellStyle(contentStyle);
			setText(cell, ownerName);
			//支付对象
			String payMent = data.getPayMent();
			if(StringUtils.equals("1", data.getPayMent())){
				payMent = "司机";
			}else if(StringUtils.equals("2", data.getPayMent())){
				payMent = "车主";
			}
			cell = getCell(sheet, cellNum, 19);
			cell.setCellStyle(contentStyle);
			setText(cell, payMent);
			
			//运单创建时间
			String billCreaterTime = data.getBillCreaterTimeStr();
			cell = getCell(sheet, cellNum, 20);
			cell.setCellStyle(contentStyle);
			setText(cell, billCreaterTime);
			
			//接受运单时间
			String acceptTime = data.getAcceptTimeStr();
			cell = getCell(sheet, cellNum, 21);
			cell.setCellStyle(contentStyle);
			setText(cell, acceptTime);
			
			//提货确认时间
			String pickupTime = data.getPickupTimeStr();
			cell = getCell(sheet, cellNum, 22);
			cell.setCellStyle(contentStyle);
			setText(cell, pickupTime);
			
			//卸货确认时间
			String unloadTime = data.getUnloadTimeStr();
			cell = getCell(sheet, cellNum, 23);
			cell.setCellStyle(contentStyle);
			setText(cell, unloadTime);
			
			//签收运单时间
			String signTime = data.getSignTimeStr();
			cell = getCell(sheet, cellNum, 24);
			cell.setCellStyle(contentStyle);
			setText(cell, signTime);
		}
		if(count != null){
			String distinct = count.getDistinctCount();
			String distincts= big2(Double.parseDouble(distinct));
			cell = getCell(sheet, userCount+1, 11);
			cell.setCellStyle(contentStyle);
			setText(cell, distincts);
			
			String venderWeight = count.getVenderWeightCount();
			String venderWeights= big2(Double.parseDouble(venderWeight));
			cell = getCell(sheet, userCount+1, 12);
			cell.setCellStyle(contentStyle);
			setText(cell, venderWeights);
			
			String pickupWeight = count.getPickupWeightCount();
			String pickupWeights= big2(Double.parseDouble(pickupWeight));
			cell = getCell(sheet, userCount+1, 13);
			cell.setCellStyle(contentStyle);
			setText(cell, pickupWeights);
			
			String unloadWeight = count.getUnloadWeightCount();
			String unloadWeights= big2(Double.parseDouble(unloadWeight));
			cell = getCell(sheet, userCount+1, 14);
			cell.setCellStyle(contentStyle);
			setText(cell, unloadWeights);
			
			String trueWeight = count.getTrueWeightCount();
			String trueWeights= big2(Double.parseDouble(trueWeight));
			cell = getCell(sheet, userCount+1, 15);
			cell.setCellStyle(contentStyle);
			setText(cell, trueWeights);
		}
	}
	private static String big2(double d) {
        BigDecimal d1 = new BigDecimal(Double.toString(d));
        BigDecimal d2 = new BigDecimal(Integer.toString(1));
        // 四舍五入,保留2位小数
        return d1.divide(d2,2,BigDecimal.ROUND_HALF_UP).toString();
    }

	public String getBillStatus(String sta){
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
			status = sta;
			break;
		}
		return status;
	}

}
