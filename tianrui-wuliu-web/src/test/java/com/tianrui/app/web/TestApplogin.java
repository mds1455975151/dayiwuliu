package com.tianrui.app.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.req.app.AppMemberReq;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.utils.Md5Utils;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.Head;
import com.tianrui.service.util.ConcatUrl;

public class TestApplogin {

	
	
	public static void main(String[] args) throws Exception {
		
		//!&@#2016#  1fdc30df364d9936fce1d7ade7391954
		//String rs="{\"head\":{\"account\":\"18039330360\",\"appVersion\":\"1.0.0\",\"callType\":\"ios\"},\"sign\":\"!&@#2016#\",\"body\":{\"account\":\"18039330360\",\"pswdMd5\":\"f379eaf3c831b04de153469d1bec345e\"}}";
		//System.out.println(Md5Utils.MD5(rs));
		
		//URL url = new URL("http://127.0.0.1:8080/app/member/login");
		//URL url = new URL("http://172.19.4.23:8091/app/member/login");
		URL url = new URL("http://172.19.4.23:8091/app/planvender/page");
		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 设置url请求方式 ‘get’ 或者 ‘post’
		connection.setRequestMethod("POST");
		StringBuffer params = new StringBuffer();
		
		
		AppParam<AppMemberReq> appParam =new AppParam<AppMemberReq>();
		Head head = new Head();
		head.setAccount("1803120310");
		head.setAppVersion("1.0.0");
		head.setCallType("ios");
		
		AppMemberReq req =new AppMemberReq();
		req.setAccount("18039330360");
		req.setPswdMd5(Md5Utils.MD5("666666"));
		
		appParam.setHead(head);
		appParam.setBody(req);
		
		appParam.setSign(Constant.apiAuthKey);
		
		String sign = Md5Utils.MD5(JSON.toJSONString(appParam));
		appParam.setSign(sign);
		
	    // 表单参数与get形式一样
		connection.setDoOutput(true);// 是否输入参数
        params.append("param").append("=").append(JSON.toJSONString(appParam));
        byte[] bypes = params.toString().getBytes();
        connection.getOutputStream().write(bypes);// 输入参数
		
		
		// 发送
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String response = in.readLine();
		System.out.println(response);
	}
}
