/**
 * 
 */
package com.tianrui.web.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * @author Hongten
 * @created 2014-5-18
 */
public class ReadExcel {

	public static void main(String[] args) {
		try {
			System.out.println(readXls().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static JSONArray readXls() throws IOException {
		InputStream is = new FileInputStream("H:/bank_address.xls");
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		JSONArray array = new JSONArray();
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					HSSFCell no = null;
					
					JSONObject json = new JSONObject();
//					no = hssfRow.getCell((short) 0);
//					json.put("A", getValue(no));
					
					no = hssfRow.getCell((short) 1);
					json.put("address", getValue(no));
					
					no = hssfRow.getCell((short) 2);
					try {
						BigDecimal db = new BigDecimal(getValue(no));
						System.out.println("普通计数：" + db.toPlainString());
						json.put("code", db.toPlainString());
					} catch (Exception e) {
						json.put("code", getValue(no));
					}
					
					no = hssfRow.getCell((short) 3);
					json.put("innercode", getValue(no));
					
					no = hssfRow.getCell((short) 4);
					json.put("name", getValue(no));
					
					array.add(json);
				}
			}
		}
		return array;
	}
	
	private static String getValue(HSSFCell hssfCell) {
	        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
	            return String.valueOf(hssfCell.getNumericCellValue());
	        } else {
	        	return String.valueOf(hssfCell.getStringCellValue());
	        }
	    }
}
