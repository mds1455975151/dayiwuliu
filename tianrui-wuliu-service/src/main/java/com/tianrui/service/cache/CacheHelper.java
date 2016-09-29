package com.tianrui.service.cache;

import com.tianrui.common.constants.Constant;

/**
 * 
 * @类描述：redis缓存功能实现助手
 * @创建人：tank
 * @创建时间：2016年1月16日下午2:32:47
 *
 * @修改人：tank
 * @修改时间：2016年1月16日下午2:32:47
 * @修改备注：
 *
 */
public class CacheHelper {

	private static final String preStr = Constant.PRE_REDIS;

	public static String buildKey(CacheModule module, String string) {
		return  preStr+module.getCode()+ "_" + string;
	}
	
	public static String buildKey(CacheModule module, String[] string) {
		StringBuffer sb=new StringBuffer();
		sb.append(preStr);
		sb.append(module.getCode());
		for (int i = 0; i < string.length; i++) {
			sb.append("_" + string[i]);
		}
		return sb.toString();
	}
	
}
