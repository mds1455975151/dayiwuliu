package com.tianrui.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.tianrui.common.constants.Constant;

public class MessageUtils {
	

	//验证参数个数与消息内容是否是否一直
	public static boolean validLen(List<String> params,String msg){
		boolean rs =false;
		if( StringUtils.isNotBlank(msg) ){
			
			String[] strArr =msg.split(Constant.MESSAGE_SPLIT);
			if( CollectionUtils.isNotEmpty(params) ){
				rs =  params.size() ==(strArr.length -1);
			}else{
				rs = strArr.length ==1;
			}
		}
		return rs;
	}
	
	//获取消息内容 返回null表示参数不匹配
	public static String  getMsgText(List<String> params,String msg){
		String rs =null;
		if( validLen(params, msg) ){
			rs =msg;
			if( CollectionUtils.isNotEmpty(params) ){
				for( String param:params ){
					rs=rs.replaceFirst( Constant.MESSAGE_SPLIT,param);
				}
				
			}
		}
		return rs;
	}
	
	
	public static void main(String[] args) {
		List<String> params =new ArrayList<String>();
		params.add("11");
		params.add("113");
		
		String msg = "<_>测试数据一().";
		//System.out.println(MessageUtils.validLen(params, msg));
		System.out.println(MessageUtils.getMsgText(params, msg));
	}
}
