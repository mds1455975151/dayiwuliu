package com.tianrui.app.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.front.api.APIPositionReq;
import com.tianrui.common.utils.Md5Utils;

public class ApiTest {

	private static String url="http://www.appb2b.com/other/anlianApi/uploadPosition";
	//防篡改
	private static String API_KEY="!tR2016@#%";
	//用户标识验证
	private static String API_SECRET_KEY="anlian2016";
	static APIPositionReq getParam(){
		APIPositionReq req =new APIPositionReq();
		req.setLat("lat");
		req.setLng("lon");
		req.setTime("2016-12-30 10:45:00");
		req.setTrackingid("Trackingid");
		req.setTrackingdate("2016-12-30 9:45:00");
		return req;
	}
	
	
	static void setkey(APIPositionReq req){
		req.setKey(Md5Utils.MD5(req.getTime()+API_SECRET_KEY));
	}
	
	static void setMd5(APIPositionReq req){
		req.setMd5(API_KEY);
		req.setMd5(Md5Utils.MD5(JSON.toJSONString(req)));
	}
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
		APIPositionReq req =getParam();
		setkey(req);
		setMd5(req);
		String param =JSON.toJSONString(req);
		System.out.println(httpPost(url,param));
		
		
	}
	
	
}
