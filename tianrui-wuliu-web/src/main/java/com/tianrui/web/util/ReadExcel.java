/**
 * 
 */
package com.tianrui.web.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * @author Hongten
 * @created 2014-5-18
 */
public class ReadExcel {

	public static void main(String[] args) {
		try {
			List<Map<String,String>> list = readXls();
			System.out.println(list.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Map<String,String>> readXls() throws IOException {
		InputStream is = new FileInputStream("H:/wuche.xls");
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		List<Map<String,String>> lis = new ArrayList<Map<String,String>>();
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					HSSFCell no = hssfRow.getCell((short) 0);
					HSSFCell name = hssfRow.getCell((short) 1);
					Map<String,String> map = new HashMap<String,String>();
					map.put("code", getValue(no).substring(0, 6));
					map.put("name", getValue(name));
					lis.add(map);
				}
			}
		}
		return lis;
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
