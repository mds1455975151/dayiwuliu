package com.tianrui.app.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.front.api.VehicleGpsReq;
import com.tianrui.common.utils.Md5Utils;

public class AlctApiTest {

	private static String url="https://oapi-staging.alct56.com/api/v1/openapi/enterprises/login";
	
	
	static JSONObject getParam2(){
		JSONObject obj = new JSONObject();
		obj.put("enterpriseCode", "E0000109");
		obj.put("enterpriseIdentity", "8fcf372af06d11e79148246e965b4750");
		obj.put("enterpriseKey", "9730fdddf06d11e7ae3d0242ac140002");
		return  obj;
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
            System.out.println(sb);
            reader.close();
            // 断开连接
            conn.disconnect();
	    }catch(Exception e){
	    	System.out.println("异常信息"+e.getMessage());
	    }
	    return sb.toString();
	}
	public static void main(String[] args) {
		String param =JSON.toJSONString(getParam2());
		System.out.println(param);
		System.out.println(url);
		System.out.println(httpPost(url,param));
	}
	
	
}
