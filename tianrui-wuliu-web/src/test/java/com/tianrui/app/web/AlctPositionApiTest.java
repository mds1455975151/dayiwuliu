package com.tianrui.app.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.tianrui.service.bean.alct.BodyConsigneeInfo;
import com.tianrui.service.bean.alct.BodyConsignorInfo;
import com.tianrui.service.bean.alct.BodyGoodsInfo;
import com.tianrui.service.bean.alct.BodyPriceInfo;
import com.tianrui.service.bean.alct.Shipments;
import com.tianrui.service.bean.alct.ShipmentsBody;
import com.tianrui.service.bean.alct.ShipmentsHead;
import com.tianrui.service.bean.alct.Traces;
import com.tianrui.service.bean.alct.TracesPosition;

public class AlctPositionApiTest {

	private static String url="https://oapi-staging.alct56.com/v1/shipments//traces/";
	
	
	static Traces getParam2(){
		TracesPosition traces = new TracesPosition();
		traces.setLatitude(35.063279);
		traces.setLongitude(108.042335);
		traces.setTime("20180105090100");
		
		Traces rs = new Traces();
		rs.setCoordinationType("WGS84");
		rs.setShipmentCode("YD09876");
		rs.setTraces(traces);
		return rs;
	}
	
	static String  httpPost(String url,String param){
		StringBuffer sb = new StringBuffer("");
	   
	    try {
			URL realUrl = new URL(url);
	        // 打开和URL之间的连接
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
	        // 设置通用的请求属性
			conn.addRequestProperty("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjRDNTQwNzlDQTU4OUZDRUREMTg5NzYzRUZBQTU4MTUzNTQ5MUNCOEMiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJURlFIbktXSl9PM1JpWFktLXFXQlUxU1J5NHcifQ.eyJuYmYiOjE1MTUwMzg3MzksImV4cCI6MTUxNzYzMDczOSwiaXNzIjoiaHR0cDovL29hdXRoOjQwMDAiLCJhdWQiOlsiaHR0cDovL29hdXRoOjQwMDAvcmVzb3VyY2VzIiwib3BlbmFwaSJdLCJjbGllbnRfaWQiOiJTZXJ2aWNlQVBJIiwic3ViIjoiLTEiLCJhdXRoX3RpbWUiOjE1MTUwMzg3MzksImlkcCI6ImxvY2FsIiwiZWNvZGUiOiJFMDAwMDEwOSIsInR5cGUiOiJkcml2ZXIiLCJzY29wZSI6WyJvcGVuYXBpIl0sImFtciI6WyJlbnRlcnByaXNlX2FwcCJdfQ.AOK0XGoWZhfP2sPQI9G3y8UO6sebKjZH2tvMzThNtJLccaPIOzNKSnQXlP9eWdn__LENQuryInXOfvQ-Rl7hU_VJZ1qVGvfBVdd2l1Ljz4jZtGfcmO1LGytgKMpVkmEblwmuk28i07r6ywTosOUmDZRmDI8TykFOVfZMRxof0h2F4SHIHwzCeSrgq0iV39Os6d_683A1TihLM3SxyfArjzPPKFTAGIzf6bbSFVbKdfZN9R4gSiKDU5Mtl2bAVAsr3gVx1OSVMnMBBw1UW9f8HmI0Fleyj7sV6ufIFJmsghPHxwuMkWgUJy5nPymxzT7JkhpHT4CniYXOi8lKKiDHXLHkHWcv2G7PeE_VTUPHw2MbcsR2zm09YVX-DzFWYcxtJLVl7wxXqOes9CK2KqQHmelOqVKc34iFQkqCztOzp0yEb-A2xck1kI_zdZFUALjed3C28qvxk52kDlVxlFgGQNXOi0BDSYlQAdvk6DUjQMfMeMYETdcRhBFVx_fuQD0w5kajtO8wDq3OaobSAkoT7eHOqS_wSD36pcs6AyaRNYJHZjpaV-CrNvAW1qxq3Rm-ZqygDYTX0FASr7cR07WyzP03aQH8w7HGBiTe_dNn0QVqT-oidieWuAaIfV2kc2NKRBAk3-gI399NOFgpxKOjXsRpfoYIVD2JguYSJ9G7c4Q");
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type","application/json");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			
	        // 发送POST请求必须设置如下两行     
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        //连接
	        conn.connect();
	        // 获取URLConnection对象对应的输出流
	        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	        out.write(param.getBytes("utf-8"));
	        // flush输出流的缓冲
	        out.flush();
            out.close();
	        // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String lines="";
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes("utf-8"), "utf-8");
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
	public static void main(String[] args) throws UnsupportedEncodingException {
		String param =JSON.toJSONString(getParam2());
		System.out.println(param);
		System.out.println(url);
		System.out.println(httpPost(url,param));
	}
	
	
}
