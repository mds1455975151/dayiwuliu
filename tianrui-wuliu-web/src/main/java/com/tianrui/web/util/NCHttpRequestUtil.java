package com.tianrui.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONObject;


public class NCHttpRequestUtil {

	public static void main(String[] args) {
		putRequest("");
	}
	
	public static String putRequest(String bankcord){
		PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	String url = "http://172.20.10.161:80/service/TrSupplierAddServlet";
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            JSONObject objw = new JSONObject();
            objw.put("name", "啊说的话");
            objw.put("vbusinlicense", "41122319921211");
            objw.put("suppid", "hdfuoiwej88kv");
//            new String(objw.toString().getBytes(),"UTF-8");
            
//            new String(Base64.decodeBase64(objw.toString().getBytes()));
            
            out.print(objw.toString());
//            out.print(Base64.encodeBase64String(objw.toString().getBytes("utf-8")));
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = in.readLine()) != null) {
                result += line;
            }
//            JSONObject obj = JSONObject.parseObject(result);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
	}
}
