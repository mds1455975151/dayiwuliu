package com.tianrui.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.Result;

public class JsonUtil {
	
	static Logger logger =LoggerFactory.getLogger(JsonUtil.class);

	public static  <T> AppParam<T> getAppParam(String param, @SuppressWarnings("rawtypes") Class clazz){
		AppParam<T> appParam =JSON.parseObject(param, TypeReferenceUtil.getTypeReference(clazz));
		return appParam;
	}
	
	
	public static boolean validateSign(String json, String signValue, String authKey) {
		boolean result = false;
		try {
			// 将签名字符串替换为签名KEY
			String sourceStr = json.replaceAll(signValue, authKey);
			// MD5加密
			String md5Str = Md5Utils.MD5(sourceStr);
			// 比较MD5字符串
			result = (md5Str.equalsIgnoreCase(signValue));
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
		}
		return result;
	}
	
	public static void main(String[] args) {
		AppParam<Result> appParam =new AppParam<Result>();
		Head head =new Head();
		head.setAccount("13800000000");
		head.setAppVersion("1.0.0");
		Result rs =Result.getSuccessResult();
		appParam.setBody(rs);
		appParam.setHead(head);
		
		
		String param =JSON.toJSONString(appParam);
		AppParam<Result> appParam2 =JsonUtil.getAppParam(param, Result.class);
		System.out.println(appParam2.getHead().getAppVersion());
		System.out.println(appParam2.getBody().getCode());
		
	}
	
	
}
