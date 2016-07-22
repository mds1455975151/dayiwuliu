package com.tianrui.common.utils;

import java.security.MessageDigest;

/**
 * 
 * @类描述： md5加密工具
 * @创建人：tank
 * @创建时间：2016年1月17日下午5:09:32
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午5:09:32
 * @修改备注：
 *
 */
public class Md5Utils {

	private Md5Utils() {
	}
	/**
	 * 
	 * @描述: 把字符串进行md5加密
	 * @param s
	 * @return
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年1月17日下午5:09:49
	 */
	public final static String MD5(String s) {
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = (md[i]) & 0xff;
				if (val < 16)
					sb.append("0");
				sb.append(Integer.toHexString(val));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}
}
