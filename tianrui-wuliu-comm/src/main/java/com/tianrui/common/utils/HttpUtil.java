package com.tianrui.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.common.vo.ApiResult;


public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	
	public static void main(String[] args) {
		List<JSONObject> list = new ArrayList<>();
		JSONObject obj = new JSONObject();
		obj.put("billIds", "746bba8d9e1940bba6bf5466c0f7a344");
		obj.put("payMent", "2");
		list.add(obj);
		System.out.println(post(list,"http://172.19.4.69:9082/service/TrPayStatusQryServlet"));
	}
	
	public static ApiResult post(Object object, String path){
		logger.debug("post url:{},params:{}",new Object[]{path,JSON.toJSONString(object)});
		ApiResult apiResult = null;
		HttpURLConnection connection = null;
		OutputStream out = null;
		InputStreamReader input = null;
		BufferedReader reader = null;
        try {
        	URL url = new URL(path);
			//打开链接
			connection = (HttpURLConnection) url.openConnection();
			//连接超时设置
			connection.setConnectTimeout(120000);
			//读取超时设置
			connection.setReadTimeout(150000);
			//设置请求类型
			connection.setRequestMethod("POST");
			//设置是否带参数
			connection.setDoOutput(true);

			byte[] bytes = JSON.toJSONString(object).toString().getBytes();
			out = connection.getOutputStream();
			out.write(bytes);
			out.flush();
			input = new InputStreamReader(connection.getInputStream(), "UTF-8");
			reader = new BufferedReader(input);
			//发送请求
			String result = reader.readLine();
			apiResult = JSON.parseObject(result, ApiResult.class);
        } catch (Exception e) {
        	logger.error("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        } finally {
        	try {
				if (reader != null) {
					reader.close();
				}
				if (out != null) {
					out.close();
				}
				if (input != null) {
					input.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e2) {
			}
        }
        return apiResult;
	}
	
	public static ApiResult post_longlong(Object object, String path){
		logger.debug("post url:{},params:{}",new Object[]{path,JSON.toJSONString(object)});
		ApiResult apiResult = null;
		HttpURLConnection connection = null;
		OutputStream out = null;
		InputStreamReader input = null;
		BufferedReader reader = null;
        try {
        	URL url = new URL(path);
			//打开链接
			connection = (HttpURLConnection) url.openConnection();
          //连接超时设置
			connection.setConnectTimeout(50000);
			//读取超时设置
			connection.setReadTimeout(150000);
			//设置请求类型
			connection.setRequestMethod("POST");
			//设置是否带参数
			connection.setDoOutput(true);

			//拼接参数
			String params = JSON.toJSONString(object).toString();
			StringBuffer sb = new StringBuffer("payInvoiceDetail=");
			sb.append(params);
			byte[] bytes = sb.toString().getBytes();
			out = connection.getOutputStream();
			out.write(bytes);
			out.flush();
			input = new InputStreamReader(connection.getInputStream(), "UTF-8");
			reader = new BufferedReader(input);
			//发送请求
			String result = reader.readLine();
			apiResult = JSON.parseObject(result, ApiResult.class);
        } catch (Exception e) {
        	logger.error("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        } finally {
        	try {
				if (reader != null) {
					reader.close();
				}
				if (out != null) {
					out.close();
				}
				if (input != null) {
					input.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e2) {
			}
        }
        return apiResult;
	}
	
}
