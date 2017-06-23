package com.tianrui.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.tianrui.common.vo.AppResult;


public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	public static AppResult post(Object object, String path){
		logger.info("post url:{},params:{}",new Object[]{path,JSON.toJSONString(object)});
		AppResult appResult = null;
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

			byte[] bytes = JSON.toJSONString(object).toString().getBytes();
			out = connection.getOutputStream();
			out.write(bytes);
			out.flush();
			input = new InputStreamReader(connection.getInputStream(), "UTF-8");
			reader = new BufferedReader(input);
			//发送请求
			String result = reader.readLine();
			appResult = JSON.parseObject(result, AppResult.class);
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
        return appResult;
	}
}
