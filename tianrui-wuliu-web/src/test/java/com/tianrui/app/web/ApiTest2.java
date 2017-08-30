package com.tianrui.app.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.front.api.VehicleGpsReq;
import com.tianrui.common.utils.Md5Utils;

public class ApiTest2 {

	private static String url="http://www.appb2b.com/otherApi/vehicle/queryTrack";

	//private static String url="http://127.0.0.1/other/anlianApi/uploadPosition";
	
	
	static VehicleGpsReq getParam2(){
		VehicleGpsReq req =new VehicleGpsReq();
		try {
			req.setVehicleNO(new String("豫DR0868".getBytes("utf-8"),"iso8859-1"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setBeginTime("2017-08-27 09:08:00");
		req.setEndTime("2017-08-30 12:08:00");
		req.setTime("2016-12-30 10:45:00");
		req.setToken(Md5Utils.MD5("anlian!@2017#2016-12-30 10:45:00"));
		return  req;
	}
	
	
	
//	static APIPositionReq getParam2(){
//		APIPositionReq req =new APIPositionReq();
//		req.setLat("lat");
//		req.setLng("lon");
////		req.setTime("2016-12-30 10:45:00");
////		req.setTrackingid("Trackingid");
////		req.setTrackingdate("2016-12-30 9:45:00");
//		return req;
//	}
	
	
	static String  httpPost(String url,String param){
		StringBuffer sb = new StringBuffer("");
	   
	    try {
			URL realUrl = new URL(url);
	        // 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
	        // 设置通用的请求属性
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type","application/json");
	        // 发送POST请求必须设置如下两行
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        //连接
	        conn.connect();
	        // 获取URLConnection对象对应的输出流
	        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	        out.writeBytes(param);
	        // flush输出流的缓冲
	        out.flush();
            out.close();
	        // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines="";
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            reader.close();
            // 断开连接
            conn.disconnect();
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    }
	    return sb.toString();
	}
	public static void main(String[] args) {
		String param =JSON.toJSONString(getParam2());
		System.out.println(param);
		//System.out.println(httpPost(url,req.toJSONString()));
		System.out.println(httpPost(url,param));
		
	}
	
	
}
