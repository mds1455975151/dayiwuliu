package com.tianrui.service.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class VehicleXmlSetUtil {

	public static void main(String[] args) {
		System.out.println(confirmType("src/main/resources/bill.xml","vehicle","4dd756"));
	}
	public static boolean confirmType(String path,String type,String name){
		try {  
//			 String rootPath=getClass().getResource("/").getFile().toString()
			System.out.println("---------请求数据--"+name+"---"+type+"---"+path);
			File f = new File(path);   
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
		    DocumentBuilder builder = factory.newDocumentBuilder();   
		    Document doc = builder.parse(f);   
		    //获取跟目录
		    Element root = (Element) doc.getElementsByTagName(type+"Type").item(0);
		    if(!"1".equals(root.getElementsByTagName("value").item(0).getFirstChild().getNodeValue())){
		    	System.out.println("------------------"+type+"判断已关闭");
		    	return true;
		    }
		    NodeList nl = doc.getElementsByTagName(type);
		    for (int i = 0; i < nl.getLength(); i++) {  
		    	String value = doc.getElementsByTagName(type).item(i).getFirstChild().getNodeValue();
		    	if(value.indexOf(name)!=-1){
		    		return true;
		    	}
		    }   
	     } catch (Exception e) {   
	      e.printStackTrace();   
	     }  
	    return false;
	}
}
