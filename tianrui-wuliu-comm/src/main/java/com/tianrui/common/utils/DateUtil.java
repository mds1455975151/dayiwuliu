package com.tianrui.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @类描述：日期工具类
 * @创建人：tank
 * @创建时间：2016年1月17日下午5:02:41
 *
 * @修改人：tank
 * @修改时间：2016年1月17日下午5:02:41
 * @修改备注：
 *
 */
public class DateUtil {
	
	public final static String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 
	 * @描述:获取当前日期格式字符串yyyy-MM-dd HH:mm:ss
	 * @return
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年1月17日下午5:02:55
	 */
	public static String getDateString(){
		return new SimpleDateFormat(Y_M_D_H_M_S).format( new Date());
	}
	/**
	 * 
	 * @描述:获取日期格式字符串yyyy-MM-dd HH:mm:ss
	 * @param d
	 * @return
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年2月21日下午4:26:28
	 */
	public static String getDateString(Date d){
		return new SimpleDateFormat(Y_M_D_H_M_S).format( d);
	}
	/**
	 * 
	 * @描述:获取日期格式字符串yyyy-MM-dd HH:mm:ss
	 * @param d
	 * @return
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年2月21日下午4:26:28
	 */
	public static String getDateString(Date d,String format){
		return new SimpleDateFormat(format).format( d);
	}
	/**
	 * 
	 * @描述:获取日期格式字符串yyyy-MM-dd HH:mm:ss
	 * @param d
	 * @return
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年2月21日下午4:26:28
	 */
	public static String getDateString(long d,String format){
		return new SimpleDateFormat(format).format( new Date(d));
	}
	/**
	 * 
	 * @描述:获取日期格式字符串yyyy-MM-dd HH:mm:ss
	 * @param d
	 * @return
	 * @返回类型 String
	 * @创建人 tank
	 * @创建时间 2016年2月21日下午4:26:28
	 */
	public static Long parse(String str,String format){
		Long time=null;
		try {
			time=new SimpleDateFormat(format).parse(str).getTime();
		} catch (ParseException e) {
		}
		return time;
	}
}
