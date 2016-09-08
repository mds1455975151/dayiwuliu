package com.tianrui.app.web;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class TestExcil {

	 /**
     * @作者：heasen
     * @日期：2010-3-24
     * @功能：手工构建一个简单格式的Excel
     */
    
    public static void main(String[] args) throws Exception {
        //第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("学生表一");
        //第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int)0);
        //第四步，创建单元格，并设置值表头  设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //创建一个居中格式
        
        HSSFCell cell = row.createCell((short)0);
        cell.setCellValue("学号"); cell.setCellStyle(style);
        cell = row.createCell((short)1);
        cell.setCellValue("姓名"); cell.setCellStyle(style);
        cell = row.createCell((short)2);
        cell.setCellValue("年龄"); cell.setCellStyle(style);
    
        //第五步，写入实体数据 实际应用中这些数据从数据库得到，

        for(int i=0;i<3;i++){
            row = sheet.createRow((int)i+1);
            //第四步，创建单元格，并设置值
            row.createCell((short)0).setCellValue("大名");
            row.createCell((short)1).setCellValue("小明");
            row.createCell((short)2).setCellValue("芳芳");
            cell = row.createCell((short)3);
        }
        //第六步，将文件存到指定位置
        try {
            FileOutputStream fout = new FileOutputStream("E:/students.xls");
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
