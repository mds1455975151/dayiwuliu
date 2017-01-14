package com.tianrui.service.anlian;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.service.bean.anlian.AnlianDriver;
import com.tianrui.service.bean.anlian.AnlianTruck;

public class AnlianTestService {

	//注册车辆
	public static String TRUCK = "http://223.255.14.186:149/api/Truck";
	//注册司机
	public static String DRIVER = "http://223.255.14.186:149/api/Driver";
	//注册挂车
	public static String TRAILER  = "http://223.255.14.186:149/api/Trailer";
	
	public static String SHIPMENT = "http://223.255.14.186:149/api/Shipment";
	
	public static void main(String[] args) {
		try {
			URL url = new URL(TRUCK);
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", " application/json");//设定 请求格式 json，也可以设定xml格式
			connection.setRequestProperty("Accept-Charset", "utf-8");  //设置编码语言
			
			String dataString = truckString();
			byte[] bypes = dataString.getBytes("utf-8");
			
			connection.getOutputStream().write(bypes);// 输入参数
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String response = in.readLine();
			System.out.println("请求返回数据："+response);
		} catch (Exception e) {
			JSONObject obj = new JSONObject();
			obj.put("code", "1");
			obj.put("error", "网络异常");
		}
	}
	/** 司机数据转换*/
	public static String drivrtString(){
		AnlianDriver driver = new AnlianDriver();
		/**司机姓名*/
		driver.setSjxm("王尼玛");
		/**性别*/
		driver.setXb("Y");
		/** 身份证号码*/
		driver.setSfzhm("411223199612124569");
		/** 出生日期*/
		driver.setCsrq("1996-12-12");
		/**身份证地址*/
		driver.setSfzdz("河南省封装镇");
		/**驾驶证*/
		driver.setJsz("411223199612124569");
		/** 初次领证日期*/
		driver.setCclzrq("2012-10-10");
		/** 发证机关*/
		driver.setFzjg("丰庄镇派出所");
		/** 准驾车型*/
		driver.setZjcx("A1");
		/** 起始日期*/
		driver.setQsrq("2012-10-10");
		/** 有效年限*/
		driver.setYxqx("10");
		/** 手机号码*/
		driver.setSjhm("18337129805");
		/***/
		driver.setEmail("");
		/***/
		driver.setQq("");
		System.out.println(JSON.toJSONString(driver));
		return JSON.toJSONString(driver);
	}
	
	/** 车辆数据拼装*/
	public static String truckString(){
		
		AnlianTruck truck = new AnlianTruck();
		//车牌号码
		truck.setCphm("豫K63265");
		//检验有效日期
		truck.setJyyxqz("2017-12-31");
		//核定在质量
		truck.setHdzzl("20");
		//总质量
		truck.setZzl("50");
		//登记证书编号
		truck.setDjzsbh("543287542");
		//所有人
		truck.setSyr("天瑞集团");
		//身份证明
		truck.setSfzm("411282199210221236");
		//标准车辆类型
		truck.setBzcllx("H11");
		//使用性质
		truck.setSyxz("1");
		//车辆识别代码
		truck.setClsbdm("123654987");
		//发动机号
		truck.setFdjh("659874");
		//发动机型号
		truck.setFdjxh("69856654");
		
		truck.setDlysjyxkzbh("");
		truck.setDlysjyxkzyxqz("");
		truck.setSf("");
		truck.setFzjg("");
		truck.setClccrq("");
		truck.setCcdjrq("");
		truck.setCwkc("");
		truck.setCwkk("");
		truck.setCwkg("");
		truck.setCsys("");
		truck.setClpp("");
		truck.setZz("");
		truck.setBxzzrq("");
		
		JSONObject obj = new JSONObject();
		obj.put("username", "E0000249");
		obj.put("pwd", "");
		//车牌号码
		obj.put("cphm", "豫M65923");//非空
		obj.put("dlysjyxkzbh", "");
		obj.put("dlysjyxkzyxqz", "");
		obj.put("sf", "");
		obj.put("fzjg", "");
		obj.put("clccrq", "");
		obj.put("ccdjrq", "");
		//检验有效日期
		obj.put("jyyxqz", "2017-12-31");//非空
		obj.put("bxzzrq", "");
		//核定在质量
		obj.put("hdzzl", "20");//非空
		//总质量
		obj.put("zzl", "50");//非空
		obj.put("cwkc", "");
		obj.put("cwkk", "");
		obj.put("cwkg", "");
		obj.put("csys", "");
		obj.put("clpp", "");
		//车辆识别代码
		obj.put("clsbdm", "12365547");
		//发动机型号
		obj.put("fdjxh", "FC-1236598");
		//发动机号
		obj.put("fdjh", "K-36598");
		obj.put("lines", new JSONArray());
		//登记证书编号
		obj.put("djzsbh", "236598741");//非空
		//所有人
		
		
		
		obj.put("syr", "天瑞集团");//非空
		//身份证明
		obj.put("sfzm", "411282199210214127");//非空
		obj.put("zz", "");
		//标准车辆类型
		obj.put("bzcllx", "H11");//非空
		//使用性质
		obj.put("syxz", "1");//1-营运 2-非营运
//		System.out.println(JSON.toJSONString(truck));
//		return JSON.toJSONString(truck);
		System.out.println(obj.toString());
		return obj.toString();
	}
	
}
